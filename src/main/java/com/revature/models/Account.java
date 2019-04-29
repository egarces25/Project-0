package com.revature.models;

public class Account {
	static int accountNumber;
	private double balance;
	private String myAccounts;
	
	
	public Account() {
		super();
		accountNumber++;
		// TODO Auto-generated constructor stub
		this.myAccounts = Integer.toString(accountNumber);
		
	}



	public Account(double balance) {
		super();
		this.balance = balance;
		
	}



	@Override
	public String toString() {
		return "Account [balance=" + balance + ", myAccounts=" + myAccounts + "]";
	}



	public String getMyAccounts() {
		return myAccounts;
	}



	public void setMyAccounts(String myAccounts) {
		this.myAccounts = myAccounts;
	}



	public static int getAccountNumber() {
		return accountNumber;
	}



	public static void setAccountNumber(int accountNumber) {
		Account.accountNumber = accountNumber;
	}



	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((myAccounts == null) ? 0 : myAccounts.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (myAccounts == null) {
			if (other.myAccounts != null)
				return false;
		} else if (!myAccounts.equals(other.myAccounts))
			return false;
		return true;
	}
	
	
	
	

}
