package com.future.bruceyin.lovemodu.controller.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;

import com.future.bruceyin.lovemodu.controller.activity.ChatActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

import java.util.List;

/**
 * Created by admin on 2017/5/11.
 */

//会话列表页面
public class ChatFragment extends EaseConversationListFragment {


    @Override
    protected void initView() {
        super.initView();

        //跳转到会话详情页面
        setConversationListItemClickListener(new EaseConversationListItemClickListener() {
            @Override
            public void onListItemClicked(EMConversation conversation) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);

                //传递参数
                intent.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId());

                //是否是群聊
                if (conversation.getType() == EMConversation.EMConversationType.GroupChat) {
                    intent.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
                }

                startActivity(intent);
            }
        });
        //  清空集合数据
        conversationList.clear();

        //监听会话消息
        EMClient.getInstance().chatManager().addMessageListener(emMessageListener);

    }

    private EMMessageListener emMessageListener = new EMMessageListener() {
        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //设置数据
            EaseUI.getInstance().getNotifier().onNewMesg(list);

            //刷新页面
            refresh();

        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageReadAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageDeliveryAckReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    };
}
