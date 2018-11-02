package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import com.psl.model.Artist;
import com.psl.service.MusicService;

@Controller
public class ArtistController {
	
	@Autowired
	MusicService musicService;
	
	@RequestMapping(method= RequestMethod.GET, value="/artist/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Resource<Artist> getArtist(@PathVariable String id){
		System.out.println("Inside this !");
		Artist artist = musicService.getArtist(id);
		Resource<Artist> resource = new Resource<Artist>(artist);
		
		resource.add(linkTo(methodOn(ArtistController.class).getArtist(id)).withSelfRel());
		
		return resource;
	}
	
}
