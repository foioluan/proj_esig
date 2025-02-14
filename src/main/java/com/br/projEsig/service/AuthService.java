package com.br.projEsig.service;

import com.br.projEsig.core.security.AuthUser;
import com.br.projEsig.domain.User;

public class AuthService {
	private UserService userService = new UserService();
	
	public Boolean signIn(String email, String password) {
		User authUser = userService.findByEmail(email);
		
		if (authUser == null) {
			return false;
		}
		
		if (!authUser.getPassword().equals(password)) {
			return false;
		}
		
		AuthUser.currentUserName = authUser.getName();
		AuthUser.currentUserEmail = authUser.getEmail();
		AuthUser.currentUserPassword = authUser.getPassword();
		AuthUser.currentUserId = authUser.getId();
		AuthUser.currentUserIsAdmin = authUser.getIsAdmin();
		
		System.out.println("\n\n\nadm:" + AuthUser.currentUserIsAdmin);
		
		return true;
	}
	
	public Boolean signUp(String email, String password, String repassword, String name) {
		
		User authUser = userService.findByEmail(email);
		
		if(authUser != null) {
			return false;
		}
		
		if(!password.equals(repassword)) {
			return false;
		}
		
		authUser = new User();
		
		authUser.setEmail(email);
		authUser.setPassword(password);
		authUser.setName(name);
		
		userService.save(authUser);
		
		return true;
	}
	
	public void signOut() {
		AuthUser.currentUserName = "";
		AuthUser.currentUserEmail = "";
		AuthUser.currentUserPassword = "";
		AuthUser.currentUserId = (long) 0;
		AuthUser.currentUserIsAdmin = false;
	}
}
