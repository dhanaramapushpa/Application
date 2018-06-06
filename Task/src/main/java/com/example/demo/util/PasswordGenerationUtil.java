package com.example.demo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.binary.Base64;

import com.example.demo.model.UserProfile;

import sun.misc.BASE64Encoder;

public class PasswordGenerationUtil {

	public static boolean checkPassword(String password, String salt, String encryptedPassword) throws NoSuchAlgorithmException {
		BASE64Encoder encoder = new BASE64Encoder();
		String passwordWithSalt = password + salt;
		byte[] passBytes = passwordWithSalt.getBytes();
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		String hashedPasswordWithSalt = encoder.encodeBuffer(sha256.digest(passBytes));
		return encryptedPassword.trim().equals(hashedPasswordWithSalt.trim());
	}

	public static Map<String,String> maskPassword(String password) throws NoSuchAlgorithmException {
		Map<String , String> resultMap = new HashMap<String, String>();
		final Random random = new SecureRandom();
		final byte[] saltArray = new byte[32];
		random.nextBytes(saltArray);
		final String randomSalt = Base64.encodeBase64String(saltArray);
		final String passwordWithSalt = password + randomSalt;
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");;
		final byte[] passBytes = passwordWithSalt.getBytes();
		final String hashedPasswordWithSalt = Base64.encodeBase64String(sha256.digest(passBytes));
		resultMap.put("salt", randomSalt);
		resultMap.put("password", hashedPasswordWithSalt);
		return resultMap;
	}
	
	
}