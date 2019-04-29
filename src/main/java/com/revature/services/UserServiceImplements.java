package com.revature.services;
import com.revature.view.MainMenu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.models.BanksList;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserServiceImplements implements UserServices{

	public void SignUp(String username, String password, String firstName, String lastName) {
		// TODO Auto-generated method stub
		 User newAccount = new User();
		 if(UserLookUp(username) ==  null) {
			 newAccount.setUsername(username);
			 newAccount.setPassword(password);
			 newAccount.setFirstName(firstName);
			 newAccount.setLastName(lastName);
			 
			 BanksList.getUsers().add(newAccount);
			 
			 
			 try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "INSERT INTO useraccounts(user_name , pass_word, first_name , last_name)  VALUES (?,?,?,?) ";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, username);
					ps.setString(2, password);
					ps.setString(3, firstName);
					ps.setString(4,lastName);
				

					ps.executeUpdate();
					}
				 catch (SQLException e) {
					e.printStackTrace();
				}
			 
		 }
		
	}

	

	public void Login(User user, String username, String password) {
		// TODO Auto-generated method stub
		boolean found = false;
		try {
			 for(int i=0; i<BanksList.getUsers().size(); i++) {
			  if(BanksList.getUsers().get(i).getUsername().equals(username)) {
				  found = true;
				  MainMenu.user = BanksList.getUsers().get(i);
			  }
				 
			 }
			 if(!found)
			 {
				 System.out.println("Invalid Login try again.");
					
			 }
			 
			
		}catch(NullPointerException e) {
			System.out.println("Invalid Login try again.");
			MainMenu.Menu();
			System.out.println("\n");
		}
		
	}

	public User UserLookUp(String username) {
		// TODO Auto-generated method stub
		User searched = new User();
		for(User p: BanksList.getUsers()) {
			if(p.getUsername().equals(username))
			{
				searched = p;
				
				return searched;
			}
		}
		return null;
	
	}
	
	public User PasswordLookUp(String password) {
		
		User searched = new User();
		for(User p: BanksList.getUsers()) {
			if(p.getPassword().equals(password))
			{
				searched = p;
				
				return searched;
			}
		}
		return null;
		
		
		
	}
	
	public void CreateAccount(String username) {
		double balance = 0;
		Account newAccount = new Account();
		User  foundUser = new User();
		if (UserLookUp(username)!= null) {
			
			foundUser = UserLookUp(username);
			
			foundUser.getUsersList().add(newAccount);
			
			BanksList.getAccounts().add(newAccount);
		
		}
		System.out.println("Account Created");
		
		
		 try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "INSERT INTO bankaccount(myaccounts,balance) VALUES (?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, newAccount.getMyAccounts());
				ps.setDouble(2,balance);
			

				ps.executeUpdate();
				String sql2 = "INSERT INTO join_map(user_account,bank_account) VALUES (?,?)";
				PreparedStatement ps2 = conn.prepareStatement(sql2);
				ps2.setString(2, newAccount.getMyAccounts());
				ps2.setString(1,foundUser.getUsername());
			

				ps2.executeUpdate();
				}
			 catch (SQLException e) {
				
			}
		 System.out.println(foundUser.getUsersList());
		 MainMenu.printOptions();
	}
	
	public void getAllAccount(User user) {
		if(user.getUsersList().isEmpty()) {
			System.out.println("You dont have account yet");
		}
		else {
			for(Account account: user.getUsersList()) {
				System.out.println(account.toString());
			}

		}
	}
//	public Account selectAccount(User user, int number) {
//		return user.getUsersList().get(number-1);
//	}

}
