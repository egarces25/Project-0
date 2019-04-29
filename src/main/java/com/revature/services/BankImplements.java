package com.revature.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.models.BanksList;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import com.revature.view.MainMenu;

public class BankImplements implements BankUserServices {

	public void Desposit(Account MyAccount, double input) {
	
		if (input > 0) {
		try {	double update = MainMenu.account.getBalance()+input;
			 MainMenu.account.setBalance(update);
			 try ( Connection conn = ConnectionUtil.getConnection()) {
					String sql = "UPDATE bankaccount SET Balance = ? where myaccounts = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setDouble(1, update);
					ps.setString(2, MainMenu.account.getMyAccounts());
				

					ps.executeUpdate();
					}
				catch (SQLException e) {
					e.printStackTrace();
				}
			 System.out.println("Your balance is now: "+MainMenu.account.getBalance());
			
		}
		catch (NumberFormatException e) {
		System.out.println("Enter a valid number");
	}}
	else {
			System.out.println("Enter a correct value. Try again\n"); 
			MainMenu.printOptions();
			
		}
			
		
	}
		

	public void  Withdrawal(Account MyAccount, double input) {
		// TODO Auto-generated method stub
		
		if(input < MyAccount.getBalance()) {
			try {
			double update = MainMenu.account.getBalance()-input;
			MainMenu.account.setBalance(update);
			 
			
			try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "UPDATE bankaccount SET Balance = ? where myaccounts = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setDouble(1, update);
					ps.setString(2, MyAccount.getMyAccounts());
				

					ps.executeUpdate();
					}
				catch (SQLException e) {
					e.printStackTrace();
				}
		
			System.out.println("Your balance is now: "+MainMenu.account.getBalance());
				}catch (NumberFormatException e ) {
					
				}
		
		
	}}
		
		
	

	public void Transfer(String receive,double input) {
		// TODO Auto-generated method stub
		Account otherAccount = null;
		for(Account a: BanksList.getAccounts())
		{
			if(a.getMyAccounts().equals(receive))
			{
				otherAccount =a;
			}
		}
			
		if (input <= MainMenu.account.getBalance()) {
			double subtract = MainMenu.account.getBalance()-input;
			MainMenu.account.setBalance(subtract);
			System.out.println(MainMenu.account);
			 try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "UPDATE bankaccount SET Balance = ? where myaccounts = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setDouble(1, subtract);
					ps.setString(2, MainMenu.account.getMyAccounts());
				

					ps.executeUpdate();
					}
				 catch (SQLException e) {
					e.printStackTrace();
				}
			double add = otherAccount.getBalance()+input;
			otherAccount.setBalance(add);
			System.out.println(otherAccount);
			System.out.println("");
			 try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "UPDATE bankaccount SET Balance = ? where myaccounts = ?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setDouble(1, add);
					ps.setString(2, otherAccount.getMyAccounts());
				

					ps.executeUpdate();
					}
				 catch (SQLException e) {
					e.printStackTrace();
				}
			
		}
	}

	public void Joint(String receive) {
		// TODO Auto-generated method stub
		User otherUser = new User();
		for(User a: BanksList.getUsers())
		{
			try {
				if(a.getUsername().equals(receive))
				{
					otherUser = a;
				}
			}
			catch(NullPointerException e)
			{
				continue;
			}
			
		}
		otherUser.getUsersList().add(MainMenu.account);
		System.out.println("You successfully joined ");
		
		 try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "INSERT INTO join_map VALUES (?, ?) ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, receive);
				ps.setString(2, MainMenu.account.getMyAccounts());
			

				ps.executeUpdate();
				}
			 catch (SQLException e) {
				e.printStackTrace();
			}
		
	}

	public Account SearchForAccount(String lookup) {
		// TODO Auto-generated method stub
		
		for (Account i : BanksList.getAccounts()) {
			if(i.getMyAccounts().equals(lookup))
			{
				MainMenu.account=i;
			}
			
		}
		return MainMenu.account;
		
		
		
	
		
	}








	
	
}
