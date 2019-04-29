package com.revature.models;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BanksList {
	
	 static List <User> Users = new ArrayList<User>();
	static Set<Account> Accounts = new HashSet<Account>();
	
	private static BanksList single_instance = null;
	
	
	private static void BankBuilder() {
		single_instance = new BanksList();
	}

	public static BanksList getBank()
			{
				if(single_instance == null)
				{
					BankBuilder();
				}
				return single_instance;
		
			}
	

	public static List<User> getUsers() {
		return Users;
	}

	public static void setUsers(List<User> users) {
		Users = users;
	}

	public static Set<Account> getAccounts() {
		return Accounts;
	}

	public static void setAccounts(Set<Account> accounts) {
		Accounts = accounts;
	}

	@Override
	public String toString() {
		return "BanksList [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
	
	

}
