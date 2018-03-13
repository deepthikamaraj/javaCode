package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.entity.TPrd;
import com.cognizant.opserv.sp.core.entity.TPrdAlgmnt;
import com.cognizant.opserv.sp.model.ProductAlignment;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.auth.UserDetails;

/**
 * ****************************************************************************
 * Class Name: ProductAlignmentEntityAssembler for converting ProductAlignment
 * Model Object dts to the TPrdAlgmnt Entity.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 1.0
 * @since 1/4/2016
 *        **************************************************************
 *        *************
 */
@Component
public class ProductAlignmentEntityAssembler {

	/**
	 * toEntity object.
	 * 
	 * @param ProductAlignment
	 *            the Product alignment Model Object dts
	 * @return the TPrdAlgmnt Entity Object dts
	 */
	public TPrdAlgmnt convertProdAlgmntModeltoEntity(ProductAlignment productAlignment) {

		TPrdAlgmnt tPrdAlgmnt = null;

		if (null != productAlignment) {
			tPrdAlgmnt = new TPrdAlgmnt();
			tPrdAlgmnt.setPrdAlgmntId(productAlignment.getId());
			tPrdAlgmnt.setEffStartDt(productAlignment.getStartDate());
			tPrdAlgmnt.setEffEndDt(productAlignment.getEndDate());
			tPrdAlgmnt.setWtg(productAlignment.getWeightage());
			if (productAlignment.isActive()) {
				tPrdAlgmnt.setActiveFlag(CommonConstants.ACTIVE_Y);
			} else {
				tPrdAlgmnt.setActiveFlag(CommonConstants.ACTIVE_N);
			}

			TPrd prd = null;
			if (null != productAlignment.getProduct()) {
				prd = new TPrd();
				prd.setPrdId(productAlignment.getProduct().getId());
				prd.setPrdName(productAlignment.getProduct().getName());

			}
			tPrdAlgmnt.settPrd(prd);
			if (null != productAlignment.getSalesPosition()) {
				tPrdAlgmnt.setSalesPosId(productAlignment.getSalesPosition().getId());
			}
			if (null != productAlignment.getSalesPosition().getAlignmment()) {
				tPrdAlgmnt.setAlgmntId(productAlignment.getSalesPosition().getAlignmment().getId());
			}
			if (null != productAlignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit()) {
				tPrdAlgmnt.setBussUnitId(productAlignment.getSalesPosition().getAlignmment().getSalesTeam().getBusinessUnit().getId());
			}
			if (null != productAlignment.getSalesPosition().getAlignmment().getSalesTeam()) {
				tPrdAlgmnt.setSalesTeamId(productAlignment.getSalesPosition().getAlignmment().getSalesTeam().getId());
			}
			if (null != productAlignment.getSalesPosition().getHierarchy()) {
				tPrdAlgmnt.setSalesHierId(productAlignment.getSalesPosition().getHierarchy().getId());
			}

		}

		return tPrdAlgmnt;

	}

	/**
	 * Convert tot prd algnmt.
	 *
	 * @param tPrd the t prd
	 * @param salesPosition the sales position
	 * @param userDetails the user details
	 * @return the t prd algmnt
	 */
	public TPrdAlgmnt convertTotPrdAlgnmt(TPrd tPrd,
			SalesPosition salesPosition, UserDetails userDetails) {
		TPrdAlgmnt tPrdAlgmnt = new TPrdAlgmnt();
		tPrdAlgmnt.setActiveFlag(CommonConstants.ACTIVE_N); // SET as 'N'
		if (null != salesPosition.getAlignmment()) {
			tPrdAlgmnt.setAlgmntId(salesPosition.getAlignmment().getId());
		}
		if (null != salesPosition.getAlignmment()
				&& null != salesPosition.getAlignmment().getSalesTeam()
				&& null != salesPosition.getAlignmment().getSalesTeam()
						.getBusinessUnit()) {
			tPrdAlgmnt.setBussUnitId(salesPosition.getAlignmment()
					.getSalesTeam().getBusinessUnit().getId());
		}
		if (null != salesPosition.getAlignmment()
				&& null != salesPosition.getAlignmment().getSalesTeam()) {
			tPrdAlgmnt.setSalesTeamId(salesPosition.getAlignmment()
					.getSalesTeam().getId());
		}
		tPrdAlgmnt.setCreatedBy(userDetails.getStaffId());
		tPrdAlgmnt.setCreateDt(DateUtil.getCurrentDate());
		tPrdAlgmnt.setUpdatedBy(userDetails.getStaffId());
		tPrdAlgmnt.setUpdateDt(DateUtil.getCurrentDate());
		tPrdAlgmnt.setEffEndDt(salesPosition.getEndDate());
		tPrdAlgmnt.setEffStartDt(salesPosition.getStartDate());
		tPrdAlgmnt.setSalesHierId(salesPosition.getHierarchy().getId());
		tPrdAlgmnt.setSalesPosId(salesPosition.getId());
		tPrdAlgmnt.setTenantId(userDetails.getTenantId());
		tPrdAlgmnt.settPrd(tPrd);
		return tPrdAlgmnt;
	}
	
	

}
