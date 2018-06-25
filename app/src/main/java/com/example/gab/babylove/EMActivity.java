package com.example.gab.babylove;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fy.baselibrary.application.IBaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

/**
 * Created by 初夏小溪 on 2018/6/25 0025.
 */
public class EMActivity extends AppCompatActivity implements IBaseActivity {

    @Override
    public boolean isShowHeadView() {
        return false;
    }

    @Override
    public int setView() {
        return 0;
    }

    @Override
    public void setStatusBar(Activity activity) {

    }

    @Override
    public void initData(Activity activity, Bundle savedInstanceState) {
        //创建一条文本消息，content为消息文字内容，toChatUsername为对方用户或者群聊的id，后文皆是如此
//        EMMessage message = EMMessage.createTxtSendMessage(content, toChatUsername);
//        //如果是群聊，设置chattype，默认是单聊
//        if (chatType == CHATTYPE_GROUP)
//            message.setChatType(EMMessage.ChatType.GroupChat);
//        //发送消息
//        EMClient.getInstance().chatManager().sendMessage(message);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void reTry() {

    }
}
