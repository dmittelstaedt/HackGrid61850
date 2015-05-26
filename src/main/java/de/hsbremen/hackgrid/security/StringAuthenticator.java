package de.hsbremen.hackgrid.security;

public class StringAuthenticator implements Authenticator{
	
	private String password;
	
	public StringAuthenticator(String password) {
		this.password = password;
	}

	@Override
	public boolean accept(String authenticationParameter) {
		return password.equals(authenticationParameter);
	}

}
