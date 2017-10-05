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

import tcc.radarsocialregras.repository.FacebookRepository;
import tcc.radarsocialregras.repository.FeedRepository;
import static java.nio.charset.StandardCharsets.*;

@Controller
public class FeedController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void index(){ 
		System.out.println("Carregando os produtos"); 
//		return "index";
	}
	
	@RequestMapping(value = "/feedSearch", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String buscaPorFiltros(@RequestBody String body) throws JSONException, ParseException{ 
		
//		String[] portal = body.split(":");
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = FeedRepository.buscaPorFiltro(jsonObj.getString("redeSocial"),jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("orderBy"),jsonObj.getString("palavraChave"));
		}
		
		return response.toString();
	}
	
	@RequestMapping(value = "/feedSearchPublicacao", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String buscaPorFiltrosLink(@RequestBody String body) throws JSONException, ParseException{ 
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = FeedRepository.buscaPorFiltroLink(jsonObj.getString("link"));
		}
	
		return response.toString();
	}
	
}
