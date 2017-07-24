/*
 * @(#) JstoreDAO.java 2004. 7.27.
 * Copyright (c) 2001-2004 Samsung SDS Co., Ltd. All Rights Reserved.
 */

package com.sds.acube.luxor.common.dao;

import com.sds.acube.jstor.JSTORApi;
import com.sds.acube.jstor.JSTORApiFactory;
import com.sds.acube.luxor.framework.config.LuxorConfig;
import com.sds.acube.luxor.framework.dao.BaseDAO;

/**
 * JSTORE api를 이용해서 저장서버로 등록, 삭제, 수정(삭제후 등록)
 * 하나의 instance안에 한 Tx가 유지된다.
 * 
 * e.g.) JstoreDAO jDao = new JstoreDAO(messages);
 * 		jDao.registerFiles(list);
 * 		jDao.commit();
 * 		jDao.close();
 * 
 * @author Kim Jea Ryun
 *
 * @version $Date: 2011/02/10 06:05:42 $
 * Last Revised $Revision: 1.1.6.1 $
 */
@SuppressWarnings("unchecked")
public class JstoreDAO extends BaseDAO {

    private int connectionID;
    private JSTORApi jSTOR;
    private JSTORApiFactory jsFactory;
    private String moduleName;
    private String filter;
    private int guiFlag;

    /**
     * Constructor
     */
    public JstoreDAO() {
        this.connectionID = -1;
        this.jsFactory = new JSTORApiFactory();
        this.moduleName = "Common";
        this.filter = LuxorConfig.getString(moduleName, "ATTACH.STOR_FILTER");
        this.guiFlag = 0;
    }
    
    public JstoreDAO(String moduleName) {
        this.connectionID = -1;
        this.jsFactory = new JSTORApiFactory();
        this.moduleName = moduleName;
        this.filter = LuxorConfig.getString(moduleName, "ATTACH.STOR_FILTER");
        this.guiFlag = 0;
    }

