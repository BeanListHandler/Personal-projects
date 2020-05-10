package com.itzjy.service.impl;

import com.itzjy.dao.Articledao;
import com.itzjy.domian.Article;
import com.itzjy.domian.Classification;
import com.itzjy.domian.PageBean;
import com.itzjy.service.Articleservice;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class Articleserviceimpl implements Articleservice {

    @Autowired
    private Articledao articledao;

    @Override
    public List<Article> getallArticle() {
        return articledao.getallArticle();
    }

    @Override
    public PageBean getpageData(DetachedCriteria detachedCriteria, Integer currPage, int pageSize) {
        PageBean<Article> pageBean = new PageBean<Article>();
        //设置当前页
        pageBean.setCurrentPage(currPage);
        //设置当前页有多少条数据
        pageBean.setPageSize(pageSize);
        //获取总记录数从数据库查询
        Integer totalCount = articledao.gettotalCount(detachedCriteria);
        pageBean.setTotalCount(totalCount);
        //设置总页数
        pageBean.setTotalPage(pageBean.getTotalCount());
        //设置数据当前页数据 查询数据库
        List<Article> list = articledao.getPageData(detachedCriteria, pageBean.getIndex(), pageBean.getPageSize());
        //计算
        pageBean.setList(list);
        System.out.println(pageBean);
        return pageBean;
    }

    @Override
    public void del(Article article) {
        articledao.del(article);
    }

    @Override
    public List<Classification> getcAtegory(Integer parentid) {
        return  articledao.getCategory(parentid);

    }

    @Override
    public void save(Article article) {
        articledao.save(article);
    }

    @Override
    public Article saveclass(Integer article_id) {
        return articledao.saveclass(article_id);
    }

    @Override
    public void update(Article article) {
        articledao.update(article);
    }
}
