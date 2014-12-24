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
	
	// Static variables for media volume
	private static final String CLICKED1_0   = "1_0";
	private static final String CLICKED1_1   = "1_1";
	private static final String CLICKED1_2   = "1_2";
	private static final String CLICKED1_3   = "1_3";
	private static final String CLICKED1_4   = "1_4";
	private static final String CLICKED1_5   = "1_5";
	private static final String CLICKED1_6   = "1_6";
	private static final String CLICKED1_7   = "1_7";
	private static final String CLICKED1_8   = "1_8";
	private static final String CLICKED1_9   = "1_9";
	private static final String CLICKED1_10  = "1_10";
	private static final String CLICKED1_11  = "1_11";
	private static final String CLICKED1_12  = "1_12";
	private static final String CLICKED1_13  = "1_13";
	private static final String CLICKED1_14  = "1_14";
	private static final String CLICKED1_15  = "1_15";
	private static final String CLICKED2_0   = "2_0";
	private static final String CLICKED2_1   = "2_1";
	private static final String CLICKED2_2   = "2_2";
	private static final String CLICKED2_3   = "2_3";
	private static final String CLICKED2_4   = "2_4";
	private static final String CLICKED2_5   = "2_5";
	private static final String CLICKED2_6   = "2_6";
	private static final String CLICKED2_7   = "2_7";
	private static final String CLICKED3_0   = "3_0";
	private static final String CLICKED3_1   = "3_1";
	private static final String CLICKED3_2   = "3_2";
	private static final String CLICKED3_3   = "3_3";
	private static final String CLICKED3_4   = "3_4";
	private static final String CLICKED3_5   = "3_5";
	private static final String CLICKED3_6   = "3_6";
	private static final String CLICKED3_7   = "3_7";
	
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
            views.setTextViewText(R.id.textViewWidget1, ( audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / audioManager
    				.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");
            views.setTextViewText(R.id.textViewWidget2, (audioManager.getStreamVolume(AudioManager.STREAM_RING) * 100 / audioManager
    				.getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
            views.setTextViewText(R.id.textViewWidget3, (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) * 100 / audioManager
    				.getStreamMaxVolume(AudioManager.STREAM_ALARM)) + "%");
            
            redrawImages( views, "1", audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) );
            redrawImages( views, "2", audioManager.getStreamVolume(AudioManager.STREAM_RING) );
            redrawImages( views, "3", audioManager.getStreamVolume(AudioManager.STREAM_ALARM) );

            // Define on click button action
            views.setOnClickPendingIntent(R.id.button1_0, getPendingSelfIntent(context, CLICKED1_0));
            views.setOnClickPendingIntent(R.id.button1_1, getPendingSelfIntent(context, CLICKED1_1));
            views.setOnClickPendingIntent(R.id.button1_2, getPendingSelfIntent(context, CLICKED1_2));
            views.setOnClickPendingIntent(R.id.button1_3, getPendingSelfIntent(context, CLICKED1_3));
            views.setOnClickPendingIntent(R.id.button1_4, getPendingSelfIntent(context, CLICKED1_4));
            views.setOnClickPendingIntent(R.id.button1_5, getPendingSelfIntent(context, CLICKED1_5));
            views.setOnClickPendingIntent(R.id.button1_6, getPendingSelfIntent(context, CLICKED1_6));
            views.setOnClickPendingIntent(R.id.button1_7, getPendingSelfIntent(context, CLICKED1_7));
            views.setOnClickPendingIntent(R.id.button1_8, getPendingSelfIntent(context, CLICKED1_8));
            views.setOnClickPendingIntent(R.id.button1_9, getPendingSelfIntent(context, CLICKED1_9));
            views.setOnClickPendingIntent(R.id.button1_10, getPendingSelfIntent(context, CLICKED1_10));
            views.setOnClickPendingIntent(R.id.button1_11, getPendingSelfIntent(context, CLICKED1_11));
            views.setOnClickPendingIntent(R.id.button1_12, getPendingSelfIntent(context, CLICKED1_12));
            views.setOnClickPendingIntent(R.id.button1_13, getPendingSelfIntent(context, CLICKED1_13));
            views.setOnClickPendingIntent(R.id.button1_14, getPendingSelfIntent(context, CLICKED1_14));
            views.setOnClickPendingIntent(R.id.button1_15, getPendingSelfIntent(context, CLICKED1_15));
            
            views.setOnClickPendingIntent(R.id.button2_0, getPendingSelfIntent(context, CLICKED2_0));
            views.setOnClickPendingIntent(R.id.button2_1, getPendingSelfIntent(context, CLICKED2_1));
            views.setOnClickPendingIntent(R.id.button2_2, getPendingSelfIntent(context, CLICKED2_2));
            views.setOnClickPendingIntent(R.id.button2_3, getPendingSelfIntent(context, CLICKED2_3));
            views.setOnClickPendingIntent(R.id.button2_4, getPendingSelfIntent(context, CLICKED2_4));
            views.setOnClickPendingIntent(R.id.button2_5, getPendingSelfIntent(context, CLICKED2_5));
            views.setOnClickPendingIntent(R.id.button2_6, getPendingSelfIntent(context, CLICKED2_6));
            views.setOnClickPendingIntent(R.id.button2_7, getPendingSelfIntent(context, CLICKED2_7));
            
            views.setOnClickPendingIntent(R.id.button3_0, getPendingSelfIntent(context, CLICKED3_0));
            views.setOnClickPendingIntent(R.id.button3_1, getPendingSelfIntent(context, CLICKED3_1));
            views.setOnClickPendingIntent(R.id.button3_2, getPendingSelfIntent(context, CLICKED3_2));
            views.setOnClickPendingIntent(R.id.button3_3, getPendingSelfIntent(context, CLICKED3_3));
            views.setOnClickPendingIntent(R.id.button3_4, getPendingSelfIntent(context, CLICKED3_4));
            views.setOnClickPendingIntent(R.id.button3_5, getPendingSelfIntent(context, CLICKED3_5));
            views.setOnClickPendingIntent(R.id.button3_6, getPendingSelfIntent(context, CLICKED3_6));
            views.setOnClickPendingIntent(R.id.button3_7, getPendingSelfIntent(context, CLICKED3_7));
            
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
        if( array[0].equals("1") ){
        	
        	views.setTextViewText(R.id.textViewWidget1, ( Integer.parseInt( array[1] ) * 100 /audioManager
					.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");

			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
					Integer.parseInt( array[1] ), AudioManager.FLAG_PLAY_SOUND);
			
			redrawImages(views, array[0], Integer.parseInt( array[1]) );
			
        } else if( array[0].equals("2") ) {
        	views.setTextViewText(R.id.textViewWidget2, ( Integer.parseInt( array[1] ) * 100 /audioManager
					.getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");

			audioManager.setStreamVolume(AudioManager.STREAM_RING,
					Integer.parseInt( array[1] ), AudioManager.FLAG_PLAY_SOUND);
			
			redrawImages(views, array[0], Integer.parseInt( array[1]) );
        } else if( array[0].equals("3") ) {
        	views.setTextViewText(R.id.textViewWidget3, ( Integer.parseInt( array[1] ) * 100 /audioManager
					.getStreamMaxVolume(AudioManager.STREAM_ALARM)) + "%");

			audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
					Integer.parseInt( array[1] ), AudioManager.FLAG_PLAY_SOUND);
			
			redrawImages(views, array[0], Integer.parseInt( array[1]) );
        }
        
        ComponentName componentName= new ComponentName(context, VolumeWidgetApp.class);
		AppWidgetManager.getInstance(context).updateAppWidget(componentName, views);
    }
    
    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }
    
    /**
     * Redraw the buttons of the widget
     * @param type
     * @param value
     */
    public void redrawImages( RemoteViews views, String type, int value ){
    	//redraw the multimedia
    	if ( type.equals("1") ){
    		defineSrcImage( views, R.id.button1_0, 0, value );
    		defineSrcImage( views, R.id.button1_1, 1, value );
    		defineSrcImage( views, R.id.button1_2, 2, value );
    		defineSrcImage( views, R.id.button1_3, 3, value );
    		defineSrcImage( views, R.id.button1_4, 4, value );
    		defineSrcImage( views, R.id.button1_5, 5, value );
    		defineSrcImage( views, R.id.button1_6, 6, value );
    		defineSrcImage( views, R.id.button1_7, 7, value );
    		defineSrcImage( views, R.id.button1_8, 8, value );
    		defineSrcImage( views, R.id.button1_9, 9, value );
    		defineSrcImage( views, R.id.button1_10, 10, value );
    		defineSrcImage( views, R.id.button1_11, 11, value );
    		defineSrcImage( views, R.id.button1_12, 12, value );
    		defineSrcImage( views, R.id.button1_13, 13, value );
    		defineSrcImage( views, R.id.button1_14, 14, value );
    		defineSrcImage( views, R.id.button1_15, 15, value );
    	} else if( type.equals("2")){
    		defineSrcImage( views, R.id.button2_0, 0, value );
    		defineSrcImage( views, R.id.button2_1, 1, value );
    		defineSrcImage( views, R.id.button2_2, 2, value );
    		defineSrcImage( views, R.id.button2_3, 3, value );
    		defineSrcImage( views, R.id.button2_4, 4, value );
    		defineSrcImage( views, R.id.button2_5, 5, value );
    		defineSrcImage( views, R.id.button2_6, 6, value );
    		defineSrcImage( views, R.id.button2_7, 7, value );
    	} else if( type.equals("3")){
    		defineSrcImage( views, R.id.button3_0, 0, value );
    		defineSrcImage( views, R.id.button3_1, 1, value );
    		defineSrcImage( views, R.id.button3_2, 2, value );
    		defineSrcImage( views, R.id.button3_3, 3, value );
    		defineSrcImage( views, R.id.button3_4, 4, value );
    		defineSrcImage( views, R.id.button3_5, 5, value );
    		defineSrcImage( views, R.id.button3_6, 6, value );
    		defineSrcImage( views, R.id.button3_7, 7, value );
    	}
    }
    
    public void defineSrcImage( RemoteViews views, int r, int element, int value ){
    	if( value > element ) {
    		views.setImageViewResource(r, R.drawable.widget_full);
    	} else if ( value == element ){
    		views.setImageViewResource(r, R.drawable.widget_empty);
    	} else {
    		views.setImageViewResource(r, R.drawable.widget_empty);
    	}
    }
}