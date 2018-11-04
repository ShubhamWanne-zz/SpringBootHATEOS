package com.psl.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.psl.model.Album;
import com.psl.model.Artist;

@Service
public class MusicService {
	private Map<String, Album> albums;
	private Map<String, Artist> artists;
	
	public MusicService() {
		albums = new HashMap<>();
		artists= new HashMap<>();
	    
		Artist artist1 = new Artist("arman", "Arman Malik");
	    Artist artist2 = new Artist("sonu", "Sonu Nigam");
	    artists.put(artist1.getId(), artist1);
	    artists.put(artist2.getId(), artist2);
	    

	    Album album1 = new Album("1", "Deewana", artist2, 2);
	    Album album2 = new Album("2", "Jaan", artist2, 3);
	    Album album3 = new Album("3", "Armaan", artist1, 4);
	    Album album4 = new Album("4", "Sanam Re", artist1, 2);
	    albums.put(album1.getId(), album1);
	    albums.put(album2.getId(), album2);
	    albums.put(album3.getId(), album3);
	    albums.put(album4.getId(), album4);
		
	    System.out.println(artists);
	    System.out.println(albums);
	}
	
	public Collection<Album> getAlbums() {
		return albums.values();
	}
	public Collection<Artist> getArtists() {
		return artists.values();
	}
	public Artist getArtist(String id){
		return artists.get(id);
	}
	public Album getAlbum(String id) {
		return albums.get(id);
	}
	
}
