package com.thuan.hibernate.main;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.thuan.hibernate.entity.Category;
import com.thuan.hibernate.entity.Product;

public class ProductCategoryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
		Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();

		SessionFactory factory = meta.getSessionFactoryBuilder().build();
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		Category category = new Category("Electronic Device");
		Product product1 = new Product("Television");
		Product product2 = new Product("Iphone");
		Product product3 = new Product("Samsung Galaxy S9");

		Set<Product> products = new HashSet<>();
		products.add(product1);
		products.add(product2);
		products.add(product3);
		
		category.setProducts(products);

		session.save(category);
		t.commit();
		System.out.println("successfully saved");
		System.out.println(category);
		System.out.println(product1);
		System.out.println(product2);
		System.out.println(product3);
		factory.close();
		session.close();
	}

}
