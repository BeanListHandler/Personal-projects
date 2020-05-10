package com.itzjy.dao.impl;

import com.itzjy.dao.Articledao;
import com.itzjy.domian.Article;
import com.itzjy.domian.Classification;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Articledaoimpl extends HibernateDaoSupport implements Articledao {
    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public List<Article> getallArticle() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        List<Article> list = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return list;
    }

    @Override
    public Integer gettotalCount(DetachedCriteria detachedCriteria) {
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> byCriteria = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (byCriteria.size() > 0) {
            return byCriteria.get(0).intValue();
        }
        return 0;
    }

    @Override
    public List<Article> getPageData(DetachedCriteria detachedCriteria, Integer index, Integer pageSize) {
        //清空查询总记录数的条件
        detachedCriteria.setProjection(null);
        return (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria, index, pageSize);
    }

    @Override
    public void del(Article article) {
        this.getHibernateTemplate().delete(article);
    }

    @Override
    public List<Classification> getCategory(Integer parentid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Classification.class);
        detachedCriteria.add(Restrictions.eq("parentid", parentid));

        List<Classification> byCriteria = (List<Classification>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        return byCriteria;
    }

    @Override
    public void save(Article article) {
        this.getHibernateTemplate().save(article);
    }

    @Override
    public Article saveclass(Integer article_id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Article.class);
        detachedCriteria.add(Restrictions.eq("article_id", article_id));

        List<Article> byCriteria = (List<Article>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (byCriteria.size() > 0){
            return byCriteria.get(0);
        }
        return null;

    }

    @Override
    public void update(Article article) {
        this.getHibernateTemplate().update(article);
    }
}
