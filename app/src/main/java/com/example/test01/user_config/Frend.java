package com.example.test01.user_config;

import cn.bmob.v3.BmobObject;

/**
 * Frend说明：
 */
public class Frend extends BmobObject {
   private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser_frend() {
        return user_frend;
    }

    public void setUser_frend(User user_frend) {
        this.user_frend = user_frend;
    }

    private User user_frend;
}
