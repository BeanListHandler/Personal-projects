package com.itzjy.dao;

import com.itzjy.domian.Article;
import com.itzjy.domian.Classification;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface Articledao {
    /**
     * 所有文章
     * @return
     */
    List<Article> getallArticle();

    /**
     * 总记录数
     * @param detachedCriteria
     * @return
     */
    Integer gettotalCount(DetachedCriteria detachedCriteria);

    /**
     * 分页数据
     * @param detachedCriteria
     * @param index
     * @param pageSize
     * @return
     */
    List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize);

    /**
     *  删除文章
     * @param article
     */
    void del(Article article);

    /**
     * 根据id获取分类
     * @param parentid
     * @return
     */
    List<Classification> getCategory(Integer parentid);

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
