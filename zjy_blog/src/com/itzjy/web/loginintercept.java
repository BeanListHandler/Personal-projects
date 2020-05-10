package com.itzjy.web;

import com.itzjy.domian.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.ServletActionContext;

public class loginintercept extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("拦截");
        User login = (User) ServletActionContext.getRequest().getSession().getAttribute("login");
        if(login == null){
            ActionSupport action = (ActionSupport) actionInvocation.getAction();
            action.addActionError("没有登录,无权访问");
            return "login";
        }else {
            //放行
            return actionInvocation.invoke();
        }
    }
}
