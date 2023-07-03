package com.fintech.beans;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	private Integer UserID;
	private String UserName;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(Integer userID, String userName) {
		super();
		UserID = userID;
		UserName = userName;
	}
	public Integer getUserID() {
		return UserID;
	}
	public void setUserID(Integer userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	@Override
	public String toString() {
		return "Users [ UserName=" + UserName + "]\n";
	}
	
	

}
