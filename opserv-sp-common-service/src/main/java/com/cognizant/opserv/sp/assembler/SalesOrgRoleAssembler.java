package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TRoleList;
import com.cognizant.opserv.sp.model.SalesOrgRole;
import com.cognizant.opserv.sp.model.auth.UserDetails;

@Component
public class SalesOrgRoleAssembler {

	/**
	 * mapSalesOrgRoleModelToEntity.
	 *
	 * @param salesOrgRole
	 * @param userDetails
	 * @return TRoleList
	 */
	public TRoleList mapSalesOrgRoleModelToEntity(SalesOrgRole salesOrgRole, UserDetails userDetails) {
		
		TRoleList trList = new TRoleList();
		
		trList.setRoleName(salesOrgRole.getName());
		trList.setRoleDesc(salesOrgRole.getDescription());
		
		if (null != salesOrgRole.getParentSalesOrgRoleId()) {
			trList.setPrnRoleId(salesOrgRole.getParentSalesOrgRoleId().intValue());
		}
		
		char flag = 'N';
		Boolean active = true;
		if (active.equals(salesOrgRole.isActive())) {
			flag = 'Y';
		}
		trList.setActiveFlag(flag);
		trList.setDefRoleFlag('Y');
		trList.setEffStartDt(salesOrgRole.getStartDate());
		trList.setEffEndDt(salesOrgRole.getEndDate());
		trList.setCreatedBy(salesOrgRole.getCreatedBy());
		trList.setCreateDt(salesOrgRole.getCreatedDate());
		trList.setTenantId(userDetails.getTenantId());
		
		return trList;
		
	}
	
	/**
	 * mapEntityToSalesOrgRoleModel.
	 *
	 * @param tRoleList
	 * @return SalesOrgRole
	 */
	public SalesOrgRole mapEntityToSalesOrgRoleModel(TRoleList tRoleList) {
		
		SalesOrgRole salesOrgRole = new SalesOrgRole();
		
		salesOrgRole.setId(tRoleList.getRoleId().longValue());
		
		if (null != tRoleList.getPrnRoleId()) {
			salesOrgRole.setParentSalesOrgRoleId(tRoleList.getPrnRoleId().longValue());
		}
		salesOrgRole.setName(tRoleList.getRoleName());
		salesOrgRole.setDescription(tRoleList.getRoleDesc());
		boolean flag = false;
		Character status = 'Y';
		if (status.equals(tRoleList.getActiveFlag())) {
			flag = true;
		}
		salesOrgRole.setActive(flag);
		salesOrgRole.setStartDate(tRoleList.getEffStartDt());
		salesOrgRole.setEndDate(tRoleList.getEffEndDt());
		salesOrgRole.setCreatedBy(tRoleList.getCreatedBy());
		salesOrgRole.setCreatedDate(tRoleList.getCreateDt());
		salesOrgRole.setUpdatedBy(tRoleList.getUpdatedBy());
		salesOrgRole.setUpdatedDate(tRoleList.getUpdateDt());

		return salesOrgRole;
	}
	
	/**
	 * mapSalesOrgRoleModelToEntityUpdate.
	 *
	 * @param salesOrgRoleDB
	 * @param salesOrgRole
	 * @param userDetails
	 * @return TRoleList
	 */
	public TRoleList mapSalesOrgRoleModelToEntityUpdate(SalesOrgRole salesOrgRoleDB, SalesOrgRole salesOrgRole, UserDetails userDetails) {
		
		TRoleList trList = new TRoleList();
		
		trList.setRoleId(salesOrgRoleDB.getId().intValue());
		trList.setRoleName(salesOrgRole.getName());
		trList.setRoleDesc(salesOrgRole.getDescription());
		
		if (null != salesOrgRole.getParentSalesOrgRoleId()) {
			trList.setPrnRoleId(salesOrgRole.getParentSalesOrgRoleId().intValue());
		}
		
		char flag = 'N';
		Boolean active = true;
		if (active.equals(salesOrgRoleDB.isActive())) {
			flag = 'Y';
		}
		trList.setDefRoleFlag('Y');
		trList.setActiveFlag(flag);
		trList.setEffStartDt(salesOrgRole.getStartDate());
		trList.setEffEndDt(salesOrgRole.getEndDate());
		trList.setUpdatedBy(salesOrgRole.getUpdatedBy());
		trList.setCreatedBy(salesOrgRoleDB.getCreatedBy());
		trList.setCreateDt(salesOrgRoleDB.getCreatedDate());
		trList.setUpdateDt(DateUtil.getCurrentDate());
		trList.setTenantId(userDetails.getTenantId());
		
		return trList;
		
	}
}
