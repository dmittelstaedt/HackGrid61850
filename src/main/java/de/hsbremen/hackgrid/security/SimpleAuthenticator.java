package de.hsbremen.hackgrid.security;

/**
 * 
 * @author david
 *
 */
public class SimpleAuthenticator implements Authenticator {
	
	@Override
	public boolean accept(String authenticationParameter) {
		return true;
	}

}
