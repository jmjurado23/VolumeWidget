package com.application.volumewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.RemoteViews;

public class VolumeWidgetApp extends AppWidgetProvider {


    private static final String plus_multi   = "multi_plus";
    private static final String minus_multi   = "multi_minus";
    private static final String plus_phone   = "phone_plus";
    private static final String minus_phone   = "phone_minus";
    private static final String plus_alarm   = "alarm_plus";
    private static final String minus_alarm   = "alarm_minus";
	
	int[] appWidgetIdss;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        
        appWidgetIdss = appWidgetIds;
        

        // Perform this loop procedure for each App Widget that belongs to this provider
        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];
            
            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_widget);
            
            // Initialize value of button how system
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            views.setTextViewText(R.id.text_multi_0, ( audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / audioManager
    				.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");
            views.setTextViewText(R.id.text_phone_1, (audioManager.getStreamVolume(AudioManager.STREAM_RING) * 100 / audioManager
    				.getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
            views.setTextViewText(R.id.text_alarm_2, (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) * 100 / audioManager
    				.getStreamMaxVolume(AudioManager.STREAM_ALARM)) + "%");

            // Define on click button action
            views.setOnClickPendingIntent(R.id.plus_multi_0, getPendingSelfIntent(context, plus_multi));
            views.setOnClickPendingIntent(R.id.minus_multi_0, getPendingSelfIntent(context, minus_multi));
            views.setOnClickPendingIntent(R.id.plus_phone_1, getPendingSelfIntent(context, plus_phone));
            views.setOnClickPendingIntent(R.id.minus_phone_1, getPendingSelfIntent(context, minus_phone));
            views.setOnClickPendingIntent(R.id.plus_alarm_2, getPendingSelfIntent(context, plus_alarm));
            views.setOnClickPendingIntent(R.id.minus_alarm_2, getPendingSelfIntent(context, minus_alarm));

            
            // Tell the AppWidgetManager to perform an update on the current app widget
//            appWidgetManager.updateAppWidget(appWidgetId, views);
            ComponentName componentName= new ComponentName(context, VolumeWidgetApp.class);
    		AppWidgetManager.getInstance(context).updateAppWidget(componentName, views);
        }
    }
    
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        super.onReceive(context, intent);

        String[]  array = intent.getAction().split("_");
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
 
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.main_widget);
        
        //manage the multimedia
        if( array[0].equals("multi") ){

            if( array[1].equals("plus"))
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        getNormalizedVolume(AudioManager.STREAM_MUSIC, 1, context),
                        AudioManager.FLAG_PLAY_SOUND);
            else
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        getNormalizedVolume(AudioManager.STREAM_MUSIC, -1, context),
                        AudioManager.FLAG_PLAY_SOUND);

            views.setTextViewText(R.id.text_multi_0, ( audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");
			
        } else if( array[0].equals("phone") ) {
            if( array[1].equals("plus"))
                audioManager.setStreamVolume(AudioManager.STREAM_RING,
                        getNormalizedVolume(AudioManager.STREAM_RING, 1, context),
                        AudioManager.FLAG_PLAY_SOUND);
            else
                audioManager.setStreamVolume(AudioManager.STREAM_RING,
                        getNormalizedVolume(AudioManager.STREAM_RING, -1, context),
                        AudioManager.FLAG_PLAY_SOUND);

            views.setTextViewText(R.id.text_phone_1, ( audioManager.getStreamVolume(AudioManager.STREAM_RING) * 100 / audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
        } else if( array[0].equals("alarm") ) {
            if( array[1].equals("plus"))
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                    getNormalizedVolume(AudioManager.STREAM_ALARM, 1, context),
                    AudioManager.FLAG_PLAY_SOUND);
            else
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                    getNormalizedVolume(AudioManager.STREAM_ALARM, -1, context),
                    AudioManager.FLAG_PLAY_SOUND);

            views.setTextViewText(R.id.text_alarm_2, ( audioManager.getStreamVolume(AudioManager.STREAM_ALARM) * 100 / audioManager
                    .getStreamMaxVolume(AudioManager.STREAM_ALARM)) + "%");

        }
        
        ComponentName componentName= new ComponentName(context, VolumeWidgetApp.class);
		AppWidgetManager.getInstance(context).updateAppWidget(componentName, views);
    }



    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

   public int getNormalizedVolume(int stream, int increment, Context context) {

       AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

       // check if increment is positive or negative
       if( increment > 0){
           if((audioManager.getStreamVolume(stream) + increment) <= audioManager.getStreamMaxVolume(stream))
               return ( audioManager.getStreamVolume(stream) + increment );
           else
                return  audioManager.getStreamMaxVolume(stream);
       }
       else{
           if((audioManager.getStreamVolume(stream) + increment) >= 0)
               return ( audioManager.getStreamVolume(stream) + increment );
           else
               return  0;
       }
   }

}