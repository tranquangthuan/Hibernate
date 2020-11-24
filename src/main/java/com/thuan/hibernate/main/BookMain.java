package com.thuan.hibernate.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.thuan.hibernate.entity.Book;
import com.thuan.hibernate.entity.Book2;

public class BookMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Book book = new Book();
		book.setBno(401);
		book.setBname("Java Complete Reference");
		book.setPrice(550);
		
		Book2 book2 = new Book2();
		book2.setBno(401);
		book2.setBname("Java Complete Reference - book 2");
		book2.setPrice(550);

		session.save(book);
		session.save(book2);
		t.commit();
		System.out.println("successfully saved");
		factory.close();
		session.close();
	}

}
