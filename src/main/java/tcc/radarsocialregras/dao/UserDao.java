package tcc.radarsocialregras.dao;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import tcc.radarsocial.db.ConnectionFactory;
import tcc.radarsocialregras.model.User;

@Repository
public class UserDao implements UserDetailsService{
	
	DBCollection collection = ConnectionFactory.connectDB().getCollection("LoginUsuario");
	
	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				
		List<User> users = buscaUsuarioPorNome(username);
		
		return (UserDetails) users.get(0);
	}
	
	public List<User> buscaUsuarioPorNome(String username){
BasicDBList and = new BasicDBList();

		List<User> user = null;
		
		DBObject clauseLink = new BasicDBObject("name", username); 
		and.add(clauseLink);
			
		DBObject query = new BasicDBObject("$and", and);
		
		DBCursor cursor = collection.find(query);
		String serialize = JSON.serialize(cursor);
		
		JSONArray array = new JSONArray(serialize); 
		for(int i=0; i<array.length(); i++){
		    JSONObject jsonObj = array.getJSONObject(i);
		    User u = new User();
		    u.setName(jsonObj.get("name").toString());
		    user.add(u);
		}
		
		return user;
		
	}

}
