package tcc.radarsocialregras.repository;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;

import com.mongodb.AggregationOutput;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import tcc.radarsocial.dao.FacebookDao;
import tcc.radarsocial.model.Pagina;
import tcc.radarsocial.model.PostFacebook;
import tcc.radarsocialregras.util.Util;

public class FacebookRepository {

	public static String getAll(){
		
		FacebookDao dao = new FacebookDao();		
		DBCursor cursor = dao.buscarTodosDadosFacebook();
		int count = cursor.count();
	
        String serialize = JSON.serialize(cursor);
        System.out.println(serialize);
		return serialize;
		
	}
	
    public static AggregationOutput getTodosPortais(){
        
        
        FacebookDao dao = new FacebookDao();
        AggregationOutput portais = dao.buscarTodosPortaisSemFiltro();
        
//        String serialize = JSON.serialize(portais);
        System.out.println(portais.results());
        return portais;        
        
    }

	
	public static String getPortais(String portal,String dataInicial,String dataFinal,String link){
		
		
		FacebookDao dao = new FacebookDao();
		AggregationOutput portais = dao.buscarTodosPortais(portal,dataInicial,dataFinal,link);
		
//		String serialize = JSON.serialize(portais);
	    System.out.println(portais.results());
		return portais.results().toString();		
		
	}
	
	public static String buscaPorFiltro(String portal,String dataInicial, String dataFinal, String link) throws ParseException{
		
		
		FacebookDao dao = new FacebookDao();		
		DBCursor cursor = dao.buscaPorFiltro(portal,dataInicial,dataFinal,link);

        String serialize = JSON.serialize(cursor);
        System.out.println(serialize);
		return serialize;
		
	}
	
	public static String buscaPorFiltroFeeds(String portal,String dataInicial, String dataFinal, String link) throws ParseException{
		
		
		FacebookDao dao = new FacebookDao();		
		DBCursor cursor = dao.buscaPorFiltroFeeds(portal,dataInicial,dataFinal,link);

        String serialize = JSON.serialize(cursor);
        System.out.println(serialize);
		return serialize;
		
	}
}
