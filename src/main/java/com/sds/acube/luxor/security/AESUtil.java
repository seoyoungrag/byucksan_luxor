package com.sds.acube.luxor.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

// javascript(AesUtil.js) 와 연동
public class AESUtil {
	private int keySize = 128;
	private int iterationCount = 1;
	private Cipher cipher = null;

	public AESUtil() {
		this(128, 1);
	}

	public AESUtil(int keySize, int iterationCount) {
		this.keySize = keySize;
		this.iterationCount = iterationCount;

		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}

	public static String encrypt(HttpServletRequest request, String plaintext) {
		AESUtil aes = new AESUtil();
		String salt = "0123456789ABCDEF"; // Not modified!!!(see : util.js)
		String iv = "10006000000000000000000000000001"; // Not modified!!!(see : util.js)
		String passphrase = request.getContextPath();
		return aes.encrypt(salt, iv, passphrase, plaintext);
	}
	
	public static String decrypt(HttpServletRequest request, String ciphertext) {
		AESUtil aes = new AESUtil();
		String salt = "0123456789ABCDEF"; // Not modified!!!(see : util.js)
		String iv = "10006000000000000000000000000001"; // Not modified!!!(see : util.js)
		String passphrase = request.getContextPath();
		return aes.decrypt(salt, iv, passphrase, ciphertext);
	}
	
	public String encrypt(String salt, String iv, String passphrase, String plaintext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
			return base64(encrypted);
		} catch (UnsupportedEncodingException e) {
			throw fail(e);
		}
	}

	public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
		try {
			SecretKey key = generateKey(salt, passphrase);
			byte[] decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64(ciphertext));
			return new String(decrypted, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw fail(e);
		}
	}

	private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
		try {
			cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
			return cipher.doFinal(bytes);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private SecretKey generateKey(String salt, String passphrase) {
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
			SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return key;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String random(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
		return hex(salt);
	}

	public static String base64(byte[] bytes) {
		return DatatypeConverter.printBase64Binary(bytes);
	}

	public static byte[] base64(String str) {
		return DatatypeConverter.parseBase64Binary(str);
	}

	public static String hex(byte[] bytes) {
		return DatatypeConverter.printHexBinary(bytes);
	}

	public static byte[] hex(String str) {
		return DatatypeConverter.parseHexBinary(str);
	}

	private IllegalStateException fail(Exception e) {
		return new IllegalStateException(e);
	}
}
