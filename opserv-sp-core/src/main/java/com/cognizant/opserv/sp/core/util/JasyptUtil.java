/**
 * 
 */
package com.cognizant.opserv.sp.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.Provider;
import java.security.Security;
import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEBigIntegerEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;


// TODO: Auto-generated Javadoc
/**
 * ****************************************************************************.
 *
 * @class JasyptUtil to JasyptUtil
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public class JasyptUtil{
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(JasyptUtil.class);
	/**
	 * Instantiates a new jasypt util.
	 */
	private JasyptUtil(){
		
	}
	
	/**
	 * Encrypt.
	 *
	 * @param stringToEncrypt the string to encrypt
	 * @return the string
	 */
	public static String encrypt(String stringToEncrypt){
		return initializeStringEncryptor().encrypt(stringToEncrypt);
	}
	
	/**
	 * Decrypt.
	 *
	 * @param encryptedStr the encrypted str
	 * @return the string
	 */
	public static String decrypt(String encryptedStr){
		return initializeStringEncryptor().decrypt(encryptedStr);
	}
	
	/**
	 * Encrypt.
	 *
	 * @param intToEncrypt the int to encrypt
	 * @return the big integer
	 */
	public static BigInteger encrypt(BigInteger intToEncrypt){
		return initializeBigIntEncryptor().encrypt(intToEncrypt);
	}
	
	/**
	 * Decrypt.
	 *
	 * @param encryptedInt the encrypted int
	 * @return the big integer
	 */
	public static BigInteger decrypt(BigInteger encryptedInt){
		return initializeBigIntEncryptor().decrypt(encryptedInt);
	}	
	
	/**
	 * Compare.
	 *
	 * @param originalStr the original str
	 * @param encrptdStr the encrptd str
	 * @return true, if successful
	 */
	public static boolean compare(String originalStr,String encrptdStr){
		if(originalStr!=null && encrptdStr!=null){
			if(originalStr.equals(decrypt(encrptdStr))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Initialize string encryptor.
	 *
	 * @return the standard pbe string encryptor
	 */
	private static StandardPBEStringEncryptor initializeStringEncryptor(){
		
		Properties prop = new Properties();
		InputStream input = null;
		StandardPBEStringEncryptor standardPBEStringEncryptor = null;
		
		try{
			input = ClassLoader.getSystemResourceAsStream("config.properties"); 
			prop.load(input);
			
			// get the property value and print it out
			//System.out.println("Algorithm ::: "+prop.getProperty("jasypt.algorithm.name"));
			//System.out.println("Password ::: "+prop.getProperty("jasypt.algorithm.password"));
			if(isAlgorithmSupported(prop.getProperty("jasypt.algorithm.name"))){
				
				standardPBEStringEncryptor = new StandardPBEStringEncryptor();
				standardPBEStringEncryptor.setAlgorithm(prop.getProperty("jasypt.algorithm.name"));
				standardPBEStringEncryptor.setPassword("jasypt.algorithm.password");
			}else{
				return null;
			}

			
		}catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}		 
		 return standardPBEStringEncryptor;
	}	
	
	/**
	 * Initialize big int encryptor.
	 *
	 * @return the standard pbe big integer encryptor
	 */
	private static StandardPBEBigIntegerEncryptor initializeBigIntEncryptor(){
			
		Properties prop = new Properties();
		InputStream input = null;
		
		StandardPBEBigIntegerEncryptor standardPBEBigIntegerEncryptor = null;
		
		try{
			input = ClassLoader.getSystemResourceAsStream("config.properties"); 
			prop.load(input);
			
			// get the property value and print it out
			if(isAlgorithmSupported(prop.getProperty("jasypt.algorithm.name"))){
				
				standardPBEBigIntegerEncryptor = new StandardPBEBigIntegerEncryptor();
				standardPBEBigIntegerEncryptor.setAlgorithm(prop.getProperty("jasypt.algorithm.name"));
				standardPBEBigIntegerEncryptor.setPassword("jasypt.algorithm.password");
			}else{
				return null;
			}
			
		}catch (IOException ex) {
			LOGGER.error(ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOGGER.error(e.getMessage());
				}
			}
		}		 
		 return standardPBEBigIntegerEncryptor;
	}	
	
	
	/**
	 * Checks if is algorithm supported.
	 *
	 * @param algorithm the algorithm
	 * @return true, if is algorithm supported
	 */
	private static boolean isAlgorithmSupported(String algorithm){
		
		 for (Provider provider : Security.getProviders()) {
	           // System.out.println("Provider: " + provider.getName());
	            for (Provider.Service service : provider.getServices()) {
	              //  System.out.println("  Algorithm: " + service.getAlgorithm());
	                if(algorithm.equals(service.getAlgorithm())){
	                	return true;
	                }
	            }
	        }
		
		return false;
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]){
		 String message="opserv";
		 String encryptedMessage = encrypt(message);	 
		 compare(message,encryptedMessage);
		 
		 BigInteger intVal= BigInteger.valueOf(3610836876914035028L);
		 encrypt(intVal);
	}
}
