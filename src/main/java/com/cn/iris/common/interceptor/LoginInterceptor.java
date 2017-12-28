package com.cn.iris.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cn.iris.admin.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: IrisNew
 * Description:拦截器
 * Date: 2017/12/14 14:02
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

	//在请求处理之前进行调用（Controller方法调用之前）
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Object user = request.getSession().getAttribute("iris_user");
		if (user == null || !(user instanceof User)) {
			response.sendRedirect(request.getContextPath() + "/welcome");
			return false;
		}
		return true;
	}

	//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		// do nothing 不处理
	}

	//在整个请求结束之后被调用
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception modelAndView)
			throws Exception {
		// do something 不处理
	}
}
