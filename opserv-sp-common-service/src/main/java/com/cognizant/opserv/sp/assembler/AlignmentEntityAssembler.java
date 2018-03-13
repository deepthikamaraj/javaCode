package com.cognizant.opserv.sp.assembler;



import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TBussUnit;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.auth.UserDetails;
public class AlignmentEntityAssembler {
	/**
	 * 
	 * @param talg
	 * @param alg
	 * @param userDetails
	 * @return
	 */
	public static TAlgmnt convertAlgmntModeltoEntity(TAlgmnt talg ,Alignment alg,UserDetails userDetails) {
		if(alg !=null)
		{	talg.setUpdateDt(DateUtil.getCurrentDate());
			talg.setUpdatedBy(userDetails.getUserId());
			talg.setAlgmntStatusId(2);
			}
		
		return talg;
		
	}
	/**
	 * 
	 * @param talg
	 * @param alg
	 * @param userDetails
	 * @return
	 */
	public static TAlgmnt convertAlgmntModeltoAlgmntEntity(TAlgmnt talg ,Alignment alg,UserDetails userDetails) {
		if(alg !=null)
		{
			talg.setUpdateDt(DateUtil.getCurrentDate());
			talg.setUpdatedBy(userDetails.getUserId());
			if(alg.isActive())
			{
			talg.setAlgmntStatusId(1);
			}
			talg.setAlgmntName(alg.getName());
			talg.setEffStartDt(alg.getStartDate());
			talg.setEffEndDt(alg.getEndDate());
			talg.setTBussUnit(talg.getTBussUnit());
			}
		
		return talg;
		
	}
	/**
	 * 
	 * @param tAlgmntSalesTeam
	 * @param alg
	 * @param userDetails
	 * @return
	 */
	public static TAlgmntSalesTeam convertTAlgmntSalesTeamEntity(TAlgmntSalesTeam tAlgmntSalesTeam ,Alignment alg,UserDetails userDetails) {
		if(alg !=null)
		{
			tAlgmntSalesTeam.setEffEndDt(alg.getEndDate());
			tAlgmntSalesTeam.setEffEndDt(alg.getStartDate());
			tAlgmntSalesTeam.setUpdatedBy(userDetails.getStaffId());
			tAlgmntSalesTeam.setUpdateDt(DateUtil.getCurrentDate());
	
			
		}
		return tAlgmntSalesTeam;
	}
	/**
	 * 
	 * @param alg
	 * @param userDetails
	 * @return
	 */
	public static TAlgmnt convertAlgmntModeltoEntityForCreateAlignment(Alignment alg,UserDetails userDetails) {
		
		TBussUnit bu  =new TBussUnit();
		TAlgmnt talg  =new TAlgmnt();
		if(alg !=null)
		{
			talg.setAlgmntId(alg.getId());
			talg.setCreatedBy(userDetails.getUserId());
			talg.setCreateDt(DateUtil.getCurrentDate());
			talg.setTenantId(userDetails.getTenantId());
			bu.setBussUnitId(alg.getSalesTeam().getBusinessUnit().getId());
			if(alg.isActive())
			{
			talg.setAlgmntStatusId(1);
			}
			talg.setAlgmntName(alg.getName());
			talg.setEffStartDt(alg.getStartDate());
			talg.setEffEndDt(alg.getEndDate());
			talg.setTBussUnit(bu);
			}
		
		return talg;
		
	}
	
	/**
	 * 
	 * @param tAlgmnt
	 * @param alg
	 * @param userDetails
	 * @return
	 */
	public static TAlgmntSalesTeam convertTAlgmntSalesTeamEntityForCreateAlignment(TAlgmnt tAlgmnt ,Alignment alg,UserDetails userDetails) {
		TAlgmntSalesTeamId tAlgmntSalesTeamId = new TAlgmntSalesTeamId();
		TAlgmntSalesTeam tAlgmntSalesTeam = new TAlgmntSalesTeam();
		if(alg !=null)
		{
			
			tAlgmntSalesTeamId.setAlgmntId(tAlgmnt.getAlgmntId());
			tAlgmntSalesTeamId.setBussUnitId(alg.getSalesTeam().getBusinessUnit().getId());
			tAlgmntSalesTeamId.setSalesTeamId(alg.getSalesTeam().getId());
			tAlgmntSalesTeam.setTAlgmntSalesTeamId(tAlgmntSalesTeamId);
			tAlgmntSalesTeam.setEffEndDt(alg.getEndDate());
			tAlgmntSalesTeam.setEffStartDt(alg.getStartDate());
			tAlgmntSalesTeam.setCreatedBy(userDetails.getUserId());
			tAlgmntSalesTeam.setCreateDt(DateUtil.getCurrentDate());
			tAlgmntSalesTeam.setTenantId(userDetails.getTenantId());
			tAlgmntSalesTeam.setActiveFlag(CommonConstants.ACTIVE_Y);
			
		}
		return tAlgmntSalesTeam;
	}

}
