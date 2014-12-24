package com.application.volumewidget;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.VerticalSeekBar;

public class MainActivity extends Activity implements OnClickListener {

	Integer number;
	TextView text1;
	TextView text2;
	TextView text3;
	VerticalSeekBar vSeekBar1;
	VerticalSeekBar vSeekBar2;
	VerticalSeekBar vSeekBar3;
	AudioManager audioManager;
    ImageButton multi_button;
    ImageButton phone_button;
    ImageButton alarm_button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Set the vertical seekbars
		vSeekBar1 = (VerticalSeekBar) findViewById(R.id.seekBar1);
		vSeekBar2 = (VerticalSeekBar) findViewById(R.id.seekBar2);
		vSeekBar3 = (VerticalSeekBar) findViewById(R.id.seekBar3);

		text1 = (TextView) findViewById(R.id.textView1);
		text2 = (TextView) findViewById(R.id.textView2);
		text3 = (TextView) findViewById(R.id.textView3);

        multi_button = (ImageButton) findViewById(R.id.imageButtonMulti);
        phone_button = (ImageButton) findViewById(R.id.imageButtonPhone);
        alarm_button = (ImageButton) findViewById(R.id.imageButtonAlarm);

		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        refreshProgressBarsStatus();
        updateImageButtonStatus();

        createEvents();
	}

    @Override
    public void onResume(){


        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        refreshProgressBarsStatus();
        updateImageButtonStatus();

        super.onResume();
    }



    private void refreshProgressBarsStatus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Initialize the values of the textViews and seekBars
        text1.setText((audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) * 100 / audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");
        text2.setText((audioManager.getStreamVolume(AudioManager.STREAM_RING) * 100 / audioManager
                .getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
        text3.setText((audioManager.getStreamVolume(AudioManager.STREAM_ALARM) * 100 / audioManager
                .getStreamMaxVolume(AudioManager.STREAM_ALARM)) + "%");

        vSeekBar1.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        vSeekBar2.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_RING));
        vSeekBar3.setMax(audioManager
                .getStreamMaxVolume(AudioManager.STREAM_ALARM));

        vSeekBar1.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));
        vSeekBar2.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_RING));
        vSeekBar3.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_ALARM));

        vSeekBar1.updateThumb();
        vSeekBar2.updateThumb();
        vSeekBar3.updateThumb();
    }

    private void createEvents(){
        // Music media seekbar
        vSeekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                text1.setText((progress * 100 / audioManager
                        .getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");

                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        progress, AudioManager.FLAG_PLAY_SOUND);

                updateImageButtonStatus();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // RingTone seekbar
        vSeekBar2.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                text2.setText((progress * 100 / audioManager
                        .getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
                audioManager.setStreamVolume(AudioManager.STREAM_RING,
                        progress, AudioManager.FLAG_PLAY_SOUND);

                updateImageButtonStatus();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Alarm seekbar
        vSeekBar3.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text3.setText((progress * 100 / audioManager
                        .getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                        progress, AudioManager.FLAG_PLAY_SOUND);

                updateImageButtonStatus();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        multi_button.setOnClickListener(this);
        phone_button.setOnClickListener(this);
        alarm_button.setOnClickListener(this);
    }

    /**
     * Update the background of the image button of the application
     */
    private void updateImageButtonStatus() {
        //MultiVolume
        if( audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0 ){
            multi_button.setBackgroundResource(R.drawable.multi_off);
        }else{
            multi_button.setBackgroundResource(R.drawable.multi);
        }

        //MultiVolume
        if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT ){
            phone_button.setBackgroundResource(R.drawable.phone_off);
        }else if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL){
            phone_button.setBackgroundResource(R.drawable.phone);
        }else if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE ){
            phone_button.setBackgroundResource(R.drawable.phone_viv);
        }

        //MultiVolume
        if( audioManager.getStreamVolume(AudioManager.STREAM_ALARM) == 0 ){
            alarm_button.setBackgroundResource(R.drawable.alarm_off);
        }else{
            alarm_button.setBackgroundResource(R.drawable.alarm);
        }

    }

    @Override
    public void onClick(View v) {
        if( v.equals(multi_button)){
            //Puts volume to max
            if( audioManager.getStreamVolume(AudioManager.STREAM_MUSIC) == 0 ){
                multi_button.setBackgroundResource(R.drawable.multi);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
                        , AudioManager.FLAG_PLAY_SOUND);
            }else{
                multi_button.setBackgroundResource(R.drawable.multi_off);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0
                        , AudioManager.FLAG_PLAY_SOUND);
            }
        } else if( v.equals(alarm_button)) {
            //Puts volume to max
            if (audioManager.getStreamVolume(AudioManager.STREAM_ALARM) == 0) {
                alarm_button.setBackgroundResource(R.drawable.alarm);
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_ALARM)
                        , AudioManager.FLAG_PLAY_SOUND);
            } else {
                alarm_button.setBackgroundResource(R.drawable.alarm_off);
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, 0
                        , AudioManager.FLAG_PLAY_SOUND);
            }
        } else if( v.equals(phone_button)) {
            if( audioManager.getRingerMode() == AudioManager.RINGER_MODE_SILENT || audioManager.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE){
                phone_button.setBackgroundResource(R.drawable.phone);
                audioManager.setStreamVolume(AudioManager.STREAM_RING,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_RING)
                        , AudioManager.FLAG_PLAY_SOUND);
            } else if(audioManager.getRingerMode() == AudioManager.RINGER_MODE_NORMAL ){
                phone_button.setBackgroundResource(R.drawable.phone_off);
                audioManager.setStreamVolume(AudioManager.STREAM_RING,
                        0, AudioManager.FLAG_PLAY_SOUND);
            }
        }

        refreshProgressBarsStatus();
    }


}