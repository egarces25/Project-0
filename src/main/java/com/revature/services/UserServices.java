package com.revature.services;

import com.revature.models.User;

public interface UserServices {
	
     public User UserLookUp(String username);
	public User PasswordLookUp(String password);
	void SignUp( String username, String password, String firstName, String lastName);
	
	void CreateAccount(String  username);

	public void Login(User user, String username, String password);
	
	 

}
