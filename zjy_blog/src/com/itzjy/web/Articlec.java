package com.itzjy.web;

import com.itzjy.domian.Article;
import com.itzjy.domian.Classification;
import com.itzjy.domian.PageBean;
import com.itzjy.service.Articleservice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import lombok.Setter;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Controller("article")
@Scope("prototype")
public class Articlec extends ActionSupport implements ModelDriven<Article> {
    @Autowired
    private Articleservice articleservice;
    private Article article = new Article();

    @Override
    public Article getModel() {
        return article;
    }

    public String list() {
        List<Article> articles = articleservice.getallArticle();
        ActionContext.getContext().getValueStack().set("articles", articles);
        return "list";
    }

    /*获取分页数据*/
    @Setter
    private Integer currPage = 1;
    @Setter
    private String sou;

    public String pagelist() {
        System.out.println(sou);
        //创建离线查询
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        //搜索功能
        if (sou != null) {
            //条件
            detachedCriteria.add(Restrictions.like("article_title", "%" + sou + "%"));
        }
        //调用业务层
        PageBean pageBean = articleservice.getpageData(detachedCriteria, currPage, 6);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "list";
    }

    public String del() {
        Article article2 = new Article();
        article2.setArticle_id(article.getArticle_id());
        articleservice.del(article);
        return "del";
    }

    @Setter
    private Integer parentid;

    public String getCategory() throws IOException {
        //根据id查询分类
        List<Classification> list = articleservice.getcAtegory(parentid);
        System.out.println("webbb" + list);
        //以json格式响应给页面
        JSONArray jsonArray = JSONArray.fromObject(list, new JsonConfig());
        System.out.println("json" + jsonArray);
        //响应浏览器
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return null;
    }

    //添文章
    @Setter
    private String uploadFileName;
    @Setter
    private File upload;
    @Setter
    private String uploadContentType;

    public String add() throws IOException {
        if (upload != null) {
            //获取扩展名
            int i = uploadFileName.lastIndexOf(".");
            String sub = uploadFileName.substring(i);
            //随机生成文件名拼接
            String uuid = UUID.randomUUID().toString();
            String uuidfile = uuid.replace("-", "") + sub;

            //上传路径
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            //凭借新路径
            File descfile1 = new File(path + "/" + uuidfile);
            System.out.println("文件路径" + descfile1);
            FileUtils.copyFile(upload, descfile1);
            //设置图片
            article.setArticle_pic(uuidfile);

        }
        //时间
        article.setArticle_time(System.currentTimeMillis());
        //存入数据库
        articleservice.save(article);
        System.out.println("上传文件" + article);
        return "del";
    }

    public String edit() {
        //获取文章
        Article articlel = articleservice.saveclass(article.getArticle_id());
        //存入值栈
        ActionContext.getContext().getValueStack().push(articlel);
        return "edit";
    }

    public String update() throws IOException {
        //判断是否上传图片
        if (upload != null) {
            //上传路径
            String path = ServletActionContext.getServletContext().getRealPath("/upload");
            //获取图片名
            String picname = article.getArticle_pic();
            //删除原图
            if (picname != null || "".equals(picname)) {
                File file = new File(path + picname);
                file.delete();
            }
            //获取扩展名
            int i = uploadFileName.lastIndexOf(".");
            String sub = uploadFileName.substring(i);
            //随机生成文件名拼接
            String uuid = UUID.randomUUID().toString();
            String uuidfile = uuid.replace("-", "") + sub;


            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            //凭借新路径
            File descfile1 = new File(path + "/" + uuidfile);
            System.out.println("文件路径" + descfile1);
            FileUtils.copyFile(upload, descfile1);
            //设置图片
            article.setArticle_pic(uuidfile);
        }
        //时间
        article.setArticle_time(System.currentTimeMillis());
        articleservice.update(article);


        return "del";
    }


}