package com.application.volumewidget;

import android.annotation.TargetApi;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

public class VolumeWidgetApp2 extends AppWidgetProvider {

    private static final String button_multi = "button_multi";
    private static final String button_phone = "button_phone";
    private static final String button_alarm = "button_alarm";

	int[] appWidgetIdss;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        
        appWidgetIdss = appWidgetIds;
        

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_widget2);

            // Initialize value of button how system
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

            views.setOnClickPendingIntent(R.id.imageButtonMulti2, getPendingSelfIntent(context, button_multi));
            views.setOnClickPendingIntent(R.id.imageButtonPhone2, getPendingSelfIntent(context, button_phone));
            views.setOnClickPendingIntent(R.id.imageButtonAlarm2, getPendingSelfIntent(context, button_alarm));

            updateImageButtonStatus(context, views, audioManager);


            // Tell the AppWidgetManager to perform an update on the current app widget
//            appWidgetManager.updateAppWidget(appWidgetId, views);
            ComponentName componentName= new ComponentName(context, VolumeWidgetApp2.class);
    		AppWidgetManager.getInstance(context).updateAppWidget(componentName, views);
        }
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        String[]  array = intent.getAction().split("_");
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_widget2);

       if( array[0].equals("button") ) {
            if( array[1].equals("multi") ){
                //Puts volume to max
                if( audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0 ){
                    views.setInt(R.id.imageButtonMulti2, "setBackgroundResource", R.drawable.multi);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                            audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                            , AudioManager.FLAG_PLAY_SOUND);
                }else{
                    views.setInt(R.id.imageButtonMulti2, "setBackgroundResource", R.drawable.multi_off);
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0
                            , AudioManager.FLAG_PLAY_SOUND);
                }

            } else if( array[1].equals("phone") ) {
                //Puts volume to max
                if( audioManager.getStreamVolume(AudioManager.STREAM_RING) == 0 ){
                    views.setInt(R.id.imageButtonPhone2, "setBackgroundResource", R.drawable.phone);
                    audioManager.setStreamVolume(AudioManager.STREAM_RING,
                            audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
                            , AudioManager.FLAG_PLAY_SOUND);
                }else{
                    views.setInt(R.id.imageButtonPhone2, "setBackgroundResource", R.drawable.phone_off);
                    audioManager.setStreamVolume(AudioManager.STREAM_RING, 0
                            , AudioManager.FLAG_PLAY_SOUND);
                }

            } else if( array[1].equals("alarm") ) {
                //Puts volume to max
                if( audioManager.getStreamVolume(AudioManager.STREAM_ALARM) == 0 ){
                    views.setInt(R.id.imageButtonAlarm2, "setBackgroundResource", R.drawable.alarm);
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                            audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
                            , AudioManager.FLAG_PLAY_SOUND);
                }else{
                    views.setInt(R.id.imageButtonAlarm2, "setBackgroundResource", R.drawable.alarm_off);
                    audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0
                            , AudioManager.FLAG_PLAY_SOUND);
                }
            }
        }

        updateImageButtonStatus(context, views, audioManager);

        ComponentName componentName= new ComponentName(context, VolumeWidgetApp2.class);
		AppWidgetManager.getInstance(context).updateAppWidget(componentName, views);
    }


    /**
     * Update the background of the image button of the application
     */
    private void updateImageButtonStatus(Context context, RemoteViews views, AudioManager audioManager) {
        //MultiVolume
        if (audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0) {
            views.setInt(R.id.imageButtonMulti2, "setBackgroundResource", R.drawable.multi_off);
        } else {
            views.setInt(R.id.imageButtonMulti2, "setBackgroundResource", R.drawable.multi);
        }

        //PhoneVolume
        if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT ){
            views.setInt(R.id.imageButtonPhone2, "setBackgroundResource", R.drawable.phone_off);
        }else if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
            views.setInt(R.id.imageButtonPhone2, "setBackgroundResource", R.drawable.phone);
        }else if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE ){
            views.setInt(R.id.imageButtonPhone2, "setBackgroundResource", R.drawable.phone_viv);
        }

        //AlarmVolume
        if( audioManager.getStreamVolume(AudioManager.STREAM_ALARM) == 0 ){
            views.setInt(R.id.imageButtonAlarm2, "setBackgroundResource", R.drawable.alarm_off);
        }else{
            views.setInt(R.id.imageButtonAlarm2, "setBackgroundResource", R.drawable.alarm);
        }
    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int appWidgetId, Bundle newOptions) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, appWidgetId, newOptions);
        onUpdate(context, appWidgetManager, new int[] {appWidgetId});
    }

}