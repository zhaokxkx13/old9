package com.cn.iris.common.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Author: IrisNew
 * Description:文件上传公共类
 * Date: 2018/6/28 17:08
 */
public class FileUploadUtil {

    /**
     * @param file 上传请求资源
     * @param type 保存类型：0-临时保存，1-长期保存
     * @return 文件完全路径及名称
     * @throws IOException logger记录后抛出异常至调用层处理
     */
    public static String getFilePathName(MultipartFile file,int type) throws IOException{
        String fileName = getName(file);
        QiNiuResult result = QiniuUtil.upload(getBucketName(type),file.getBytes(),fileName);
        return getUrl(type)+result.getFileName();
    }

    /**
     * 创建文件名：采用UUID随机生成
     */
    private static String getName(MultipartFile file) {
        String[] data = file.getOriginalFilename().split("\\.");
        String type = data[data.length - 1];
        return UUID.randomUUID().toString() + "." + type;
    }

    /**
     * @param type 0-临时保存，1-长期保存
     * @return bucketName
     */
    private static String getBucketName(int type){
        String bucket = QiniuUtil.bucket_temp;
        if(type == 1){
            bucket = QiniuUtil.bucket_basic;
        }
        return bucket;
    }

    /**
     * 不同类型存储的空间不一样，所以返回的公网URL不一样
     * @param type 0-临时保存，1-长期保存
     * @return bucketName
     */
    private static String getUrl(int type){
        String url = QiniuUtil.temp_url;
        if(type == 1){
            url = QiniuUtil.basic_url;
        }
        return url;
    }

}
