package com.thuan.hibernate.main;

import com.thuan.hibernate.service.UserService;

public class UserManageMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserService userService = new UserService();
		// userService.initData();

		userService.printUsers(userService.getAll());
		userService.checkSLC(1);
	}

}
