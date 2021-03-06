package com.example.sadmansakib.todo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    private String title,details;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent it =  new Intent(context, MainActivity.class);
        title=intent.getStringExtra("TITLE");
        details=intent.getStringExtra("DETAILS");
        createNotification(context, it, "New Message", title);

    }
    public void createNotification(Context context, Intent intent, CharSequence ticker, CharSequence title){

        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent p = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        //builder.setContentText(descricao);
        builder.setSmallIcon(R.mipmap.ic_stat_onesignal_default);
        builder.setContentIntent(p);
        Notification n = builder.build();
        //create the notification
        n.vibrate = new long[]{150, 300, 150, 400};
        n.flags = Notification.FLAG_AUTO_CANCEL;

        nm.notify(R.mipmap.ic_stat_onesignal_default, n);

        Log.d("###ALARM:","Alarm is found@ " + System.currentTimeMillis());

        //create a vibration
        try{

            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }



}