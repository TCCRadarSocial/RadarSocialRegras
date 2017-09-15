package tcc.radarsocialregras.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tcc.radarsocial.servico.IntegracaoFacebook;
import tcc.radarsocial.servico.IntegracaoTwitter;
import twitter4j.TwitterException;

@Controller
public class RedeSocialController {
	
	@RequestMapping("/salvaRedeSocial")
	public ModelAndView salvaRedeSocial(@RequestParam("nome") String nome,@RequestParam("tipo") String tipo) 
			throws TwitterException{
		ModelAndView model = new ModelAndView("home");
		
		if(tipo.equals("facebook")){
			IntegracaoFacebook login = new IntegracaoFacebook("1818125321786434", "874b37094b726ca18b0bc7684cf4c757");
			if(login.hasPage(nome)){
				try {
					login.retornaJson(nome.toLowerCase());
					model.addObject("mensagem", "Facebook adicionado.");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{				
				model.addObject("mensagem", "Página facebook não existe ou verifique sua conexão.");
			}
			
		}else if(tipo.equals("twitter")){
			IntegracaoTwitter intTwitter = new IntegracaoTwitter();
			if(intTwitter.hasTwitter(intTwitter.autenticar(),nome)){
				System.out.println("twitter existe");
			}
		}
		
		return model;
		
	}

}
