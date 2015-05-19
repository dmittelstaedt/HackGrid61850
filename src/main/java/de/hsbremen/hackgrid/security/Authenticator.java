package de.hsbremen.hackgrid.security;

/**
 * 
 * @author david
 *
 */
public interface Authenticator {
	
	/**
	 * 
	 * @param authenticationParameter
	 * @return true, when the authentication parameter is right, otherwise false
	 */
	public boolean accept(String authenticationParameter);

}
