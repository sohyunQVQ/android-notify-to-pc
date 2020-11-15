package com.abigeater.com.notifylistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.abigeater.com.notifylistener.SendService;

public class SmsService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String format = intent.getStringExtra("format");
        if (null != bundle) {
            Object[] smsObj = (Object[]) bundle.get("pdus");
            for (Object object : smsObj) {

                SmsMessage smsMessage=SmsMessage.createFromPdu((byte [])object, format);
                String sender=smsMessage.getDisplayOriginatingAddress();
                String content=smsMessage.getMessageBody();

                if(content != null){
                    SendService.send(sender, content);
                    Log.i("test",content);
                }
            }
        }
    }
}
