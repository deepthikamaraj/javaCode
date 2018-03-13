package com.cognizant.opserv.sp.assembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.json.GeoInfo;
import com.cognizant.opserv.sp.json.SPTargetedGeoLocation;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;

@Component
public class GeographyAlignmentModelAssembler {
	
	
	/**
	 * @param postalCodeList
	 * @param salesPos
	 * @return List<GeographyAlignment>
	 */
	public List<GeographyAlignment> convertTTerrZipAlgmntsToModel(
			List<String> postalCodeList, SalesPosition salesPos) {

		List<GeographyAlignment> geographyAlignmentsList = new ArrayList<GeographyAlignment>();

		if (postalCodeList != null && !postalCodeList.isEmpty()) {
			for (String obj : postalCodeList) {
				GeographyAlignment geographyAlignment = new GeographyAlignment();
				geographyAlignment.setSalesPosition(salesPos);
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(obj);
				geographyAlignment.setPostalCode(postalCode);
				geographyAlignmentsList.add(geographyAlignment);
			}
		}

		return geographyAlignmentsList;
	}

	/**
	 * convert Postal code to Model
	 * 
	 * @param allPostalCodes
	 * @return List<PostalCode>
	 */
	public List<PostalCode> convertPostalCodesToModel(
			List<TTerrZipAlgmnt> terrZipAlgmntList) {
		List<PostalCode> postalCodesList = new ArrayList<PostalCode>();
		if (null != terrZipAlgmntList && !terrZipAlgmntList.isEmpty()) {
			for (TTerrZipAlgmnt terrZipAlgmnt : terrZipAlgmntList) {
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(terrZipAlgmnt.getPostalCd());
				postalCode.setActive(terrZipAlgmnt.getActiveFlag() != null
						&& terrZipAlgmnt.getActiveFlag().equals(
								CommonConstants.ACTIVE_Y) ? true : false);
				postalCode.setCreatedBy(terrZipAlgmnt.getCreatedBy());
				postalCode.setCreatedDate(terrZipAlgmnt.getCreateDt());
				postalCode.setEndDate(terrZipAlgmnt.getEffEndDt());
				postalCode.setStartDate(terrZipAlgmnt.getEffStartDt());
				postalCode.setId(terrZipAlgmnt.getGeoZipId().longValue());
				postalCode.setGeoCode(terrZipAlgmnt.getTSalesPosGeoUnit()
						.getTSalesPosGeoUnitId().getGeoId());
				postalCode.setTenantId(terrZipAlgmnt.getTenantId());
				postalCodesList.add(postalCode);
			}

		}
		return postalCodesList;
	}

	/**
	 * convert AlignedSalesPositionInfoForZip To Model
	 * 
	 * @param Aligned
	 * @return SalesPosition
	 */
	public SalesPosition convertAlignedSalesPositionInfoForZipToModel(
			List<TSalesPos> tSalesPos) {

		SalesPosition salesPosition = new SalesPosition();
		if (tSalesPos != null && !tSalesPos.isEmpty()) {

			for (TSalesPos salesPos : tSalesPos) {

				SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
				Alignment alignment = new Alignment();
				BusinessUnit businessUnit = new BusinessUnit();
				SalesTeam salesTeam = new SalesTeam();
				
				salesOrgHierarchy.setId(salesPos.getTAlgmntSalesHier().getSalesHierId());
				alignment.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
				salesTeam.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
				businessUnit.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
				salesTeam.setBusinessUnit(businessUnit);
				alignment.setSalesTeam(salesTeam);
				salesPosition.setName(salesPos.getPosName());
				salesPosition.setCode(salesPos.getPosCd());
				salesPosition.setId(salesPos.getSalesPosId());
				salesPosition.setActive(salesPos.getActiveFlag() != null && salesPos.getActiveFlag().equals(
						CommonConstants.ACTIVE_Y) ? true : false);
				salesPosition.setStartDate(salesPos.getEffStartDt());
				salesPosition.setEndDate(salesPos.getEffEndDt());
				salesPosition.setHierarchy(salesOrgHierarchy);
				salesPosition.setAlignmment(alignment);

			}
		}
		return salesPosition;
	}

