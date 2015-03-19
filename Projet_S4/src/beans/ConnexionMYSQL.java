package beans;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Cette classe fournit une connexion MYSQL
 * a la BDD distante 
 * @author amaury
 *
 */
public class ConnexionMYSQL {

	
	Connection connexion = null;

	
    public Connection getConnection () {
    	
    	try {
    		
    		Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			System.out.println("Driver O.K.");
			connexion = DriverManager.getConnection("jdbc:mysql://91.121.155.7/projet", "lala", "lala");
			System.out.println("connection mysql OK");
			

		} catch (Exception e) {
			System.out.println("Connexion a la BDD non etablie.");
			e.printStackTrace();
	
		}
    	
    	return connexion;
    }


	public Connection getConnexion() {
		return connexion;
	}


	public void setConnexion(Connection connexion) {
		this.connexion = connexion;
	}
	
	
	
	
}
