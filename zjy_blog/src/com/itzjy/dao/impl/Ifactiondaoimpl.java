package com.itzjy.dao.impl;

import com.itzjy.dao.Ifactiondao;
import com.itzjy.domian.Classification;
import com.itzjy.domian.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Ifactiondaoimpl extends HibernateDaoSupport implements Ifactiondao {
    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public void save(Classification classification) {
        this.getHibernateTemplate().save(classification);
    }

    @Override
    public List<Classification> list() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Classification.class);
        return (List<Classification>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
    }

    @Override
    public Classification getonegor(Integer cid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Classification.class);

        detachedCriteria.add(Restrictions.eq("cid",cid));
        List<Classification> byCriteria = (List<Classification>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        if (list().size()>0){
            System.out.println(byCriteria.get(0));
            return byCriteria.get(0);
        }
        return null;
    }

    @Override
    public void updatec(Classification classification) {
        this.getHibernateTemplate().update(classification);

    }

    @Override
    public void delete(Classification classification) {
        this.getHibernateTemplate().delete(classification);
    }
}
