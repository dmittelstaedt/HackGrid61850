package de.hsbremen.hackgrid.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openmuc.openiec61850.BdaFloat32;
import org.openmuc.openiec61850.BdaQuality;
import org.openmuc.openiec61850.BdaTimestamp;
import org.openmuc.openiec61850.ClientAssociation;
import org.openmuc.openiec61850.ClientEventListener;
import org.openmuc.openiec61850.ClientSap;
import org.openmuc.openiec61850.Fc;
import org.openmuc.openiec61850.FcModelNode;
import org.openmuc.openiec61850.Report;
import org.openmuc.openiec61850.ServerModel;
import org.openmuc.openiec61850.ServiceError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hsbremen.hackgrid.model.ClientConfiguration;
import de.hsbremen.hackgrid.security.SimplePasswordEncrypter;
import de.hsbremen.hackgrid.utils.SimpleProperties;

/**
 * This implementation is based on the SampleClient code from openmuc.
 * 
 * @author david
 *
 */
public class SimpleClient implements ClientEventListener{
	
	private final static Logger logger = LoggerFactory.getLogger(SimpleClient.class);
	
	private final static String configFileName = "client.properties";
	
	public static void main(String[] args) throws ServiceError, IOException {
		
		InetAddress inetAddress;
		SimpleProperties simpleProperties = new SimpleProperties();
		ClientConfiguration clientConfiguration = simpleProperties.readClientProperties(configFileName);
		
		try {
			inetAddress = InetAddress.getByName(clientConfiguration.getRemoteHost());
		} catch (UnknownHostException e) {
			logger.error("Unknown host: " + clientConfiguration.getRemoteHost());
			return;
		}
		
		ClientSap clientSap = new ClientSap();
		
		SimpleClient simpleClient = new SimpleClient();
		
		ClientAssociation clientAssociation;
		
		SimplePasswordEncrypter encrypter = new SimplePasswordEncrypter();
		
		logger.info("Trying to connect to server: " + clientConfiguration.getRemoteHost() + " using port: " + clientConfiguration.getRemotePort());
		try {
			clientAssociation = clientSap.associate(inetAddress, clientConfiguration.getRemotePort(), encrypter.encryptPassword(clientConfiguration.getPassword()), simpleClient);
		} catch (IOException e) {
			logger.error("Error connecting to server: " + e.getMessage());
			return;
		}
		
		ServerModel serverModel;
		try {
			 serverModel = clientAssociation.retrieveModel();
		} catch (ServiceError e) {
			logger.error("Service Error requesting model.", e);
			clientAssociation.close();
			return;
		} catch (IOException e) {
			logger.error("Fatal IOException requesting model.", e);
			return;
		}
		
		// writing a variable
		FcModelNode modCtlModel = (FcModelNode) serverModel.findModelNode("ied1lDevice1/CSWI1.Mod.ctlModel", Fc.CF);
		clientAssociation.setDataValues(modCtlModel);
		
		// reading a variable
		FcModelNode totW = (FcModelNode) serverModel.findModelNode("ied1lDevice1/MMXU1.TotW", Fc.MX);
		BdaFloat32 totWmag = (BdaFloat32) totW.getChild("mag").getChild("f");
		BdaTimestamp totWt = (BdaTimestamp) totW.getChild("t");
		BdaQuality totWq = (BdaQuality) totW.getChild("q");
		
		while (true) {
			clientAssociation.getDataValues(totW);
			
			logger.info("totWmag: " + totWmag.getFloat());
			logger.info("totWt: " + totWt.getDate());
			logger.info("totWq: " + totWq.getValidity());
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
		
	}

	@Override
	public void newReport(Report report) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void associationClosed(IOException e) {
		logger.info("Association was closed");
	}

}
