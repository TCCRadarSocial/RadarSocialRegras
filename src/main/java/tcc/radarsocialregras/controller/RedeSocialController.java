package tcc.radarsocialregras.controller;

import java.text.ParseException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tcc.radarsocial.dao.RedeSocialDao;
import tcc.radarsocial.servico.IntegracaoFacebook;
import tcc.radarsocial.servico.IntegracaoTwitter;
import twitter4j.TwitterException;

@Controller
public class RedeSocialController {
	
	
	@RequestMapping("/redesSociais")
	public String indexRedes(){
				
		return "redes.sociais";
		
	}
	
	@RequestMapping("/salvaRedeSocial")
	public ModelAndView salvaRedeSocial(@RequestParam("nome") String nome,@RequestParam("tipo") String tipo) 
			throws TwitterException{
		ModelAndView model = new ModelAndView("home");
		
		if(tipo.equals("facebook")){
			IntegracaoFacebook login = new IntegracaoFacebook("1818125321786434", "874b37094b726ca18b0bc7684cf4c757");
			if(login.hasPage(nome)){
				Thread t = new Thread(new Runnable() {
				    public void run() {
				        try {
				            login.retornaJson(nome.toLowerCase());
				        } catch (ParseException e) {
				            e.printStackTrace();
				        }
				    }
				});

				t.start();

				model.addObject("mensagem", "Facebook adicionado.");
			}else{				
				model.addObject("mensagem", "Página facebook não existe ou verifique sua conexão.");
			}
			
		}else if(tipo.equals("twitter")){
			IntegracaoTwitter intTwitter = new IntegracaoTwitter();
			if(intTwitter.hasTwitter(intTwitter.autenticar(),nome)){
				System.out.println("twitter existe");
				intTwitter.buscarDadosTwitter(intTwitter.autenticar(), nome);
			}
			else{
				System.out.println("twitter nao existe");
				model.addObject("mensagem", "Página facebook não existe ou verifique sua conexão.");
			}
		}
		
		return model;
		
	}
	
	@RequestMapping(value="/listaRedes", method = RequestMethod.POST, produces = {"application/json" }) 
	public ModelAndView list(){ 
		ModelAndView modelAndView = new ModelAndView("usuarios"); 
		RedeSocialDao dao =  new RedeSocialDao();
		System.out.println(dao.listar());
		modelAndView.addObject("redesSociais", dao.listar()); 
		return modelAndView; 
	}

}
