package com.itzjy.dao;

import com.itzjy.domian.User;

public interface logindao {
    public User getuser(String usernmae,String password);
}
