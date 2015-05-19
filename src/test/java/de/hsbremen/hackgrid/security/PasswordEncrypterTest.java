package de.hsbremen.hackgrid.security;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PasswordEncrypterTest {
	
	private SimplePasswordEncrypter encrypter;
	
	@Before
	public void setup() {
		encrypter = new SimplePasswordEncrypter();
	}
	
	@Test
	public void testCheckPassword() {
		Assert.assertTrue(encrypter.checkPassword("Password", "pf/P6fUjCVYJbCEMOLtCpG+XGL3ZR5YjOu9PC8n3uDm5wCJDb0RZ9Vpqns6A5XqB"));
	}
	
	@Test
	public void testEncryptPassword() {
		String hash = encrypter.encryptPassword("Password");
		System.out.println(hash);
	}

}
