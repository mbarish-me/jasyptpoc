package com.poc.jasyptpoc;


import java.sql.Connection;
import java.sql.SQLException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;


public class App {


	public static void runTest(){

	/*	SessionFactory sessionFactory = new AnnotationConfiguration()
				.configure().buildSessionFactory();*/
		SessionFactory  sessionFactory = new Configuration().configure().buildSessionFactory();
           
          // builds a session factory from the service registry
		Session session = sessionFactory.getCurrentSession();

		Transaction tx = session.beginTransaction();

	
		session.doWork(new Work() {
		    @Override
		    public void execute(Connection connection) throws SQLException {
		        System.out.println("CONNECTION ESTABLISHED TO DB ::)) WITH JASYPT ENC");
		    }
		});

		tx.commit();
	
	}
	
}