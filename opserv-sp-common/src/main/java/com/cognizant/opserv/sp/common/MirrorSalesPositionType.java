
package com.cognizant.opserv.sp.common;

/**
 * ****************************************************************************.
 *
 * @enum MirrorSalesPositionType - values from table t_mir_type
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 * ***************************************************************************
 */
public enum MirrorSalesPositionType {
	ONE_NONE(1),
	ONE_N(2),
	N_ONE(3);
	
	private Integer id;
	
	private MirrorSalesPositionType(Integer id){
		this.id =id;
	}
	
	public Integer getId(){
		return this.id;
	}
	
	/**
	 * Gets the mirror sales position type.
	 *
	 * @param id the id
	 * @return the mirror sales position type
	 */
	public static MirrorSalesPositionType getMirrorSalesPositionType(Integer id) {
		for (MirrorSalesPositionType mirrorSalesPositionType : MirrorSalesPositionType
				.values()) {
			if (mirrorSalesPositionType.getId().equals(id)) {
				return mirrorSalesPositionType;
			}
		}
		return null;
	}
	
}
