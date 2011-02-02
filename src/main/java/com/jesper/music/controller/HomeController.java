package com.jesper.music.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jesper.music.model.Album;
import com.jesper.music.model.Artist;
import com.jesper.music.model.Genre;
import com.jesper.music.service.MusicService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	enum ManagedEntities {
		album (Album.class),
		artist (Artist.class),
		genre (Genre.class);
		
		private Class<? extends Object> clazz;
		
		ManagedEntities(Class<? extends Object> clazz) {
			this.clazz = clazz;
		}
		
		public String getClassName() { return clazz.getName(); }
	}
	
	@Inject
	MusicService musicService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value="/{entity}/", method=RequestMethod.GET)
	public ModelAndView entityList(ModelAndView mv, @PathVariable("entity") ManagedEntities entity, @RequestParam(value="q", required=false) String query) {
		long t=System.currentTimeMillis();
		mv.addObject("list", musicService.getList(entity.getClassName(),query));
		mv.setViewName(entity+"_list");
		logger.info("Controller method took "+(System.currentTimeMillis()-t)+" millis.");
		return mv;
	}
}

