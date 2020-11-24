package com.thuan.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.thuan.hibernate.entity.User;
import com.thuan.hibernate.utils.HibernateUtils;

public class UserDAO {

	private SessionFactory factory;

	public Integer save(User User) {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer UserId = null;

		try {
			transaction = session.beginTransaction();
			UserId = (Integer) session.save(User);

			System.out.println("Save User success " + User);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return UserId;
	}

	public List<User> getAll() {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		List<User> users = new ArrayList<User>();
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(User.class);
			users = criteria.list();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return users;
	}

	public void checkSLC(int userId) {
		factory = HibernateUtils.getSessionFactory();
		Session session1 = factory.openSession();
		Session session2 = factory.openSession();
		Transaction transaction1 = null;
		Transaction transaction2 = null;

		try {
			transaction1 = session1.beginTransaction();
			transaction2 = session2.beginTransaction();
			User user1 = session1.get(User.class, userId);
			System.out.println("Session 1 " + user1);

			User user2 = session2.get(User.class, userId);
			System.out.println("Session 2 " + user2);

		} catch (Exception e) {
			// TODO: handle exception
			if (transaction1 != null) {
				transaction1.rollback();
			}
			if (transaction2 != null) {
				transaction2.rollback();
			}
		} finally {
			session1.close();
			session2.close();
		}

	}
}
