package com.itzjy.dao.impl;

import com.itzjy.dao.logindao;
import com.itzjy.domian.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class logindaoimpl extends HibernateDaoSupport implements logindao {
    @Autowired
    public void setMySessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }

    @Override
    public User getuser(String username, String password) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
        detachedCriteria.add(Restrictions.eq("username",username));
        detachedCriteria.add(Restrictions.eq("password",password));
        List<User> list = (List<User>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
        System.out.println(list);
        System.out.println(list.size());

        if (list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
