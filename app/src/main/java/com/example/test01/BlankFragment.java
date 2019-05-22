package com.example.test01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.test01.user_config.User;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        EditText Cnt=view.findViewById(R.id.cnt);
        EditText Pwd=view.findViewById(R.id.pwd);
        Button Login=view.findViewById(R.id.login);
        Button Reg=view.findViewById(R.id.register);
        final User user=new User();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.loginByAccount("qwe", "qwe", new LogInListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e==null){
                            Toast.makeText(getContext(), "successed", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    user.setUsername("qwe");
                    user.setPassword("ewq");
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e==null){
                                Toast.makeText(getContext(), "register successed", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(getContext(), "refgister error:"+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
        return view;
    }

}
