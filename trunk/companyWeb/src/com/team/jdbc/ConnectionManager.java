package com.team.jdbc;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.EventObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.team.util.ConfigUtil;

public class ConnectionManager {

	// 与 双重检查锁并用
	// private static volatile ConnectionManager instance;
	// private static ConnectionManager instance = new ConnectionManager();

	private static ConnectionPool connectPool;
	private static int maxNum;
	private static int minNum;
	private static int connectNum = 0;
	/** 连接池最近使用时间*/
	private static long activityTime = 0;
	private static boolean isPoolReduce = true;
	private static Timer timer = new Timer(true);
	private static long defaultIdleTime;
	private static long defaultTimeout;
	private static long defaultReduceTime;
	private static List<ConnectionManager> managerContains;
	private static Semaphore connectionSemaphore = new Semaphore(1);
	private static final ReadWriteLock rwlock = new ReentrantReadWriteLock();
	// private static final Lock readLock = rwlock.readLock();
	private static final Lock writeLock = rwlock.writeLock();

	static {
		maxNum = ConfigUtil.getIntegerValue("connection.pool.maxNum");
		minNum = ConfigUtil.getIntegerValue("connection.pool.minNum");
		connectPool = new ConnectionPool(maxNum, minNum);
		defaultIdleTime = ConfigUtil
				.getLongValue("connection.connected.idleTime");
		defaultTimeout = ConfigUtil.getLongValue("connection.acquire.timeout");
		defaultReduceTime = ConfigUtil
				.getLongValue("connection.acquire.reduceTime");
		managerContains = new ArrayList<ConnectionManager>(maxNum);
		try {
			connectPool.init();
			timer.schedule(new PoolReduceTask(), defaultReduceTime,
					defaultReduceTime);
			timer.schedule(new ManagerReleaseTask(), defaultIdleTime,
					defaultIdleTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection connection;
	/** 连接的离线时间 */
	private long idleTime;
	/** 获取连接的过期时间 */
	private long timeout;
	/** 连接的创建时间 */
	private long createTime;
	private ConnectionListener listener;

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(long idleTime) {
		this.idleTime = idleTime;
	}

	/**
	 * 注册监视器
	 * 
	 * @param listener
	 */
	public void registConnectionListener(ConnectionListener listener) {
		this.listener = listener;
	}

	/**
	 * 激活事件
	 * 
	 * @param event
	 */
	public void activateListener() {
		ConnectionEvent event = new ConnectionEvent(this);
		this.listener.releaseConnection(event);
	}

	private ConnectionManager() {
		idleTime = defaultIdleTime;
		timeout = defaultTimeout;
		this.registConnectionListener(new ConnectionManagerListener());
	}

	public static ConnectionManager getConnectionManager() {
		// if(instance == null){
		// synchronized (ConnectionManager.class) {
		// if(instance == null)
		// instance = new ConnectionManager();
		// }
		//
		// }//懒加载，双重检查锁的方式

		// return instance;
		activityTime = System.currentTimeMillis();
		return new ConnectionManager();
	}

	/**
	 * 如果获取不到连接则返回null
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			connection = connectPool.getConnection(timeout,
					TimeUnit.MILLISECONDS);
			if (connection == null) {
				if (connectNum > minNum && connectNum < maxNum) {
					if (connectionSemaphore.tryAcquire()) {
						connectPool.addConnection();
						isPoolReduce = false;
						connectionSemaphore.release();
					}
					connection = connectPool.getConnection(timeout,
							TimeUnit.MILLISECONDS);
				}
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		if (connection != null) {
			writeLock.lock();
			managerContains.add(this);
			connectNum++;
			writeLock.unlock();
			createTime = System.currentTimeMillis();

		}
		return connection;
	}

	/**
	 * 释放连接
	 */
	public void releaseConnection() {
		try {
			connectPool.releaseConnection(connection);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * 当连接池长时间没使用的时候，减少连接池中的连接数
	 *
	 */
	private static class PoolReduceTask extends TimerTask {

		@Override
		public void run() {
			long time = System.currentTimeMillis() - activityTime;
			if (!isPoolReduce && time > defaultReduceTime) {
				try {
					connectionSemaphore.acquire();
					connectPool.reduceCoonection();
					isPoolReduce = true;
					connectionSemaphore.release();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		}

	}

	/**
	 * 当连接离线时间超时，自动释放连接
	 * 
	 * 
	 */
	private static class ManagerReleaseTask extends TimerTask {

		@Override
		public void run() {
			long currentTime = System.currentTimeMillis();
			writeLock.lock();
			for (ConnectionManager manager : managerContains) {
				if (currentTime - manager.createTime > manager.idleTime) {
					manager.activateListener();
					managerContains.remove(manager);
					connectNum--;
				}
			}
			writeLock.unlock();

		}

	}

	/**
	 * 
	 * Connection监视器，用于释放Connection
	 * 
	 */
	private interface ConnectionListener extends EventListener {
		/**
		 * 释放Connection
		 * 
		 * @param event
		 */
		public void releaseConnection(ConnectionEvent event);
	}

	/**
	 * 
	 * @see ConnectionListener
	 * 
	 */
	private class ConnectionManagerListener implements ConnectionListener {

		public void releaseConnection(ConnectionEvent event) {
			ConnectionManager manager = (ConnectionManager) event.getSource();
			manager.releaseConnection();
		}

	}

	/**
	 * 
	 * Connection事件
	 * 
	 */
	private class ConnectionEvent extends EventObject {

		private static final long serialVersionUID = -4435735742083535989L;

		public ConnectionEvent(ConnectionManager manager) {
			super(manager);
		}

	}
}
