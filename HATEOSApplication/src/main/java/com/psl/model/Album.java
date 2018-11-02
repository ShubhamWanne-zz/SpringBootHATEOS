package com.psl.model;

public class Album {
	
	private final String id;
	private final String name;
	private final Artist artist;
	private int availableStock;

	public Album(String id, String name, Artist artist, int availableStock) {
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.availableStock = availableStock;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Artist getArtist() {
		return artist;
	}
	
	
	
}
