package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @enum BussObjType - values from t_buss_obj table
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum BussObj {
	
	/**
	 * values from t_buss_obj table
	 */
	
	CUSTOMER_ALIGNMENT_VIEW("Customer Alignment view"),
	ZIP_SALESPOS_VIEW ("Zip SalesPos View"),
	CHANGE_REQUEST_VIEW ("Change Request view");
	
	/**
	 * bussObjName - the bussObjName
	 */
	private String bussObjName;
	
	/**
	 * @param bussObjName
	 */
	private BussObj(String bussObjName){
		this.bussObjName = bussObjName;
	}

	/**
	 * @return
	 */
	public String getBussObjName() {
		return bussObjName;
	}


	/**
	 * @param bussObjName
	 */
	public void setBussObjName(String bussObjName) {
		this.bussObjName = bussObjName;
	}

	/**
	 * Gets the bussObj type.
	 *
	 * @param id the id
	 * @return the bussObj type
	 */
	public static BussObj getBussObjName(String bussObj) {
		for (BussObj bussObject : BussObj.values()) {
			if (bussObject.getBussObjName().equals(bussObj)) {
				return bussObject;
			}
		}
		return null;
	}

}
