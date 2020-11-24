package com.thuan.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.thuan.hibernate.entity.Company;
import com.thuan.hibernate.entity.CompanyEmployee;

public class CompanyEmployeeMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Company company = new Company();
		company.setName("Google");
		session.save(company);

		CompanyEmployee emp1 = new CompanyEmployee();
		emp1.setName("kai");
		emp1.setCompany(company);

		CompanyEmployee emp2 = new CompanyEmployee();
		emp2.setName("sena");
		emp2.setCompany(company);

		session.save(emp1);
		session.save(emp2);
		t.commit();
		System.out.println("successfully saved");
		System.out.println(company);
		System.out.println(emp1);
		System.out.println(emp2);
		factory.close();
		session.close();
	}

}
