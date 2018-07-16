package com.cn.iris.common.util;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.UUID;

/**
 * Author: IrisNew
 * Description:七牛云存储使用工具类：用于文件存储
 * Date: 2018/6/29 16:09
 */
public class QiniuUtil {

    private static Logger logger = LoggerFactory.getLogger(QiniuUtil.class);

    private static final String accessKey = "e3TdMbMPuMx62A4AywUVd_FPDWkzJlCS_E5_8bpk";
    private static final String secretKey = "67_yLeLnG-JLwNq9_4SOZliRqoKy5RYKikAOl7gk";
    static final String bucket_basic = "irisnew";
    static final String bucket_temp = "temp";
    static final String basic_url = "http://pb882w3bl.bkt.clouddn.com/";
    static final String temp_url = "http://pb8er1d24.bkt.clouddn.com/";

    /**
     * 获取七牛云的上传Token
     * @param bucket 空间
     * @return Token
     */
    private static String getToken(String bucket,StringMap putPolicy) {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket,null,3600,putPolicy);
    }

    /**
     * 获取上传管理器
     * @return UploadManager
     */
    private static UploadManager getUploadManager() {
        //构造一个带指定Zone对象的配置类
        //区域需和bucket区域对应，不然会上传失败
        //华东    Zone.zone0()
        //华北    Zone.zone1()
        //华南    Zone.zone2()
        //北美    Zone.zoneNa0()
        Configuration cfg = new Configuration(Zone.zone2());
        return new UploadManager(cfg);
    }
    /**
     * 获取Bucket的管理对象
     * @return BucketManager
     */
    private static BucketManager getBucketManager() {
        //构造一个带指定Zone对象的配置类
        //区域需和bucket区域对应，不然会上传失败
        //华东    Zone.zone0()
        //华北    Zone.zone1()
        //华南    Zone.zone2()
        //北美    Zone.zoneNa0()
        Configuration cfg = new Configuration(Zone.zone2());
        Auth auth = Auth.create(accessKey, secretKey);
        return new BucketManager(auth, cfg);
    }

    /*//获取所有的bucket--用处不大，注释掉
    public static void getBucketsInfo() {
        try {
            BucketManager bucketManager = getBucketManager();
            //获取所有的bucket信息
            String[]  bucketNms = bucketManager.buckets();
            for(String bucket:bucketNms) {
                System.out.println(bucket);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 获取bucket里面所有文件的信息
     * @param bucket 空间名称
     */
    public static void getFileInfo(String bucket) {
        try {
            BucketManager bucketManager = getBucketManager();
            //文件名前缀
            String prefix = "";
            //每次迭代的长度限制，最大1000，推荐值1000
            int limit = 1000;
            //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
            String delimiter = "";
            //列举空间文件列表
            BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(bucket, prefix, limit, delimiter);
            while (fileListIterator.hasNext()) {
                //处理获取的file list结果
                FileInfo[] items = fileListIterator.next();
                for (FileInfo item : items) {
                    System.out.println(item.key);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * 删除多个文件
     * 单次批量请求的文件数量不得超过1000 , 七牛规定
     * @param bucket    空间名称
     * @param keys      文件名称数组
     * @return Result
     */
    public static QiNiuResult deletes(String bucket ,String [] keys) {
        QiNiuResult result = null;
        try {
            //当文件大于1000的时候，就直接不处理
            if(keys.length >1000) {
                return new QiNiuResult(false,"文件数量不得超过1000个！");
            }

            //设定删除的数据
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(bucket, keys);
            BucketManager bucketManager = getBucketManager();
            //发送请求
            Response response = bucketManager.batch(batchOperations);
            //返回数据
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keys.length; i++) {
                BatchStatus status = batchStatusList[i];
                String key = keys[i];
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.data.error);
                    return new QiNiuResult(false,status.data.error);
                }
            }
            result = new QiNiuResult(true,"批量删除成功！");
        }catch (QiniuException e) {
            result = new QiNiuResult(false,e.response.toString());
            logger.error(e.response.toString());
        }
        return result;
    }
    /**
     * 删除bucket中的文件名称
     * @param bucket    空间名称
     * @param key       文件名称
     * @return Result
     */
    public static QiNiuResult delete(String bucket ,String key) {
        QiNiuResult result = null;
        try {
            BucketManager bucketManager = getBucketManager();
            bucketManager.delete(bucket, key);
            result = new QiNiuResult(true,"删除单文件成功！");
        }catch (QiniuException e) {
            result = new QiNiuResult(false,e.response.toString());
            logger.error(e.response.toString());
        }
        return result;
    }

    /**
     * 上传字节流
     * @param bucket    空间名称
     * @param bytes     字节流
     * @param fileName  需要上传的文件的名称
     * @return Result
     */
    public static QiNiuResult upload(String bucket, byte[] bytes, String fileName) {
        QiNiuResult result = null;
        try {
            UploadManager uploadManager = getUploadManager();
            //设置返回报文信息格式
            StringMap putPolicy = new StringMap();
            putPolicy.put("returnBody", "{\"fileName\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
            //获取token
            String token = getToken(bucket,putPolicy);
            //上传字节流
            Response response = uploadManager.put(bytes,fileName,token);
            //解析上传成功的结果
            result = response.jsonToObject(QiNiuResult.class);
            //DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(result.getFileName());
            System.out.println(result.getHash());

        }catch (QiniuException ex) {
            Response r = ex.response;
            result = new QiNiuResult(false,r.toString());
            System.err.println(r.toString());
            logger.error(ex.response.toString());
        }
        return result;
    }
    /**
     * 通过文件来传递数据
     * @param bucket    空间名称
     * @param file      文件名
     * @return Result
     */
    public static QiNiuResult upload(String bucket,File file) {
        QiNiuResult result = null;
        try {
            UploadManager uploadManager = getUploadManager();
            //设置返回报文信息格式
            StringMap putPolicy = new StringMap();
            putPolicy.put("returnBody", "{\"fileName\":\"$(key)\",\"hash\":\"$(etag)\",\"bucket\":\"$(bucket)\",\"fsize\":$(fsize)}");
            String token = getToken(bucket,putPolicy);
            Response response = uploadManager.put(file.getAbsolutePath(),newName(file.getName()), token);

            //解析上传成功的结果
            result = response.jsonToObject(QiNiuResult.class);
            //DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(result.getFileName());
            System.out.println(result.getHash());
        } catch (QiniuException ex) {
            Response r = ex.response;
            result = new QiNiuResult(false,r.toString());
            System.err.println(r.toString());
            logger.error(ex.response.toString());
        }
        return result;

    }

    /**
     * 自动生产需上传的文件名
     * @param oldName   原文件
     * @return String   新文件名称
     */
    private static String newName(String oldName) {
        String[] data = oldName.split("\\.");
        String type = data[data.length - 1];
        return UUID.randomUUID().toString() + "." + type;
    }

}
