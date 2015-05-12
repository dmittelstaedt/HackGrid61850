package de.hsbremen.hackgrid.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openmuc.openiec61850.BasicDataAttribute;
import org.openmuc.openiec61850.BdaFloat32;
import org.openmuc.openiec61850.BdaQuality;
import org.openmuc.openiec61850.BdaTimestamp;
import org.openmuc.openiec61850.Fc;
import org.openmuc.openiec61850.SclParseException;
import org.openmuc.openiec61850.ServerEventListener;
import org.openmuc.openiec61850.ServerModel;
import org.openmuc.openiec61850.ServerSap;
import org.openmuc.openiec61850.ServiceError;
import org.openmuc.openiec61850.BdaQuality.Validity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implementation is based on the SampleServer code from openmuc.
 * 
 * @author david
 *
 */
public class SimpleServer implements ServerEventListener{
	
	private final static Logger logger = LoggerFactory.getLogger(SimpleServer.class);
	
	private static ServerSap serverSap = null;
	
	private final static int port = 10002;
	
	private static final String icdFileName = "sampleModel.icd";
	
	public static void main(String[] args) throws IOException {
		
		List<ServerSap> serverSaps = null;
		try {
			serverSaps = ServerSap.getSapsFromSclFile(icdFileName);
		} catch (SclParseException e) {
			logger.warn("Error parsing SCL/ICD file: " + e.getMessage());
		}
		
		serverSap = serverSaps.get(0);
		serverSap.setPort(port);
		
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run() {
				if (serverSap != null) {
					serverSap.stop();
				}
				logger.error("Server was stopped!");
			}
		});
		
		ServerModel serverModel = serverSap.getModelCopy();
		
		SimpleServer simpleServer = new SimpleServer();
		
		serverSap.startListening(simpleServer);
		
		BdaFloat32 totWMag = (BdaFloat32) serverModel.findModelNode("ied1lDevice1/MMXU1.TotW.mag.f", Fc.MX);
		BdaQuality q = (BdaQuality) serverModel.findModelNode("ied1lDevice1/MMXU1.TotW.q", Fc.MX);
		BdaTimestamp t = (BdaTimestamp) serverModel.findModelNode("ied1lDevice1/MMXU1.TotW.t", Fc.MX);
		
//		BdaInt32 stVal = (BdaInt32) serverModel.findModelNode("ied1lDevice1/CSWI1.Beh.stVal", Fc.ST);
//		BdaQuality q2 = (BdaQuality) serverModel.findModelNode("ied1lDevice1/CSWI1.Beh.q", Fc.ST);	
//		LogicalNode ln = (LogicalNode) serverModel.findModelNode("ied1lDevice1/LPHD1", null);
		
		List<BasicDataAttribute> totWBdas = new ArrayList<BasicDataAttribute>(3);
		totWBdas.add(totWMag);
		totWBdas.add(q);
		totWBdas.add(t);
		
		float totWMagVal = 0.0f;
		q.setValidity(BdaQuality.Validity.GOOD);
		
		while (true) {
			totWMagVal += 1.0;
			logger.info("setting totWMag to: " + totWMagVal);
			totWMag.setFloat(totWMagVal);
			t.setCurrentTime();
			if (q.getValidity() == Validity.GOOD) {
				q.setValidity(BdaQuality.Validity.INVALID);
			}
			else {
				q.setValidity(BdaQuality.Validity.GOOD);
			}
			
			serverSap.setValues(totWBdas);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
		
	}

	@Override
	public List<ServiceError> write(List<BasicDataAttribute> bdas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void serverStoppedListening(ServerSap serverSAP) {
		logger.error("The SAP stopped listening");
	}

}