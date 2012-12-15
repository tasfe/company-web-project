package com.team.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
	private static Properties config = new Properties();
	static {
		InputStream fis = null;
		try {
			fis = ConfigUtil.class.getResourceAsStream("/config.properties");
			config.load(fis);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	public static String getValue(String key) {
		return config.getProperty(key).trim();
	}

	public static Integer getIntegerValue(String key) {
		return Integer.valueOf(getValue(key));
	}

	public static Long getLongValue(String key) {
		return Long.valueOf(getValue(key));
	}

	public static Double getDoubleValue(String key) {
		return Double.valueOf(getValue(key));
	}

	public static Float getFloatValue(String key) {
		return Float.valueOf(getValue(key));
	}
}
