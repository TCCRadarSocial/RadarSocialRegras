package tcc.radarsocialregras.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Override 
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**"); 
	}

	@Override 
	protected void configure(HttpSecurity http) throws Exception { 
		http.authorizeRequests() 
		.antMatchers("/").permitAll() 
		.anyRequest().authenticated() 
		.and() 
		.formLogin().and() 
		.httpBasic(); 
	}
	
	private UserDetailsService users;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception { 
		auth.userDetailsService(users). passwordEncoder(new BCryptPasswordEncoder()); 
	}



}

