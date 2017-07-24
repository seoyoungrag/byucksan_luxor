package com.sds.acube.luxor.security.asymmetric;

import java.security.PublicKey;
import java.security.Security;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sds.acube.luxor.security.SecurityDecoder;
import com.sds.acube.luxor.security.SecurityFactory;


/**
* Data 디코딩 모듈 */
public class AsymmKeyDecoder implements SecurityDecoder
{
    public AsymmKeyDecoder()
    {
        Security.addProvider(new BouncyCastleProvider());
    }


    /**
    * <pre>
    * 인코딩된 Data를 공개키로 디코딩한다.
    * 공개키는 CLASSPATH 에서 /properties/security/acube_public.key 가 사용된다.
    * <pre>
    * @param encodedData 인코딩된 Data
    * @return 디코딩된 Data. 오류가 발생하면 null 리턴.
    */
    public String decode(String encodedData) throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String certFilesHome = classLoader.getResource("esso/security").getPath();//@mod 2014.02.28

        return decode(encodedData, certFilesHome + "acube_public.key"); //@mod 2014.02.28
    }


    /**
    *  인코딩된 Data를 공개키로 디코딩한다.
    * @param encodedData 인코딩된 Data
    * @param publicKeyFile 공개키 파일(절대경로)
    * @return 디코딩된 Data. 오류가 발생하면 null 리턴.
    */
    public String decode(String encodedData, String publicKeyFile) throws Exception
    {
        String ssoData = null;
        try
        {
			String enc = SecurityFactory.getInstance().getEncoding();

        	StringTokenizer token = new StringTokenizer(encodedData, ";");
            if (token.countTokens() != 2) {
                throw new Exception("Invalid SSO data");
            }

            byte[] baHeader = SecurityUtils.base64Decode(token.nextToken().getBytes());
            byte[] baBody = SecurityUtils.base64Decode(token.nextToken().getBytes());

            /////////////////////////////////////////////////////////////
            // Step 1. Header 부분
            PublicKey publicKey = SecurityUtils.getPublicKey(publicKeyFile);
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding", "BC");
            rsaCipher.init(Cipher.DECRYPT_MODE, publicKey);
            String md5AndDesKey = "";
            
            if (enc.equals("DEFAULT")) {
            	md5AndDesKey = new String(rsaCipher.doFinal(baHeader));
            } else {
            	md5AndDesKey = new String(rsaCipher.doFinal(baHeader),enc);
            }
            StringTokenizer headerToken = new StringTokenizer(md5AndDesKey , ";");
            if (headerToken.countTokens() != 2) {
                throw new Exception("Invalid SSO header data");
            }

            String md5Data = headerToken.nextToken();
            byte[] baDesKey = SecurityUtils.base64Decode(headerToken.nextToken());


            /////////////////////////////////////////////////////////////
            // Step 2. Body 부분
            SecretKeySpec secretKeySpec = new SecretKeySpec(baDesKey, "DES");
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES", "BC");
            SecretKey secretKey = secretKeyFactory.generateSecret(secretKeySpec);

            Cipher desCipher = Cipher.getInstance("DES", "BC");
            desCipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] baSsoData = desCipher.doFinal(baBody);
            if (enc.equals("DEFAULT")) {
                ssoData = new String(baSsoData);
            } else {
                ssoData = new String(baSsoData,enc);
            }


            /////////////////////////////////////////////////////////////
            // Step 3. MD5 검사 (데이타 무결성 검증)
            String nowMd5Data = "";
            
            if (enc.equals("DEFAULT")) {
                nowMd5Data = SecurityUtils.getMD5String(baSsoData);
            } else {
                nowMd5Data = SecurityUtils.getMD5String(ssoData);
            }
           
            if (md5Data.equals(nowMd5Data) == false) {
                throw new Exception("SSO Data may be corrupted");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return ssoData;
    }
}
