package com.sds.acube.luxor.security.asymmetric;

import java.security.PrivateKey;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sds.acube.luxor.security.SecurityEncoder;
import com.sds.acube.luxor.security.SecurityFactory;


/**
* Data 인코딩 모듈 */
public class AsymmKeyEncoder implements SecurityEncoder
{
    public AsymmKeyEncoder()
    {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
    * <pre>
    * 개인키로 Data를 인코딩한다.
    * 개인키는 CLASSPATH 에서 /properties/security/acube_private.key 가 사용된다.
    * <pre>
    * @param sourceData 인코딩할 Data
    * @return 인코딩된 Data. 오류가 발생하면 "" 리턴.
    */
    public String encode(String sourceData) throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String certFilesHome = classLoader.getResource("esso/security").getPath();//@mod 2014.02.28

        return encode(sourceData, certFilesHome + "acube_private.key");//@mod 2014.02.28
    }


    /**
    * 개인키로 Data를 인코딩한다.
    * @param sourceData 인코딩할 Data
    * @param privKeyFile 개인키 파일(절대경로)
    * @return 인코딩된 Data. 오류가 발생하면 "" 리턴.
    */
    public String encode(String sourceData, String privKeyFile) throws Exception
    {
        StringBuffer ssoBuffer = new StringBuffer();

        try
        {
			String enc = SecurityFactory.getInstance().getEncoding();
 
	        /////////////////////////////////////////////////////////////
            // Step 1. MD5 생성 (데이타 무결성 검증용)
            String md5Data = SecurityUtils.getMD5String(sourceData);


            /////////////////////////////////////////////////////////////
            // Step 2. Header 생성
//            SecureRandom sec_rand = SecureRandom.getInstance("SHA1PRNG", "SUN");

            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecretKey desKey = keygen.generateKey();

            byte[] baDesKey = desKey.getEncoded();

            String desKeyB64 = new String(SecurityUtils.base64Encode(baDesKey));
            String md5AndDesKey = md5Data + ";" + desKeyB64;

            PrivateKey privateKey = SecurityUtils.getPrivateKey(privKeyFile);
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            rsaCipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] baHeader = null;
            
            if (enc.equals("DEFAULT")) {
                baHeader = SecurityUtils.base64Encode(rsaCipher.doFinal(md5AndDesKey.getBytes()));
            } else {
                baHeader = SecurityUtils.base64Encode(rsaCipher.doFinal(md5AndDesKey.getBytes(enc)));
            }


            /////////////////////////////////////////////////////////////
            // Step 3. Body 생성
            SecretKeySpec secretKeySpec = new SecretKeySpec(baDesKey, "DES");
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES", "BC");
            SecretKey secretKey = secretKeyFactory.generateSecret(secretKeySpec);

            Cipher desCipher = Cipher.getInstance("DES", "BC");
            desCipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] baSsoData = null;
            if (enc.equals("DEFAULT")) {
            	baSsoData = desCipher.doFinal(sourceData.getBytes());
            } else {
            	baSsoData = desCipher.doFinal(sourceData.getBytes(enc));
            }

            byte[] baBody = SecurityUtils.base64Encode(baSsoData);


            ssoBuffer.append(new String(baHeader));
            ssoBuffer.append(";");
            ssoBuffer.append(new String(baBody));
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ssoBuffer.toString();
    }
}
