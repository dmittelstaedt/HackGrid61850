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
	private int delay;
	
	public ServerConfiguration(int port, String password, int delay) {
		this.port = port;
		this.password = password;
		this.delay = delay;
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

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

}
