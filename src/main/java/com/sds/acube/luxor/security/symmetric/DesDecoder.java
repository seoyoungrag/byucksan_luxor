package com.sds.acube.luxor.security.symmetric;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityDecoder;

public class DesDecoder implements SecurityDecoder {

	public static final String DEFAULT_DES_ALGORITHM = "DES";

	public static final String TRIPLE_DES_ALGORITHM = "DESede";

	private String algorithm;

	private String deskey;
	
	public DesDecoder() {
		this.algorithm = LuxorConfig.getString("Common", "DES3.ALGORITHM", "DESede");
		this.deskey = LuxorConfig.getString("Common", "DES3.KEY", "x0134-ad17s658601j56-q75k2we0des-key");
		this.deskey = (this.deskey.length() < 16) ? "x0134-ad17s658601j56-q75k2we0des-key" : this.deskey;
	}
	
	public String decode(String data) throws Exception {
		return decodeDes(data, algorithm);
	}

	public String decode(String data, String algorithm) throws Exception {
		return decodeDes(data, algorithm);
	}

	private String decodeDes(String data, String algorithm) {
		try {
			Cipher cipher = null;
			if (algorithm.equals(DEFAULT_DES_ALGORITHM)) { // 16 bytes
				cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			} else if (algorithm.equals(TRIPLE_DES_ALGORITHM)) { // 24 bytes
				cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			}
			cipher.init(Cipher.DECRYPT_MODE, getKey(algorithm));
			
			byte e[] = cipher.doFinal(Base64.decodeBase64(data.getBytes()));
			return new String(e);			
			//return new String(cipher.doFinal(DatatypeConverter.parseHexBinary(data)));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Key getKey(String algorithm) {
		try {
			String key = "";
			if (algorithm.equals(DEFAULT_DES_ALGORITHM)) { // 16 bytes
				key = (deskey.length() > 16) ? deskey.substring(0, 16) : deskey;
				DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
				return keyFactory.generateSecret(desKeySpec);
			} else if (algorithm.equals(TRIPLE_DES_ALGORITHM)) { // 24 bytes
				key = (deskey.length() > 24) ? deskey.substring(0, 24) : deskey;
				DESedeKeySpec desKeySpec = new DESedeKeySpec(key.getBytes());
				SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
				return keyFactory.generateSecret(desKeySpec);
			}
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
}
