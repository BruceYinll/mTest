package com.future.bruceyin.lovemodu.controller.activity;

//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//
//import com.future.bruceyin.lovemodu.R;
//import com.future.bruceyin.lovemodu.model.Model;
//import com.hyphenate.chat.EMClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.future.bruceyin.lovemodu.R;
import com.future.bruceyin.lovemodu.model.Model;
import com.future.bruceyin.lovemodu.model.bean.UserInfo;
import com.hyphenate.chat.EMClient;

//欢迎页面
public class SplashActivity extends Activity {

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
//            如果当前activity已经退出，就不处理handler中的消息
            if (isFinishing()) {
                return;
            }

            //判断进入主页面还是登陆页面
            toMainOrLogin();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //        发送2s的延时时间
        handler.sendMessageDelayed(Message.obtain(), 2000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private void toMainOrLogin() {
//        new Thread() {
//            public void run() {
//
//            }
//        }.start();
        //利用线程池  单例模式获取线程并执行
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //判断当前账号是否已经·登陆过
                if (EMClient.getInstance().isLoggedInBefore()) {//登陆过
                    //获取到当前登陆用户的信息
                    UserInfo account = Model.getInstance().getUserAccountDao().getAccountByHxid(EMClient.getInstance().getCurrentUser());

                    if (account == null) {
                        //跳转到主界面
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        //登陆成功后的方法
                        Model.getInstance().loginSuccess(account);
                        //跳转到主界面
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                } else {//没登陆过
                    //跳转到登陆页面
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                //结束当前页面
                finish();
            }
        });
    }
}
