package com.customerfeedback.customerfeedback;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="login")
public class login {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int loginid;
	@JsonProperty("username")
	private String username;
	
	@JsonProperty("password")
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginid() {
		return loginid;
	}
	public void setLoginid(int loginid) {
		this.loginid = loginid;
	}
	login(){
		
	}
	login(int loginid, String username, String password){
		this.loginid = loginid;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "login [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
