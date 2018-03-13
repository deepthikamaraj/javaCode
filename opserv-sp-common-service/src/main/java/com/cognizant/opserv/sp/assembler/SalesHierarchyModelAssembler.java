package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;

/**
 * The Class SalesHierarchyModelAssembler.
 */
public class SalesHierarchyModelAssembler {
	
	/**
	 * To model object.
	 *
	 * @param tAlgmntSalesHier the t algmnt sales hier
	 * @return the sales org hierarchy
	 */
	public static SalesOrgHierarchy toModelObject(TAlgmntSalesHier tAlgmntSalesHier) {
		SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
		if(tAlgmntSalesHier!=null){
			salesOrgHierarchy.setId(tAlgmntSalesHier.getSalesHierId());
			salesOrgHierarchy.setName(tAlgmntSalesHier.getHierName());
			if('Y'==tAlgmntSalesHier.getActiveFlag() || 'y'==tAlgmntSalesHier.getActiveFlag()){
				salesOrgHierarchy.setActive(true);
			}else{
				salesOrgHierarchy.setActive(false);
			}
			
			salesOrgHierarchy.setCreatedBy(tAlgmntSalesHier.getCreatedBy());
			//salesOrgHierarchy.setColorCode(tAlgmntSalesHier.get.getHierName());
			salesOrgHierarchy.setCreatedDate(tAlgmntSalesHier.getCreateDt());
			salesOrgHierarchy.setStartDate(tAlgmntSalesHier.getEffStartDt());
			salesOrgHierarchy.setEndDate(tAlgmntSalesHier.getEffEndDt());
			salesOrgHierarchy.setUpdatedDate(tAlgmntSalesHier.getUpdateDt());
			salesOrgHierarchy.setUpdatedBy(tAlgmntSalesHier.getUpdatedBy());
			if('Y' == tAlgmntSalesHier.getAssignZipFlag() || 'y' == tAlgmntSalesHier.getAssignZipFlag()){
				salesOrgHierarchy.setAssignZipFlag(true);
			}else{
				salesOrgHierarchy.setAssignZipFlag(false);
			}
			//salesOrgHierarchy.setCreatedDate(tAlgmntSalesHier.getMoveSPFlag());
			salesOrgHierarchy.setLevel(tAlgmntSalesHier.getHierLevel());
			SalesOrgHierarchy prnSalesOrgHierarchy = new SalesOrgHierarchy();
			prnSalesOrgHierarchy.setId(tAlgmntSalesHier.getPrnSaleHierId());
			salesOrgHierarchy.setParentHierarchy(prnSalesOrgHierarchy);
		}
		return salesOrgHierarchy;
	} 

	
	/**
	 * To model object list.
	 *
	 * @param tAlgmntSalesHier the t algmnt sales hier
	 * @return the list
	 */
	public static List<SalesOrgHierarchy> toModelObjectList(List<TAlgmntSalesHier> tAlgmntSalesHier) {
		List<SalesOrgHierarchy> salesOrgHierarchyList = new ArrayList<SalesOrgHierarchy>();
		SalesOrgHierarchy salesOrgHierarchy = null;
		
		for(TAlgmntSalesHier salesHierObj:tAlgmntSalesHier){
			salesOrgHierarchy = SalesHierarchyModelAssembler.toModelObject(salesHierObj);
			salesOrgHierarchyList.add(salesOrgHierarchy);
		}
		
		return salesOrgHierarchyList;
	} 
}
