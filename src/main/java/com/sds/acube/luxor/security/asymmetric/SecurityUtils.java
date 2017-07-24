package com.sds.acube.luxor.security.asymmetric;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;


/**
 * @since 20120925 GPL 라이센스 코드 -> Apache Commons 로 대체함
 */
public class SecurityUtils {
	private static HashMap keyPool = new HashMap();

	/*
	// This array maps the characters to their 6 bit values
	private final static char b64EncodeTable[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', // 0
	    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', // 1
	    'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', // 2
	    'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', // 3
	    'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', // 4
	    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', // 5
	    'w', 'x', 'y', 'z', '0', '1', '2', '3', // 6
	    '4', '5', '6', '7', '8', '9', '+', '/' // 7
	};
	private final static byte b64DecodeTable[] = new byte[256];
	static {
		for (int i = 0; i < 255; i++) {
			b64DecodeTable[i] = -1;
		}
		for (int i = 0; i < b64EncodeTable.length; i++) {
			b64DecodeTable[b64EncodeTable[i]] = (byte) i;
		}
	}
	*/

	// Base64 encode
	public static byte[] base64Encode(String inData) {
		// 20120925 Apache Commons Codec 으로 대체
		//return base64Encode(inData.getBytes());
		return Base64.encodeBase64(inData.getBytes());
	}

	/*
	// 20120925 Apache Commons Codec 으로 대체 가능
	public static byte[] base64Encode(byte[] inbuf) {
		if (inbuf.length == 0) return inbuf;
		byte[] outbuf = new byte[((inbuf.length + 2) / 3) * 4];
		int inpos = 0, outpos = 0;
		int size = inbuf.length;
		while (size > 0) {
			byte a, b, c;
			if (size == 1) {
				a = inbuf[inpos++];
				b = 0;
				c = 0;
				outbuf[outpos++] = (byte) b64EncodeTable[(a >>> 2) & 0x3F];
				outbuf[outpos++] = (byte) b64EncodeTable[((a << 4) & 0x30) + ((b >>> 4) & 0xf)];
				outbuf[outpos++] = (byte) '='; // pad character
				outbuf[outpos++] = (byte) '='; // pad character
			} else if (size == 2) {
				a = inbuf[inpos++];
				b = inbuf[inpos++];
				c = 0;
				outbuf[outpos++] = (byte) b64EncodeTable[(a >>> 2) & 0x3F];
				outbuf[outpos++] = (byte) b64EncodeTable[((a << 4) & 0x30) + ((b >>> 4) & 0xf)];
				outbuf[outpos++] = (byte) b64EncodeTable[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)];
				outbuf[outpos++] = (byte) '='; // pad character
			} else {
				a = inbuf[inpos++];
				b = inbuf[inpos++];
				c = inbuf[inpos++];
				outbuf[outpos++] = (byte) b64EncodeTable[(a >>> 2) & 0x3F];
				outbuf[outpos++] = (byte) b64EncodeTable[((a << 4) & 0x30) + ((b >>> 4) & 0xf)];
				outbuf[outpos++] = (byte) b64EncodeTable[((b << 2) & 0x3c) + ((c >>> 6) & 0x3)];
				outbuf[outpos++] = (byte) b64EncodeTable[c & 0x3F];
			}
			size -= 3;
		}
		return outbuf;
	}
	*/

	public static byte[] base64Encode(byte[] inbuf) {
		return Base64.encodeBase64(inbuf);
	}
	
	// Base64 decode
	public static byte[] base64Decode(String encoded) {
		// 20120925 Apache Commons Codec 으로 대체
		//return base64Decode(encoded.getBytes());
		return Base64.decodeBase64(encoded.getBytes());
	}


	/*
	// 20120925 Apache Commons Codec 으로 대체 가능
	public static byte[] base64Decode(byte[] inbuf) {
		int size = (inbuf.length / 4) * 3;
		if (size == 0) return inbuf;
		if (inbuf[inbuf.length - 1] == '=') {
			size--;
			if (inbuf[inbuf.length - 2] == '=') size--;
		}
		byte[] outbuf = new byte[size];
		int inpos = 0, outpos = 0;
		size = inbuf.length;
		while (size > 0) {
			byte a, b;
			a = b64DecodeTable[inbuf[inpos++] & 0xff];
			b = b64DecodeTable[inbuf[inpos++] & 0xff];
			// The first decoded byte
			outbuf[outpos++] = (byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3));
			if (inbuf[inpos] == '=') // End of this BASE64 encoding
			return outbuf;
			a = b;
			b = b64DecodeTable[inbuf[inpos++] & 0xff];
			// The second decoded byte
			outbuf[outpos++] = (byte) (((a << 4) & 0xf0) | ((b >>> 2) & 0xf));
			if (inbuf[inpos] == '=') // End of this BASE64 encoding
			return outbuf;
			a = b;
			b = b64DecodeTable[inbuf[inpos++] & 0xff];
			// The third decoded byte
			outbuf[outpos++] = (byte) (((a << 6) & 0xc0) | (b & 0x3f));
			size -= 4;
		}
		return outbuf;
	}
	*/
	public static byte[] base64Decode(byte[] inbuf) {
		return Base64.decodeBase64(inbuf);
	}

