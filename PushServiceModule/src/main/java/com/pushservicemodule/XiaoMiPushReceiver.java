package com.pushservicemodule;

import android.content.Context;

import com.xiaomi.mipush.sdk.MiPushMessage;
import com.xiaomi.mipush.sdk.PushMessageReceiver;

public class XiaoMiPushReceiver extends PushMessageReceiver {
    @Override
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
        super.onReceiveMessage(context, miPushMessage);
        BaseReceiver baseReceiver = PushManager.$().mBaseReceiver;
        if (null != baseReceiver)
            baseReceiver.onTextMessage(context, miPushMessage.getTopic());
    }
}
