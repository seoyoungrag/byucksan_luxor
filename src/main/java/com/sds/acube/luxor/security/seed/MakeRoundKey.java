package com.sds.acube.luxor.security.seed;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MakeRoundKey
{
    public static void main(String[] args) throws Exception
    {
        String roundkeyFile = "seed.roundkey";
        if (args.length > 0)
        {
            roundkeyFile = args[0];
        }
        System.out.println("라운드키 파일 : " + roundkeyFile);

        System.out.print("유저키 입력  > ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String userkey = br.readLine();

        int pdwRoundKey[] = new int[32];
        byte pbUserKey[] = {
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
        };

        byte[] tmpUserkey = userkey.getBytes();
        for (int i = 0; i < (tmpUserkey.length > 16 ? 16 : tmpUserkey.length); i++)
        {
            pbUserKey[i] = tmpUserkey[i];
        }

        Seedx.SeedEncRoundKey(pdwRoundKey, pbUserKey);

        StringBuffer roundkeyStmt = new StringBuffer();
        for (int i = 0; i < pdwRoundKey.length; i++)
        {
            if (i > 0) roundkeyStmt.append(",");
            roundkeyStmt.append("" + pdwRoundKey[i]);
        }

        String roundkeyCert = SeedBean.doEncrypt(roundkeyStmt.toString());

        BufferedWriter out = null;
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        
        try {
	        fos = new FileOutputStream(roundkeyFile);
	        osw = new OutputStreamWriter(fos);
	        out = new BufferedWriter(osw);
	        out.write(roundkeyCert);
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
        	if(out!=null) out.close();
        	if(fos!=null) fos.close();
        	if(osw!=null) osw.close();
        }
        
        System.out.println("라운드키 파일이 생성되었습니다. : " + roundkeyFile);
    }
}
