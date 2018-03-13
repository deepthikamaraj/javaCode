package com.cognizant.opserv.sp.batch.common;

/**
 * ****************************************************************************.
 *
 * @class DataRepType
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 27/11/2014
 * ***************************************************************************
 */
public enum DataRepType {

	
	/** The appdb. */
	APPDB(1, "APPDB"),
	
	/** The dmdb. */
	DMDB(2, "DMDB"),
	
	/** The umdb. */
	UMDB(3, "UMDB");

	
	/** The data rep type id. */
	private int dataRepTypeId;
	
	/** The data rep type name. */
	private String dataRepTypeName;
	
	/**
	 * Instantiates a new data rep type.
	 *
	 * @param dataRepTypeId the data rep type id
	 * @param dataRepTypeName the data rep type name
	 */
	DataRepType(int dataRepTypeId, String dataRepTypeName){
		this.dataRepTypeId = dataRepTypeId;
		this.dataRepTypeName = dataRepTypeName;
	}
	
	/**
	 * Gets the data rep type id.
	 *
	 * @return the data rep type id
	 */
	public int getDataRepTypeId(){
		return dataRepTypeId;
	}
	
	/**
	 * Gets the data rep type name.
	 *
	 * @return the data rep type name
	 */
	public String getDataRepTypeName(){
		return dataRepTypeName;
	}

}
