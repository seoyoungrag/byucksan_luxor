package com.sds.acube.luxor.common.util.namo;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.MessagingException;
import javax.mail.internet.MimeUtility;
import com.sds.acube.luxor.common.util.UtilINIControl;


/**
 * ActiveSqaure 4.0 이상에서 생성되는 MIMEValue를 디코딩하는 클래스. 디코딩 전용이며 인코딩은 지원하지 않는다. 또한 일반적인 MIME 인코딩 데이터와
 * 호환이 되지 않을수도 있다.
 */
public class NamoMime
{
    // multipart 인가?
    private boolean multipart;

    // multipart 인 경우 바운더리는?
    private String boundary;

    // 디코드할 파트들
    private Vector decodePart;

    // save Path
    private String savePath;

    // save URL
    private String saveURL;


    // 기본 생성자
    public NamoMime()
    {
        multipart = false;
        boundary = null;
    }


    public void setSavePath(String path)
    {
        savePath = path;
    }


    public void setSaveURL(String url)
    {
        saveURL = url;
    }


    // 디코딩을 한다
    public boolean decode(String encodedString) throws MimeDecodeException, IOException
    {
        decodePart = new Vector();
        checkMimeType(encodedString);
        splitMimePart(encodedString);

        return true;
    }


    // 본문을 디코딩하여 가져온다
    public String getBodyContent() throws MimeDecodeException
    {
        if (decodePart.size() <= 0)
        {
            return null;
        }

        MimePart part = (MimePart) decodePart.elementAt(0);
        // 인코딩 타입이 없으므로 인코딩 하지 말것
        if (part.getEncoding() == null)
        {
            return part.getBodypart();
        }

        String decodeText = part.getBodypart();
        byte[] decodeByte;

        try
        {
            // String을 바이트로 바꿔서 입력 스트림으로 바꾼다.
            // 이때 인코딩 타입을 iso-8859-1로 셋팅한다
            InputStream is = new ByteArrayInputStream(part.getBodypart().getBytes("iso-8859-1"));
            try
            {
                is = MimeUtility.decode(is, part.getEncoding()); // 디코딩
            }
            catch (MessagingException me)
            {
                throw new MimeDecodeException("Cannot Decoding");
            }

            decodeByte = new byte[is.available() + 1]; // 바이트배열로 읽어 들인다
            is.read(decodeByte);

            String contentType = part.getContentType();
            String enc = null;

            // 스트링으로 바꾼다
            if (contentType != null)
            {
                int index = contentType.indexOf("charset");
                if (index > -1)
                {
                    StringTokenizer charsetToken = new StringTokenizer(
                            contentType.substring(index), "=\"");
                    if (charsetToken.hasMoreTokens())
                    {
                        charsetToken.nextToken();
                    }
                    if (charsetToken.hasMoreTokens())
                    {
                        enc = charsetToken.nextToken();
                    }
                }
            }

            if (enc != null)
            {
                decodeText = new String(decodeByte, enc);
            }
            else
            {
                decodeText = new String(decodeByte);
            }
        }
        catch (UnsupportedEncodingException uee)
        {
            throw new MimeDecodeException(uee.getMessage());
        }
        catch (IOException ioe)
        {
            throw new MimeDecodeException("Cannot create input stream");
        }

        if (multipart)
        {
            decodeText = changeCIDPath(decodeText);
        }
        return decodeText;
    }


