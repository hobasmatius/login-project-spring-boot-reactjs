package com.login.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavigationController {
	
	@RequestMapping(value = "/")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value = "/home")
	public String home() {
		return "home";
	}

}
