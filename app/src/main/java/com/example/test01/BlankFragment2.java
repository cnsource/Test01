package com.example.test01;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test01.user_config.Post;
import com.example.test01.user_config.User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {


    public BlankFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        final Post post=new Post();
        final TextView tv=view.findViewById(R.id.conte);
        Button btnGet=view.findViewById(R.id.getinfo);
        Button btnRep=view.findViewById(R.id.report);
        btnRep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post.setTitle("第一篇发布的帖子");
                post.save(new SaveListener<String>() {//将帖子信息存储到服务器的表中
                    @Override
                    public void done(String s, BmobException e) {
                        if (e==null){
                            Toast.makeText(getContext(), "Successed report", Toast.LENGTH_SHORT).show();
                            update(post);//成功发布帖子，更新_User表中的post字段
                        }else{
                            Toast.makeText(getContext(), "fault report", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<User> queryUser=new BmobQuery();
                queryUser.addWhereEqualTo("username","aaa");
                //queryUser.include("post.title");
                queryUser.findObjects(new FindListener<User>() {
                    @Override
                    public void done(List<User> list, BmobException e) {
                        if (e==null&&list.size()>0){
                            tv.setText(list.get(0).getPost().getTitle());
                            final Post post1=list.get(0).getPost();
                            //quePost(post1,tv);
                            Toast.makeText(getContext(), "pwd:"+list.get(0).getObjectId().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return view;
    }

    private void quePost(Post post1,final TextView tv) {
        BmobQuery<Post> queryPost=new BmobQuery();
        queryPost.addQueryKeys("title");
        queryPost.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                Toast.makeText(getContext(), "list:"+list.size(), Toast.LENGTH_SHORT).show();
                if (e==null&&list.size()>0){
                    tv.setText(""+list.get(0).getTitle());
                }else{
                    tv.setText(""+e.getMessage());
                }
            }
        });
    }

    private void update(Post post) {
     if (BmobUser.isLogin()){
            User user=BmobUser.getCurrentUser(User.class);
            user.setPost(post);
            user.update(new UpdateListener() {//更新主表中的post字段
                @Override
                public void done(BmobException e) {
                    if (e==null){
                        Toast.makeText(getContext(), "Successed update", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Error update", Toast.LENGTH_SHORT).show();
                    }
                }
            });
     }
    }

}
