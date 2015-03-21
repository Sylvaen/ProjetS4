package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import beans.Parties;

public class DAOParties {

	public Parties getPartiesById(int id){
		
		Session s = DAOUtil.getSession();
		Parties parties = null;
		try {
			s.beginTransaction();
			parties = (Parties) s
					.createQuery(
							"from beans.Parties where id=:i")
					.setParameter("i", id).uniqueResult();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return parties;
	}
	
}
