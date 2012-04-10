package com.rolandmai.showtracker;

public class Episode {
	Season season;
	int current;

	public Episode() {
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season value) {
		season = value;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int value) {
		current = value;
	}
}
