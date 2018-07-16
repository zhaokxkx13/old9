package com.cn.iris.common.util;

/**
 * Author: IrisNew
 * Description:七牛返回信息
 * Date: 2018/7/2 16:56
 */
public class QiNiuResult {

    private String fileName;
    private String hash;
    private String bucket;
    private Long fsize;
    private boolean suc;
    private String message;

    public QiNiuResult(boolean suc,String message) {
        super();
        this.suc = suc;
        this.message = message;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public Long getFsize() {
        return fsize;
    }

    public void setFsize(Long fsize) {
        this.fsize = fsize;
    }


    public boolean isSuc() {
        return suc;
    }

    public void setSuc(boolean suc) {
        this.suc = suc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
