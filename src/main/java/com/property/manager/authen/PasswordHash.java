package com.property.manager.authen;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Create a password hash for the user and validate it when a user tries to re-log in
 */
public class PasswordHash {

	private static PasswordHash instance = null;

	private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

	private PasswordHash() {

	}

	public static void init() {

		if (instance == null) {

			instance = new PasswordHash();
		}
	}

	public static PasswordHash get() {

		return instance;
	}

	public String generateStorngPasswordHash(String password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		int iterations = 10000;

		char[] chars = password.toCharArray();

		byte[] salt = getSalt();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);

		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);

		byte[] hash = skf.generateSecret(spec).getEncoded();

		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
	}

	private byte[] getSalt() throws NoSuchAlgorithmException {

		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

		byte[] salt = new byte[64];

		sr.nextBytes(salt);

		return salt;
	}

	private String toHex(byte[] array) throws NoSuchAlgorithmException {

		BigInteger bi = new BigInteger(1, array);

		String hex = bi.toString(16);

		int paddingLength = (array.length * 2) - hex.length();

		if (paddingLength > 0) {

			return String.format("%0" + paddingLength + "d", 0) + hex;

		} else {

			return hex;

		}
	}

	public boolean validatePassword(String originalPassword, String storedPassword)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String[] parts = storedPassword.split(":");

		int iterations = Integer.parseInt(parts[0]);

		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);

		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);

		SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);

		byte[] testHash = skf.generateSecret(spec).getEncoded();

		int diff = hash.length ^ testHash.length;

		for (int i = 0; i < hash.length && i < testHash.length; i++) {

			diff |= hash[i] ^ testHash[i];
		}

		return diff == 0;
	}

	private byte[] fromHex(String hex) throws NoSuchAlgorithmException {

		byte[] bytes = new byte[hex.length() / 2];

		for (int i = 0; i < bytes.length; i++) {

			bytes[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);

		}

		return bytes;
	}
}