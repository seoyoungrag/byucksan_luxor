package com.sds.acube.luxor.security.seed;

import com.sds.acube.luxor.security.SecurityDecoder;
import com.sds.acube.luxor.security.SecurityEncoder;


public class SeedWrapper implements SecurityEncoder, SecurityDecoder
{
    public String encode(String sourceData) throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String certFilesHome = classLoader.getResource("esso/security").getPath();//@mod 2014.02.28
        return encode(sourceData, certFilesHome + "seed.roundkey");//@mod 2014.02.25
    }


    public String encode(String sourceData, String key) throws Exception
    {
        return SeedBean.doEncrypt(sourceData, key);
    }


    public String decode(String encodedData) throws Exception
    {
        ClassLoader classLoader = getClass().getClassLoader();
        String certFilesHome = classLoader.getResource("security").getPath();

        return decode(encodedData, certFilesHome + "/seed.roundkey");
    }


    public String decode(String encodedData, String key) throws Exception
    {
        return SeedBean.doDecode(encodedData, key);
    }
}
