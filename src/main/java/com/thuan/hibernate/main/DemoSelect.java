package com.thuan.hibernate.main;

import java.util.List;

import com.thuan.hibernate.dao.CompanyDAO;
import com.thuan.hibernate.entity.Company;
import com.thuan.hibernate.entity.CompanyEmployee;

public class DemoSelect {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CompanyDAO companyDAO = new CompanyDAO();
		int companyId = 1;
	    //Company company = companyDAO.findById(companyId);
		Company company = companyDAO.findByIdWithEmployee(companyId);
		if (company != null) {
			List<CompanyEmployee> listEmployee = company.getEmployees();
//	      List<Employee> listEmployee = companyDAO.getListEmployeeByCompany(company);
			for (CompanyEmployee emp : listEmployee) {
				System.out.println(emp);
			}
		} else {
			System.out.println("Not found company with id = " + companyId);
		}

	}

}
