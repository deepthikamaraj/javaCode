package com.cognizant.opserv.sp.assembler;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.core.entity.TAlgmntSalesHier;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.model.GeographyAlignment;

/**
 * The Class GeographyAlignmentEntityAssembler.
 */
@Component
public class GeographyAlignmentEntityAssembler {

	
	/**
	 * Map geo align model to entity.
	 * 
	 * @param geographyAlignment
	 *            the geography alignment
	 * @return the t terr zip algmnt
	 */
	public TTerrZipAlgmnt mapGeoAlignModelToEntity(
			GeographyAlignment geographyAlignment) {

		TTerrZipAlgmnt terrZipAlgmnt = new TTerrZipAlgmnt();
		TSalesPos tSalesPos = new TSalesPos();
		
		if(geographyAlignment.getSalesPosition() != null) {
			if (geographyAlignment.getSalesPosition().getHierarchy().getId() != null) {
				TAlgmntSalesHier algmntSalesHier = new TAlgmntSalesHier();
				algmntSalesHier.setSalesHierId(geographyAlignment.getSalesPosition().getHierarchy().getId());
				tSalesPos.setTAlgmntSalesHier(algmntSalesHier);
			}
			if (geographyAlignment.getSalesPosition().getId() != null) {
				tSalesPos.setSalesPosId(geographyAlignment.getSalesPosition().getId());	
			}
		}
		terrZipAlgmnt.setTenantId(geographyAlignment.getTenantId());
		terrZipAlgmnt.setTSalesPos(tSalesPos);

		return terrZipAlgmnt;

	}

	/**
	 * Map postal cd align model to entity.
	 * 
	 * @param geographyAlignment
	 *            the geography alignment
	 * @return the t terr zip algmnt
	 */
	public TTerrZipAlgmnt mapPostalCdAlignModelToEntity(
			GeographyAlignment geographyAlignment) {

		TTerrZipAlgmnt terrZipAlgmnt = new TTerrZipAlgmnt();
		if (geographyAlignment.getSalesPosition().getAlignmment().getId() != null) {
			if (geographyAlignment.getSalesPosition().getAlignmment()
					.getSalesTeam().getId() != null) {
				if (geographyAlignment.getSalesPosition().getAlignmment()
						.getSalesTeam().getBusinessUnit().getId() != null) {

					TSalesPosGeoUnit posGeoUnit = new TSalesPosGeoUnit();
					posGeoUnit.setAlgmntId(geographyAlignment
							.getSalesPosition().getAlignmment().getId());
					posGeoUnit.setSalesTeamId(geographyAlignment
							.getSalesPosition().getAlignmment().getSalesTeam()
							.getId());
					posGeoUnit.setBussUnitId(geographyAlignment
							.getSalesPosition().getAlignmment().getSalesTeam()
							.getBusinessUnit().getId());

					terrZipAlgmnt.setTenantId(geographyAlignment.getTenantId());
					terrZipAlgmnt.setPostalCd(geographyAlignment
							.getPostalCode().getCode());
					terrZipAlgmnt.setTSalesPosGeoUnit(posGeoUnit);
				}
			}
		}
		return terrZipAlgmnt;
	}

	/**
	 * Map aligned sales position info for zip to entity.
	 *
	 * @param geographyAlignment the geography alignment
	 * @return the t terr zip algmnt
	 */
	public TTerrZipAlgmnt mapAlignedSalesPositionInfoForZipToEntity(
			GeographyAlignment geographyAlignment) {
		TTerrZipAlgmnt terrZipAlgmnt = new TTerrZipAlgmnt();
		if (geographyAlignment.getPostalCode() != null) {
			terrZipAlgmnt.setPostalCd(geographyAlignment.getPostalCode()
					.getCode().toString());
			terrZipAlgmnt.setTenantId(geographyAlignment.getTenantId());
		}

		return terrZipAlgmnt;
	}

}