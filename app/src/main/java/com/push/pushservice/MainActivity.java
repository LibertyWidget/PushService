package com.push.pushservice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pushservicemodule.BaseReceiver;
import com.pushservicemodule.PushManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushManager.$().init(this, true);
        PushManager.$().initMz(MainActivity.this, "1003644", "71bad480d4834f4d97633065643feb6e");
        PushManager.$().setReceiver(new BaseReceiver() {
            @Override
            protected void onTextMessage(Context context, String message) {
                Log.e("tag", "mess->>" + message);
            }
        });
    }
}
