package de.hsbremen.hackgrid.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsbremen.hackgrid.model.ClientConfiguration;
import de.hsbremen.hackgrid.model.ServerConfiguration;

/**
 * This class is responsible for reading the properties files.
 * 
 * @author david
 *
 */
public class SimpleProperties {
	
	private final static Logger logger = LoggerFactory.getLogger(SimpleProperties.class);
	
	public ClientConfiguration readClientProperties(String fileName) {
		
		List<String> propertyNames = new ArrayList<>(2);
		List<String> propertyAttributes = new ArrayList<>(2);
		propertyNames.add("remoteHost");
		propertyNames.add("remotePort");
		propertyNames.add("password");
		propertyAttributes = loadProperties(propertyNames, fileName);
		return new ClientConfiguration(propertyAttributes.get(0), Integer.parseInt(propertyAttributes.get(1)), propertyAttributes.get(2));
	}
	
	public ServerConfiguration readServerProperites(String fileName) {
		
		List<String> propertyNames = new ArrayList<>(1);
		List<String> propertyAttributes = new ArrayList<>(1);
		propertyNames.add("port");
		propertyNames.add("password");
		propertyAttributes = loadProperties(propertyNames, fileName);
		return new ServerConfiguration(Integer.parseInt(propertyAttributes.get(0)), propertyAttributes.get(1));
	}
	
	private List<String> loadProperties(List<String> propertyNames, String fileName) {
		
		List<String> propertyAttributes = new ArrayList<>(propertyNames.size());
		Properties properties = new Properties();
		InputStream input = null;
		
		try {
			input = new FileInputStream(fileName);
//			if(input == null) {
//				logger.error("Unable to find properties file: " + fileName);
//				return null;
//			}
			properties.load(input);
			for (String propertyName : propertyNames) {
				propertyAttributes.add(properties.getProperty(propertyName));
			}
			return propertyAttributes;
		} catch (IOException e) {
			logger.error("Error while reading properties file: " + e.getMessage());
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					logger.error("Error while reading properties file: " + e.getMessage());
				}
			}
		}
		
	}

}
