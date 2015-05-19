package de.hsbremen.hackgrid.model;

/**
 * Model of the configuration of the server.
 * 
 * @author david
 *
 */
public class ServerConfiguration {
	
	private int port;
	private String password;
	
	public ServerConfiguration(int port, String password) {
		this.port = port;
		this.password = password;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
