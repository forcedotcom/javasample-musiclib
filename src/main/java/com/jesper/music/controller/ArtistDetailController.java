package com.jesper.music.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.force.sdk.connector.ForceServiceConnector;
import com.jesper.music.model.Artist;
import com.jesper.music.service.MusicService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ArtistDetailController {

	//private static final Logger logger = LoggerFactory.getLogger(AlbumDetailController.class);

	@Inject
	MusicService musicService;
	
	@Inject
	ForceServiceConnector forceService;
	
	@ModelAttribute("artist")
	public Artist init(@PathVariable String id) {
		return (Artist) musicService.findEntity(Artist.class,id);
	}
	
	@RequestMapping(value="/artist/{id}", method=RequestMethod.GET)
	public ModelAndView view(ModelAndView mv, @ModelAttribute Artist artist) {
		mv.addObject("artist",artist);
		mv.setViewName("artist_detail");
		return mv;
	}

	@RequestMapping(value="/artist/{id}", method=RequestMethod.POST)
	public String update(ModelAndView mv, @ModelAttribute Artist artist) {
		return "redirect:/artist/"+musicService.saveArtist(artist).getId();
	}
	
	@RequestMapping(value="/artist/{id}/albums", method=RequestMethod.GET)
	public ModelAndView albums(ModelAndView mv, @ModelAttribute Artist artist) {
		mv.addObject("albums",artist.getAlbums());
		mv.setViewName("album_list");
		return mv;
	}
	
}

