package com.cn.iris.common.core.beetl;

import com.cn.iris.common.core.shiro.ShiroUtil;
import com.cn.iris.common.util.CommonUtil;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

public class BeetlConfiguration extends BeetlGroupUtilConfiguration {

    @Override
    public void initOther() {

        groupTemplate.registerFunctionPackage("shiro", new ShiroUtil());
        groupTemplate.registerFunctionPackage("tool", new CommonUtil());
//        groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());

    }

}
