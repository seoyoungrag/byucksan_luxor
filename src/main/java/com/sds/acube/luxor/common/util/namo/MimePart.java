package com.sds.acube.luxor.common.util.namo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;



public class MimePart
{

    public static String[] HEADER_KEYS = {
            "mime-version", "content-type", "content-transfer-encoding", "content-id", "name",
            "x-generator"
    };

    private String bodypart;

    private String contentType;

    private String contentID;

    private String encoding;

    private String name;

    private String ori_name;


    public void setName(String name)
    {
        this.name = name;
    }


    public void setOriName(String name)
    {
        this.ori_name = name;
    }




    public String getOriName()
    {
        return ori_name;
    }


    public String getName()
    {
        return name;
    }


    public void setBodypart(String part)
    {
        bodypart = part;
    }


    public String getBodypart()
    {
        return bodypart;
    }


    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }


    public String getContentType()
    {
        return contentType;
    }


    public void setContentID(String contentID)
    {
        this.contentID = contentID;
    }


    public String getContentID()
    {
        return contentID;
    }


    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }


    public String getEncoding()
    {
        return encoding;
    }


    public void parseMimePart(String mimeString) throws IOException
    {
        // 한줄씩 읽기 위해서
        BufferedReader br = new BufferedReader(new StringReader(mimeString));
        String line;

//        StringBuffer header = new StringBuffer();
        StringBuffer body = new StringBuffer();

        boolean headStarted = false;
        boolean headEnded = false;
        int blankLineCount = 0;
        while ((line = br.readLine()) != null)
        {
            line = line.trim();
            if (line.length() == 0)
            {
                // 처음 공백라인 제거...
                if (headStarted == false)
                {
                    continue;
                }
                blankLineCount++;
            }
            headStarted = true;

            if (headEnded)
            {
                body.append(line).append("\n");
            }
            else
            {
                parseHeader(line);
//            	header.append(line).append("\n");
            }

            // 본문 시작...
            if (blankLineCount > 0)
            {
                headEnded = true;
            }
        }

//        parseHeader(header.toString());
        setBodypart(body.toString());
    }


    public void parseHeader(String header)
    {
        String headerLower = header.toLowerCase();

        if(headerLower.indexOf("content-type") != -1)									// content type
        {
        	setContentType(header.substring(header.indexOf(":") + 1).trim());
        }
        
        if(headerLower.indexOf("charset") != -1)
        {
        	setContentType(getContentType() + header);
        }
        
        if(headerLower.indexOf("content-transfer-encoding") != -1)						// encoding type
        {
        	setEncoding(header.substring(header.indexOf(":") + 1).trim());
        }
        
        if(headerLower.indexOf("content-id") != -1)									// CID
        {
           String temp_header = header.substring(header.indexOf("<") + 1, header.indexOf(">"));
           setContentID(temp_header);
        }
        
        if(headerLower.indexOf("name") != -1)											// File name
        {
           String temp_header = header.substring(header.indexOf("\"") + 1, header.length() - 1);

           setName(temp_header);
           setOriName(temp_header);

        }
        
    }

}
