package tcc.radarsocialregras.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.taglibs.standard.functions.Functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tcc.radarsocialregras.conf.JPAConfiguration;
import tcc.radarsocialregras.dao.UserDao;
import tcc.radarsocialregras.model.Role;
import tcc.radarsocialregras.model.User;

@Controller
@Transactional
public class UserController {
	
	@Autowired 
	private UserDao userDao;
	
	@RequestMapping("/usuarios")
	public String indexUsuario(){
				
		return "usuarios";
		
	}
	
	
	@RequestMapping("/salvaUsuario")
	public ModelAndView salvaUsuario(@RequestParam("nome") String nome,@RequestParam("login") String login,
				@RequestParam("senha") String senha){
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext( JPAConfiguration.class, UserDao.class);; 
		UserDao userDao = ctx.getBean(UserDao.class);
		PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class); 
		TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionAttribute());
		
		String mensagem = null;
		ModelAndView model = null;
		
		if(!login.equals("") && !nome.equals("") && !senha.equals("")){
		
			User user = new User();
			user.setLogin(login);
			user.setName(nome);
			user.setPassword(hashPassword(senha));
			
			Role r = new Role();
			r.setName("ROLE_USER");
			
			List<Role> role = new ArrayList<Role>();
			role.add(r);
			
			user.setRoles(role);
			userDao.save(user);
			
			try{
				txManager.commit(transaction); 
				mensagem = "";
			}
			catch(Exception erro){
				mensagem = "O usuario nao pode ser salvo";
			}
			finally{
				ctx.close();
			}
			
		}else{
			mensagem = "* Preencha todos os campos";
		}
		model = new ModelAndView("usuarios");
		model.addObject("mensagem", mensagem);
		
		return model;
		
	}
	
	@RequestMapping(value="/editaUsuario/{username}&{nome}",method = RequestMethod.GET)
	public ModelAndView editaUsuario(@PathVariable("username") String username,@PathVariable("nome") String nome){
		ModelAndView model = new ModelAndView("usuarios");
		model.addObject("valorLogin", username);
		model.addObject("valorNome", nome);
		
		return model;
		
	}
	
	@RequestMapping(value="/excluiUsuario/{username:.+}",method = RequestMethod.GET)
	public String excluirUsuario(@PathVariable(value="username") String username){
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext( JPAConfiguration.class, UserDao.class);; 
		UserDao userDao = ctx.getBean(UserDao.class);
		PlatformTransactionManager txManager = ctx.getBean(PlatformTransactionManager.class); 
		TransactionStatus transaction = txManager.getTransaction(new DefaultTransactionAttribute());
		
		User user = userDao.findLogin(username);
		userDao.delete(user);
		try{
			txManager.commit(transaction); 
		}
		catch(Exception erro){
			
		}
		finally{
			ctx.close();
		}
		
		return "usuarios";
	}
	
	@RequestMapping(value="/listaUsuario", method = RequestMethod.POST, produces = {"application/json" }) 
	public ModelAndView list(){ 
		ModelAndView modelAndView = new ModelAndView("usuarios"); 
		modelAndView.addObject("users", userDao.list()); 
		return modelAndView; 
	}

	
	public static String hashPassword(String password) {
		String salt = BCrypt.gensalt(12);
		String hashed_password = BCrypt.hashpw(password, salt);

		return(hashed_password);
	}
}

