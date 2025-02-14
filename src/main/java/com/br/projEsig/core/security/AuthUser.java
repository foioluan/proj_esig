package com.br.projEsig.core.security;

public abstract class AuthUser {
	static public String currentUserName = "";
	static public String currentUserEmail = "";
	static public String currentUserPassword = "";
	static public Long currentUserId = (long) 0;
	static public boolean currentUserIsAdmin = false;
}
