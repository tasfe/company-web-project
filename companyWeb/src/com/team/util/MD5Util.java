package com.team.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author Allen
 *
 */
public class MD5Util {
	private static MessageDigest digest;
	private static char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	static {
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static String getMD5String(String str) {
		digest.reset();
		digest.update(str.getBytes());
		byte[] result = digest.digest();
		return toHexString(result);
	}

	private static String toHexString(final byte[] bs) {
		int length = bs.length;
		char[] str = new char[length * 2];

		for (int i = 0; i < length; i++) {
			str[i] = hexDigits[bs[i] >>> 4 & 0x0f];
			str[i + 1] = hexDigits[bs[i] & 0x0f];
		}

		return new String(str);

	}
}
