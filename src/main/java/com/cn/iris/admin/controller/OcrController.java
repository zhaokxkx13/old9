package com.cn.iris.admin.controller;

import com.cn.iris.common.util.AipOcrUtil;
import com.cn.iris.common.util.AjaxRetBean;
import com.cn.iris.common.util.FileUploadUtil;
import com.cn.iris.common.util.ResWriteUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public void savePic(@RequestParam("file") String file, HttpServletResponse response){
        AjaxRetBean<Object> returnBean =  new AjaxRetBean<> ();
        try {
            String str = AipOcrUtil.UploadFile(file);
            returnBean.setSuccess(true);
            returnBean.setData(str);
        } catch (Exception e){
            logger.error(e.getMessage());
            returnBean.setSuccess(false);
            returnBean.setMessage(e.getMessage());
        }
        ResWriteUtil.writeObject(response,returnBean);
    }


}
