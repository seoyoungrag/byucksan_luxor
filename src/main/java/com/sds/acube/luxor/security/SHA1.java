package com.sds.acube.luxor.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

/**
 * Reference : http://www.anyexample.com/programming/java/java_simple_class_to_compute_sha_1_hash.xml
 */
public class SHA1 {
	
	/*
	// Apache Commons Codec 으로 대체 가능
	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while(two_halfs++ < 1);
		}
		return buf.toString();
	}
	*/

	public static String hash(String text) {
		if(text==null) {
			return "";
		}
		
		byte[] sha1Hash = new byte[40];
        try {
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        //md.update(text.getBytes("iso-8859-1"), 0, text.length());
	        md.update(text.getBytes("iso-8859-1"));
	        sha1Hash = md.digest();
        } catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
	        e.printStackTrace();
        }
        // 20120925 Apache Commons Codec 으로 대체
		//return convertToHex(sha1Hash);
        return Hex.encodeHexString(sha1Hash);
	}

}
