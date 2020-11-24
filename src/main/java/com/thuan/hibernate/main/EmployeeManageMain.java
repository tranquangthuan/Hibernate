package com.thuan.hibernate.main;

import com.thuan.hibernate.service.EmployeeService;

public class EmployeeManageMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EmployeeService service = new EmployeeService();
		// initData one time
		//service.initData();

		//System.out.println("------All employee------");
		//service.printEmployees(service.getAll());
		//service.checkFLCIn2SessionWithClearSession(1);
		service.checkFLCClearSession(1);
	}

}
