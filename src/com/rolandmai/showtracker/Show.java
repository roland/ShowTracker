package com.rolandmai.showtracker;

public class Show {
	private int ID;
	private String name;
	private int currentSeason;
	private int currentEpisode;

	public Show() {
	}

	public int getID() {
		return ID;
	}

	public void setID(int value) {
		ID = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

	public int getCurrentSeason() {
		return currentSeason;
	}

	public void setCurrentSeason(int value) {
		currentSeason = value;
	}

	public int getCurrentEpisode() {
		return currentEpisode;
	}

	public void setCurrentEpisode(int value) {
		currentEpisode = value;
	}
}
