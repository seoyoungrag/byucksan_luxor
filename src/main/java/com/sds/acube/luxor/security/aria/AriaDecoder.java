package com.sds.acube.luxor.security.aria;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityDecoder;

public class AriaDecoder implements SecurityDecoder {

	public static final String ARIA_DECODER = "ARIA.DECODER";
	
	private static final int BLOCK_SIZE = 16;

	private AriaCipher aria = null;
	
	public String decode(String data) throws Exception {
		return decodeAria(data, LuxorConfig.getString("Common", "ARIA.KEY", "a19oaj492X3dmzqi014k196"));
	}

	public String decode(String data, String key) throws Exception {
		return decodeAria(data, key);
	}

	public String decodeAria(String data, String key) {
		try {
			byte bkey[] = createKey(key);
			aria = new AriaCipher(bkey.length * 8);
			aria.setKey(bkey);
			aria.setupRoundKeys();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		byte[] bytes = decrypt(Base64.decodeBase64(data));
		return new String(bytes);
	}

	public byte[] decrypt(byte[] data) {
		byte[] out = new byte[data.length];
		try {
			for (int i = 0; i < data.length; i += BLOCK_SIZE) {
				aria.decrypt(data, i, out, i);
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}

		return AriaCipher.unpad(out, BLOCK_SIZE);
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
}
