package com.example.test01;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test01.user_config.Frend;
import com.example.test01.user_config.User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment3 extends Fragment {
    private List<User> frendList = new ArrayList<>();
    private ListView list;

    public BlankFragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_blank_fragment3, container, false);
        list = view.findViewById(R.id.lis);
        Button btn = view.findViewById(R.id.getFrend);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobQuery<Frend> frand = new BmobQuery<>();
                User user =BmobUser.getCurrentUser(User.class);

                frand.addWhereEqualTo("user",user);
                frand.include("user_frend");
                frand.findObjects(new FindListener<Frend>() {
                    @Override
                    public void done(List<Frend> list, BmobException e) {
                        if (list!=null)
                        Log.i("Frand_List:",list.size()+"----------");
                        else
                            Toast.makeText(getContext(), "Null", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        list.setAdapter(new BaseAdapter() {

            @Override
            public int getCount() {
                return frendList.size();
            }

            @Override
            public User getItem(int position) {
                return frendList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view1;
                if (convertView == null) {
                    view1 = LayoutInflater.from(getContext()).inflate(R.layout.layout_list, container, false);
                    TextView tvl = view1.findViewById(R.id.list_tv);
                    tvl.setText(getItem(position).getUsername());
                }
                return view;
            }
        });

        return view;
    }

}
