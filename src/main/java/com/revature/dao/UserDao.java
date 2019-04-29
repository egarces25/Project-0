package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.models.Account;
import com.revature.models.BanksList;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class UserDao {
	
		public void safeSaveUser(User user) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "INSERT INTO useraccounts (first_name, last_name, user_name,pass_word ) VALUES " + 
			"(?, ?, ?, ?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getUsername());
				ps.setString(4, user.getPassword());
	
				ps.executeUpdate();
				
			}
			catch(SQLException e) {
				e.printStackTrace();
				}
		   }
			
		public void SaveBankAccounts(Account accounts) {
			try (Connection conn = ConnectionUtil.getConnection()) {
				String sql = "INSERT INTO BankAccount (MyAccounts, Balance) VALUES " + 
			"(?, ?) ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setDouble(1, accounts.getBalance());
				ps.setString(2, accounts.getMyAccounts());
				
				
				ps.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
			}
			
		
		public void getUser() {
				

				try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "SELECT * FROM useraccounts";
					PreparedStatement ps = conn.prepareStatement(sql);


					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						
						String username = rs.getString("user_name");
						String firstName = rs.getString("first_name");
						String lastName = rs.getString("last_name");
						String password = rs.getString("pass_word");
						User user = new User();
						user.setUsername(username);
						user.setLastName(lastName);
						user.setFirstName(firstName);
						user.setPassword(password);
						
						String sql2 = "select bank_account from join_map where user_account = ?"; 
						PreparedStatement ps2 = conn.prepareStatement(sql2);
						ps2.setString(1,user.getUsername());
						
						ResultSet rs2 = ps2.executeQuery();
						while(rs2.next()) {
			
							String accountName = rs2.getString("bank_account");
							
							//System.out.println(accountName);
							for(Account account:BanksList.getAccounts())
							{
								if(account.getMyAccounts().equals(accountName))
								{
									Account userAccount = new Account();
									userAccount.setMyAccounts(accountName);
									
									for(Account a: BanksList.getAccounts())
									{
										if(a.getMyAccounts().equals(accountName))
										{
											userAccount.setBalance(a.getBalance());
										}
									}
											//System.out.println(user);
											user.getUsersList().add(userAccount);
											
										
								
								
									
								}
							}
						
						
					}
						BanksList.getUsers().add(user);
						
				}
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			public void getBankAccount() {

				try (Connection conn = ConnectionUtil.getConnection()) {
					String sql = "SELECT * FROM BankAccount";
					PreparedStatement ps = conn.prepareStatement(sql);

					

					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						
						String myAccount = rs.getString("myAccounts");
						double Balance = rs.getDouble("Balance");
						Account account  = new Account();
						account.setMyAccounts(myAccount);
						account.setBalance(Balance);
						BanksList.getAccounts().add(account);
						
						
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

			
		
		
	}	
	

}
