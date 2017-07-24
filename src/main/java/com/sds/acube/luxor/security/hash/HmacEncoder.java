package com.sds.acube.luxor.security.hash;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityEncoder;

public class HmacEncoder implements SecurityEncoder {

	public static final String HMAC_ENCODER = "HMAC.ENCODER";
	
	public static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
	
	public static final String HMAC_SHA256_ALGORITHM = "HmacSHA256";
	
	public String encode(String data) throws Exception {
		String secretkey = LuxorConfig.getString("Common", "HMAC.KEY", getTimeBasedSecretKey());
		return encodeHmac(data, secretkey, HMAC_SHA256_ALGORITHM);
	}

	public String encode(String data, String secretkey) throws Exception {
		return encodeHmac(data, secretkey, HMAC_SHA256_ALGORITHM);
	}

	private String encodeHmac(String data, String secretkey, String algorithm) {
		try {
			Mac mac = Mac.getInstance(algorithm);
			mac.init(new SecretKeySpec(secretkey.getBytes(), algorithm));
			return Base64.encodeBase64String(mac.doFinal(data.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static String getTimeBasedSecretKey() {
		TimeZone timezone = TimeZone.getTimeZone(LuxorConfig.getString("Common", "LOCALE.DEFAULT_TIMEZONE"));
		Calendar calendar = new GregorianCalendar(timezone);
		return Long.toString(calendar.getTime().getTime());
	}	
	
	public static String getRandomSecretKey() {
		String skey = "";
		try {
			byte[] salt = new byte[16];
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			sr.nextBytes(salt);
			skey = Base64.encodeBase64String(salt);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return skey;
	}
	
}
