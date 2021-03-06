package dao;


import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import beans.Parties;
import beans.Plateau;
import beans.Saccoche;
import beans.User;

public class DAOParties {

	public static Parties getPartiesById(int id) {

		Session s = DAOUtil.getSession();
		Parties parties = null;
		try {
			s.beginTransaction();
			parties = (Parties) s.createQuery("from beans.Parties where id=:i")
					.setParameter("i", id).uniqueResult();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return parties;
	}
	
	/**
	 * Retourne la partie dont le nom est 'name'
	 * @param name
	 * @return
	 */
	public static Parties getPartiesByName(String name) {

		Session s = DAOUtil.getSession();
		Parties parties = null;
		try {
			s.beginTransaction();
			parties = (Parties) s.createQuery("from beans.Parties where nom=:name")
					.setParameter("name", name).uniqueResult();
		
			
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return parties;
	}

	public static List<Parties> getParties() {
		List<Parties> listParties = new ArrayList<Parties>();
		Session s = DAOUtil.getSession();
		try {
			s.beginTransaction();
			listParties = (ArrayList<Parties>)s.createQuery("from beans.Parties").list();
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return listParties;
	}

	public static Parties createPartie(User user, String name) {
		Session s = DAOUtil.getSession();
		Parties p = new Parties(user);
		Saccoche saccoche = new Saccoche();
		p.setSaccoche(saccoche);
		try {
			Plateau pla = new Plateau();
			pla.initialisePlateau();
			p.setNom(name);
			
			p.setIdj1(user.getId());
			p.setPlateauString(pla.afficherPlateau2(pla.toString()));
			System.out.println("DAOPArties plateau = " + pla.afficherPlateau2(pla.toString()) );
			p.setPj1(0);
			char [] l = p.initialiseLettresj1(p.getSaccoche());
			p.setLettresj1_str(p.convertLettres(l));
			p.afficheLettres(p.getLettresj1());
			p.setPj2(0);
			p.setUser1(user);
			s.beginTransaction();
			s.save(p);
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		saccoche.setIdparties(p.getId());
		DAOSaccoche.save(saccoche);
		return p;
	}

	public static Parties rejoindrePartie(User user, String name) {
		Session s = DAOUtil.getSession();
		Parties parties = null;
		try {
			s.beginTransaction();
			parties = (Parties) s.createQuery("from beans.Parties where nom=:name")
					.setParameter("name", name).uniqueResult();
			parties.setIdj2(user.getId());
			char [] l = parties.getLettresj2();			
			Saccoche saccoche = DAOSaccoche.getSaccocheByPartieId(parties.getId());
			System.out.println("idP:"+parties.getId());
			l = parties.initialiseLettresj2(saccoche);
			parties.setLettresj2_str(parties.convertLettres(l));
			parties.afficheLettres(parties.getLettresj2());
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return parties;
	}
	
	public static void update(Parties p) {
		Session s = DAOUtil.getSession();
		try {
			s.beginTransaction();
			s.update(p);
			s.beginTransaction().commit();
			s.close();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	
}
