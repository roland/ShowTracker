package com.rolandmai.showtracker;

import java.util.List;

public class Season {
	List<Episode> episodes;
	int current;

	public Season() {
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	public void setEpisodes(List<Episode> value) {
		episodes = value;
	}

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int value) {
		current = value;
	}
}
