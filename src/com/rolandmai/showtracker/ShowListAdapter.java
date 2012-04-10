package com.rolandmai.showtracker;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShowListAdapter extends ArrayAdapter<Show> {
	List<Show> shows;
	Context context;

	public ShowListAdapter(Context context, List<Show> objects) {
		super(context, R.layout.show_lineitem, objects);
		this.shows = objects;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View lineItemView = inflater.inflate(R.layout.show_lineitem, parent,
				false);

		TextView showName = (TextView) lineItemView.findViewById(R.id.showName);
		TextView showStatus = (TextView) lineItemView
				.findViewById(R.id.showStatus);

		Show show = shows.get(position);

		showName.setText(show.getName());
		showStatus.setText(String.format("S%1$sE%2$s", show.getCurrentSeason(),
				show.getCurrentEpisode()));

		return lineItemView;
	}
}
