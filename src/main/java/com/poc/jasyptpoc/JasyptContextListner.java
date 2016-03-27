package com.poc.jasyptpoc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

public class JasyptContextListner 
               implements ServletContextListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContextListener destroyed");

	}

        //Run this before web application is started
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContextListener started");	
		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setPassword("MYPASS_WORD");
		  HibernatePBEEncryptorRegistry registry =
		      HibernatePBEEncryptorRegistry.getInstance();
		  registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);

		  App.runTest();
		  
		  
	}
}