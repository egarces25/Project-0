package com.revature.view;
import com.revature.services.*;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.BanksList;
import com.revature.models.User;
public class MainMenu {
  private static Scanner input = new Scanner(System.in);
	private static BankImplements insert = new BankImplements();
	 private static UserServiceImplements logging = new UserServiceImplements();
	 public static User user=new User();
	 public static Account account;
	public static  void  printOptions() {
		System.out.println("What would you like to do today\n");
		System.out.println("1.Create Account");
		System.out.println("2.Select Account");
		System.out.println("3.Deposit");
		System.out.println("4.Withdraw");
		System.out.println("5.Transfer");
		System.out.println("6.Join");
		System.out.println("7.Quit");
		
		
		
		int select = input.nextInt();
		
		switch(select) {
		case 1: CreateAccount();
		break;
		case 2:SelectAccount();
		break;
		case 3:   Deposit();
		break;
		case 4: Withdraw();
		break;
		case 5: Transfer();
		break;
		case 6: Joint();
		break;
		case 7: Quit();
		default: ;
	}
	}
	
	
	
public static void Login() {
	
System.out.println("Enter your Login\n");
System.out.println("Enter UserName: ");
String username = input.next();


 
System.out.println("Enter Password");
  String password = input.next(); 
  
if (username != null && password != null) {
	logging.Login(MainMenu.user,username, password);
	
	MainMenu.ChooseAccount();
	
	}
}

	 
  


public static void SignUp(){
	
	
	System.out.println("Enter First Name: ");
	String firstName = input.next();
	
	System.out.println("Enter Last Name: ");
	String lastName = input.next();
	
	
	System.out.println("Create Username: ");
	String username = input.next();
	System.out.println("Create Password: ");
	String password = input.next();
	
	logging.SignUp(username, password, firstName, lastName);
	MainMenu.Menu();
	
	
}
public static void Deposit() {
 
 
 System.out.println("How much much would you deposit");
     String amount = input.next();
     
     insert.Desposit(MainMenu.account, Double.parseDouble(amount));
     System.out.println();
     MainMenu.printOptions();
  
 
}

public static  void Withdraw() {
	 System.out.println("How much much would you like to withdraw");
     String amount = input.next();
 
     insert.Withdrawal(MainMenu.account, Double.parseDouble(amount));
     MainMenu.printOptions();
}


public static void Transfer() {
	System.out.println("Enter which account you want to transfer: ");
	String transfer = input.next();
	System.out.println("How much would you like to send: ");
	String amount = input.next();
	insert.Transfer(transfer, Double.parseDouble(amount));
	MainMenu.printOptions();
	
	
	



}
public static void Menu() {
	System.out.println("Welcome to the Bank App\n");
	System.out.println("1. Login");
	System.out.println("2. Sign up");
	System.out.println("3. Quit");

	int selection = input.nextInt();
	
	switch(selection) {
	case 1:  Login();
	break;
	case 2: SignUp();
	break;
	case 3: Quit();
	break;
	default:;
}


}


public static void Joint() {
	System.out.println("Enter the user that you want to join your account with");
	String user = input.next();
	insert.Joint(user);
	MainMenu.printOptions();
	
}


public static void CreateAccount() {
	logging.CreateAccount(user.getUsername());
	



}

public static void SelectAccount(){
	System.out.println("Enter account number to do transactions with: ");
	logging.getAllAccount(MainMenu.user);
	String choice = input.next();
	insert.SearchForAccount(choice);
	printOptions();
}

public static void Quit() {
	System.out.println("GoodBye.\n");
	MainMenu.account = null;
	MainMenu.user =  null ;
	MainMenu.Menu();
	
}

//public static void GetAllAccount() {
//	System.out.println(user);
//	System.out.println();
//	logging.getAllAccount(user);
//	int index =0;
//	String command;
//	command = input.nextLine();
//	try {
//		index = Integer.parseInt(command);
//		account = logging.selectAccount(user, index);
//	}
//	catch(Exception e)
//	{
//		System.out.println("Invalid command.");
//		printOptions();
//	}
//}
public static  void  ChooseAccount() {
	System.out.println("How would you like start\n");
	System.out.println("1.Create Account");
	System.out.println("2.Select Account");

	
	

	int select = input.nextInt();
	
	switch(select) {
	case 1: CreateAccount();
	break;
	case 2:SelectAccount();
	break;
	default: ;
}
}


}
