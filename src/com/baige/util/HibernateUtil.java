package com.baige.util;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 *
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;
	// 创建线程局部变量 threadLocal，用来保存 Hibernate 的 session
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	// 使用静态代码块初始化 Hibernate
	static {
		try {
//			SAXReader reader = new SAXReader();
////			reader.setEncoding("GBK"); //这里指定编码，好像不管用
//			InputStream is = ConfigHelper.getConfigStream("/hibernate.cfg.xml" );
//			Document doc = reader.read(new InputStreamReader( is ), "utf-8"); //在读取文件时，指定编码才有效。
//			is.close();
			Configuration configuration = new Configuration().configure(); // 读取配置文件 hibernate.cfg.xml
			sessionFactory = configuration.buildSessionFactory();	// 创建 SesstionFactory
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}
	// 获得SesstionFactory实例
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	// 获得ThreadLocal对象管理的Session实例
	public static Session getSession() throws HibernateException {
		Session session = threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null || sessionFactory.isClosed()) {
				rebuildSesstionFactory();
			}
			// 通过SessionFactory对象创建session对象
			session = (sessionFactory != null) ? sessionFactory.openSession():null;
			// 将新打开的Session实例保存到线程局部变量threadLocal中
			threadLocal.set(session);
		}
		return session;
	}
	// 关闭Session实例
	public static void closeSession() throws HibernateException {
		Session session = threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}
	// 重建SessionFactory
	public static void rebuildSesstionFactory() {
		try {
			Configuration configuration = new Configuration().configure(); // 读取配置文件 hibernate.cfg.xml
			sessionFactory = configuration.buildSessionFactory();	// 创建 SesstionFactory
//			Configuration.configure(/hibernate.cfg.xml); // 读取配置文件 hibernate.cfg.xml
//			sessionFactory = configuration.buildSessionFactory();	// 创建 SesstionFactory
		} catch (Exception e) {
			System.err.println("Error Creating SessionFactory");
			e.printStackTrace();
		}
	}
	// 关闭缓存和连接池
	public static void shutdown() {
		getSessionFactory().close();
	}

}
