package myFunction;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashSHA1 {
	public static String byteToHex(byte[] arr) {
        // Convert byte array into signum representation 
        BigInteger no = new BigInteger(1, arr);
        // Convert message digest into hex value 
        String result = no.toString(16);

        // Add preceding 0s to make it 32 bit 
        while (result.length() < 32) {
            result = "0" + result;
        }
        return "0x"+result.toUpperCase();
    }

    public static byte[] sha1Enc(String plain) {
        byte[] messageDigest = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // digest() method is called 
            // to calculate message digest of the input string 
            // returned as array of byte 
            messageDigest = md.digest(plain.getBytes());

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(HashSHA1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return messageDigest;
    }
    
    public static String encr(String str) {
    	byte[] b = sha1Enc(str);
    	String c = byteToHex(b);
    	return c;
    	
    }
}
