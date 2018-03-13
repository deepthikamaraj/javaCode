package com.cognizant.opserv.sp.common;
/**
 * ****************************************************************************.
 *
 * @enum AffiliationRelationType holds data from t_aff_rel_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum AffiliationRelationType {
	
	/**
	 * values from table - t_aff_rel_type
	 */
	MULTIDOC(1),
	NON_MULTIDOC(2),
	ACCT_PROF(3);
	
	private Integer id;
	
	private AffiliationRelationType(Integer id) {
		this.id=id;
	}

	public Integer getId(){
		return id;
	}

	public static AffiliationRelationType getAffiliationRelationType(Integer id) {
		for (AffiliationRelationType affiliationRelationType : AffiliationRelationType.values()) {
			if (affiliationRelationType.getId().equals(id)) {
				return affiliationRelationType;
			}
		}
		return null;
	}
}