	public static String getMD5String(String data) {
		return getMD5String(data.getBytes());
	}


	public static String getMD5String(byte[] b) {
		String javaPackageMD5 = "";
		try {
			byte[] javaDigest = MessageDigest.getInstance("MD5").digest(b);
			// 20120925 Apache Commons Codec 으로 대체
			//javaPackageMD5 = toHex(javaDigest);
			javaPackageMD5 = Hex.encodeHexString(javaDigest);
		} catch (Exception ex) {}
		return javaPackageMD5;
	}

	/*
	// 20120925 Apache Commons Codec 으로 대체가능
	private static String toHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		for (int i = 0; i < hash.length; i++) {
			int intVal = hash[i] & 0xff;
			if (intVal < 0x10) {
				// append a zero before a one digit hex number to make it two digits.
				buf.append("0");
			}
			buf.append(Integer.toHexString(intVal));
		}
		return buf.toString();
	}
	*/

	public static PrivateKey getPrivateKey(String keyFile) throws Exception {
		PrivateKey privateKey = (PrivateKey) keyPool.get(keyFile);
		if (privateKey == null) {
			byte[] baKey = loadDataFromFile(keyFile);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(baKey);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			privateKey = factory.generatePrivate(keySpec);
			keyPool.put(keyFile, privateKey);
		}
		return privateKey;
	}


	public static PublicKey getPublicKey(String keyFile) throws Exception {
		PublicKey publicKey = (PublicKey) keyPool.get(keyFile);
		if (publicKey == null) {
			byte[] baKey = loadDataFromFile(keyFile);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(baKey);
			KeyFactory factory = KeyFactory.getInstance("RSA");
			publicKey = factory.generatePublic(keySpec);
			keyPool.put(keyFile, publicKey);
		}
		return publicKey;
	}


	private static byte[] loadDataFromFile(String keyFile) throws IOException {
		byte[] bytes = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(keyFile);
			bytes = new byte[fis.available()];
			fis.read(bytes);
		} catch (Exception e) {} finally {
			fis.close();
		}
		return bytes;
	}


	public static void clearCache() {
		keyPool.clear();
	}
	
	public static void main(String args[]) {
		byte[] b1 = {(byte)0xff};
		byte[] b2 = {(byte)0x1f, (byte)0xf5};
		byte[] b3 = {(byte)0xa9, (byte)0xde};
		byte[] b4 = {(byte)0x4, (byte)0xa1, (byte)0xd5};
		byte[] b5 = {(byte)0x4, (byte)0xa1, (byte)0xd5, (byte)0xf1};
		byte[] b6 = {(byte)0xfc, (byte)0x04, (byte)0xa1, (byte)0xd5, (byte)0xf1};
		byte[] b7 = {(byte)0x0a, (byte)0x6d, (byte)0x04, (byte)0xa1, (byte)0xd5, (byte)0xf1};
		byte[] b8 = {(byte)0x90, (byte)0xba, (byte)0x6d, (byte)0x04, (byte)0xa1, (byte)0xd5, (byte)0xf1};
		
		/*
		System.out.println("=== encoding test 1 ===");
		System.out.println(toHex(b1));
		System.out.println(toHex(b2));
		System.out.println(toHex(b3));
		System.out.println(toHex(b4));
		System.out.println(toHex(b5));
		System.out.println(toHex(b6));
		System.out.println(toHex(b7));
		System.out.println(toHex(b8));
		*/

		System.out.println("=== encoding test 2 ===");
		System.out.println(Hex.encodeHexString(b1));
		System.out.println(Hex.encodeHexString(b2));
		System.out.println(Hex.encodeHexString(b3));
		System.out.println(Hex.encodeHexString(b4));
		System.out.println(Hex.encodeHexString(b5));
		System.out.println(Hex.encodeHexString(b6));
		System.out.println(Hex.encodeHexString(b7));
		System.out.println(Hex.encodeHexString(b8));
	}
	
}
