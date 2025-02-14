package com.br.projEsig.core.security;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.br.projEsig.service.AuthService;

@ManagedBean(name="authController")
@ViewScoped
public class AuthController implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	AuthService service = new AuthService();
	
	private String name;
	private String email;
	private String password;
	private String repassword;
	
	public AuthController() {
		
		
		if (AuthUser.currentUserName.equals("")) {
			System.out.println("\n\n\n\n\n eh nulo");
		}
		
		this.name = AuthUser.currentUserName;
		this.email = AuthUser.currentUserEmail;
		this.password = AuthUser.currentUserPassword;
		this.repassword = "";
	}
	
	public String signIn() {
		if(service.signIn(email, password)) {
			if (AuthUser.currentUserIsAdmin) {
				return "home?faces-redirect=true";
			}
			
			return "noAdminHome?faces-redirect=true";
		}
		
		return "login?faces-redirect=true"; 
	}
	
	public String signUp() {
		if(service.signUp(email, password, repassword, name)) {
			return "login?faces-redirect=true"; 
		}
		
		return "register?faces-redirect=true";
	}
	
	public String signOut() {
		service.signOut();
		
		return "login?faces-redirect=true";
	}
	
	public String redirectToRegister() { 
	    return "register?faces-redirect=true"; 
	}
	
	public String redirectToLogin() { 
	    return "login?faces-redirect=true"; 
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepassword() {
		return repassword;
	}
	
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
