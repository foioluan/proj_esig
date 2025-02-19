package com.br.projEsig.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "user_table")
@SQLDelete(sql = "UPDATE user_table SET deleted_at = CURRENT_TIMESTAMP where id=?")
@Where(clause = "deleted_at IS NULL")
public class User extends GenericEntity{
	
	private String email;
	private String password;
	private String name;
	private Boolean isAdmin = false;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
