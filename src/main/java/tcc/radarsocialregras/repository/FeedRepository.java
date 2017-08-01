package tcc.radarsocialregras.repository;

import java.text.ParseException;

import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import tcc.radarsocial.dao.FeedsDao;

public class FeedRepository {

public static String buscaPorFiltro(String redeSocial,String portal,String dataInicial, String dataFinal) throws ParseException{
		
		
		FeedsDao dao = new FeedsDao();		
		DBCursor cursor = dao.buscaPorFiltro(portal,dataInicial,dataFinal,redeSocial);

        String serialize = JSON.serialize(cursor);
        System.out.println(serialize);
		return serialize;
		
	}
}
