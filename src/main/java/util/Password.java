package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Password {
	private static final SecureRandom RANDOM = new SecureRandom();

	private Password() {
	}

	public static byte[] generateSalt() {
		final byte[] salt = new byte[32];
		RANDOM.nextBytes(salt);
		return salt;
	}

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
