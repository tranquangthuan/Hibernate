package com.thuan.hibernate.service;

import java.util.List;

import com.thuan.hibernate.dao.EmployeeDAO;
import com.thuan.hibernate.entity.Employee;

public class EmployeeService {
	private EmployeeDAO employeeDAO = new EmployeeDAO();

	public Integer save(Employee employee) {
		return employeeDAO.save(employee);
	}

	public Integer update(int employeeId, String name, String address) {
		return employeeDAO.update(employeeId, name, address);
	}

	public List<Employee> getAll() {
		return employeeDAO.getAll();
	}

	public void printEmployees(List<Employee> employees) {
		for (Employee employee : employees) {
			System.out.println(employee);
		}
	}

	public void deleteAll() {
		employeeDAO.deleteAll();
	}

	public void initData() {
		Employee emp1 = new Employee("Thuan", "Da Nang");
		Employee emp2 = new Employee("An", "Quang Tri");
		Employee emp3 = new Employee("Binh", "Hue");
		Employee emp4 = new Employee("Thanh", "Quang Nam");
		Employee emp5 = new Employee("Tuan", "Da Nang");
		Employee emp6 = new Employee("Chuong", "Binh Dinh");
		employeeDAO.save(emp1);
		employeeDAO.save(emp2);
		employeeDAO.save(emp3);
		employeeDAO.save(emp4);
		employeeDAO.save(emp5);
		employeeDAO.save(emp6);
	}

	public void checkFLC(int employeeId) {
		employeeDAO.checkFLC(employeeId);
	}

	public void checkFLCIn2Session(int employeeId) {
		employeeDAO.checkFLCIn2Session(employeeId);
	}
	
	public void checkFLCClearSession(int employeeId) {
		employeeDAO.checkFLCClearSession(employeeId);
	}

}
