package tcc.radarsocialregras.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tcc.radarsocialregras.repository.FeedRepository;

@Controller
public class FeedController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void index(){ 
		System.out.println("Carregando os produtos"); 
//		return "index";
	}
}
