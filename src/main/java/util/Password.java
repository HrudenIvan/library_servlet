package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * utility class for password hashing
 */
public class Password {
	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * Protect constructor to deny instantiation
	 */
	private Password() {
	}

	/**
	 * Generates random salt
	 * @return salt as array of bytes
	 */
	public static byte[] generateSalt() {
		final byte[] salt = new byte[32];
		RANDOM.nextBytes(salt);
		return salt;
	}

	/**
	 * Hashes given raw password with given salt
	 * @param password given raw password
	 * @param salt given salt
	 * @return hashed password as array of bytes
	 */
	public static byte[] hash(String password, byte[] salt) {
		MessageDigest digest;
		byte[] hash = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.update(salt);
			hash = digest.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hash;
	}

	/**
	 * Checks if given password is equal to given expectedPassword
	 * @param password given password
	 * @param expectedPassword given expected password
	 * @return true if passwords are equal, false otherwise
	 */
	public static boolean isExpectedPassword(byte[] password, byte[] expectedPassword) {
		if (password.length != expectedPassword.length) {
			return false;
		}
		for (int i = 0; i < password.length; i++) {
			if (password[i] != expectedPassword[i]) {
				return false;
			}
		}
		return true;
	}
}
