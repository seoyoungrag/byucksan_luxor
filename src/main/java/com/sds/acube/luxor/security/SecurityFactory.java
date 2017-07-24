package com.sds.acube.luxor.security;

import java.util.HashMap;
import java.util.Map;

import com.sds.acube.luxor.framework.config.LuxorConfig;

public class SecurityFactory {
	
	public static final String DES = "DES";

	public static final String RSA = "RSA";

	public static final String SEED = "SEED";

	public static final String AES = "AES";

	public static final String ARIA = "ARIA";

	public static final String DES3 = "DES3";

	public static final String HMAC = "HMAC";

	public static final String HASH = "HASH";

	public static final String DEFAULT_HASH_ALGORITHM = "SHA-256";
	
	private static SecurityFactory instance = new SecurityFactory();

	private String defaultAlgorithm;

	private String encoding;

	private Map<String, Object> objectMap = new HashMap<String, Object>();

	private SecurityFactory() {
		this.defaultAlgorithm = LuxorConfig.getString("Common", "SECURITY.METHOD", "DES");
		this.encoding = LuxorConfig.getString("Common", "SECURITY.ENCODING", "DEFAULT");
	}

	public static SecurityFactory getInstance() {
		if (instance == null) {
			instance = new SecurityFactory();
		}

		return instance;
	}

	public void clearInstance() {
		instance = null;
	}

	public SecurityEncoder getEncoder() {
		return getEncoder(defaultAlgorithm);
	}

	public SecurityEncoder getEncoder(String algorithm) {
		algorithm = (algorithm == null) ? defaultAlgorithm : algorithm;
		String keyName = algorithm.concat(".ENCODER");
		String encoderClass = LuxorConfig.getString("Common", keyName);
		SecurityEncoder encoderObject = null;
		try {
			encoderObject = (SecurityEncoder) objectMap.get(keyName);
			if (encoderObject == null) {
				encoderObject = (SecurityEncoder) Class.forName(encoderClass).newInstance();
				objectMap.put(keyName, encoderObject);
			}
		} catch (Exception e) {
			System.out.println(encoderClass + " : " + e.getMessage());
			e.printStackTrace();
		}
		return encoderObject;
	}

	public SecurityEncoder getHashEncoder() {
		return getHashEncoder(HASH);
	}

	public SecurityEncoder getHashEncoder(String algorithm) {
		algorithm = (algorithm == null) ? HASH : algorithm;
		String keyName = algorithm.concat(".ENCODER");
		String encoderClass = LuxorConfig.getString("Common", keyName);
		SecurityEncoder encoderObject = null;
		try {
			encoderObject = (SecurityEncoder) objectMap.get(keyName);
			if (encoderObject == null) {
				encoderObject = (SecurityEncoder) Class.forName(encoderClass).newInstance();
				objectMap.put(keyName, encoderObject);
			}
		} catch (Exception e) {
			System.out.println(encoderClass + " : " + e.getMessage());
			e.printStackTrace();
		}
		return encoderObject;
	}
	
	public SecurityDecoder getDecoder() {
		return getDecoder(defaultAlgorithm);
	}

	public SecurityDecoder getDecoder(String algorithm) {
		SecurityDecoder decoderObject = null;
		algorithm = (algorithm == null) ? defaultAlgorithm : algorithm;
		String keyName = algorithm.concat(".ENCODER");
		String decoderClass = LuxorConfig.getString("Common", keyName);
		try {
			decoderObject = (SecurityDecoder) objectMap.get(keyName);
			if (decoderObject == null) {
				decoderObject = (SecurityDecoder) Class.forName(decoderClass).newInstance();
				objectMap.put(keyName, decoderObject);
			}
		} catch (Exception e) {
			System.out.println(decoderClass + " : " + e.getMessage());
			e.printStackTrace();
		}
		return decoderObject;
	}

	/**
	 * ARIA ENCODER
	 * 
	 * @since 2014.02.25
	 * @return
	 */
	public SecurityEncoder getAriaEncoder() {
		SecurityEncoder encoderObject = null;
		String encoderClass = LuxorConfig.getString("Common", com.sds.acube.luxor.security.aria.AriaEncoder.ARIA_ENCODER);
		try {
			encoderObject = (SecurityEncoder) objectMap.get(com.sds.acube.luxor.security.aria.AriaEncoder.ARIA_ENCODER);
			if (encoderObject == null) {
				encoderObject = (SecurityEncoder) Class.forName(encoderClass).newInstance();
				objectMap.put(com.sds.acube.luxor.security.aria.AriaEncoder.ARIA_ENCODER, encoderObject);
			}
		} catch (Exception e) {
			System.out.println(encoderClass + " : " + e.getMessage());
			e.printStackTrace();
		}
		return encoderObject;
	}

	/**
	 * ARIA DECODER
	 * 
	 * @since 2014.02.25
	 * @return
	 */
	public SecurityDecoder getAriaDecoder() {
		SecurityDecoder decoderObject = null;
		String decoderClass = LuxorConfig.getString("Common", com.sds.acube.luxor.security.aria.AriaDecoder.ARIA_DECODER);
		try {
			decoderObject = (SecurityDecoder) objectMap.get(com.sds.acube.luxor.security.aria.AriaDecoder.ARIA_DECODER);
			if (decoderObject == null) {
				decoderObject = (SecurityDecoder) Class.forName(decoderClass).newInstance();
				objectMap.put(com.sds.acube.luxor.security.aria.AriaDecoder.ARIA_DECODER, decoderObject);
			}
		} catch (Exception e) {
			System.out.println(decoderClass + " : " + e.getMessage());
			e.printStackTrace();
		}
		return decoderObject;
	}

	/**
	 * HMAC ENCODER
	 * 
	 * @since 2014.02.25
	 * @return
	 */
	public SecurityEncoder getHmacEncoder() {
		SecurityEncoder encoderObject = null;
		String encoderClass = LuxorConfig.getString("Common", com.sds.acube.luxor.security.hash.HmacEncoder.HMAC_ENCODER);
		try {
			encoderObject = (SecurityEncoder) objectMap.get(com.sds.acube.luxor.security.hash.HmacEncoder.HMAC_ENCODER);
			if (encoderObject == null) {
				encoderObject = (SecurityEncoder) Class.forName(encoderClass).newInstance();
				objectMap.put(com.sds.acube.luxor.security.hash.HmacEncoder.HMAC_ENCODER, encoderObject);
			}
		} catch (Exception e) {
			System.out.println(encoderClass + " : " + e.getMessage());
			e.printStackTrace();
		}

		return encoderObject;
	}	
	
	public void setEncoding(String str) {
		this.encoding = str;
	}

	public String getEncoding() {
		return this.encoding;
	}
}
