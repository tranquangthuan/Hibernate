package com.thuan.hibernate.main;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.thuan.hibernate.entity.Company;
import com.thuan.hibernate.entity.CompanyEmployee;

public class FindCompanyEmployee {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Company company = session.find(Company.class, 1);
		System.out.println("--aaa");
		
		company.getEmployees().forEach(employee -> System.out.println(employee));

		System.out.println(company);

		System.out.println("--------CompanyEmployee---------");
		CompanyEmployee emp1 = session.find(CompanyEmployee.class, 1);
		System.out.println(emp1);
		t.commit();
		factory.close();
		session.close();
	}

}
