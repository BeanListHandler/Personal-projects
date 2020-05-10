package com.itzjy.service;

import com.itzjy.domian.User;

public interface loginservice {
    /**
     * 后台登录
     * @param user
     * @return
     */

    public User login(User user);
}
