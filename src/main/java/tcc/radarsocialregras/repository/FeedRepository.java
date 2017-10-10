package tcc.radarsocialregras.repository;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;
import java.text.ParseException;

import com.mongodb.AggregationOutput;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import tcc.radarsocial.dao.FeedsDao;

public class FeedRepository {

	public static String buscaPorFiltro(String redeSocial,String portal,String dataInicial, String dataFinal,String orderBy, String palavraChave) throws ParseException{
			
			
			FeedsDao dao = new FeedsDao();		
			DBCursor cursor = dao.buscaPorFiltro(portal,dataInicial,dataFinal,redeSocial,orderBy,palavraChave);
	
	        String serialize = JSON.serialize(cursor);
	        System.out.println(serialize);
			return serialize;
			
		}
	
	public static String buscaPorFiltroLink(String link) throws ParseException{
		
		
		FeedsDao dao = new FeedsDao();		
		DBCursor cursor = dao.buscaPorFiltroPorLink(link);
	
	    String serialize = JSON.serialize(cursor);
	    System.out.println(serialize);
		return serialize;
		
	}
	
	public static String buscarComparativoAgregacaoRedes(String portalFacebook, String portalTwitter,String dataInicial,String dataFinal) throws ParseException{
		
		
		FeedsDao dao = new FeedsDao();		
		String comparativos = dao.buscarComparativoAgregacaoRedes(portalFacebook,portalTwitter,dataInicial,dataFinal);
	
//	    String serialize = JSON.serialize(cursor);
//	    System.out.println(serialize);
		return comparativos;
		
	}
	
	
}
