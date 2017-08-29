package tcc.radarsocialregras.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
//	@Override 
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/resources/**"); 
//	}
	
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }

	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		http.authorizeRequests() 
		.anyRequest().authenticated()
	    .and().formLogin().loginPage("/login").permitAll()
	    .and().httpBasic()
	    .and()
	    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")); 
		
		http.csrf().disable();

	}
	@Autowired
	private UserDetailsService users;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(users). passwordEncoder(new BCryptPasswordEncoder()); 
	}



}