    // 파일을 저장한다
    public void saveFile() throws MimeDecodeException
    {
        File outFile, makeDir;
        OutputStream os;
        InputStream is;
        MimePart part;
        byte[] fileContent;
        int i;

        for (i = 1; i < decodePart.size(); i++)
        {
            outFile = null;
            os = null;
            is = null;
            fileContent = null;
            part = (MimePart) decodePart.elementAt(i); // Mime file 을 가져온다
            try
            {
                makeDir = new File(savePath); // 업로드할 디렉토리 생성

                if (!makeDir.exists())
                {
                    makeDir.mkdirs();
                }
                is = new ByteArrayInputStream(part.getBodypart().getBytes("iso-8859-1"));
                is = MimeUtility.decode(is, part.getEncoding());
                fileContent = new byte[is.available() + 1];
                is.read(fileContent);

                String originalFileName = MimeUtility.decodeText(part.getOriName());
                String fileExt = "";
                int extIndex = originalFileName.lastIndexOf(".");
                if (extIndex > -1)
                {
                    fileExt = originalFileName.substring(extIndex);
                }
                
                String contentID = part.getContentID();

                String key = contentID;
                key = key.replace('@', '_');
                key = key.replace('<', '_');
                key = key.replace('>', '_');
                key = key.replace('\"', '_');
                key = key.replace('\'', '_');

                String savedFileName = key + i + fileExt;
                outFile = new File(savePath + "/" + savedFileName);
                os = new FileOutputStream(outFile);
                os.write(fileContent);
                os.close();

                // 파일 정보 프로파일 저장
                String FilePath = savePath + "/attach.prop";

                UtilINIControl.writeIniString(contentID, "CUR_FILENAME", savedFileName,
                        FilePath);
                UtilINIControl.writeIniString(contentID, "ORI_FILENAME",
                        originalFileName, FilePath);
                UtilINIControl.writeIniString(contentID, "CONTENT-TYPE", part
                        .getContentType(), FilePath);
            }
            catch (FileNotFoundException fnfe)
            {
                fnfe.printStackTrace();
                throw new MimeDecodeException("Cannot create file");
            }
            catch (IOException ioe)
            {
                throw new MimeDecodeException("Cannot write file");
            }
            catch (MessagingException me)
            {
                throw new MimeDecodeException("Cannot decode file");
            }
            finally
            {
                try
                {
                    if (is != null)
                        is.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                try
                {
                    if (os != null)
                        os.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    // 현재 MimeConstants 데이터의 타입을 검사한다
    protected void checkMimeType(String encodedString) throws MimeDecodeException, IOException
    {
        String line;
        int pos = 0;
        BufferedReader br = new BufferedReader(new StringReader(encodedString));

        do
        {
            line = br.readLine();
            if (line == null)
                throw new MimeDecodeException("Cannot find Content-Type:");
            line = line.toLowerCase();
            pos = line.indexOf("content-type");
        }
        while (pos == -1);

        // content-type 이 들어있는 문자열을 찾았다.
        pos = line.indexOf("multipart"); // multipart 가 content-type인가?
        if (pos != -1)
        {
            this.multipart = true;
            // multipart 이므로 boundary를 찾는다
            do
            {
                line = br.readLine();
                if (line == null)
                    throw new MimeDecodeException("Cannot find boundary");
                pos = line.indexOf("boundary");
            }
            while (pos == -1);

            pos = line.indexOf("\"");
            if (pos == -1)
                throw new MimeDecodeException("Connot find boudary");
            this.boundary = line.substring(pos + 1, line.indexOf("\"", pos + 1));
        }
    }


    // MimeConstants 을 part 별로 나누고, 정보를 분석한다
    protected void splitMimePart(String encodedString) throws IOException
    {
        if (this.multipart) // 멀티 파트인경우
        {
            splitMultiPart(encodedString);
        }
        else
        // 싱글 파트인경우
        {
            splitSinglePart(encodedString);
        }
    }


    // MultiPart 인 경우 나눈다
    protected void splitMultiPart(String encodedString) throws IOException
    {
        String part = null;
        int start = 0;
        int pos = 0;

        while (true)
        {
            pos = encodedString.indexOf("--" + boundary, start); // 바운더리를 찾는다
            if (pos == -1) // 다음 바운더리가 없는 경우 패스
                break;
            start = encodedString.indexOf("--" + boundary, pos + boundary.length() + 2);
            if (start == -1)
                break;
            part = encodedString.substring(pos + boundary.length() + 2, start);
            splitSinglePart(part);
        }
    }


    // 나눠진 하나의 파트의 내용을 분리해 낸다
    protected void splitSinglePart(String encodedString) throws IOException
    {
        MimePart part = new MimePart();
        part.parseMimePart(encodedString);
        decodePart.addElement(part);
    }


    // 컨텐트 스트링을
    protected String changeCIDPath(String content)
    {
        int i = 0;
        String convert;
        MimePart part;
        convert = content;

        for (i = 1; i < decodePart.size(); i++)
        {
            part = (MimePart) decodePart.elementAt(i);
            String contentID = part.getContentID();
            if (contentID != null)
            {
                if (saveURL == null || saveURL.length() <= 0)
                {
                    convert = replace(convert, "cid:" + contentID, part.getName());
                }
                else
                {
                    convert = replace(convert, "cid:" + contentID, saveURL + "&sep=|&cid="
                            + contentID);
                }
            }
        }

        return convert;
    }


    // replace string
    public String replace(String original, String oldstr, String newstr)
    {
        String convert = new String();
        int pos = 0;
        int begin = 0;
        pos = original.indexOf(oldstr);

        if (pos == -1)
            return original;

        while (pos != -1)
        {
            convert = convert + original.substring(begin, pos) + newstr;
            begin = pos + oldstr.length();
            pos = original.indexOf(oldstr, begin);
        }
        convert = convert + original.substring(begin);

        return convert;
    }
}
