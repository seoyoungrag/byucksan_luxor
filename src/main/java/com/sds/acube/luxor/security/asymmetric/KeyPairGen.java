package com.sds.acube.luxor.security.asymmetric;

import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.Security;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


/**
* <pre>
* 비대칭키[공개키/개인키] 생성 유틸리티
*
* 사용방법
* java KeyPairGen [publicKeyFile] [privateKeyFile]
* java KeyPairGen -default
*
* -default로 하면 acube_public.key, acube_private.key 파일이 생생된다.
* </pre>
*/
public class KeyPairGen
{
    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("java KeyPairGen [publicKeyFile] [privateKeyFile]");
            System.out.println("java KeyPairGen -default");
            System.exit(0);
        }


        String publicKeyName = "acube_public.key";
        String privateKeyName = "acube_private.key";

        if (args.length >= 2)
        {
            publicKeyName = args[0];
            privateKeyName = args[1];
        }

        try
        {
            Security.addProvider(new BouncyCastleProvider());

            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            generator.initialize(1024, random);

            KeyPair pair = generator.generateKeyPair();
            Key pubKey = pair.getPublic();
            Key privKey = pair.getPrivate();

            FileOutputStream pubFOS = null;
            FileOutputStream privFOS = null;
            
            try {
	            pubFOS = new FileOutputStream(publicKeyName);
	            pubFOS.write(pubKey.getEncoded());

	            privFOS = new FileOutputStream(privateKeyName);
	            privFOS.write(privKey.getEncoded());
            } catch (Exception e) {
            } finally {
            	pubFOS.close();
            	privFOS.close();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
