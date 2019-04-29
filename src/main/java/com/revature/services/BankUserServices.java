package com.revature.services;
 import com.revature.models.*;
public interface BankUserServices {
	
	
	
	public Account SearchForAccount(String lookup );
	
	void Desposit(Account MyAccount, double input);
	
	 void Withdrawal(Account MyAccount , double input);
	
	
	void Transfer(String receive,double input );
	
	void Joint(String receive );

}
