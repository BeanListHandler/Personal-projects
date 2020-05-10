package com.itzjy.service.impl;

import com.itzjy.dao.logindao;
import com.itzjy.domian.User;
import com.itzjy.service.loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class loginserviceimpl implements loginservice {
    @Autowired
    private logindao logindao;
    @Override
    public User login(User user) {
        return  logindao.getuser(user.getUsername(),user.getPassword());
    }
}
