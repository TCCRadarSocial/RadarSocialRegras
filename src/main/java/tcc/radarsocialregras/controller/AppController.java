package tcc.radarsocialregras.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/home")
	public String index(){
		
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(){		
		return "login";
	}
	
//	@RequestMapping("/logout")
//	public void logout(HttpServletResponse response){
//		try {
//			response.sendRedirect("/login");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
////		return "login";
//	}
}
