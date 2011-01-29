package com.jesper.music.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

	@RequestMapping(value="/clear/logoutSuccess", method=RequestMethod.GET)
	public String logoutSuccess (ModelAndView mv, HttpServletRequest request, HttpServletResponse response) {
		// Do any additional cleanup that might be prudent...
		
		return "logoutSuccess";
	}
}
