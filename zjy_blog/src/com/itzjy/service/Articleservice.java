package com.itzjy.service;

import com.itzjy.domian.Article;
import com.itzjy.domian.Classification;
import com.itzjy.domian.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface Articleservice {
    /**
     * 所有文章
     */
    List<Article>  getallArticle();

    /**
     * 获取pagebean
     * @param detachedCriteria
     * @param currPage
     * @param pageSize
     * @return
     */
    PageBean getpageData(DetachedCriteria detachedCriteria, Integer currPage, int pageSize);

    /**
     * 删除文章
     * @param article
     */
    void del(Article article);

    /**
     * 根据id获取分类
     * @return
     */

    List<Classification> getcAtegory(Integer parentid);

    /**
     * 保存文章
     * @param article
     */
    void save(Article article);

    /**
     * 查询文章
     * @param article_id
     * @return
     */
    Article saveclass(Integer article_id);

    //修改文章
    void update(Article article);
}
