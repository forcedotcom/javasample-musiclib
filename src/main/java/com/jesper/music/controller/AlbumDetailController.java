package com.jesper.music.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.force.sdk.connector.ForceServiceConnector;
import com.jesper.music.model.Album;
import com.jesper.music.service.MusicService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AlbumDetailController {

	//private static final Logger logger = LoggerFactory.getLogger(AlbumDetailController.class);

	@Inject
	MusicService musicService;
	
	@Inject
	ForceServiceConnector forceService;
	
	@ModelAttribute("album")
	public Album init(@PathVariable String id) {
		return (Album) musicService.findEntity(Album.class,id);
	}
	
	@RequestMapping(value="/album/{id}", method=RequestMethod.GET)
	public ModelAndView view(ModelAndView mv, @ModelAttribute Album album) {
		mv.addObject("album", album);
		mv.setViewName("album_detail");
		return mv;
	}

	@RequestMapping(value="/album/{id}", method=RequestMethod.POST)
	public String update(ModelAndView mv, @ModelAttribute Album album) {
		return "redirect:/album/"+musicService.saveAlbum(album).getId();
	}
}