	/**
	 * Convert SPGeoLocation location to model.
	 * 
	 * @param spgeoLocation
	 *            the spgeo location
	 * @return the list of GeographyAlignment
	 */
	public List<GeoCustomerAlignment> convertSPGeoLocationToModel(
			SPTargetedGeoLocation spgeoLocation) {
		
		List<GeoCustomerAlignment> geographyCustAlignmentsList =new ArrayList<GeoCustomerAlignment>();
		
		
		if(null !=spgeoLocation.getGeoInfos() && !spgeoLocation.getGeoInfos().isEmpty()){
			
			SalesPosition salesPosition = null;
			if(null != spgeoLocation.getSalesposId()){
				salesPosition = new SalesPosition();
				salesPosition.setId(spgeoLocation.getSalesposId());
				salesPosition.setName(null == spgeoLocation.getSalesposName() ? "" : spgeoLocation.getSalesposName());
			}
			
			
			for (GeoInfo geoInfo:spgeoLocation.getGeoInfos()) {
				GeoCustomerAlignment geographyCustAlignment=new GeoCustomerAlignment();
				if(null != salesPosition){
					geographyCustAlignment.setSalesPosition(salesPosition);
				}
				
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(geoInfo.getZip() == null ? "" : geoInfo.getZip());
				postalCode.setGeoCode(geoInfo.getGeoCode());
				geographyCustAlignment.setPostalCode(postalCode);
				geographyCustAlignment.setCompEligibleCustomerCount(geoInfo.getCompEligibleCustomerCount());
				geographyCustAlignment.setCompGeoAlignedCustomerCount(geoInfo.getCompGeoAlignedCustomerCount());
				
				geographyCustAlignmentsList.add(geographyCustAlignment);
			}
		}
		
		return geographyCustAlignmentsList;
	}
	
	/**
	 * @param postalCodeList
	 * @param salesPos
	 * @param geoAlgdCustMap
	 * @param compElgCustMap
	 * @return List<GeoCustomerAlignment>
	 */
	public List<GeoCustomerAlignment> convertTTerrZipAlgmntsToModel(
			List<TTerrZipAlgmnt> terrZipAlgmntList, SalesPosition salesPos, Map<String, Long> geoAlgdCustMap, Map<String, Long> compElgCustMap) {

		List<GeoCustomerAlignment> geographyAlignmentsList = new ArrayList<GeoCustomerAlignment>();

		if (terrZipAlgmntList != null && !terrZipAlgmntList.isEmpty()) {
			for (TTerrZipAlgmnt terrZipAlgmnt : terrZipAlgmntList) {
				GeoCustomerAlignment geoCustomerAlignment = new GeoCustomerAlignment();
				geoCustomerAlignment.setSalesPosition(salesPos);
				
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(terrZipAlgmnt.getPostalCd());
				postalCode.setActive(true);
				postalCode.setCreatedBy(terrZipAlgmnt.getCreatedBy());
				postalCode.setCreatedDate(terrZipAlgmnt.getCreateDt());
				postalCode.setEndDate(terrZipAlgmnt.getEffEndDt());
				postalCode.setStartDate(terrZipAlgmnt.getEffStartDt());
				postalCode.setId(terrZipAlgmnt.getGeoZipId().longValue());
				postalCode.setGeoCode(terrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId());
				postalCode.setTenantId(terrZipAlgmnt.getTenantId());
				geoCustomerAlignment.setAssigned(CommonConstants.TRUE);
				if(terrZipAlgmnt.getCountryId() != null){
				geoCustomerAlignment.setCountryCode(terrZipAlgmnt.getCountryId().toString());
				}
				if(terrZipAlgmnt.getStatusId() != null){
					geoCustomerAlignment.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(terrZipAlgmnt.getStatusId()));	
				}
				
				geoCustomerAlignment.setPostalCode(postalCode);
				if(compElgCustMap != null && geoAlgdCustMap != null && compElgCustMap.get(postalCode.getCode()) != null){
					geoCustomerAlignment.setCompEligibleCustomerCount((compElgCustMap.get(postalCode.getCode())).intValue());
					geoCustomerAlignment.setCompGeoAlignedCustomerCount((geoAlgdCustMap.get(postalCode.getCode())).intValue());
				} else {
					geoCustomerAlignment.setCompEligibleCustomerCount(CommonConstants.zeroCount);
					geoCustomerAlignment.setCompGeoAlignedCustomerCount(CommonConstants.zeroCount);
				}
				geoCustomerAlignment.setPointZipFlag(terrZipAlgmnt.getPointZipFlag() != null && terrZipAlgmnt.getPointZipFlag().equals(CommonConstants.ACTIVE_Y)? true :false);
				
				geographyAlignmentsList.add(geoCustomerAlignment);
			}
		}
		
