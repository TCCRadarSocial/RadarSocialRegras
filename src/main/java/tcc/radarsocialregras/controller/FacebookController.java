package tcc.radarsocialregras.controller;

import java.text.ParseException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.AggregationOutput;

import tcc.radarsocialregras.repository.FacebookRepository;

@Controller
public class FacebookController {

	@RequestMapping(value = "/facebook", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public @ResponseBody String facebook(){ 
		
		String response = FacebookRepository.getAll();
		return response;
	}
	
	@RequestMapping(value = "/facebookPortais", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String facebookPortais(@RequestBody String body){ 
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = FacebookRepository.getPortais(jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("link"));
		}
		return response.toString();
	}
	
	
    @RequestMapping(value = "/facebookTodosPortais", method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public @ResponseBody String facebookPortais(){ 
        
        AggregationOutput response = FacebookRepository.getTodosPortais();
//        return response.toString();
        return response.results().toString();
    }

	
	@RequestMapping(value = "/facebookSearch", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String buscaPorFiltros(@RequestBody String body) throws JSONException, ParseException{ 
		
//		String[] portal = body.split(":");
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = FacebookRepository.buscaPorFiltro(jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("link"));
		}
		
		return response.toString();
	}
	@RequestMapping(value = "/facebookSearchFeeds", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
	public @ResponseBody String buscaPorFiltrosFeeds(@RequestBody String body) throws JSONException, ParseException{ 
		
//		String[] portal = body.split(":");
		
		JSONArray array = new JSONArray(body); 
		String response = null;
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    response = FacebookRepository.buscaPorFiltroFeeds(jsonObj.getString("portal"),jsonObj.getString("dataInicial"),jsonObj.getString("dataFinal"),jsonObj.getString("link"));
		}
		
		return response.toString();
	}
}
