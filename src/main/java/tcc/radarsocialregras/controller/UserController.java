package tcc.radarsocialregras.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tcc.radarsocialregras.dao.UserDao;
import tcc.radarsocialregras.model.Role;
import tcc.radarsocialregras.model.User;

@Controller
public class UserController {
	
	@RequestMapping("/salvaUsuario")
	public void salvaUsuario(@RequestParam("nome") String nome,@RequestParam("login") String login,
				@RequestParam("senha") String senha){
		
		UserDao userDao = new UserDao();
		User user = new User();
		user.setLogin(login);
		user.setName(nome);
		user.setPassword(senha);
		
//		Role r = new Role();
//		r.setName("ROLE_ADMIN");
//		
//		List<Role> role = new ArrayList<Role>();
//		role.add(r);
		
//		user.setRoles(role);
		userDao.insertUser(user);
		
	}
}

