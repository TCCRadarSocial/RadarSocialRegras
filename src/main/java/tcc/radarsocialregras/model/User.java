package tcc.radarsocialregras.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity 
public class User implements UserDetails{
		
	@Id 
	private String login; 
	
	private String password; 
	private String name; 
	
	public String getName() {
		return name;
	}
	@ManyToMany(fetch = FetchType.EAGER) 
	private List<Role> roles = new ArrayList<>();
	
	//outros getters e setters
	@Override 
	public String getPassword() { 
		return password; 
	}
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return roles; 
	}
	
	@Override 
	public String getUsername() {
		return login; 
	}
	@Override 
	public boolean isAccountNonExpired() {
		return true; 
		}
	@Override 
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override 
	public boolean isCredentialsNonExpired() { 
		return true; 
		}
	@Override 
	public boolean isEnabled() { 
		return true; 
		}

	public void setLogin(String login) {
		this.login = login;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
