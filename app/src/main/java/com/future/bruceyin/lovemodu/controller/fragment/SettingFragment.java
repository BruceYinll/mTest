package com.future.bruceyin.lovemodu.controller.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.future.bruceyin.lovemodu.R;
import com.future.bruceyin.lovemodu.controller.activity.LoginActivity;
import com.future.bruceyin.lovemodu.model.Model;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

/**
 * Created by admin on 2017/5/11.
 */

public class SettingFragment extends Fragment {
    private Button bt_setting_out;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_setting, null);

        initView(view);

        return view;
    }

    private void initView(View view) {
        bt_setting_out = (Button) view.findViewById(R.id.bt_setting_out);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    private void initData() {
        //首先在button上显示当前用户的名称
        bt_setting_out.setText("退出登录(" + EMClient.getInstance().getCurrentUser() + ")");

        //退出登录的逻辑处理
        bt_setting_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
                    @Override
                    public void run() {
                        //登录环信服务器退出登录
                        EMClient.getInstance().logout(false, new EMCallBack() {
                            @Override
                            public void onSuccess() {

                                //关闭DBHelper
                                Model.getInstance().getDbManager().close();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //回到登录页面
                                        Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
                                        //更新UI显示
                                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                                        startActivity(intent);

                                        getActivity().finish();
                                    }
                                });

                            }

                            @Override
                            public void onError(int i, String s) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "退出失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onProgress(int i, String s) {

                            }
                        });
                    }
                });
            }
        });
    }
}
