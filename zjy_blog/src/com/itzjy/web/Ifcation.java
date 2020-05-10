package com.itzjy.web;
import com.itzjy.domian.Classification;
import com.itzjy.service.Ifactionservice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.List;

@Controller("ifcation")
@Scope("prototype")
public class Ifcation extends ActionSupport implements ModelDriven<Classification> {
    private  Classification classification = new Classification();
    @Autowired
    private Ifactionservice ifactionservice;

    /* 添加分类*/
    public String add(){
        ifactionservice.sava(classification);
        return "listAction";
    }

    /* 遍历分类*/
    public String list(){
        List<Classification> list = ifactionservice.getall();
        //存入值栈
        ActionContext.getContext().getValueStack().set("list",list);
        return "list";
    }

    public String updateui() throws IOException {
        //调用业务层
        Classification getone = ifactionservice.getone(classification.getCid());
        System.out.println(getone+"web");
        //以json响应给页面
        JSONArray jsonArray = JSONArray.fromObject(getone, new JsonConfig());
        System.out.println("json"+jsonArray);
        //响应浏览器
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    public String update(){
        ifactionservice.updatec(classification);
        return "listAction";
    }


    public String delete(){
        ifactionservice.delete(classification);
        return "listAction";
    }
    @Override
    public Classification getModel() {
        return classification;
    }
}
