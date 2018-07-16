package com.cn.iris.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONException;

import java.util.HashMap;

/**
 * Author: IrisNew
 * Description:百度Ocr文字识别
 * Date: 2018/5/8 15:23
 */
public class AipOcrUtil {
    //设置APPID/AK/SK
    private static final String APP_ID = "11204721";
    private static final String API_KEY = "G2z3eUSPxuPHi9Vf6HNq7Oqe";
    private static final String SECRET_KEY = "W70aGAQNcLjWufdTKuYQsd4vcUokWj0n";

    public static String UploadFile(String path) throws JSONException {
        // 初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        String resultStr = "";
        org.json.JSONObject res = client.basicGeneralUrl(path, new HashMap<String, String>());
        JSONObject jsonObject = JSONObject.parseObject(res.toString());
        JSONArray resultList = jsonObject.getJSONArray("words_result");
        for(Object result:resultList){
            JSONObject temp = (JSONObject) result;
            resultStr += temp.getString("words")+"<br/>";
        }
        return resultStr;
    }

}
