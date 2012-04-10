package com.rolandmai.showtracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShowEditActivity extends Activity {

	Show show;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_edit);

		Button btnSave = (Button) findViewById(R.id.btnEditSave);
		Button btnCancel = (Button) findViewById(R.id.btnEditCancel);
		EditText txtEditShowName = (EditText)findViewById(R.id.txtEditShowName);

		Intent intent = getIntent();
		String showIDExtra = intent.getStringExtra("showID");

		Integer showID;

		try {
			showID = Integer.parseInt(showIDExtra);
		} catch (Exception ex) {
			Toast.makeText(this, "Bad show id parameter", 3000);
			RedirectToList();
			return;
		}
		
		getShow(showID);
		
		txtEditShowName.setText(show.getName());
		txtEditShowName.setSelection(txtEditShowName.getText().length());
		
		btnSave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditShow();
			}
		});

		btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RedirectToList();
			}
		});
	}

	private void getShow(Integer showID) {
		DatabaseHandler dbh = new DatabaseHandler(this);
		show = dbh.getShow(showID);
	}

	private void EditShow() {
		DatabaseHandler dbh = new DatabaseHandler(this);
		EditText txt = (EditText) findViewById(R.id.txtEditShowName);
		show.setName(txt.getText().toString());
		dbh.updateShow(show);
		RedirectToList();
	}

	private void RedirectToList() {
		Intent intent = new Intent(getApplicationContext(),
				ShowListActivity.class);
		startActivity(intent);
	}
}
