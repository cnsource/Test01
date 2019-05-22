package com.example.test01.user_config;

import cn.bmob.v3.BmobObject;

/**
 * post说明：
 */
public class Post extends BmobObject {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
