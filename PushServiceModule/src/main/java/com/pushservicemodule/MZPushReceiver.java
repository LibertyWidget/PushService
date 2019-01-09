package com.pushservicemodule;

import android.content.Context;
import android.util.Log;

import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public class MZPushReceiver extends MzPushMessageReceiver {

    @Override
    public void onRegister(Context context, String s) {
        Log.e("tag", "onRegister");

    }

    @Override
    public void onMessage(Context context, String s) {
        BaseReceiver baseReceiver = PushManager.$().mBaseReceiver;
        if (null != baseReceiver)
            baseReceiver.onTextMessage(context, s);
        Log.e("tag", "onMessage" + s);
    }

    @Override
    public void onUnRegister(Context context, boolean b) {
        Log.e("tag", "onUnRegister");
    }

    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus) {
        Log.e("tag", "onPushStatus");
    }

    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus) {
        Log.e("tag", "onRegisterStatus");
    }

    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus) {
        Log.e("tag", "onUnRegisterStatus");
    }

    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus) {
        Log.e("tag", "onSubTagsStatus");
    }

    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus) {
        Log.e("tag", "onSubAliasStatus");
    }
}
