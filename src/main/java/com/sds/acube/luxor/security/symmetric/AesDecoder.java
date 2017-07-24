package com.sds.acube.luxor.security.symmetric;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityDecoder;

public class AesDecoder implements SecurityDecoder {

	private String secretkey; // 32bit 이상
	
	private String keyspeckey; // 16 bit
	
	private String ivkey; // 32 bit
	
	public AesDecoder() {
		this.secretkey = LuxorConfig.getString("Common", "AES.KEY", "8ec6064898ac0432e2158ef6e5b9b231");
		this.keyspeckey = (this.secretkey.length() < 16) ? "" : this.secretkey.substring(0, 16);  
		this.ivkey = (this.secretkey.length() >= 32) ? this.secretkey.substring(0, 32) : "";	
	}
	
	public String decode(String data) throws Exception {
		return decodeAes(data);
	}

	public String decode(String data, String key) throws Exception {
		return decodeAes(data);
	}

	public String decodeAes(String data) throws Exception {
		try {
			if (keyspeckey == null || keyspeckey.equals("")) {
				throw new Exception("NOT FOUND KEY SPEC KEY!!!");
			}
			if (ivkey == null || ivkey.equals("")) {
				throw new Exception("NOT FOUND IV KEY!!!");
			}
			SecretKeySpec skeySpec = new SecretKeySpec(keyspeckey.getBytes(), "AES"); // 16 bit
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(DatatypeConverter.parseHexBinary(ivkey))); // 32 bit
			return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(data)));
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
}
