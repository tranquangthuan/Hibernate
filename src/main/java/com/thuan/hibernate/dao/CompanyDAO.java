package com.thuan.hibernate.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.thuan.hibernate.entity.Company;
import com.thuan.hibernate.entity.CompanyEmployee;

public class CompanyDAO {
	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

	public Company findById(int id) {
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Company company = session.find(Company.class, 1);

		t.commit();
		factory.close();
		session.close();

		return company;
	}

	public Company findByIdWithEmployee(int id) {
		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Company company = session.find(Company.class, 1);
		List<CompanyEmployee> listEmployee = company.getEmployees();
		listEmployee.size();

		t.commit();
		factory.close();
		session.close();

		return company;
	}

}
