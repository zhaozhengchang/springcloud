package com.ceiec.twmp.tmp.utils.tools.ftp;

import com.ceiec.twmp.tmp.common.exception.BusinessException;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * CreateDate：2019/1/14
 * Author：wenliang
 * Description: The type Ftp client utils.
 **/
@Component
public class FTPClientUtils {

	/**
	 *  FTP配置对象类
	 */
	private static FTPConfig ftpConfig;

	/** FTPClientPool  */
	private static FTPClientPool ftpClientPool;

	private static FTPClient uploadClient;

	private static FTPClient downloadClient;

	/** logger */
	private static final Logger logger = LoggerFactory.getLogger(FTPClientUtils.class);

	@Autowired(required = true)
	public  void setFtpConfig(FTPConfig ftpConfig) {
		FTPClientUtils.ftpConfig = ftpConfig;
	}


	/*************************************************************************************************************************************
	 ** @Description 初始化ftp的配置
	 ** @Author Ding
	 ** @Date 2018/4/16 17:46
	 **
	 ************************************************************************************************************************************/
	private static void initFTPClient(){
		if(ftpClientPool == null ){
			ftpClientPool = new FTPClientPool(ftpConfig);
		}
	}


	/*************************************************************************************************************************************
	 ** @Description get upload ftp client
	 ** @param:
	 ** @Return void
	 ** @Author Ding
	 ** @Date 2019/4/9 16:57
	 **
	 ************************************************************************************************************************************/
	private static void getUploadClient(){
		initFTPClient();

		if(uploadClient == null){
			try {
				uploadClient = ftpClientPool.borrowObject();
			} catch (Exception e) {
				logger.error("get ftpClient is error :",e);
			}
		}
	}

	/*************************************************************************************************************************************
	 ** @Description get download ftp client
	 ** @param:
	 ** @Return void
	 ** @Author Ding
	 ** @Date 2019/4/9 16:57
	 **
	 ************************************************************************************************************************************/
	private static void getDownloadFtpClient(){
		if(downloadClient == null ){
			initFTPClient();
		}

		if(downloadClient == null){
			try {
				downloadClient = ftpClientPool.borrowObject();
			} catch (Exception e) {
				logger.error("get download ftpClient is error :",e);
			}
		}
	}



	/*************************************************************************************************************************************
	 ** @Description 上传ftp文件     PS fileName是相对文件名称
	 ** @Author Ding
	 ** @Date 2018/4/16 17:46
	 **
	 ************************************************************************************************************************************/
	public synchronized static String uploadFile(InputStream inputStream, String fileName) {

		getUploadClient();
		try {
			if (null != uploadClient) {
				String basicPath = ftpConfig.getFtpTopFolder();
				String[] fps = fileName.split("/");
				for (int i = 0; i < fps.length-1; i++) {
                    if(StringUtils.isNullOrEmpty(fps[i])){
                        continue;
                    }
                    basicPath = basicPath + "/" + fps[i];

                    if(!uploadClient.changeWorkingDirectory(basicPath)){
						uploadClient.makeDirectory(basicPath);
                    }
                }
				if(uploadClient.storeFile(ftpConfig.getFtpTopFolder()+fileName, inputStream)){
                    return ftpConfig.getFtpTopFolder()+fileName;
                }else{
                    throw new BusinessException("upload ftp file is error, please retry ");
                }
			}

		} catch (Exception e) {
			logger.error("upload ftp file is error !", e);
			ftpClientPool.returnObject(uploadClient);
			uploadClient = null;
		}
		return null;

	}



	/*************************************************************************************************************************************
     ** @Description 获取ftp上的文件   PS fileName是相对文件名称
     ** @Author Ding
     ** @Date 2018/4/16 18:05
     ** zks备注：ftpClient.getReply()这个方法在获取retrieveFileStream后要调用，否则之后请求retrieveFileStream就永远是null了
     ************************************************************************************************************************************/
	public synchronized static void downloadFile(String fileName, OutputStream os) {

		getDownloadFtpClient();


		InputStream is = null;
		try {
			if (null != downloadClient) {
				is = downloadClient.retrieveFileStream(ftpConfig.getFtpTopFolder()+fileName);

				byte[] b = new byte[512];
				int len;
				while ((len = is.read(b)) > 0){
					os.write(b, 0, len);
				}
				os.flush();
				os.close();
				is.close();
				downloadClient.getReply();
			}
		} catch (Exception e) {
			logger.error("download ftp file is error !", e);
			ftpClientPool.returnObject(downloadClient);
			downloadClient = null;
		}

	}





//	/*************************************************************************************************************************************
//	 ** @Description 删除文件的功能
//	 ** @Author Ding
//	 ** @Date 2018/4/17 10:16
//	 **
//	 ************************************************************************************************************************************/
//	public static  boolean deleteFile(String fileName){
//		getFtpClient();
//		if (null != ftpClient) {
//			try {
//                return ftpClient.deleteFile(ftpConfig.getFtpTopFolder()+fileName);
//            } catch (Exception e) {
//				logger.error("delete ftp file is error !", e);
//				ftpClientPool.returnObject(ftpClient);
//				ftpClient = null;
//            }
//		}
//		return false;
//
//	}
//
//
//	/*************************************************************************************************************************************
//	 ** @Description 删除整个文件夹的功能
//	 ** @Author Ding
//	 ** @Date 2018/4/17 10:15
//	 **
//	 ************************************************************************************************************************************/
//	public static boolean deleteDirectory(String path){
//		getFtpClient();
//		if (null != ftpClient) {
//			try {
//                FTPFile[] files = ftpClient.listFiles(ftpConfig.getFtpTopFolder()+path);
//                for(FTPFile f:files){
//                    String fs = path+"/"+f.getName();
//                    if(f.isFile()){
//                        // 是文件就删除文件
//                        ftpClient.deleteFile(ftpConfig.getFtpTopFolder()+fs);
//                    }else if(f.isDirectory()){
//                        deleteDirectory(fs);
//                    }
//                }
//                return ftpClient.removeDirectory(ftpConfig.getFtpTopFolder()+path);
//            } catch (Exception e) {
//				logger.error("delete ftp file is error !", e);
//				ftpClientPool.returnObject(ftpClient);
//				ftpClient = null;
//            }
//		}
//		return false;
//	}
//
//
//
//	/*************************************************************************************************************************************
//	 ** @Description 检查Ftp文件是否存在
//	 ** @Author Ding
//	 ** @Date 2018/4/17 14:10
//	 **
//	 ************************************************************************************************************************************/
//	public static boolean checkFileExists(String remoteFilePathAndName) {
//		getFtpClient();
//		if (null != ftpClient) {
//			try {
//                InputStream inputStream = ftpClient.retrieveFileStream(ftpConfig.getFtpTopFolder()+remoteFilePathAndName);
//                Integer returnCode = ftpClient.getReplyCode();
//                if (inputStream == null || returnCode == 550) {
//                    return false;
//                }
//                return true;
//            } catch (IOException e) {
//				logger.error("check ftp file is error !", e);
//				ftpClientPool.returnObject(ftpClient);
//				ftpClient = null;
//            }
//		}
//		return false;
//
//	}


}
