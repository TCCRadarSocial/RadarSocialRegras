package tcc.radarsocialregras.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/home")
	public String index(){
		
//		User user = Util.getUsuarioLogado();
//		long id = user.getId();
//		System.out.println(id);
//		
//		if(!user.getAuthorities().isEmpty()){
//			List<Role> roles = (List<Role>) user.getAuthorities();
//			
//			for(Role r: roles){
//				if(r.getAuthority().contains("ADMIN")){
//					return "index";
//				}else{
//					return "home";
//				}
//			}
//		}else{
//			return "home";
//		}
//			
//		return null;
		return "home";
	}
	
	@RequestMapping("/login")
	public String login(){		
		return "login";
	}
	
	@RequestMapping("/logout")
	public void logout(HttpServletResponse response){
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return "login";
	}
}
