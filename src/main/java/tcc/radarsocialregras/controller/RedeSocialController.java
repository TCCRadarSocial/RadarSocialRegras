package tcc.radarsocialregras.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tcc.radarsocial.servico.IntegracaoFacebook;
import tcc.radarsocial.servico.IntegracaoTwitter;
import twitter4j.TwitterException;

@Controller
public class RedeSocialController {
	
	@RequestMapping("/salvaRedeSocial")
	public void salvaRedeSocial(@RequestParam("nome") String nome,@RequestParam("tipo") String tipo) throws TwitterException{
		if(tipo.equals("facebook")){
			IntegracaoFacebook login = new IntegracaoFacebook("1818125321786434", "874b37094b726ca18b0bc7684cf4c757");
			if(login.hasPage(nome)){
				System.out.println("facebook existe");
			}
			
		}else if(tipo.equals("twitter")){
			IntegracaoTwitter intTwitter = new IntegracaoTwitter();
			if(intTwitter.hasTwitter(intTwitter.autenticar(),nome)){
				System.out.println("twitter existe");
			}
		}
		
	}

}
