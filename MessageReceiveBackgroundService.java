package com.example.hi.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Hi on 21-04-2017.
 */

public class MessageReceiveBackgroundService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String message=remoteMessage.getNotification().getBody();
        sendnotification(message);

        Log.e(">>>>>",message);

    }

    public void sendnotification(String message){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentTitle("first notification");
        builder.setContentText(message);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("New Notification");
        builder.setAutoCancel(true);

        Intent intent= new Intent(this,MainActivity.class);
        intent.putExtra("msg",message);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        Notification notification=builder.build();
        NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1,notification);
    }
}

