package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeam;
import com.cognizant.opserv.sp.core.entity.TAlgmntSalesTeamId;
import com.cognizant.opserv.sp.core.entity.TCallPlanConfig;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.core.entity.TCustCallPlan;
import com.cognizant.opserv.sp.model.CallPlanConfiguration;
import com.cognizant.opserv.sp.model.CustomerAlignment;

// TODO: Auto-generated Javadoc
/**
 * The Class callPlanEntityAssembler.
 */
@Component
public class CallPlanEntityAssembler {


	/**
	 * Map call plan config to entity.
	 * 
	 * @param planConfiguration
	 *            the plan configuration
	 * @return the t call plan config
	 */
	public TCallPlanConfig mapCallPlanConfigToEntity(CallPlanConfiguration planConfiguration) {
		TCallPlanConfig callPlanConfiguration = new TCallPlanConfig();
		if (planConfiguration != null) {
				TAlgmntSalesTeam algmntSalesTeam = new TAlgmntSalesTeam();
				TAlgmntSalesTeamId tAlgmntSalesTeamId = new TAlgmntSalesTeamId();

				tAlgmntSalesTeamId.setAlgmntId(planConfiguration.getAlignment().getId());
				if(null!=planConfiguration.getAlignment() && null!= planConfiguration.getAlignment().getSalesTeam() && null!=planConfiguration.getAlignment().getSalesTeam().getBusinessUnit()){
				tAlgmntSalesTeamId.setBussUnitId(planConfiguration.getAlignment().getSalesTeam().getBusinessUnit().getId());
				}
				if(null!= planConfiguration.getAlignment()&& null!= planConfiguration.getAlignment().getSalesTeam()){
				tAlgmntSalesTeamId.setSalesTeamId(planConfiguration.getAlignment().getSalesTeam().getId());
				}
				algmntSalesTeam.setTAlgmntSalesTeamId(tAlgmntSalesTeamId);
				callPlanConfiguration.setTAlgmntSalesTeam(algmntSalesTeam);
				callPlanConfiguration.setTenantId(planConfiguration.getTenantId());
		}

		return callPlanConfiguration;
	}

	/**
	 * Map cust call plan infoo entity.
	 * 
	 * @param custalignment
	 *            the custalignment
	 * @return the t cust call plan
	 */
	public TCustCallPlan mapCustCallPlanInfoEntity(CustomerAlignment custalignment) {
		TCustCallPlan tCustCallPlan = new TCustCallPlan();
		if (custalignment.getSalesPosition().getId() != null) {
			if (custalignment.getSalesPosition().getHierarchy().getId() != null) {
				if (custalignment.getSalesPosition().getAlignmment().getId() != null) {
					if (custalignment.getSalesPosition().getAlignmment().getSalesTeam().getId() != null) {
						if (custalignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId() != null) {
							TCustAlgmnt tCustAlgmnt = new TCustAlgmnt();
							TCust tCust = new TCust();
							if(null != custalignment.getCustomer()){
							tCust.setCustId(custalignment.getCustomer().getId().intValue());
							}
							if(null!= custalignment.getSalesPosition()){
							tCustAlgmnt.setSalesPosId(custalignment.getSalesPosition().getId());
							}
							if(null!=custalignment.getSalesPosition()&& null!= custalignment.getSalesPosition().getHierarchy()){
							tCustAlgmnt.setSalesHierId(custalignment.getSalesPosition().getHierarchy().getId());
							}
							if(null!= custalignment.getSalesPosition() && null != custalignment.getSalesPosition().getAlignmment()){
							tCustAlgmnt.setAlgmntId(custalignment.getSalesPosition().getAlignmment().getId());
							}
							if(null != custalignment.getSalesPosition() && null != custalignment.getSalesPosition().getAlignmment() && null != custalignment.getSalesPosition().getAlignmment().getSalesTeam() && null != custalignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit()){
							tCustAlgmnt.setBussUnitId(custalignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId());
							}
							if(null != custalignment.getSalesPosition() && null != custalignment.getSalesPosition().getAlignmment() && null != custalignment.getSalesPosition().getAlignmment().getSalesTeam()){
							tCustAlgmnt.setSalesTeamId(custalignment.getSalesPosition().getAlignmment().getSalesTeam().getId());
							}
							tCustAlgmnt.setTenantId(custalignment.getTenantId());
							tCustAlgmnt.setTCust(tCust);

							tCustCallPlan.setTCustAlgmnt(tCustAlgmnt);

						}

					}

				}

			}

		}

		return tCustCallPlan;
	}
}
