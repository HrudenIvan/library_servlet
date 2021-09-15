package util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

public class PasswordTest {
	
	@Test
	public void whenGenerateSaltReturnsDifferentResult() {
		byte[] salt1 = Password.generateSalt();
		byte[] salt2 = Password.generateSalt();
		assertNotEquals(salt1, salt2);
	}
	
	@Test
	public void whenHashReturnsHsh() {
		byte[] salt = Password.generateSalt();
		byte[] hash = Password.hash("123", salt);
		assertNotNull(hash);
	}
	
	@Test
	public void whenEqualsPasswordThenIsExpectedPasswordReturnsTrue () {
		byte[] salt = Password.generateSalt();
		byte[] hash1 = Password.hash("123", salt);
		byte[] hash2 = Password.hash("123", salt);
		assertTrue(Password.isExpectedPassword(hash1, hash2));
	}
	
	@Test
	public void whenNotEqualsPasswordThenIsisExpectedPasswordReturnsTrue () {
		byte[] salt = Password.generateSalt();
		byte[] hash1 = Password.hash("123", salt);
		byte[] hash2 = Password.hash("132", salt);
		assertFalse(Password.isExpectedPassword(hash1, hash2));
	}
	
	@Test
	public void whenPasswordHaveDifferentLengthThenIsExpectedPasswordReturnsFalse () {
		byte[] hash1 = new byte[] {100, 90, 124};
		byte[] hash2 = new byte[] {100, 90};
		assertFalse(Password.isExpectedPassword(hash1, hash2));
	}
	
	@Test
	public void whenPasswordHaveSameLengthButDifferentValuesThenIsExpectedPasswordReturnsFalse () {
		byte[] hash1 = new byte[] {100, 90, 124};
		byte[] hash2 = new byte[] {100, 124, 90};
		assertFalse(Password.isExpectedPassword(hash1, hash2));
	}
	
	@Test
	@Ignore
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