    /**
     * StorageServer object에 지정된 저장서버에 connection을 맺는다.
     */
    public void connect() throws Exception {
        try {
            jSTOR = jsFactory.getInstance();
            connectionID = jSTOR.JSTOR_Connect(LuxorConfig.getString(moduleName, "ATTACH.STOR_SVR"),
            		LuxorConfig.getInt(moduleName, "ATTACH.STOR_PORT"));

            if (connectionID < 0) {
                logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
                throw new Exception("STORServ Connect Failed error code = "
                        + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
            } else {
                logger.info("저장서버에 연결되었습니다. Connection ID [" + connectionID + "]");
            }
        } catch (Exception e) {
            throw new Exception("STORServ connection Failed ", e);
        }
    }

    /**
     * 저장서버 connection commit
     */
    public void commit() throws Exception {
        int nRet = jSTOR.JSTOR_Commit(connectionID);
        
        if (nRet < 0) {
            logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
            throw new Exception("STORServ commit failed, error code = "
                    + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
        }
    }

    /**
     * 저장서버 connection rollback
     */
    public void rollback() {
        jSTOR.JSTOR_Rollback(connectionID);
    }

    /**
     * 저장서버 connection close
     *
     */
    public void disconnect() {
        if (connectionID > 0) {
            logger.info("저장서버와의 연결이 해제되었습니다.");
            jSTOR.JSTOR_Disconnect(connectionID);
        }
    }

    /**
     * @param filePath
     * @return
     * @throws Exception
     */
    public String registerFile(String filePath) throws Exception {
        String[][] sInfoRegArr = new String[1][4];
        sInfoRegArr[0][0] = new String(filePath); // SRC Reg. File Path
        sInfoRegArr[0][1] = LuxorConfig.getString(moduleName, "ATTACH.STOR_VOL"); // Vol ID 
        sInfoRegArr[0][2] = filter; // Filter ID 
        sInfoRegArr[0][3] = null;

        logger.info("1개의 파일을 저장서버에 저장합니다.");

        int nRet = jSTOR.JSTOR_FileReg(connectionID, 1, sInfoRegArr, guiFlag);

        if (nRet < 0) {
            logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = "
                    + jSTOR.JSTOR_getErrMsg());
            throw new Exception("STORServ register failed, error code = "
                    + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
        } else {
            logger.info("저장서버에 파일을 저장했습니다.");
            return jSTOR.JSTOR_getRegFileID()[0];
        }
    }
    
    /**
     * @param storIDs
     * @return
     * @throws Exception
     */
    public String[] copyFiles(String[] storIDs) throws Exception {
        String[] r = null;
        String[][] sInfoCpyArr = null;       // 등록 복사 정보
        try{
            int length = storIDs.length;
            sInfoCpyArr = new String[length][6];
            
            for(int i=0;i<length;i++) {
                sInfoCpyArr[i][0] = storIDs[i];  // 대상파일
                sInfoCpyArr[i][1] = LuxorConfig.getString(moduleName, "ATTACH.STOR_VOL"); // Vol ID 
                sInfoCpyArr[i][2] = filter; // Filter ID 
                sInfoCpyArr[i][3] = new String("1");   // 로컬(1), 원격(2)
                
                sInfoCpyArr[i][4] = null;
                sInfoCpyArr[i][5] = null;
            }
            
            int nRet = jSTOR.JSTOR_FileCpy(connectionID, length, sInfoCpyArr);
            if (nRet < 0) {
                logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = "
                        + jSTOR.JSTOR_getErrMsg());
                throw new Exception("STORServ copy failed, error code = "
                        + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
            } else {
                logger.info("저장서버에 파일을 복사했습니다.");
                r = jSTOR.JSTOR_getNewCpyFileID();
            }
            
            ///////////////////////////////////////////////////////////
        }catch(Exception ae) {
            throw ae;
        }
        return r;
    }

    /**
     * storeFid를 이용하여 저장서버의 파일을 삭제
     * 
     * @param connectionID
     * @param delList
     * @return int => 서비스 성공(0), 서비스 실패(음수, Minus)
     */
    public int deleteFiles(String[] arrStoreFid) throws Exception {

        int nRet = jSTOR.JSTOR_FileDel(connectionID, arrStoreFid.length, arrStoreFid);

        if (nRet < 0) {
            jSTOR.JSTOR_Rollback(connectionID);
            logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = "
                    + jSTOR.JSTOR_getErrMsg());
            throw new Exception("STORServ delete failed, error code = "
                    + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
        } else {
            logger.info("저장서버의 파일을 삭제했습니다.");
        }
        return nRet;
    }    

    /**
     * @param ArrayList 
     * @return int => 서비스 성공(0), 서비스 실패(음수, Minus)
     */
//    public int getFiles(ArrayList list) throws Exception {
//        logger.info(list.size() + " 개의 파일을 저장서버에서 가져옵니다.");
//        logger.debug("information array to get files : " + list.getInfoArr2GetFiles()[0][0]);
//
//        int nRet = jSTOR.JSTOR_FileGet(connectionID, list.size(), list.getInfoArr2GetFiles(),0);
//
//        if (nRet < 0) {
//            jSTOR.JSTOR_Rollback(connectionID);
//            logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = "
//                    + jSTOR.JSTOR_getErrMsg());
//            throw new Exception("Stored file get failed, error code = "
//                    + jSTOR.JSTOR_getErrCode() + ", error msg = " + jSTOR.JSTOR_getErrMsg());
//        } else {
//            logger.info("저장서버에서 파일을 가져왔습니다.");
//        }
//        return nRet;
//    }

    /**
     * STORServ 파일반출(가져오기) 서비스 템프 경로로 파일을 다운로드
     * 
     * @param tempPath 
     * @param fileId
     * @return 내용 문자열 에러시 null;
     * @throws Exception 
     */
//    public String getFile(String tempPath, String fileId) throws Exception {
//        String filePath = tempPath + fileId;
//
//        int nRet;
//
//        String[][] sInfoGetArr = new String[1][3];
//        sInfoGetArr[0][0] = fileId; // SRC Reg. File Path
//        sInfoGetArr[0][1] = filePath; // Vol ID 
//        sInfoGetArr[0][2] = filter; // Filter ID 
//        
//
//        nRet = jSTOR.JSTOR_FileGet(connectionID, 1, sInfoGetArr, guiFlag);
//        if (nRet < 0) {
//            logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = "
//                    + jSTOR.JSTOR_getErrMsg());
//            return null;
//        }
//
//        File destFile = new File(filePath);
//        byte b[] = new byte[(new Long(destFile.length())).intValue()];
//
//        try {
//            FileInputStream inFile = new FileInputStream(destFile);
//            inFile.read(b);
//            inFile.close();
//
//            destFile.delete();
//        } catch (Exception e) {
//            logger.error("file read error = " + e);
//        }
//
//        return (new String(b));
//    }
    
    /**
     * 지정된 path로 storID의 파일은 download한다.
     * @param filePath
     * @param storID
     * @return
     * @throws Exception
     */
    public String getFile(String filePath, String storID) throws Exception {
        
        int nRet;
        String r = null;

        String[][] sInfoGetArr = new String[1][3];
        sInfoGetArr[0][0] = storID; // SRC Reg. File Path
        sInfoGetArr[0][1] = filePath; // Vol ID 
        sInfoGetArr[0][2] = filter; // Filter ID 
        

        nRet = jSTOR.JSTOR_FileGet(connectionID, 1, sInfoGetArr, guiFlag);
        if (nRet < 0) {
            logger.error("error code = " + jSTOR.JSTOR_getErrCode() + ", error msg = "
                    + jSTOR.JSTOR_getErrMsg());
            return r;
        }

        r = filePath.substring(filePath.lastIndexOf("/") + 1 );
        return r;
    }
    
}