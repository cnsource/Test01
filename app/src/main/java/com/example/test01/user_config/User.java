package com.example.test01.user_config;

import cn.bmob.v3.BmobUser;

/**
 * User说明：
 */
public class User extends BmobUser {
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    private Post post;
}
