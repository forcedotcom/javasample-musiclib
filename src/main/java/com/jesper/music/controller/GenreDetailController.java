package com.jesper.music.controller;

import com.force.sdk.connector.ForceServiceConnector;
import com.jesper.music.model.Genre;
import com.jesper.music.service.MusicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

/**
 * Handles requests for the application home page.
 */
@Controller
public class GenreDetailController {

	//private static final Logger logger = LoggerFactory.getLogger(AlbumDetailController.class);

	@Inject
	MusicService musicService;
	
	@Inject
	ForceServiceConnector forceService;
	
	@ModelAttribute("genre")
	public Genre init(@PathVariable String id) {
		return (Genre) musicService.findEntity(Genre.class,id);
	}
	
	@RequestMapping(value="/genre/{id}", method=RequestMethod.GET)
	public ModelAndView view(ModelAndView mv, @ModelAttribute Genre genre) {
		mv.addObject("genre",genre);
		mv.setViewName("genre_detail");
		return mv;
	}

	@RequestMapping(value="/genre/{id}", method=RequestMethod.POST)
	public String update(ModelAndView mv, @ModelAttribute Genre genre) {
		return "redirect:/genre/"+musicService.saveGenre(genre).getId();
	}
	
}

