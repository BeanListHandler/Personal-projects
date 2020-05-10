package com.itzjy.web;

import com.itzjy.domian.User;
import com.itzjy.service.loginservice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


@Controller("loginaction")
@Scope("prototype")
public class Loginaction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Autowired
    private loginservice loginservice;

    @Override
    public User getModel() {
        return user;
    }

    //登录
    public String login() {

        User login = loginservice.login(user);
        if (login == null) {
            //错误信息
            this.addActionError("用户名或密码错误");
            return LOGIN;
        } else {
            //保存用户信息session
            ActionContext.getContext().getSession().put("login", login);
            return SUCCESS;
        }
        //登录业务逻辑
    }

    //退出
    public String loginexit() {
        ActionContext.getContext().getSession().remove("login");
        return "loginexit";
    }


}
