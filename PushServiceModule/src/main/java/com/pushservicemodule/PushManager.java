package com.pushservicemodule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.XGPushManager;

public class PushManager {
    public BaseReceiver mBaseReceiver;
    private static PushManager ins;

    PushManager() {

    }

    public synchronized static PushManager $() {
        if (null == ins) {
            ins = new PushManager();
        }
        return ins;
    }

    public void init(Context context, boolean debug) {
        //打开第三方推送
        XGPushConfig.enableOtherPush(context, true);
        //huaWei
        XGPushConfig.setHuaweiDebug(debug);
        //注册数据更新监听器
        MsgReceiver updateListViewReceiver = new MsgReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.xgdemo.activity.UPDATE_LISTVIEW");
        context.registerReceiver(updateListViewReceiver, intentFilter);
          /*
        注册信鸽服务的接口
        如果仅仅需要发推送消息调用这段代码即可
        */
        XGPushManager.registerPush(context,
                new XGIOperateCallback() {
                    @Override
                    public void onSuccess(Object data, int flag) {
                        Log.e("tag", "+++ register push sucess. token:" + data + "flag" + flag);
                    }

                    @Override
                    public void onFail(Object data, int errCode, String msg) {
                        Log.e("tag",
                                "+++ register push fail. token:" + data
                                        + ", errCode:" + errCode + ",msg:"
                                        + msg);
                    }
                });
        XGPushConfig.getToken(context);
    }

    public class MsgReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("tag", "123");
        }
    }

    public void initXiaoMi(Context context, String app_id, String app_key) {
        //小米
        XGPushConfig.setMiPushAppId(context, app_id);
        XGPushConfig.setMiPushAppKey(context, app_key);
    }

    public void initMz(Context context, String app_id, String app_key) {
        //设置魅族APPID和APPKEY
        XGPushConfig.setMzPushAppId(context, app_id);
        XGPushConfig.setMzPushAppKey(context, app_key);
    }

    public void setReceiver(BaseReceiver baseReceiver) {
        this.mBaseReceiver = baseReceiver;
    }
}
