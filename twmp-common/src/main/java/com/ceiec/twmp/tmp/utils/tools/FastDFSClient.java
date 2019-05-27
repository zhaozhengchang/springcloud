package com.ceiec.twmp.tmp.utils.tools;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
 
/**
 * CreateDate：2018/11/7
 * Author：wenliang
 * Description: FastDFS文件上传下载工具类
 **/
public class FastDFSClient {

    private static final String CONFIG_FILENAME = "fdfs_client.conf";

    private static StorageClient1 storageClient1 = null;
    private static String tracker_http = null;

    static {
        try {
            //初始化FastDFS Client
            ClientGlobal.init(CONFIG_FILENAME);
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            TrackerServer trackerServer = trackerClient.getConnection();
            if (trackerServer == null) {
                throw new IllegalStateException("getConnection return null");
            }

            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            if (storageServer == null) {
                throw new IllegalStateException("getStoreStorage return null");
            }
            storageClient1 = new StorageClient1(trackerServer, storageServer);

            //获取tracker server的http访问地址
            tracker_http = (trackerServer.getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @param fileName 文件名
     * @param http true-返回http路径，false-返回非http路径
     * @return
     */
    public static String uploadFile(File file, String fileName, boolean http) {
        return uploadFile(file, fileName, null, http);
    }

    /**
     * 上传文件
     *
     * @param file 文件对象
     * @param fileName 文件名
     * @param metaList 文件元数据
     * @param http true-返回http路径，false-返回非http路径
     * @return
     */
    public static String uploadFile(File file, String fileName, Map<String, String> metaList, boolean http) {
        try {
            byte[] buff = IOUtils.toByteArray(new FileInputStream(file));
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }

            if (http) {
                return tracker_http + '/' + storageClient1.upload_file1(buff, FilenameUtils.getExtension(fileName), nameValuePairs);
            } else {
                return storageClient1.upload_file1(buff, FilenameUtils.getExtension(fileName), nameValuePairs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     *
     * @param fileContent 文件的内容，字节数组
     * @param fileName 文件名,带扩展名
     * @param metaList 文件扩展信息
     * @param http true-返回http路径，false-返回非http路径
     * @return
     */
    public static String uploadFile(byte[] fileContent, String fileName, Map<String, String> metaList, boolean http) {
        try {
            NameValuePair[] nameValuePairs = null;
            if (metaList != null) {
                nameValuePairs = new NameValuePair[metaList.size()];
                int index = 0;
                for (Iterator<Map.Entry<String, String>> iterator = metaList.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry<String, String> entry = iterator.next();
                    String name = entry.getKey();
                    String value = entry.getValue();
                    nameValuePairs[index++] = new NameValuePair(name, value);
                }
            }

            if (http) {
                return tracker_http + '/' + storageClient1.upload_file1(fileContent, FilenameUtils.getExtension(fileName), nameValuePairs);
            } else {
                return storageClient1.upload_file1(fileContent, FilenameUtils.getExtension(fileName), nameValuePairs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String uploadFile(byte[] fileContent, boolean http) {
        return uploadFile(fileContent, null, null, http);
    }

    public static String uploadFile(byte[] fileContent, String fileName, boolean http) {
        return uploadFile(fileContent, fileName, null, http);
    }

    /**
     * 获取文件元数据
     *
     * @param fileId 文件ID
     * @return
     */
    public static Map<String, String> getFileMetadata(String fileId) {
        try {
            NameValuePair[] metaList = storageClient1.get_metadata1(fileId);
            if (metaList != null) {
                HashMap<String, String> map = new HashMap<String, String>();
                for (NameValuePair metaItem : metaList) {
                    map.put(metaItem.getName(), metaItem.getValue());
                }
                return map;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param fileId 文件ID
     * @return 删除失败返回-1，否则返回0
     */
    public static int deleteFile(String fileId) {
        try {
            return storageClient1.delete_file1(fileId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 下载文件
     *
     * @param fileId 文件ID（上传文件成功后返回的ID）
     * @param outFile 文件下载保存位置
     * @return
     */
    public static int downloadFile(String fileId, File outFile) {
        FileOutputStream fos = null;
        try {
            byte[] content = storageClient1.download_file1(fileId);
            InputStream fin = new ByteArrayInputStream(content);
            fos = new FileOutputStream(outFile);
            IOUtils.copy(fin, fos);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

}