		return geographyAlignmentsList;
	}
	
	
	/**
	 * @param postalCodeList
	 * @param salesPos
	 * @param geoAlgdCustMap
	 * @param compElgCustMap
	 * @return List<GeoCustomerAlignment>
	 */
	public List<GeographyAlignment> convertTTerrZipAlgmntsToGeoAlignment(List<TTerrZipAlgmnt> terrZipAlgmntList, SalesPosition salesPos) {

		List<GeographyAlignment> geographyAlignmentsList = new ArrayList<GeographyAlignment>();

		if (terrZipAlgmntList != null && !terrZipAlgmntList.isEmpty()) {
			for (TTerrZipAlgmnt terrZipAlgmnt : terrZipAlgmntList) {
				GeographyAlignment geographyAlignment = new GeographyAlignment();
				geographyAlignment.setSalesPosition(salesPos);
				PostalCode postalCode = new PostalCode();
				postalCode.setCode(terrZipAlgmnt.getPostalCd());
				postalCode.setActive(true);
				postalCode.setCreatedBy(terrZipAlgmnt.getCreatedBy());
				postalCode.setCreatedDate(terrZipAlgmnt.getCreateDt());
				postalCode.setEndDate(terrZipAlgmnt.getEffEndDt());
				postalCode.setStartDate(terrZipAlgmnt.getEffStartDt());
				postalCode.setId(terrZipAlgmnt.getGeoZipId().longValue());
				postalCode.setGeoCode(terrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId());
				postalCode.setTenantId(terrZipAlgmnt.getTenantId());
				geographyAlignment.setAssigned(CommonConstants.TRUE);
				if (terrZipAlgmnt.getCountryId() != null) {
					geographyAlignment.setCountryCode(terrZipAlgmnt.getCountryId().toString());
				}
				if (terrZipAlgmnt.getStatusId() != null) {
					geographyAlignment.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(terrZipAlgmnt.getStatusId()));
				}
				geographyAlignment.setPostalCode(postalCode);
				geographyAlignment.setPointZipFlag(terrZipAlgmnt.getPointZipFlag() != null && terrZipAlgmnt.getPointZipFlag().equals(CommonConstants.ACTIVE_Y) ? true : false);
				geographyAlignmentsList.add(geographyAlignment);
			}
		}
		return geographyAlignmentsList;
	}

	public GeographyAlignment mapTTerrZipAlgmntToModel(TTerrZipAlgmnt terrZipAlgmnt) {

		SalesPosition salesPosition = new SalesPosition();
		TSalesPos salesPos= terrZipAlgmnt.getTSalesPos();

		if(null!=salesPos.getSalesPosId()){
				salesPosition.setId(salesPos.getSalesPosId());	
			}
			salesPosition.setCode(salesPos.getPosCd());
			Alignment alignment = new Alignment();
			SalesTeam salesTeam = new SalesTeam();
			BusinessUnit businessUnit = new BusinessUnit();
			if(null!=salesPos.getTAlgmntSalesTeam() && null!=salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId()){
				alignment.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getAlgmntId());
				
				salesTeam.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getSalesTeamId());
				businessUnit.setId(salesPos.getTAlgmntSalesTeam().getTAlgmntSalesTeamId().getBussUnitId());
			}
			salesTeam.setBusinessUnit(businessUnit);
			alignment.setSalesTeam(salesTeam);
			
			SalesOrgHierarchy salesOrgHierarchy = new SalesOrgHierarchy();
			if(null!=salesPos.getTAlgmntSalesHier() && salesPos.getTAlgmntSalesHier().getSalesHierId()!=null){
				salesOrgHierarchy.setId(salesPos.getTAlgmntSalesHier().getSalesHierId());
			}
			
			salesPosition.setAlignmment(alignment);
			salesPosition.setHierarchy(salesOrgHierarchy);
		
		GeographyAlignment geographyAlignment = new GeographyAlignment();
		geographyAlignment.setSalesPosition(salesPosition);
		PostalCode postalCode = new PostalCode();
		postalCode.setCode(terrZipAlgmnt.getPostalCd());
		postalCode.setActive(true);
		postalCode.setId(terrZipAlgmnt.getGeoZipId().longValue());
		postalCode.setGeoCode(terrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId());
		postalCode.setTenantId(terrZipAlgmnt.getTenantId());
		geographyAlignment.setAssigned(CommonConstants.TRUE);
		if (terrZipAlgmnt.getStatusId() != null) {
			geographyAlignment.setStatus(ChangeRequestStatusType.getChangeRequestStatusType(terrZipAlgmnt.getStatusId()));
		}
		geographyAlignment.setPostalCode(postalCode);
		geographyAlignment.setPointZipFlag(terrZipAlgmnt.getPointZipFlag() != null && terrZipAlgmnt.getPointZipFlag().equals(CommonConstants.ACTIVE_Y) ? true : false);
		geographyAlignment.setCountryCode(terrZipAlgmnt.getCountryId().toString());
		
		return geographyAlignment;
	}
}
