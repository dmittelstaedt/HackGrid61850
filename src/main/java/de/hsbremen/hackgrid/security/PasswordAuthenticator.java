package de.hsbremen.hackgrid.security;

/**
 * 
 * @author david
 *
 */
public class PasswordAuthenticator implements Authenticator {
	
	private String password;
	
	public PasswordAuthenticator(String password) {
		this.password = password;
	}

	@Override
	public boolean accept(String authenticationParameter) {
		SimplePasswordEncrypter encrypter = new SimplePasswordEncrypter();
		return encrypter.checkPassword(password, authenticationParameter);
	}

}
