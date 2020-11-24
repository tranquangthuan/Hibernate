package com.thuan.hibernate.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.thuan.hibernate.entity.Employee;
import com.thuan.hibernate.utils.HibernateUtils;

public class EmployeeDAO {

	private SessionFactory factory;

	public Integer save(Employee employee) {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		Integer employeeId = null;

		try {
			transaction = session.beginTransaction();
			employeeId = (Integer) session.save(employee);

			System.out.println("Save Employee success " + employee);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employeeId;
	}

	public Integer update(int employeeId, String name, String address) {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			employee.setName(name);
			employee.setAddress(address);

			session.update(employee);

			System.out.println("update Employee success, employee = " + employee);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employeeId;
	}

	public List<Employee> getAll() {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Employee.class);
			employees = (List<Employee>) criteria.list();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}

		return employees;
	}

	public void deleteAll() {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Query query = session.createQuery("truncate table Employee");
			int totalDelete = query.executeUpdate();
			System.out.println("Delete total records in Employee table, total = " + totalDelete);
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void checkFLC(int employeeId) {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			System.out.println("---Get employee first time----");
			System.out.println(employee.getName());

			employee = session.get(Employee.class, employeeId);
			System.out.println("---Get employee second time----");
			System.out.println(employee.getName());

			System.out.println("---Get employee N time----");
			// Lấy thêm nhiều lần nữa
			for (int i = 0; i < 5; i++) {
				Employee eNew = session.get(Employee.class, employeeId);
				System.out.println(eNew.getName());
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void checkFLCIn2Session(int employeeId) {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;
		Session session2 = factory.openSession();
		Transaction transaction2 = null;

		try {
			transaction = session.beginTransaction();
			transaction2 = session2.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			System.out.println("---Get employee first time, session 1----");
			System.out.println(employee.getName());

			System.out.println("Xoa session 1");
			// Xoa khoi cache
			session.evict(employee);

			Employee employee12 = session.get(Employee.class, employeeId);
			System.out.println("---Get employee second time, session 1----");
			System.out.println(employee12.getName());

			System.out.println("-----------------Session 2");
			System.out.println("");

			Employee employee21 = session2.get(Employee.class, employeeId);
			System.out.println("---Get employee first time, session 2----");
			System.out.println(employee21.getName());

			System.out.println("Xoa session 2");
			// Xoa khoi cache
			session2.clear();

			Employee employee22 = session2.get(Employee.class, employeeId);
			System.out.println("---Get employee second time, session 2----");
			System.out.println(employee22.getName());

		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			if (transaction2 != null) {
				transaction2.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
			session2.close();
		}
	}

	public void checkFLCClearSession(int employeeId) {
		factory = HibernateUtils.getSessionFactory();
		Session session = factory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Employee employee = session.get(Employee.class, employeeId);
			System.out.println("---Get employee first time ID = 1----");
			System.out.println(employee.getName());

			System.out.println("---Get employee first time ID = 2----");
			Employee eNew = session.get(Employee.class, 2);
			System.out.println(eNew.getName());

			System.out.println("-----Clear-----");
			// session.clear();
			session.evict(employee);

			employee = session.get(Employee.class, employeeId);
			System.out.println("---Get employee second time, ID = 1----");
			System.out.println(employee.getName());

			System.out.println("---Get employee N time with ID =  2----");
			// Lấy thêm nhiều lần nữa
			for (int i = 0; i < 5; i++) {
				eNew = session.get(Employee.class, 2);
				System.out.println(eNew.getName());
			}
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
