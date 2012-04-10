package com.rolandmai.showtracker;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {
	static final int DATABASE_VERSION = 1;
	static final String DATABASE_NAME = "showtracker";
	static final String SHOWS_TABLE = "shows";
	static final String SHOWS_TABLE_PK = "ID";

	public DatabaseHandler(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSql = "CREATE TABLE " + SHOWS_TABLE + " ("
				+ SHOWS_TABLE_PK + " INTEGER PRIMARY KEY, Name STRING, "
				+ "CurrentSeason INTEGER, CurrentEpisode INTEGER)";
		db.execSQL(createSql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void addShow(Show show) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Name", show.getName());
		values.put("CurrentSeason", show.getCurrentSeason());
		values.put("CurrentEpisode", show.getCurrentEpisode());
		db.insert(SHOWS_TABLE, null, values);
	}

	public int updateShow(Show show) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Name", show.getName());
		values.put("CurrentSeason", show.getCurrentSeason());
		values.put("CurrentEpisode", show.getCurrentEpisode());
		int id = db.update(SHOWS_TABLE, values, SHOWS_TABLE_PK + " = ?",
				new String[] { String.valueOf(show.getID()) });
		return id;
	}

	public List<Show> getAllShows() {
		List<Show> shows = new ArrayList<Show>();
		String selectSql = "SELECT ID, Name, CurrentSeason, "
				+ "CurrentEpisode  FROM " + SHOWS_TABLE;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectSql, null);

		if (cursor.moveToFirst()) {
			do {
				Show show = new Show();
				show.setID(Integer.parseInt(cursor.getString(0)));
				show.setName(cursor.getString(1));
				show.setCurrentSeason(Integer.parseInt(cursor.getString(2)));
				show.setCurrentEpisode(Integer.parseInt(cursor.getString(3)));
				shows.add(show);
			} while (cursor.moveToNext());
		}
		cursor.close();

		return shows;
	}

	Show getShow(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		try {
			Cursor cursor = db
					.query(SHOWS_TABLE, new String[] { SHOWS_TABLE_PK, "Name",
							"CurrentSeason", "CurrentEpisode" }, SHOWS_TABLE_PK
							+ " = ?", new String[] { String.valueOf(id) },
							null, null, null, null);
			if (cursor != null) {
				cursor.moveToFirst();

				Show show = new Show();
				show.setID(Integer.parseInt(cursor.getString(0)));
				show.setName(cursor.getString(1));
				show.setCurrentSeason(Integer.parseInt(cursor.getString(2)));
				show.setCurrentEpisode(Integer.parseInt(cursor.getString(3)));
				cursor.close();
				return show;
			}
		} catch (Exception ex) {
			Log.d("ERROR", ex.getMessage());
		}
		return null;
	}

	public void deleteShow(Show show) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(SHOWS_TABLE, SHOWS_TABLE_PK + " = ?",
				new String[] { String.valueOf(show.getID()) });
	}
}
