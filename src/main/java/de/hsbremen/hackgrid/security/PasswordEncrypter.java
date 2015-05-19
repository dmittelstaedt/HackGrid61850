package de.hsbremen.hackgrid.security;

/**
 * Interface for password encrypters.
 * 
 * @author david
 *
 */
public interface PasswordEncrypter {
	
	/**
	 * Creates a hash from a password.
	 * 
	 * @param password
	 * @return
	 */
	public String encryptPassword(String password);
	
	/**
	 * Checks a password against a hashed value.
	 * 
	 * @param password
	 * @param hashString
	 * @return
	 */
	public boolean checkPassword(String password, String hashString);

}
