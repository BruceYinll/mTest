package com.future.bruceyin.lovemodu.controller.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.RadioGroup;

import com.future.bruceyin.lovemodu.R;
import com.future.bruceyin.lovemodu.controller.fragment.ChatFragment;
import com.future.bruceyin.lovemodu.controller.fragment.ContactListFragment;
import com.future.bruceyin.lovemodu.controller.fragment.SettingFragment;
//主页面
public class MainActivity extends FragmentActivity {
    private RadioGroup rg_main;
    private SettingFragment settingFragment;
    private ContactListFragment contactListFragment;
    private ChatFragment chatFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();

        initListener();
    }

    private void initListener() {
        //RadioGroup的选择时间
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                Fragment fragment = null;
                switch (checkedId) {
                    //会话列表页面
                    case R.id.rb_main_chat:
                        fragment = chatFragment;
                        break;
                    //联系人页面
                    case R.id.rb_main_contact:
                        fragment = contactListFragment;
                        break;
                    //设置页面
                    case R.id.rb_main_setting:
                        fragment = settingFragment;
                        break;
                }

                //实现fragment的切换方法
                switchFragment(fragment);
            }
        });

        //默认选择会话列表
        rg_main.check(R.id.rb_main_chat);
    }

    //实现fragment的切换方法
    private void switchFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_main, fragment).commit();
    }

    private void initData() {
        //创建三个fragment对象
        chatFragment = new ChatFragment();
        contactListFragment = new ContactListFragment();
        settingFragment = new SettingFragment();
    }

    private void initView() {
        rg_main = (RadioGroup) findViewById(R.id.rg_main);
    }
}
