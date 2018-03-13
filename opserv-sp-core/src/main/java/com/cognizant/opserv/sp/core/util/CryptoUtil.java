package com.cognizant.opserv.sp.core.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;

import com.cognizant.peg.core.exception.OpservDataAccessException;

// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class CryptoUtil to CryptoUtil
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */

public class CryptoUtil {
	
	/**
	 * Instantiates a new crypto util.
	 */
	private CryptoUtil(){
		
	}

	/**
	 * Encrypt.
	 * @method Encrypt - Encrypt
	 * @param token the token
	 * @param seed the seed
	 * @return the string
	 * @exception OpservDataAccessException the exception - throws if any exception
	 * 
	 */
	public static String encrypt(String token, String seed) throws OpservDataAccessException {
		if (token == null || (token != null && token.isEmpty())) {
			throw new IllegalArgumentException("token is empty or null");
		}

		try {
			Key skeySpec = getKey(seed.getBytes());
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			byte[] encBytes = Base64.encodeBase64(cipher.doFinal(token
					.getBytes()));
			return new String(encBytes, "UTF-8");
		} catch (NoSuchAlgorithmException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while encrypt- No such algorithem.", e);
		} catch (NoSuchPaddingException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while encrypt- No such padding.", e);
		} catch (InvalidKeyException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while encrypt- invalid key.", e);
		} catch (IllegalBlockSizeException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while encrypt- illegal block size.", e);
		} catch (BadPaddingException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while encrypt- Bad padding.", e);
		} catch (UnsupportedEncodingException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred encoding is not supported.", e);
		}
	}

	/**
	 * Gets the key.
	 *
	 * @param seed the seed
	 * @return the key
	 * @exception OpservDataAccessException the exception
	 */
	private static SecretKey getKey(byte[] seed)  throws NoSuchAlgorithmException {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(seed);
		kgen.init(128, sr);
		return kgen.generateKey();
	}

	/**
	 * Decode.
	 * @method Decode - Decode
	 * @param token the token
	 * @param seed the seed
	 * @return the string
	 * @exception OpservDataAccessException the exception - throws if any exception
	 * 
	 */
	public static String decode(String token, String seed) throws OpservDataAccessException {
		if (token == null || (token != null && token.isEmpty())) {
			throw new IllegalArgumentException("token is empty or null");
		}
		try {
			Key skeySpec = getKey(seed.getBytes());			
			byte[] decBytes = Base64.decodeBase64(token.getBytes());
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			byte[] strBytes = cipher.doFinal(decBytes);
			String encString = new String(strBytes, "UTF-8");
			return encString;
		} catch (NoSuchAlgorithmException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while decoding- No such algorithem.", e);
		} catch (NoSuchPaddingException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while decoding-No such padding.", e);
		} catch (InvalidKeyException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while decoding-invalid key.", e);
		} catch (IllegalBlockSizeException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while decoding - illegal block size.", e);
		} catch (BadPaddingException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred while decoding- Bad padding.", e);
		} catch (UnsupportedEncodingException e) {
			throw new OpservDataAccessException(				
					"This issue is occurred encoding is not supported.", e);
		}
	}
	
	
	/**
	 * The main method.
	 * @method main
	 * @param args the arguments
	 * @exception OpservDataAccessException the exception - throws if any exception
	 * 
	 */
	public static void main(String[] args) throws OpservDataAccessException {
		String seed = "opservsfrest";
		
		String encToken = encrypt("32", seed);
	}

}
