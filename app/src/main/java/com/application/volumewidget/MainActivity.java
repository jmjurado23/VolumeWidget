package com.application.volumewidget;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
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

		// Music media seekbar
		vSeekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				text1.setText((progress * 100 / audioManager
						.getStreamMaxVolume(AudioManager.STREAM_MUSIC)) + "%");

				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,
						progress, AudioManager.FLAG_PLAY_SOUND);
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
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				text3.setText((progress * 100 / audioManager
						.getStreamMaxVolume(AudioManager.STREAM_RING)) + "%");
				audioManager.setStreamVolume(AudioManager.STREAM_ALARM,
						progress, AudioManager.FLAG_PLAY_SOUND);

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	@Override
	public void onClick(View v) {

	}

}