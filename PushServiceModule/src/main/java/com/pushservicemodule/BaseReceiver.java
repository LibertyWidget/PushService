package com.pushservicemodule;

import android.content.Context;

public abstract class BaseReceiver {
    //收到的消息
    protected abstract void onTextMessage(Context context, String message);

}
