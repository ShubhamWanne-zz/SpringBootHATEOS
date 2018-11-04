package com.psl.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.psl.exceptions.StockUndeflowException;
import com.psl.model.Album;
import com.psl.service.MusicService;

@Controller
public class AlbumController {
	@Autowired
	MusicService musicService;
	
	@RequestMapping(value= "/albums", method= RequestMethod.GET, produces= org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<Resource<Album>> getAllAlbums(){
		Collection<Album> albumCollection = musicService.getAlbums();
		List<Resource<Album>> album_resource = new ArrayList<Resource<Album>>();
		albumCollection.stream().forEach(
					(album)-> album_resource.add(getAlbumResource(album))
		);
		return album_resource;
	}
	@RequestMapping(value= "/album/{id}", method= RequestMethod.GET, produces= org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resource<Album> getAlbum(@PathVariable String id){
		Album album = musicService.getAlbum(id);
		return getAlbumResource(album);
	}	
	private Resource<Album> getAlbumResource(Album album){
		Resource<Album> resource_album = new Resource<Album>(album);
		// Link to Album
		resource_album.add(
					linkTo(methodOn(AlbumController.class).getAlbum(album.getId())).withSelfRel()
				);
		// Link to Artist
		resource_album.add(
					linkTo(methodOn(ArtistController.class).getArtist(album.getArtist().getId())).withRel("artist")
				);
		// Option to purchase Album		
		if(album.getAvailableStock() > 0) {
			resource_album.add(
					linkTo(methodOn(AlbumController.class).purchaseAlbum(album.getId())).withRel("album.purchase")
				);
		}
		return resource_album;
	}
	@RequestMapping(value= "/album/purchase/{id}", method= RequestMethod.GET, produces= org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resource<Album> purchaseAlbum(@PathVariable String id){
		Resource<Album> resource_album = new Resource<Album>(musicService.getAlbum(id));
		if(resource_album.getContent().getAvailableStock() <= 0) {
			throw new StockUndeflowException("Stock reached out of limit !");
		}
		resource_album.getContent().setAvailableStock(resource_album.getContent().getAvailableStock() - 1);
		resource_album.add(
				linkTo(methodOn(AlbumController.class).getAlbum(id)).withSelfRel()
				);
		return resource_album;
	}
}
