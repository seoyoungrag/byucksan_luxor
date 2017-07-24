package com.sds.acube.luxor.security.symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityEncoder;

public class AesEncoder implements SecurityEncoder {

	private int keySize = 128;
	
	private int iterationCount = 1000;
	
	private String secretkey; // 32bit 이상
	
	private String keyspeckey; // 16 bit
	
	private String ivkey; // 32 bit
	
	private byte fixedSalt[]; 
	
	public AesEncoder() {
		this.fixedSalt = DatatypeConverter.parseHexBinary(LuxorConfig.getString("Common", "AES.SALT", "0123456789ABCDEF"));
		this.secretkey = LuxorConfig.getString("Common", "AES.KEY", "8ec6064898ac0432e2158ef6e5b9b231");
		this.keyspeckey = (this.secretkey.length() < 16) ? getTimeBased16BitKey() : this.secretkey.substring(0, 16);
		this.ivkey = (this.secretkey.length() == 32) ? this.secretkey : generateKey("x0123-z11-z83");
	}
	
	public String encode(String data) throws Exception {
		return encodeAes(data);
	}

	public String encode(String data, String key) throws Exception {
		return encodeAes(data);
	}

	public String encodeAes(String data) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(keyspeckey.getBytes(), "AES"); // 16bit
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(DatatypeConverter.parseHexBinary(ivkey))); // 32bit
			return DatatypeConverter.printHexBinary(cipher.doFinal(data.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
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

	private String getTimeBased16BitKey() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssss");
        return sdf.format(cal.getTime());
	}
	
	private String generateKey(String passphrase) {
		SecretKeyFactory factory;
		try {
			factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), fixedSalt, iterationCount, keySize);
			SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
			return Hex.encodeHexString(key.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}	
}
