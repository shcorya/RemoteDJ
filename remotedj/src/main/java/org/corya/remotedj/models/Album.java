package org.corya.remotedj.models;

import java.util.ArrayList;

public class Album {
	
	private String artist;
	private String title;
	private ArrayList<Track> tracks;
	
	public Album(String artist, String title){
		this.artist = artist;
		this.title = title;
		tracks = new ArrayList<Track>();
	}
	
	public Album(String artist, String title, ArrayList<Track> tracks){
		this.artist = artist;
		this.title = title;
		this.tracks = tracks;
	}
	
	public void setArtist(String artist){
		this.artist = artist;
	}
	
	public String getArtist(){
		return artist;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void addTrack(Track track){
		tracks.add(track);
	}
	
	public ArrayList<Track> getTracks(){
		return tracks;
	}

	public int size() {
		return tracks.size();
	}
}
