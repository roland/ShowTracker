package com.rolandmai.showtracker;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ShowListActivity extends Activity {
	DatabaseHandler dbh;
	List<Show> shows;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_list);
		dbh = new DatabaseHandler(this);
		setListContent();

		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Show show = shows.get(position);
				Intent intent = new Intent(getApplicationContext(),
						ShowActivity.class);
				intent.putExtra("showID", Integer.toString(show.getID()));
				startActivity(intent);
			}
		});
		lv.setOnCreateContextMenuListener(this);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (hasFocus && !getApplicationContext().equals(ShowListActivity.class)) {
			setListContent();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.show_context_menu, menu);
	}

	@Override
	public boolean onContextItemSelected(android.view.MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		int itemId = item.getItemId(); 
		switch (itemId) {
			case R.id.cmShowEdit:
				editShow(info.position);
				return true;
			case R.id.cmShowDelete:
				deleteShow(info.position);
				return true;
			case R.id.cmShowCancel:
				return true;
			default:
				return super.onContextItemSelected(item);
		}
	}

	private void deleteShow(final int position) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete this show?")
			.setCancelable(true)
			.setPositiveButton("Yes, delete it.", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					doDeleteShow(position);
				}
			})
			.setNegativeButton("No", new OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
				}
			});
		AlertDialog alert = builder.create();
	    alert.show();
	}
	
	private void doDeleteShow(int position)
	{
		DatabaseHandler dbh = new DatabaseHandler(this);
		Show show = shows.get(position);
		dbh.deleteShow(show);
	}

	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		switch (item.getItemId()) {
		case R.id.optMenuAdd:
			Intent intent = new Intent(getApplicationContext(),
					ShowAddActivity.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void setListContent() {
		shows = dbh.getAllShows();

		ShowListAdapter adapter = new ShowListAdapter(this, shows);
		((ListView) findViewById(R.id.listView1)).setAdapter(adapter);
		
		TextView txtNoShows = ((TextView)findViewById(R.id.txtNoShows));
		if(shows.size() == 0)
			txtNoShows.setVisibility(View.VISIBLE);
		else
			txtNoShows.setVisibility(View.GONE);
	}

	private void editShow(int position) {
		Show show = shows.get(position);
		Intent intent = new Intent(getApplicationContext(), ShowEditActivity.class);
		intent.putExtra("showID", Integer.toString(show.getID()));
		startActivity(intent);
	}
}
