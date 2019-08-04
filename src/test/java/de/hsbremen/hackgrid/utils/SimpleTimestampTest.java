package de.hsbremen.hackgrid.utils;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

public class SimpleTimestampTest {
	
	private SimpleTimestamp simpleTimestamp;
	
	@Before
	public void setup() {
		simpleTimestamp = new SimpleTimestamp();
	}
	
	@Test
	public void testSetCurrentTime() {
//		simpleTimestamp.setCurrentTime();
//		System.out.println(simpleTimestamp.getDate());
//		System.out.println(simpleTimestamp.getDate().getTime());
//		System.out.println(simpleTimestamp.getValue());
		byte[] test = "[B@57a220c2".getBytes();
		simpleTimestamp.setValue(test);
		System.out.println(simpleTimestamp.getDate());
	}

}
