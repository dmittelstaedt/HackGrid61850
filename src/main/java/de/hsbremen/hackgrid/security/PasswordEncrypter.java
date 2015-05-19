package de.hsbremen.hackgrid.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/**
 * This class encrypts passwords and checks password against hashed strings. This implementation is taken from
 * the lecture software security in the summer semester 2014 by Egon Teiniker.
 * 
 * @author david
 *
 */
public class PasswordEncrypter {
	
	public String encryptPassword(String password) {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			byte[] salt = new byte[16];
			random.nextBytes(salt);
			
			byte[] result = encryptPasswordWithSalt(password.getBytes("UTF-8"), salt);
			return Base64.encodeBase64String(result);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public boolean checkPassword(String password, String hashString) {
		try {
			byte[] result = Base64.decodeBase64(hashString);
			byte[] salt = Arrays.copyOfRange(result, 32, 48);
			byte[] encryptedPassword = encryptPasswordWithSalt(password.getBytes("UTF-8"), salt);
			return Arrays.equals(result, encryptedPassword);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException();
		}
	}
	
	private byte[] encryptPasswordWithSalt(byte[] password, byte[] salt) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(password);
			md.update(salt);
			byte[] hash = md.digest();
			
			byte[] result = new byte[hash.length + salt.length];
			for (int i=0; i < hash.length; i++) {
				result[i] = hash[i];
			}
			for (int i=0; i < salt.length; i++) {
				result[hash.length + i] = salt[i];
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException();
		}
	}

}
