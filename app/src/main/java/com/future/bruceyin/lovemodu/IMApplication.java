package com.future.bruceyin.lovemodu;

import android.app.Application;
import android.content.Context;

import com.future.bruceyin.lovemodu.model.Model;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;

import java.util.ConcurrentModificationException;

/**
 * Created by Bruce Yin on 2017/5/6.
 */
public class IMApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化EaseUI
        EMOptions options = new EMOptions();
        options.setAcceptInvitationAlways(false);//需要同意后才能接受邀请
        options.setAutoAcceptGroupInvitation(false);//设置需要同意后才能接受群邀请
        EaseUI.getInstance().init(this, options);

        //初始化数据模型层类
        Model.getInstance().init(this);
        //初始化全局上下问对象
        mContext = this;
    }

    //获取全局上下文对象
    public static Context getGlobalApplication() {
        return mContext;
    }
}
