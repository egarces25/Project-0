package com.revature.launcher;
import com.revature.dao.UserDao;
import com.revature.view.*;
public class Launcher  {
	public static void main(String[] args) {
		UserDao dao=new UserDao();
		dao.getBankAccount();
		dao.getUser();
		MainMenu.Menu();
		
}
}