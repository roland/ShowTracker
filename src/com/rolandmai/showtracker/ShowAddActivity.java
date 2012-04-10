package com.rolandmai.showtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ShowAddActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_add);

		Button btnSave = (Button) findViewById(R.id.btnAddSave);
		Button btnCancel = (Button) findViewById(R.id.btnAddCancel);

		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AddShow();
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RedirectToList();
			}
		});
	}

	private void AddShow() {
		DatabaseHandler dbh = new DatabaseHandler(this);
		Show show = new Show();
		show.setCurrentEpisode(1);
		show.setCurrentSeason(1);
		EditText txt = (EditText) findViewById(R.id.txtAddShowName);
		show.setName(txt.getText().toString());
		dbh.addShow(show);
		RedirectToList();
	}

	private void RedirectToList() {
		Intent intent = new Intent(getApplicationContext(),
				ShowListActivity.class);
		startActivity(intent);
	}
}
