package com.cn.iris.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Author: IrisNew
 * Description:将数据写入返回
 * Date: 2017/12/28 13:36
 */
public class ResWriteUtil {

    private static Logger logger = LoggerFactory.getLogger(ResWriteUtil.class);

    public static <T> void writeObject(HttpServletResponse response, T t){
        try {
            response.setContentType("text/x-json;charset=UTF-8");
            logger.info(JSON.toJSONString(t,SerializerFeature.WriteMapNullValue));
            response.getWriter().write(JSON.toJSONString(t,SerializerFeature.WriteMapNullValue));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
