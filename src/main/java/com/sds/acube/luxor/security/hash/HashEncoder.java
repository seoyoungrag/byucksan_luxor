package com.sds.acube.luxor.security.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.security.SecurityEncoder;
import com.sds.acube.luxor.security.SecurityFactory;

public class HashEncoder implements SecurityEncoder {

	public String encode(String sourceData) throws Exception {
		return hashEncrypt(sourceData, null);
	}

	public String encode(String sourceData, String algorithm) throws Exception {
		return hashEncrypt(sourceData, algorithm);
	}

	private String hashEncrypt(String sourceData, String algorithm) {
		algorithm = (algorithm == null) ? LuxorConfig.getString("Common", "SECURITY.HASH", SecurityFactory.DEFAULT_HASH_ALGORITHM) : algorithm;
		try {
			if (sourceData != null && !sourceData.equals("")) {
				MessageDigest md = MessageDigest.getInstance(algorithm);
				md.update(sourceData.getBytes());
				byte[] dg = md.digest();
				//return Hex.encodeHexString(dg);
				return Base64.encodeBase64String(dg);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
