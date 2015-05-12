package de.hsbremen.hackgrid.model;

/**
 * Model of the configuration of the server.
 * 
 * @author david
 *
 */
public class ServerConfiguration {
	
	private int port;
	
	public ServerConfiguration(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
