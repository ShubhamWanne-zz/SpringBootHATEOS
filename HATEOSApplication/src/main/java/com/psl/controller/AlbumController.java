package com.psl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.psl.service.MusicService;

@Controller
public class AlbumController {
	@Autowired
	MusicService musicService;
}
