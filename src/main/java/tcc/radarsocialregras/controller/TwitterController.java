package tcc.radarsocialregras.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TwitterController {
	@RequestMapping(value = "/twitter", method = RequestMethod.GET)
	public void index(){ 
		System.out.println("Carregando o twitter"); 
//		return "index";
	}
}
