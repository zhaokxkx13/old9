package com.cn.iris.admin.controller;

import com.cn.iris.common.util.AipOcrUtil;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.ResWriteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Author: IrisNew
 * Description:文字识别OCR
 * Date: 2018/5/8 10:39
 */
@Controller
@RequestMapping("/ocr")
public class OcrController {

    private Logger logger = LoggerFactory.getLogger(OcrController.class);

    //index页面
    @GetMapping("/index")
    public String deptIndex() {
        return "admin/ocr/index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public void savePic(HttpServletRequest request, HttpServletResponse response){
        AjaxRetBean<Object> returnBean =  new AjaxRetBean<> ();
        try {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            String picture = "";
            MultipartHttpServletRequest muRequest = (MultipartHttpServletRequest)request;
            Map<String, MultipartFile> files = muRequest.getFileMap();//得到文件map对象
            String uploadUrl = request.getSession().getServletContext().getRealPath("/")+"upload/";//得到当前工程路径拼接上文件名
            File dir = new File(uploadUrl);
            if(!dir.exists())//目录不存在则创建
                dir.mkdirs();
            for(MultipartFile file :files.values()){
                String fileName= new Date().getTime() + "_" + file.getOriginalFilename();
                picture = uploadUrl+fileName;
                File upFile = new File(uploadUrl+fileName);//创建文件对象
                if(!upFile.exists()){//文件名不存在 则新建文件，并将文件复制到新建文件中
                        upFile.createNewFile();
                        file.transferTo(upFile);
                }
            }
            String str = AipOcrUtil.UploadFile(picture);

            returnBean.setSuccess(true);
            returnBean.setData(str);
        } catch (IOException e) {
            logger.error("文件上传错误："+e.getMessage());
        } catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }


}
