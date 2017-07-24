package com.sds.acube.luxor.security.aria;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.codec.binary.Base64;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityEncoder;

public class AriaEncoder implements SecurityEncoder {

	public static final String ARIA_ENCODER = "ARIA.ENCODER";
	
	private static final int BLOCK_SIZE = 16;

	private AriaCipher aria = null;
	
	public String encode(String data) throws Exception {
		return encodeAria(data, LuxorConfig.getString("Common", "ARIA.KEY", "a19oaj492X3dmzqi014k196"));
	}

	public String encode(String data, String key) throws Exception {
		return encodeAria(data, key);
	}

	public String encodeAria(String data, String key) {
		try {
			byte bkey[] = createKey(key);
			aria = new AriaCipher(bkey.length * 8);
			aria.setKey(bkey);
			aria.setupRoundKeys();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		
		byte[] bytes = encrypt(data.getBytes());
		return Base64.encodeBase64String(bytes);
	}

	public byte[] encrypt(byte[] data) {
		byte[] in = AriaCipher.pad(data, BLOCK_SIZE);
		byte[] out = new byte[in.length];
		try {
			for (int i = 0; i < in.length; i += BLOCK_SIZE) {
				aria.encrypt(in, i, out, i);
			}
		} catch (InvalidKeyException e) {

		}
		return out;
	}

	private byte[] createKey(String key) throws InvalidKeyException {
		MessageDigest algorithm = null;
		try {
			algorithm = MessageDigest.getInstance("MD5"); // ===> 128 bit 키
			// algorithm = MessageDigest.getInstance("SHA-256"); // ===> 160 bit 키
			algorithm.reset();
			algorithm.update(key.getBytes());
			byte[] messageDigest = algorithm.digest();
			return messageDigest;
		} catch (NoSuchAlgorithmException e) {
			throw new InvalidKeyException(e);
		}
	}
	
	public static String getTimeBasedSecretKey() {
		TimeZone timezone = TimeZone.getTimeZone(LuxorConfig.getString("Common", "LOCALE.DEFAULT_TIMEZONE"));
		Calendar calendar = new GregorianCalendar(timezone);
		return Long.toString(calendar.getTime().getTime());
	}
}
