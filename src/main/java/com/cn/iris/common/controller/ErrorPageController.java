package com.cn.iris.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Author: IrisNew
 * Description:错误页跳转
 * Date: 2018/1/15 10:49
 */
@Controller
public class ErrorPageController {

    @RequestMapping(value = "/400",method = RequestMethod.GET)
    public String error400() {
        return "error/400";
    }

    @RequestMapping(value = "/401",method = RequestMethod.GET)
    public String error401() {
        return "error/401";
    }

    @RequestMapping(value = "/404",method = RequestMethod.GET)
    public String error404() {
        return "error/404";
    }

    @RequestMapping(value = "/500",method = RequestMethod.GET)
    public String error500() {
        return "error/500";
    }

}
