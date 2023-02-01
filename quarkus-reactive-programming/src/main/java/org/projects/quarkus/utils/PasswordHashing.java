package org.projects.quarkus.utils;

import org.mindrot.jbcrypt.BCrypt;

import lombok.extern.slf4j.Slf4j;

/**
 * @author athul1998
 * INFO : Utils class for Hash comparison 
 */
@Slf4j
public class PasswordHashing {
	
	
	/**
	 * INFO : Private constructor to avoid initialization 
	 */
	PasswordHashing()
	{}

	/**
	 * @param storedPassword
	 * @param newlyHashed
	 * INFO : Compares two hashed Strings 
	 * 
	 */
	public static boolean compareHashedString(String storedPassword, String newlyHashed)
	{
		log.info("Entry : Check password");
		return  BCrypt.checkpw(newlyHashed,storedPassword);
	}
	
	
	
	/**
	 * @param password
	 * @return
	 * INFO : Method to hash a String 
	 */
	public static String hashString(String password)
	{
		return BCrypt.hashpw(password,BCrypt.gensalt(12));
	}
	
	
}
