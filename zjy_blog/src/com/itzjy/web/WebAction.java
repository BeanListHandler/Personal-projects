package com.itzjy.web;

import com.itzjy.domian.Article;
import com.itzjy.domian.Classification;
import com.itzjy.domian.PageBean;
import com.itzjy.service.Articleservice;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Setter;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller("webaction")
@Scope("prototype")
public class WebAction extends ActionSupport {
    @Autowired
    private Articleservice articleservice;

    /*获取分页数据*/
    @Setter
    private Integer currPage = 1;
    @Setter
    private String sou;
    @Setter
    private Integer params;
    @Setter
    private Integer cid;
    public void getList() throws IOException {

        //创建离线查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);

        if (params !=null ){
            List<Classification> classifications = articleservice.getcAtegory(params);
            //构建数组
           Object[] cidd = new Object[classifications.size()];
            for (int i = 0; i < classifications.size(); i++){
                Classification classification = classifications.get(i);
                cidd[i] = classification.getCid();
            }
            System.out.println(Arrays.toString(cidd));
            detachedCriteria.add(Restrictions.in("classification.cid",cidd));
        }

        if (cid != null){
            detachedCriteria.add(Restrictions.eq("classification.cid",cid));
        }

        //搜索功能
        PageBean pageBean = articleservice.getpageData(detachedCriteria, currPage, 6);
//        System.out.println("sssss"+pageBean);
        //以json返回给前端
        JsonConfig jsonConfig = new JsonConfig();
        //hibernate 延时加载
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        JSONObject jsonArray = JSONObject.fromObject(pageBean, jsonConfig);
        //响应
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());

    }


    //根据id获取指定文章
    @Setter
    private Integer id;
    public void  getDetail() throws IOException {
        Article saveclass = articleservice.saveclass(id);

        JsonConfig jsonConfig = new JsonConfig();
        //hibernate 延时加载
        jsonConfig.setExcludes(new String[]{"handler", "hibernateLazyInitializer"});
        JSONObject jsonArray = JSONObject.fromObject(saveclass, jsonConfig);
        //响应
        ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
    }

}
