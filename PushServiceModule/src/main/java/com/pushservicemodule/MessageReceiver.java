package com.pushservicemodule;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageReceiver extends XGPushBaseReceiver {

    @Override
    public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult xgPushRegisterResult) {
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            Log.e("tag", "注册成功");
        } else {
            Log.e("tag", "注册失败错误码：" + errorCode);
        }
    }

    @Override
    public void onUnregisterResult(Context context, int errorCode) {
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            Log.e("tag", "反注册成功");
        } else {
            Log.e("tag", "反注册失败：" + errorCode);
        }
    }

    //设置tag的回调
    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            Log.e("tag", tagName + " 设置成功");
        } else {
            Log.e("tag", tagName + " 设置失败,错误码：" + errorCode);
        }
    }

    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            Log.e("tag", "删除" + tagName + " 成功");
        } else {
            Log.e("tag", "删除" + tagName + " 失败,错误码：" + errorCode);
        }
    }

    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {
        String text = "收到消息:" + message.toString();
        Log.e("tag", text);
        BaseReceiver baseReceiver = PushManager.$().mBaseReceiver;
        if (null != baseReceiver)
            baseReceiver.onTextMessage(context, message.toString());
    }

    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {
        Log.e("LC", "+++++++++++++++ 通知被点击 跳转到指定页面。");
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();
        if (context == null || message == null) {
            return;
        }
        String text = "";
        if (message.getActionType() == XGPushClickedResult.NOTIFACTION_CLICKED_TYPE) {
            // 通知在通知栏被点击啦。。。。。
            // APP自己处理点击的相关动作
            // 这个动作可以在activity的onResume也能监听，请看第3点相关内容
            text = "通知被打开 :" + message;
        } else if (message.getActionType() == XGPushClickedResult.NOTIFACTION_DELETED_TYPE) {
            // 通知被清除啦。。。。
            // APP自己处理通知被清除后的相关动作
            text = "通知被清除 :" + message;
        }
        Toast.makeText(context, "广播接收到通知被点击:" + message.toString(), Toast.LENGTH_SHORT).show();
        // 获取自定义key-value
        String customContent = message.getCustomContent();
        if (customContent != null && customContent.length() != 0) {
            try {
                JSONObject obj = new JSONObject(customContent);
                // key1为前台配置的key
                if (!obj.isNull("key")) {
                    String value = obj.getString("key");
                    Log.e("tag", "get custom value:" + value);
                }
                // ...
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        // APP自主处理的过程。。。
        Log.e("tag", text);
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {
        if (context == null || notifiShowedRlt == null) {
            return;
        }
        Log.e("tag", notifiShowedRlt.toString());
    }
}
