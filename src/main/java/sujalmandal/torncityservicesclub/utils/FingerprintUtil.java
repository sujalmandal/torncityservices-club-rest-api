package sujalmandal.torncityservicesclub.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sujalmandal.torncityservicesclub.exceptions.ServiceException;

public class FingerprintUtil {

    public static String getFingerprintForAPIKey(String APIKey) {
	try {
	    return toHexString(getSHA(APIKey));
	} catch (NoSuchAlgorithmException e) {
	    throw new ServiceException("Failed to create a fingerprint for the user's APIKey!");
	}
    }

    private static byte[] getSHA(String input) throws NoSuchAlgorithmException {
	MessageDigest md = MessageDigest.getInstance("SHA-256");
	return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String toHexString(byte[] hash) {
	BigInteger number = new BigInteger(1, hash);
	StringBuilder hexString = new StringBuilder(number.toString(16));
	while (hexString.length() < 32) {
	    hexString.insert(0, '0');
	}
	return hexString.toString();
    }

}
