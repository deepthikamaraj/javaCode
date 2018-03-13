package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TAlgmnt;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.SalesTeam;
/**
 * ***************************************************************************.
 *
 * @class AlignmentModelAssembler - Mapper class for Alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * ***************************************************************************
 */
@Component
public class AlignmentModelAssembler {
	
	/** The sales team model assembler. */
	@Autowired
	private SalesTeamModelAssembler salesTeamModelAssembler;
	
	/**
	 * Convert t algnmnt to alignment model.
	 *
	 * @param alignmEntity the alignm entity
	 * @return the alignment
	 */
	public Alignment convertTAlgnmntToAlignmentModel(TAlgmnt alignmEntity) {
		Alignment alignModel = null;
		if (null != alignmEntity) {
			alignModel = new Alignment();
			alignModel.setId(alignmEntity.getAlgmntId());
			alignModel.setName(alignmEntity.getAlgmntName());

			if (alignmEntity.getTAlgmntSalesTeamss() != null) {
				Set<TAlgmntSalesTeam> tAlignST = alignmEntity
						.getTAlgmntSalesTeamss();
				SalesTeam salesTeam = new SalesTeam();
				if (tAlignST != null) {
					for (TAlgmntSalesTeam st : tAlignST) {
						salesTeam
								.setName(st.getTSalesTeam().getSalesTeamName());
						salesTeam.setId(st.getTSalesTeam().getTSalesTeamId()
								.getSalesTeamId());
					}
				}

				if (alignmEntity.getTBussUnit() != null) {
					BusinessUnit businessUnit = new BusinessUnit();
					businessUnit.setId(alignmEntity.getTBussUnit()
							.getBussUnitId());
					businessUnit.setCode(alignmEntity.getTBussUnit()
							.getBussUnitCode());
					businessUnit.setName(alignmEntity.getTBussUnit()
							.getBussUnitName());
					salesTeam.setBusinessUnit(businessUnit);
				}
				alignModel.setSalesTeam(salesTeam);
			}
		}
		return alignModel;
	}
	
	
	/**
	 * Convert t algmnt to alignment model.
	 *
	 * @param tAlgmnt the t algmnt
	 * @return the alignment
	 */
	public Alignment convertTAlgmntToAlignmentModel(TAlgmnt tAlgmnt) {
		Alignment alignment =new Alignment();
		SalesTeam salesTeam=new SalesTeam();
		if(tAlgmnt!=null)
		{
			alignment.setId(tAlgmnt.getAlgmntId());
			alignment.setStartDate(tAlgmnt.getEffStartDt());
			alignment.setEndDate(tAlgmnt.getEffEndDt());
			alignment.setName(tAlgmnt.getAlgmntName());
			alignment.setCreatedBy(tAlgmnt.getCreatedBy());
			if(tAlgmnt.getTAlgmntSalesTeamss() != null && tAlgmnt.getTAlgmntSalesTeamss().size()>0)
			{
			 List<TAlgmntSalesTeam> list = new ArrayList<TAlgmntSalesTeam>(tAlgmnt.getTAlgmntSalesTeamss());
			 salesTeam = salesTeamModelAssembler.convertTSalesTeamToSalesTeamModel(list.get(0));
			}
			alignment.setSalesTeam(salesTeam);
			
			
		
		return alignment;
		}
		return alignment;
	}
	
	
}
