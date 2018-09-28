package org.corya.remotedj.models;

import java.net.URI;
import java.util.ArrayList;

import com.google.gson.Gson;

public class Library {
	private ArrayList<Track> tracks;
	private ArrayList<String> directories;
	
	public Library(){
		tracks = new ArrayList<Track>();
		directories = new ArrayList<String>();
	};
	
	public void addTrack(Track track){
		tracks.add(track);
	}
	
	public void addDirectory(String directory){
		directories.add(directory);
	}
	
	public int size(){
		return tracks.size();
	}
	
	public Track getTrackAt(int i){
		return tracks.get(i);
	}
	
	public String toJsonString(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public int getIndOfUri(URI uri){
		 
	}
}