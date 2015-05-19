package de.hsbremen.hackgrid.model;

/**
 * Model of the configuration of the client.
 * 
 * @author david
 *
 */
public class ClientConfiguration {
	
	private String remoteHost;
	private int remotePort;
	private String password;
	
	public ClientConfiguration() {}
	
	public ClientConfiguration(String remoteHoste, int remotePort, String password) {
		this.remoteHost = remoteHoste;
		this.remotePort = remotePort;
		this.password = password;
	}
	
	public String getRemoteHost() {
		return remoteHost;
	}
	
	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}
	
	public int getRemotePort() {
		return remotePort;
	}
	
	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
