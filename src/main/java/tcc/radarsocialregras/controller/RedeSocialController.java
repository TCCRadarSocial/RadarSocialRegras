package tcc.radarsocialregras.controller;

import java.text.ParseException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mongodb.AggregationOutput;
import com.mongodb.util.JSON;

import tcc.radarsocial.dao.FacebookDao;
import tcc.radarsocial.dao.FeedsDao;
import tcc.radarsocial.dao.RedeSocialDao;
import tcc.radarsocial.dao.TwitterDao;
import tcc.radarsocial.servico.IntegracaoFacebook;
import tcc.radarsocial.servico.IntegracaoTwitter;
import tcc.radarsocialregras.conf.JPAConfiguration;
import tcc.radarsocialregras.dao.UserDao;
import tcc.radarsocialregras.model.User;
import twitter4j.TwitterException;

@Controller
public class RedeSocialController {
	
	
	@RequestMapping("/redesSociais")
	public String indexRedes(){
				
		return "redes.sociais";
		
	}
	
	@RequestMapping("/comparativo")
	public String indexComparativo(){
				
		return "comparativo";
		
	}
	
	@RequestMapping("/salvaRedeSocial")
	public ModelAndView salvaRedeSocial(@RequestParam("nome") String nome,@RequestParam("tipo") String tipo) 
			throws TwitterException, ParseException{
		ModelAndView model = new ModelAndView("redes.sociais");
		
		if(tipo.equals("facebook")){
			IntegracaoFacebook login = new IntegracaoFacebook("1818125321786434", "874b37094b726ca18b0bc7684cf4c757");
			if(login.hasPage(nome) && !nome.equals("")){
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
				try {
					Thread.sleep(4000);
					list();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				 //new ModelAndView("redirect:/listaRedes");
//				model.addObject("mensagem", "Facebook adicionado.");
			}else{				
				
				model.addObject("mensagem", "Preencha todos os campos corretamente ou verifique sua conexão.");
			}
			
		}else if(tipo.equals("twitter")){
			IntegracaoTwitter intTwitter = new IntegracaoTwitter();
			if(intTwitter.hasTwitter(intTwitter.autenticar(),nome) && !nome.equals("")){
				System.out.println("twitter existe");
				intTwitter.buscarDadosTwitter(intTwitter.autenticar(), nome);
				try {
					Thread.sleep(4000);
					list();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
//				System.out.println("twitter nao existe");
				model.addObject("mensagem", "Preencha todos os campos corretamente ou verifique sua conexão.");
			}
		}
		
		return model;
		
	}
	
	@RequestMapping(value="/listaRedes", method = RequestMethod.POST, produces = "application/json;charset=UTF-8") 
	public @ResponseBody String list(){ 
//		ModelAndView modelAndView = new ModelAndView("usuarios"); 
		TwitterDao daoTwitter =  new TwitterDao();
		FacebookDao daoFace =  new FacebookDao();
		
		AggregationOutput outputTwitter = daoTwitter.buscarTodosPortaisSemFiltro();
		AggregationOutput outputFace = daoFace.buscarTodosPortaisSemFiltro();
		
		
		String jsonTwitter =  outputTwitter.results().toString().replaceAll("\\[+\\]+","");
		String jsonFace = outputFace.results().toString().replaceAll("\\[+\\]+","");
		
		String serialize = null;
		
		if(jsonFace.equals(""))
			serialize = jsonTwitter;
		else if(jsonTwitter.equals(""))
			serialize = jsonFace;
		else
			serialize = "[" + jsonTwitter + ", " + jsonFace + "]";
		
		return serialize;

	}
	
	@RequestMapping(value="/excluiRede/{nome}&{tipo}",method = RequestMethod.GET)
	public String excluirRedeSocial(@PathVariable(value="nome") String nome,@PathVariable(value="tipo") String tipo){
		
		TwitterDao daoTwitter =  new TwitterDao();
		FacebookDao daoFace =  new FacebookDao();
		FeedsDao daoFeeds =  new FeedsDao();
		
		if(tipo.equals("facebook") && !nome.equals("")){
			
			daoFace.excluirRegistros(nome);
			daoFeeds.excluirRegistros(nome,tipo);
			
		}else if(tipo.equals("twitter") && !nome.equals("")){
			
			daoTwitter.excluirRegistros(nome);
			daoFeeds.excluirRegistros(nome,tipo);
		}
		
		return "redes.sociais";
	}

}
