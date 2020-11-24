package com.thuan.hibernate.service;

import java.util.List;

import com.thuan.hibernate.dao.UserDAO;
import com.thuan.hibernate.entity.User;

public class UserService {
	private UserDAO userDAO = new UserDAO();

	public Integer save(User User) {
		return userDAO.save(User);
	}

	public List<User> getAll() {
		return userDAO.getAll();
	}

	public void printUsers(List<User> Users) {
		for (User User : Users) {
			System.out.println(User);
		}
	}

	public void initData() {
		User emp1 = new User("Thuan", "abc@gmail.com", "Da Nang");
		User emp2 = new User("An", "abc@gmail.com", "Quang Tri");
		User emp3 = new User("Binh", "abc@gmail.com", "Hue");
		User emp4 = new User("Thanh", "abc@gmail.com", "Quang Nam");
		User emp5 = new User("Tuan", "abc@gmail.com", "Da Nang");
		User emp6 = new User("Chuong", "abc@gmail.com", "Binh Dinh");
		userDAO.save(emp1);
		userDAO.save(emp2);
		userDAO.save(emp3);
		userDAO.save(emp4);
		userDAO.save(emp5);
		userDAO.save(emp6);
	}

	public void checkSLC(int userId) {
		userDAO.checkSLC(userId);
	}

}
