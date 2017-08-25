package tcc.radarsocialregras.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	@PersistenceContext 
	private EntityManager em;

	@Override 
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
		String jpql = "select u from User u where u.login = :login";
		List<User> users = em.createQuery(jpql,User.class).setParameter("login", username).getResultList();
		
		if(users.isEmpty()){ 
			throw new UsernameNotFoundException ("O usuario "+username+" não existe"); 
			} 
		return users.get(0);
	}

}
