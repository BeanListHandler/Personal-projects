package com.itzjy.web;

import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * @author 赵佳洋
 */
@Controller("jump")
@Scope("prototype")
public class jump extends ActionSupport {
    public String top() {
        return "top";
    }

    public String left() {
        return "left";
    }

    public String account() {
        return "account";
    }

    public String add() {
        return "add";
    }
}
