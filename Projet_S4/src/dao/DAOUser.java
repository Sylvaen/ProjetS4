package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import beans.User;

public class DAOUser {
	public static User getUserByEmail(String email) {
		Session s = DAOUtil.getSession();
		User user = null;
		try {
			s.beginTransaction();
			user = (User) s
					.createQuery(
							"from beans.User where email=:mail")
					.setParameter("mail", email).uniqueResult();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static User getUserById(int id) {
		Session s = DAOUtil.getSession();
		User user = null;
		try {
			s.beginTransaction();
			user = (User) s
					.createQuery(
							"from beans.User where id=:i")
					.setParameter("i", id).uniqueResult();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static User getUserByUsername(String name) {
		Session s = DAOUtil.getSession();
		User user = null;
		try {
			s.beginTransaction();
			user = (User) s
					.createQuery(
							"from beans.User where username=:i")
					.setParameter("i", name).uniqueResult();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return user;
	}

	public static User createUser(User u) {
		Session s = DAOUtil.getSession();
		try {
			s.beginTransaction();
			s.save(u);
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;
		}
		return u;
	}

	public static void validerCompte(User user) {
		Session s = DAOUtil.getSession();
		try {
			s.beginTransaction();
			user.setValide(1);
			s.update(user);
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

	public static void update(User user) {
		Session s = DAOUtil.getSession();
		try {
			s.beginTransaction();
			s.update(user);
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
}
