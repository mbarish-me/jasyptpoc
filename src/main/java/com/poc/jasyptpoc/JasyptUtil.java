package com.poc.jasyptpoc;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;
import org.jasypt.util.password.StrongPasswordEncryptor;


public class JasyptUtil {
	
	String result = "";
	InputStream inputStream;
	
	public static void main(String[] args) {
		encryptPassword("kumar");
	//validatePassword("eRrJ9357MchtydV9IrUKfAiyws3oJKnfpxwW4v4+YKYtwPGh2zsFIL1c0tS9VA8/", "ambarish");
		
	}

	public String test(){
		System.out.println("Executing Jasyot Login on server startup ==========================");
		try {
			Properties prop = new Properties();
			String propFileName = "app.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}

			String user = prop.getProperty("name");
			System.out.println("Name from prop -"+user);
			encrypt(user);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	return result;
	}
	
	public static String encrypt(String tobeEncrypted){
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		String encryptedPassword = passwordEncryptor.encryptPassword(tobeEncrypted);
		System.out.println("encryptedPassword "+encryptedPassword);
		return tobeEncrypted;
		
	}
	
	public static String encryptPassword(String tobeEncrypted) {

		StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
		strongEncryptor.setAlgorithm("PBEWithMD5AndDES");
		strongEncryptor.setPassword("MYPASS_WORD");
		HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
		registry.registerPBEStringEncryptor("strongHibernateStringEncryptor", strongEncryptor);

		String encryptedText = strongEncryptor.encrypt("kumar");
		System.out.println("--encryptedText--- " + encryptedText);
		String normaltext = strongEncryptor.decrypt(encryptedText);
		System.out.println("--normaltext--- " + normaltext);

		return encryptedText;
		
	}
	

	
	public static boolean validatePassword(String encryptedText, String userPassword){
		StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		if (passwordEncryptor.checkPassword(userPassword, encryptedText)) {
			System.out.println("Password valid");
		  return true;
		} else {
			System.out.println("Password not valid");
		  return false;
		}
	}

}
