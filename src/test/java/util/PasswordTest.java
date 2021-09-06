package util;

import org.junit.Test;

public class PasswordTest {
	
	@Test
	public void hash() {
		System.out.println("salt:");
		byte[] salt = Password.generateSalt();
		for (byte b : salt) {
			System.out.print(String.format("%02X",b));
		}
		System.out.println();
		System.out.println("hash");
		byte[] hash = Password.hash("123", salt);
		for (byte b : hash) {
			System.out.print(String.format("%02X",b));
		}
	}

}
