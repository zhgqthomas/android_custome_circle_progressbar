package com.zhgq.progressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private CustomProgressBar mSpinBar;
	private CustomProgressBar mSweepBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// The spin bar changed the bar color in the code.
		mSpinBar = (CustomProgressBar) findViewById(R.id.my_spin_bar);
		mSweepBar = (CustomProgressBar) findViewById(R.id.my_sweep_bar);

		mSpinBar.setBarColor(Color.BLUE);
		mSpinBar.execute();

		// The sweep bar changed the circle color and bar color in the xml.
		mSweepBar.setProgressMode(CustomProgressBar.SWEEP_MODE);
		// mSweepBar.setBarColor(Color.GREEN);
		new UpdateBarProgress().execute();
	}

	private class UpdateBarProgress extends AsyncTask<Void, Integer, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i <= 360; i++) {
				try {
					// update every second
					Thread.sleep(100);
				} catch (InterruptedException e) {

				}

				publishProgress(i);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			mSweepBar.setProgress(values[0]);
			mSweepBar.execute();
		}

	}

}
