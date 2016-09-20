/*
 * This class not only produces the global org.hibernate.SessionFactory reference in its static initializer;
 * it also hides the fact that it uses a static singleton
*/

package hibernate.util;

import org.hibernate.SessionFactory;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	// static {
	// try {
	// // Create the SessionFactory from hibernate.cfg.xml
	// sessionFactory = new Configuration().configure().buildSessionFactory();
	// } catch (Throwable ex) {
	// // Make sure you log the exception, as it might be swallowed
	// System.err.println("Initial SessionFactory creation failed." + ex);
	// throw new ExceptionInInitializerError(ex);
	// }
	// }

	public void setSessionFactory(SessionFactory factory) {
		HibernateUtil.sessionFactory = factory;
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}