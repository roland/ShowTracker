package com.rolandmai.showtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowActivity extends Activity {
	Show show;
	DatabaseHandler dbh;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);

		Intent intent = getIntent();
		String showIDExtra = intent.getStringExtra("showID");

		Integer showID;

		try {
			showID = Integer.parseInt(showIDExtra);
		} catch (Exception ex) {
			Toast.makeText(this, "Bad show id parameter", 3000);
			return;
		}

		// Get show object from the database
		dbh = new DatabaseHandler(this);
		show = dbh.getShow(showID);

		if (show == null) {
			Toast.makeText(this, "Show not found", 3000);
			return;
		}

		TextView episodeNr = (TextView) findViewById(R.id.tvEpisodeNr);
		episodeNr.setText(Integer.toString(show.getCurrentEpisode()));

		TextView seasonNr = (TextView) findViewById(R.id.tvSeasonNr);
		seasonNr.setText(Integer.toString(show.getCurrentSeason()));

		TextView txtShowName = (TextView) findViewById(R.id.txtShowName);
		txtShowName.setText(show.getName());

		Button btnSeasonDecrease = (Button) findViewById(R.id.btnSeasonDecrease);
		btnSeasonDecrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DecreaseSeasonCount();
			}
		});

		Button btnSeasonIncrease = (Button) findViewById(R.id.btnSeasonIncrease);
		btnSeasonIncrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IncreaseSeasonCount();
			}
		});

		Button btnEpisodeDecrease = (Button) findViewById(R.id.btnEpisodeDecrease);
		btnEpisodeDecrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				DecreaseEpisodeCount();
			}
		});

		Button btnEpisodeIncrease = (Button) findViewById(R.id.btnEpisodeIncrease);
		btnEpisodeIncrease.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IncreaseEpisodeCount();
			}
		});
	}

	private void DecreaseSeasonCount() {
		TextView tv = (TextView) findViewById(R.id.tvSeasonNr);
		show.setCurrentSeason(Math.max(1, show.getCurrentSeason() - 1));
		dbh.updateShow(show);
		tv.setText(Integer.toString(show.getCurrentSeason()));
	}

	private void IncreaseSeasonCount() {
		TextView tv = (TextView) findViewById(R.id.tvSeasonNr);
		show.setCurrentSeason(Math.min(30, show.getCurrentSeason() + 1));
		dbh.updateShow(show);
		tv.setText(Integer.toString(show.getCurrentSeason()));
	}

	private void DecreaseEpisodeCount() {
		TextView tv = (TextView) findViewById(R.id.tvEpisodeNr);
		show.setCurrentEpisode(Math.max(1, show.getCurrentEpisode() - 1));
		dbh.updateShow(show);
		tv.setText(Integer.toString(show.getCurrentEpisode()));
	}

	private void IncreaseEpisodeCount() {
		TextView tv = (TextView) findViewById(R.id.tvEpisodeNr);
		show.setCurrentEpisode(Math.min(300, show.getCurrentEpisode() + 1));
		dbh.updateShow(show);
		tv.setText(Integer.toString(show.getCurrentEpisode()));
	}
}