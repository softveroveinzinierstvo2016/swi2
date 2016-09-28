package core.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
* The HibernateUtil class provides connectivity with
* DB.
*
* @author  Rastislav Vocko
* @version 1.0
* @since   2016-04-25 
*/

public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	
	static {
		try {
			sessionFactory = new Configuration()
					.configure("/hbnt/hibernate.cfg.xml")
					.buildSessionFactory();
		} catch (Exception e) {
			System.err.println("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}
        /**
* The method which returns sessionFactory.
*
*/
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
