package tcc.radarsocialregras.controller;

import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.AggregationOutput;

import tcc.radarsocialregras.repository.FacebookRepository;
import tcc.radarsocialregras.repository.TwitterRepository;

@Controller
public class TwitterController {
	@RequestMapping(value = "/twitter", method = RequestMethod.GET)
	public void index(){ 
		System.out.println("Carregando o twitter"); 
//		return "index";
	}
	
	@RequestMapping(value = "/twitterTodosPortais", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody String twitterPortais(){ 
        
        AggregationOutput response = TwitterRepository.getTodosPortais();
        return response.results().toString();
    }
	
	@RequestMapping(value = "/twitterPortais", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String facebookPortais(@RequestBody String body){ 
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = TwitterRepository.getPortais(jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("link"));
		}
		return response.toString();
	}
	
	@RequestMapping(value = "/twitterSearch", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String buscaPorFiltros(@RequestBody String body) throws JSONException, ParseException{ 
		
//		String[] portal = body.split(":");
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = TwitterRepository.buscaPorFiltro(jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("link"));
		}
		
		return response.toString();
	}
	
	@RequestMapping(value = "/twitterSearchFeeds", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String buscaPorFiltrosFeeds(@RequestBody String body) throws JSONException, ParseException{ 
		
//		String[] portal = body.split(":");
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = TwitterRepository.buscaPorFiltroFeeds(jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("link"));
		}
		
		return response.toString();
	}
}
