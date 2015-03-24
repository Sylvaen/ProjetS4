package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import beans.Saccoche;
import beans.User;

public class DAOSaccoche {
	
	
	public static Saccoche getSaccocheByPartieId(int id) {

		Session s = DAOUtil.getSession();
		Saccoche sac = null;
		try {
			s.beginTransaction();
			sac = (Saccoche) s.createQuery("from beans.Saccoche where idpartie=:i")
					.setParameter("i", id).uniqueResult();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return sac;
	}

	public static void save(Saccoche sac) {
		Session s = DAOUtil.getSession();
		s.beginTransaction();
		s.save(sac);
		s.beginTransaction().commit();
		s.close();
	}

	public static void update(Saccoche sac) {
		Session s = DAOUtil.getSession();
		try {
			s.beginTransaction();
			s.update(sac);
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}

}
