package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

public class DAOUtil {

    private static Session session;
    
    public static Session getSession() {
        if (session == null) try
        {
            return HibernateSessionFactory.getSessionFactory().openSession();
        }
        catch (HibernateException e)
        {
            e.printStackTrace();
        }
        return session;
    }
    
    public static void setSession(Session s) {
        session = s;
    }
    
    
    public static String getStringForDate(int year,int month,int day,int hour,int minute){
        return year+"/"+month+"/"+day+" "+hour+":"+minute;
    }
}