package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.opserv.sp.assembler.GeographyAlignmentEntityAssembler;
import com.cognizant.opserv.sp.assembler.GeographyAlignmentModelAssembler;
import com.cognizant.opserv.sp.assembler.SalesPositionModelAssembler;
import com.cognizant.opserv.sp.common.ChangeRequestOfflineStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestStatusType;
import com.cognizant.opserv.sp.common.ChangeRequestTriggerType;
import com.cognizant.opserv.sp.common.CommonConstants;
import com.cognizant.opserv.sp.common.StatusType;
import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.core.common.ApplicationConstant;
import com.cognizant.opserv.sp.core.dao.TChngReqDAO;
import com.cognizant.opserv.sp.core.dao.TChngreqOfflineDAO;
import com.cognizant.opserv.sp.core.dao.TCustAlgmntDAO;
import com.cognizant.opserv.sp.core.dao.TCustDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosDAO;
import com.cognizant.opserv.sp.core.dao.TSalesPosGeoUnitDAO;
import com.cognizant.opserv.sp.core.dao.TTerrZipAlgmntDAO;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TChngreqOffline;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TSalesPos;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnit;
import com.cognizant.opserv.sp.core.entity.TSalesPosGeoUnitId;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmnt;
import com.cognizant.opserv.sp.core.entity.TTerrZipAlgmntChngReqDet;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException.AlignmentServiceExceptionCode;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.ZipBlob;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.persistence.dao.service.CustomerAlignmentDAOService;
import com.cognizant.opserv.sp.persistence.dao.service.GeographyAlignmentDAOService;
import com.cognizant.peg.core.common.JSONUtil;
import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 * 
 * @class EmployeeAlignmentServiceImpl contains all the Dao services for
 *        employee alignment
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/03/2016
 *        ************************************************************
 *        ***************
 */
@Component
public class GeographyAlignmentDAOServiceImpl implements
		GeographyAlignmentDAOService {

	/** The t terr zip algmnt dao. */
	@Autowired
	private TTerrZipAlgmntDAO tTerrZipAlgmntDAO;
	
	/** The t sales pos dao. */
	@Autowired
	private TSalesPosDAO tSalesPosDao;
	
	@Autowired
	private TCustAlgmntDAO tCustAlgmntDAO;
	
	@Autowired
	private TSalesPosGeoUnitDAO tSalesPosGeoUnitDAO;
	
	@Autowired
	private TChngreqOfflineDAO tChngreqOfflineDAO;
	
	@Autowired
	private TChngReqDAO tChngReqDAO;
	
	/** The sales position model assembler. */
	@Autowired
	private SalesPositionModelAssembler salesPositionModelAssembler;
	
	@Autowired
	private GeographyAlignmentEntityAssembler geographyAlignmentEntityAssembler;
	
	@Autowired
	private GeographyAlignmentModelAssembler geographyAlignmentModelAssembler;
	
	@Autowired
	private TCustDAO tCustDAO;
	
	@Autowired
	private CustomerAlignmentDAOService customerAlignmentDAOService;
	
	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(GeographyAlignmentDAOServiceImpl.class);
	
	/** The Constant LOGGER. */
	private static final OpservLogger WORKLOG = OpservLoggerFactory.getOpservLogger(CommonConstants.CR_PROCESSING_LOG);

	/**
	 * This method is used to get all postal code by SPID and SHID
	 * @method getAllGeographyAlignments
	 * @param salesPosition
	 * @param userDetails
	 * @return List<GeoCustomerAlignment>
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<GeoCustomerAlignment> getAllGeographyAlignments(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
		try {
			
			LOGGER.info("***********************GET ALL GEOGRAPHY ALIGNMENTS STARTED*****************************");
			GeographyAlignment geographyAlignment = new GeographyAlignment();
			TTerrZipAlgmnt terrZipAlgmnt = new TTerrZipAlgmnt();
			List<TTerrZipAlgmnt> terrZipAlgmntList = null;
			//List<String> postalCodeList = new ArrayList<String>();

			geographyAlignment.setSalesPosition(salesPosition);
			geographyAlignment.setTenantId(userDetails.getTenantId());

			terrZipAlgmnt = geographyAlignmentEntityAssembler
					.mapGeoAlignModelToEntity(geographyAlignment);

			if (terrZipAlgmnt != null) {
				LOGGER.info("***********************DB CALL TO TERR ZIP ALGMNT TO FETCH GEOGRAPHY ALIGNMENTS*****************************");
				terrZipAlgmntList = tTerrZipAlgmntDAO
						.getAllGeographyAlignments(terrZipAlgmnt);
				SalesPosition salesPos = getSalesPositionById(
						salesPosition.getId(), userDetails.getTenantId());
				
				/*if(terrZipAlgmntList != null){
					for(TTerrZipAlgmnt tTerrZipAlgmnt:terrZipAlgmntList){
						postalCodeList.add(tTerrZipAlgmnt.getPostalCd());
					}
				}
				
				if (postalCodeList != null && !postalCodeList.isEmpty()) {
					SalesPosition salesPos = getSalesPositionById(
							salesPosition.getId(), userDetails.getTenantId());

					Long spId = salesPosition.getId();
					Long shId = salesPosition.getHierarchy().getId();

					
					Map<String, Long> geoAlgdCustMap = new HashMap<String, Long>();
					LOGGER.info("***********************DB CALL TO FETCH COUNT GEO-ALIGNED CUSTOMERS*****************************");
					List<Object[]> geoAlgdCustList = tCustAlgmntDAO
							.getCountGeoAlgdCust(spId, shId, postalCodeList,
									userDetails.getTenantId());
					if (geoAlgdCustList != null && !geoAlgdCustList.isEmpty()) {
						for (Object[] objects : geoAlgdCustList) {
							if (objects[0] != null) {
								if (objects[1] != null) {
									geoAlgdCustMap.put(objects[0].toString(),
											(Long) objects[1]);

								}

							}

						}
					}

					Map<String, Long> compElgCustMap = new HashMap<String, Long>();
					LOGGER.info("***********************DB CALL TO FETCH COUNT OF COMP ELIGIBLE CUSTOMERS*****************************");
					List<Object[]> compElgCustList = tCustAlgmntDAO
							.getCountCompElgCust(spId, shId, postalCodeList,
									userDetails.getTenantId());
					if (compElgCustList != null && !compElgCustList.isEmpty()) {
						for (Object[] objects : compElgCustList) {
							if (objects[0] != null) {
								if (objects[1] != null) {
									compElgCustMap.put(objects[0].toString(),
											(Long) objects[1]);
								}
							}

						}
					}
					LOGGER.info("***********************GET ALL GEOGRAPHY ALIGNMENTS ENDED*****************************");*/
					List<GeoCustomerAlignment> geoCustAlgnmtList = geographyAlignmentModelAssembler
							.convertTTerrZipAlgmntsToModel(terrZipAlgmntList,salesPos, null, null);
					return geoCustAlgnmtList;
				}

		} catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching Geography Alignments for a Sales Position", re);
			throw new OpservDataAccessException("This issue is occurred while Fetching Geography Alignments for a Sales Position", re);
		}
		return null;
	}

	/**
	 * This method is used to search postal code by postal code and alignment 
	 * @method getAllPostalCodes 
	 * @param code
	 * @param alignment
	 * @param userDetails
	 * @return List<PostalCode>
	 * @throws OpservDataAccessException
	 */
	@Override
	public List<PostalCode> getAllPostalCodes(String code, Alignment alignment,
			UserDetails userDetails) throws OpservDataAccessException {
		try {
			LOGGER.info("***********************GET ALL POSTAL CODES STARTED*****************************");
			PostalCode postalCode = new PostalCode();
			GeographyAlignment geographyAlignment = new GeographyAlignment();
			SalesPosition position = new SalesPosition();

			postalCode.setCode(code);
			position.setAlignmment(alignment);

			geographyAlignment.setSalesPosition(position);
			geographyAlignment.setTenantId(userDetails.getTenantId());
			geographyAlignment.setPostalCode(postalCode);
			List<TTerrZipAlgmnt> allPostalCodes = null;

			TTerrZipAlgmnt terrZipAlgmnt = geographyAlignmentEntityAssembler
					.mapPostalCdAlignModelToEntity(geographyAlignment);

			if (null != terrZipAlgmnt
					&& null != terrZipAlgmnt.getTSalesPosGeoUnit()) {
				LOGGER.info("***********************DB CALL TO TERR ZIP ALGMNT TO FETCH POSTAL CODES*****************************");
				allPostalCodes = tTerrZipAlgmntDAO
						.getAllPostalCodes(terrZipAlgmnt);
				if (allPostalCodes != null && !allPostalCodes.isEmpty()) {
					LOGGER.info("***********************GET ALL POSTAL CODES ENDED*****************************");
					List<PostalCode> postalCodeList = geographyAlignmentModelAssembler
							.convertPostalCodesToModel(allPostalCodes);
					return postalCodeList;
				}
			}
		} catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching PostalCodes.", re);
			throw new OpservDataAccessException("This issue is occurred while Fetching PostalCodes.", re);
		}
		return null;

	}
	
	/**
	 * This method is used to get SalesPosition Info for a postal code
	 * @method getAlignedSalesPositionInfoForZip
	 * @param postalCode
	 * @param userDetails
	 * @return SalesPosition
	 * @throws OpservDataAccessException
	 */
	public SalesPosition getAlignedSalesPositionInfoForZip(
			PostalCode postalCode, UserDetails userDetails)throws OpservDataAccessException {

		try {
			LOGGER.info("***********************GET ALIGNED SALESPOSITION INFO FOR ZIP STARTED*****************************");
			
			PostalCode pstlcode = new PostalCode();
			GeographyAlignment geographyAlignment = new GeographyAlignment();
			pstlcode.setCode(postalCode.getCode());

			geographyAlignment.setPostalCode(pstlcode);
			geographyAlignment.setTenantId(userDetails.getTenantId());

			List<TSalesPos> algndSPInfoFrZipDetails = null;

			TTerrZipAlgmnt terrZipAlgmnt = geographyAlignmentEntityAssembler
					.mapAlignedSalesPositionInfoForZipToEntity(geographyAlignment);

			if (terrZipAlgmnt != null) {
				LOGGER.info("***********************DB CALL TO FETCH SALESPOSITION DETAILS*****************************");
				algndSPInfoFrZipDetails = tSalesPosDao
						.getSalesPosDetails(terrZipAlgmnt);
				if (algndSPInfoFrZipDetails != null
						&& !algndSPInfoFrZipDetails.isEmpty()) {
					LOGGER.info("***********************GET ALIGNED SALESPOSITION INFO FOR ZIP ENDED*****************************");
					SalesPosition salesposition = geographyAlignmentModelAssembler.convertAlignedSalesPositionInfoForZipToModel(algndSPInfoFrZipDetails);
					return salesposition;
				}
			}
		}

		catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching PostalCodes for Aligned SalesPosition", re);
			throw new OpservDataAccessException("This issue is occurred while Fetching PostalCodes for Aligned SalesPosition", re);
		}
		return null;

	}

	/**
     * getSalesPositionById
     * 
     * @param salesPosId
     * @param tenantId
     * @return SalesPosition
     */
	public SalesPosition getSalesPositionById(Long salesPosId, Short tenantId) throws OpservDataAccessException {
		try{
			TSalesPos tSalesPos = tSalesPosDao.findTSalesPosBySPId(salesPosId,
					tenantId);
			SalesPosition salesPosition = null;
			if (null != tSalesPos) {
				salesPosition = salesPositionModelAssembler
						.mapSPEntityDetailsToSalesPos(tSalesPos, tenantId);
			}
			return salesPosition;
		}

		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while fetching SalesPosition by SalesPosId", re);
			throw new OpservDataAccessException("This issue occurred while fetching SalesPosition by SalesPosId", re);
		}
		
	}
	@Override
	public Integer deactivateZipAlgmntOffline(SalesPosition salesPosition,
			PostalCode postalCd, StatusType status, char flag,UserDetails userDetails) throws OpservDataAccessException {
		try{
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  INSIDE GeographyAlignmentDAOServiceImpl.deactivateZipAlgmntOffline() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Long saleHierId = salesPosition.getHierarchy().getId();
		Long salePosId = salesPosition.getId();
		String zipID = postalCd.getCode();
		short tenantId = userDetails.getTenantId();
		//Integer geoId = 0;

		
		Integer geoId = (int) (long) postalCd.getGeoCode();
		LOGGER.info("*****************BEFORE CALLING tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntList()  *****************");
		List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntList(saleHierId, salePosId,zipID, tenantId,geoId);
		LOGGER.debug("*****************AFTER CALLING tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntList()  *****************", tTerrZipAlgmntList);
		TTerrZipAlgmnt tTerrZipAlgmnt = null;
		if(tTerrZipAlgmntList !=null && tTerrZipAlgmntList.size()>0){
			tTerrZipAlgmnt = tTerrZipAlgmntList.get(0);
			if(StatusType.PENDING_APPROVAL.getId().equals(tTerrZipAlgmnt.getStatusId())){
				LOGGER.info("Status of ZIP is Pending for submission");
//				Object[] args = new Object[] { tTerrZipAlgmnt.getPostalCd()};
				throw new OpservDataAccessException(
						"ZIP selected is already submitted for movement");
			}else{
				geoId = tTerrZipAlgmnt
						.getTSalesPosGeoUnit()
						.getTSalesPosGeoUnitId().getGeoId();
				tTerrZipAlgmnt
					.setAssignedFlag(flag);
			tTerrZipAlgmnt
					.setActiveFlag(flag);
				tTerrZipAlgmnt.setUpdateDt(new Date());
				tTerrZipAlgmnt.setUpdatedBy(userDetails
						.getUserId());
				int statusId = status.getId();
				tTerrZipAlgmnt.setStatusId(statusId);
				LOGGER.info("*****************BEFORE CALLING tTerrZipAlgmntDAO.updateTTerrZipAlgmnt  *****************");

				tTerrZipAlgmntDAO
				.updateTTerrZipAlgmnt(tTerrZipAlgmnt);
				LOGGER.debug("*****************AFTER CALLING tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntList()  *****************");
			}
		} else {
			LOGGER.error("No Active TTerrZipAlgmnt found for the " + " saleHierId" + saleHierId + ", salePosId"
					+ salePosId + ", zipID" + zipID + ", tenantId" + tenantId + ", geoId" + geoId);
			throw new OpservDataAccessException("No Active TTerrZipAlgmnt found");
		}
		LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  BEFORE LEAVING GeographyAlignmentDAOServiceImpl.deactivateZipAlgmntOffline() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		return geoId;
		}
		catch (RuntimeException re) {
			LOGGER.error("Issue occurred while deactivating zip Algmnt offline based on salesPosition and postal code", re);
			throw new OpservDataAccessException("Issue occurred while deactivating zip Algmnt offline based on salesPosition and postal code", re);
		}
	}
	@Override
	public void deactivateZipAlgmntOffline(GeographyAlignment geoAlgmnt, StatusType status, char flag,UserDetails userDetails) throws OpservDataAccessException {
		try{
			LOGGER.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  INSIDE GeographyAlignmentDAOServiceImpl.deactivateZipAlgmntOffline() @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
			LOGGER.debug("geoAlgmnt :"+geoAlgmnt.toString() );
			Integer geoId = (int) geoAlgmnt.getPostalCode().getGeoCode();
			List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntList(geoAlgmnt.getSalesPosition().getHierarchy().getId(), 
					geoAlgmnt.getSalesPosition().getId(), geoAlgmnt.getPostalCode().getCode(), userDetails.getTenantId(),geoId);
			LOGGER.debug("tTerrZipAlgmntList :"+tTerrZipAlgmntList.toString()  );
			TTerrZipAlgmnt tTerrZipAlgmnt = null;
			if(tTerrZipAlgmntList !=null && tTerrZipAlgmntList.size()>0){
				tTerrZipAlgmnt = tTerrZipAlgmntList.get(0);
				LOGGER.debug("tTerrZipAlgmnt :"+tTerrZipAlgmnt.toString()  );

				if(StatusType.PENDING_APPROVAL.getId().equals(tTerrZipAlgmnt.getStatusId())){
					//if Zip Already present in pending approval status throwing error
					LOGGER.error("Status of ZIP is Pending for submission");
//					Object[] args = new Object[] { tTerrZipAlgmnt.getPostalCd()};
					throw new OpservDataAccessException(							
							"ZIP selected is already submitted for movement");
				}else{
					//normal execution: we change the status of the zip to pending Approval hence, locking the source mirror
					geoId = tTerrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId();
					tTerrZipAlgmnt.setAssignedFlag(flag);
					tTerrZipAlgmnt.setActiveFlag(flag);
					tTerrZipAlgmnt.setUpdateDt(new Date());
					tTerrZipAlgmnt.setUpdatedBy(userDetails.getUserId());
					tTerrZipAlgmnt.setStatusId(status.getId());
					LOGGER.info("*****************BEFORE CALLING tTerrZipAlgmntDAO.updateTTerrZipAlgmnt  *****************");
					TTerrZipAlgmnt zipAlgmntObj = tTerrZipAlgmntDAO.updateTTerrZipAlgmnt(tTerrZipAlgmnt);
					LOGGER.debug("zipAlgmntObj :"+zipAlgmntObj.toString() );
					

				}
			}else{
				LOGGER.error("ZIP doesn't belong to mirror sales pos :"+geoAlgmnt.getSalesPosition().getId());
				Object[] args = new Object[] { geoAlgmnt.getPostalCode().getCode()};
				throw new OpservDataAccessException(
						"ZIP doesn't belong to mirror sales pos");
			}
		}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while deactivating zip Algmnt offline based on salesPosition and postal code", re);
			throw new OpservDataAccessException("This issue occurred while deactivating zip Algmnt offline based on salesPosition and postal code", re);
		}
	}
	@Override
	public Integer insertUpdateZipAlgmnt(GeographyAlignment targetGeoAlign,
			PostalCode postalCd, UserDetails userDetails,Integer geoID) throws OpservDataAccessException{
		try{
		SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
		Long saleHierId = targetSalesPos.getHierarchy().getId();
		Long salePosId = targetSalesPos.getId();
		Integer geoId = 0;
		short tenantId = userDetails.getTenantId();
		//Set<String> zipSet = new HashSet<String>();
		
		TTerrZipAlgmnt tTerrZipAlgmnt = new TTerrZipAlgmnt();
		TSalesPosGeoUnitId tSalesPosGeoUnitId = new TSalesPosGeoUnitId();
		
		tSalesPosGeoUnitId.setGeoId(geoID);
		tSalesPosGeoUnitId.setSalesHierId(saleHierId);
		tSalesPosGeoUnitId.setSalesPosId(salePosId);
		TSalesPosGeoUnit tSalesPosGeoUnitById = tSalesPosGeoUnitDAO.findTSalesPosGeoUnitById(tSalesPosGeoUnitId);
		
		if(tSalesPosGeoUnitById!=null){

			tTerrZipAlgmnt.setTSalesPosGeoUnit(tSalesPosGeoUnitById);
			
			
			// assigning the pointZipFlag and CountryId in targetGeoAlign
			tTerrZipAlgmnt.setCountryId(Integer.parseInt(targetGeoAlign.getCountryCode()));
			if(targetGeoAlign.isPointZipFlag()){
				tTerrZipAlgmnt.setPointZipFlag(CommonConstants.ACTIVE_Y);
			}else{
				tTerrZipAlgmnt.setPointZipFlag(CommonConstants.ACTIVE_N);
			}
				
			
			tTerrZipAlgmnt.setPostalCd(postalCd.getCode());
			tTerrZipAlgmnt.setCreatedBy(userDetails.getUserId());
			tTerrZipAlgmnt.setCreateDt(new Date());
			tTerrZipAlgmnt.setAssignedFlag('Y');
			tTerrZipAlgmnt.setTenantId(tenantId);
			tTerrZipAlgmnt.setActiveFlag('Y');
			tTerrZipAlgmnt.setEffStartDt(targetGeoAlign.getStartDate());
			tTerrZipAlgmnt.setEffEndDt(targetGeoAlign.getEndDate());
			tTerrZipAlgmnt.setStatusId(StatusType.PENDING_SUBMISSION.getId());
			/*if (pointzipFlag != null
					&& pointzipFlag.equals("Y")) {
				newtTerrZipAlgmnt
						.setPointZipFlag(Constant.ACTIVE_FLAG);
			} else if (pointzipFlag != null
					&& pointzipFlag.equals("N")) {
				newtTerrZipAlgmnt
						.setPointZipFlag(Constant.DEACTIVE_FLAG);
			}*/
			List<TTerrZipAlgmnt> tTerrZipAlgmntForComb = tTerrZipAlgmntDAO.getTTerrZipAlgmntsByPostalCd(saleHierId,salePosId,postalCd.getCode(),userDetails.getTenantId());
				TTerrZipAlgmnt createTTerrZipAlgmnt = new TTerrZipAlgmnt();
				if (tTerrZipAlgmntForComb.size() == 0) {
					createTTerrZipAlgmnt = tTerrZipAlgmntDAO
							.createTTerrZipAlgmnt(tTerrZipAlgmnt);
					geoId = createTTerrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId();
				} else {
					for(TTerrZipAlgmnt tTerrZipAlgmntTemp : tTerrZipAlgmntForComb){
						if(ApplicationConstant.FLAG_YES.equals(tTerrZipAlgmntTemp.getActiveFlag())){
							//tTerrZipAlgmntTemp.setActiveFlag('Y');
							//tTerrZipAlgmntTemp.setAssignedFlag('Y');
							//tTerrZipAlgmntTemp.setUpdateDt(new Date());
							//tTerrZipAlgmntTemp.setUpdatedBy(userDetails.getUserId());
							if(!validateEffectiveDates(tTerrZipAlgmntTemp,targetGeoAlign)){
								throw new OpservDataAccessException(										
										"The ZIP is already assigned to this SalesPosition in this time period. Please change the Start & End dates");
							}
						}
					}
					geoId = this.insertTargetZip(targetGeoAlign,userDetails,postalCd);
					
				}
		
		}else{
			geoId = this.insertTargetZip(targetGeoAlign,userDetails,postalCd);
		}
		
		
		return geoId;
	}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while inserting or updating zip Algmnt based on target geography Alignment, salesPosition and postal code", re);
			throw new OpservDataAccessException("This issue occurred while inserting or updating zip Algmnt based on target geography Alignment, salesPosition and postal code", re);
		}
	}

	
	
	/*@Override
	public boolean validateDates(SalesPosition sourceSalesPosition,GeographyAlignment targetGeoAlign,PostalCode postalCd,UserDetails userDetails){
		
		Long saleHierId = sourceSalesPosition.getHierarchy().getId();
		Long salePosId = sourceSalesPosition.getId();
		List<TTerrZipAlgmnt> tTerrZipAlgmntForComb = tTerrZipAlgmntDAO.getTTerrZipAlgmntsByPostalCd(saleHierId,salePosId,postalCd.getCode(),userDetails.getTenantId());
		Date newStartDate = targetGeoAlign.getStartDate();
		Date newEndDate = targetGeoAlign.getEndDate();
		boolean flag = false;
		Date effStartDate = null;
		Date effEndDate = null;
		
		
		for(TTerrZipAlgmnt tTerrZipAlgmntTemp:tTerrZipAlgmntForComb){
			effStartDate = tTerrZipAlgmntTemp.getEffStartDt();
			effEndDate = tTerrZipAlgmntTemp.getEffEndDt();
			if(!((newStartDate.compareTo(effEndDate)>0 && newStartDate.compareTo(effStartDate)>0)||
					(newEndDate.compareTo(effStartDate)<0 && newEndDate.compareTo(effEndDate)<0))){
				return true;
			}
		}
		return flag;
	}*/
	
	
	
	private boolean validateEffectiveDates(TTerrZipAlgmnt tTerrZipAlgmntTemp,GeographyAlignment targetGeoAlign){
		try{
			boolean flag = false;
			Date effStartDate = tTerrZipAlgmntTemp.getEffStartDt();
			Date effEndDate = tTerrZipAlgmntTemp.getEffEndDt();

			Date newStartDate = targetGeoAlign.getStartDate();
			Date newEndDate = targetGeoAlign.getEndDate();

			if(effStartDate==null || effEndDate==null){
				return true;
			}
			if(newStartDate.compareTo(newEndDate) >= 0){
				return false;
			}
			if((newStartDate.compareTo(effEndDate)>0 && newStartDate.compareTo(effStartDate)>0)||
					(newEndDate.compareTo(effStartDate)<0 && newEndDate.compareTo(effEndDate)<0)){
				return true;
			}

			return flag;
		}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while validating effective dates", re);
			throw new OpservDataAccessException("This issue occurred while validating effective dates", re);
		}
	}
	
	private Integer insertTargetZip(GeographyAlignment targetGeoAlign,UserDetails userDetails,PostalCode postalCd) throws OpservDataAccessException{
		try{
			SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
			Set<TTerrZipAlgmnt> tTerrZipAlgmntSet = new HashSet<TTerrZipAlgmnt>();
			Alignment targetAlgmnt = targetSalesPos.getAlignmment();
			Long targetAlgmntId = targetAlgmnt.getId();
			Long targetBussUnitId = targetAlgmnt.getSalesTeam().getBusinessUnit().getId();
			Long targetSalesTeamId = targetAlgmnt.getSalesTeam().getId();
			Integer geoId = 0;

			TSalesPosGeoUnitId tSalesPosGeoUnitId = new TSalesPosGeoUnitId();
			TTerrZipAlgmnt tTerrZipAlgmnt = new TTerrZipAlgmnt();

			Integer geoID = this.getNewGeoID(userDetails);
			tSalesPosGeoUnitId.setGeoId(geoID);
			tSalesPosGeoUnitId.setSalesHierId(targetSalesPos.getHierarchy().getId());
			tSalesPosGeoUnitId.setSalesPosId(targetSalesPos.getId());

			//TSalesPosGeoUnit tSalesPosGeoUnitById = tSalesPosGeoUnitDAO.findTSalesPosGeoUnitById(tSalesPosGeoUnitId);


			TSalesPosGeoUnit tSalesPosGeoUnit = new TSalesPosGeoUnit();
			tSalesPosGeoUnit.setTSalesPosGeoUnitId(tSalesPosGeoUnitId);
			tSalesPosGeoUnit.setActiveFlag('Y');
			tSalesPosGeoUnit.setTenantId(userDetails.getTenantId());
			tSalesPosGeoUnit.setCreatedBy(userDetails.getUserId());
			tSalesPosGeoUnit.setCreateDt(DateUtil.getCurrentDate());
			tSalesPosGeoUnit.setAlgmntId(targetAlgmntId);
			tSalesPosGeoUnit.setBussUnitId(targetBussUnitId);
			tSalesPosGeoUnit.setSalesTeamId(targetSalesTeamId);

			tTerrZipAlgmnt.setPostalCd(postalCd.getCode());
			tTerrZipAlgmnt.setCreatedBy(userDetails.getUserId());
			tTerrZipAlgmnt.setCreateDt(DateUtil.getCurrentDate());
			tTerrZipAlgmnt.setAssignedFlag(CommonConstants.ACTIVE_N);
			tTerrZipAlgmnt.setActiveFlag(CommonConstants.ACTIVE_N);
			tTerrZipAlgmnt.setTenantId(userDetails.getTenantId());
			tTerrZipAlgmnt.setTSalesPosGeoUnit(tSalesPosGeoUnit);
			tTerrZipAlgmnt.setEffStartDt(targetGeoAlign.getStartDate());
			tTerrZipAlgmnt.setEffEndDt(targetGeoAlign.getEndDate());
			tTerrZipAlgmnt.setStatusId(StatusType.PENDING_APPROVAL.getId());
			// assigning the pointZipFlag and CountryId in targetGeoAlign
			tTerrZipAlgmnt.setCountryId(Integer.parseInt(targetGeoAlign.getCountryCode()));
			if(targetGeoAlign.isPointZipFlag()){
				tTerrZipAlgmnt.setPointZipFlag(CommonConstants.ACTIVE_Y);
			}else{
				tTerrZipAlgmnt.setPointZipFlag(CommonConstants.ACTIVE_N);
			}

			//zipSet.add(postalCd.getCode());
			tTerrZipAlgmntSet.add(tTerrZipAlgmnt);
			tSalesPosGeoUnit.setTTerrZipAlgmntss(tTerrZipAlgmntSet);
			TSalesPosGeoUnit tSalesPositionGeoUnit = tSalesPosGeoUnitDAO.createTSalesPosGeoUnit(tSalesPosGeoUnit);
			Set<TTerrZipAlgmnt> tTerrZipAlgmntSetReturned = tSalesPositionGeoUnit.getTTerrZipAlgmntss();
			for(TTerrZipAlgmnt tTerrZip : tTerrZipAlgmntSetReturned){
//				geoId = tTerrZip.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId();	
				geoId = tTerrZip.getGeoZipId();
//				geographyAlignment = geographyAlignmentModelAssembler.mapTTerrZipAlgmntToModel(tTerrZip);
			}

			return geoId;
		}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while inserting target zips", re);
			throw new OpservDataAccessException("This issue occurred while inserting target zips", re);
		}
	}

	@Override
	public Map<Long,Integer> getExistingGeoIDs(
			SalesPosition salesPosition, UserDetails userDetails)throws OpservDataAccessException {
		// TODO Auto-generated method stub
		try{
			Long algmntId = salesPosition.getAlignmment().getId();
			Long bussUnitId = salesPosition.getAlignmment().getSalesTeam().getBusinessUnit().getId();
			Long salesTeamId = salesPosition.getAlignmment().getSalesTeam().getId();
			Long salesPosId = salesPosition.getId();
			short tenantId = userDetails.getTenantId();

			List<TSalesPosGeoUnit> geoIDs = tSalesPosGeoUnitDAO.getExistingGeoIDs(algmntId,bussUnitId,salesTeamId,salesPosId,tenantId);

			Map<Long,Integer> salesPosGeoIds = new HashMap<Long,Integer>();

			//SalesPosition sp = null;
			Integer geoid= null;
			//SalesOrgHierarchy hierarchy = null;
			for(TSalesPosGeoUnit tSalesPosGeoUnit:geoIDs){
				/*sp = new SalesPosition();
			sp.setId(tSalesPosGeoUnit.getTSalesPosGeoUnitId().getSalesPosId());
			hierarchy = new SalesOrgHierarchy();
			hierarchy.setId(tSalesPosGeoUnit.getTSalesPosGeoUnitId().getSalesHierId());
			sp.setHierarchy(hierarchy);*/
			geoid=tSalesPosGeoUnit.getTSalesPosGeoUnitId().getGeoId();
			salesPosGeoIds.put(tSalesPosGeoUnit.getTSalesPosGeoUnitId().getSalesPosId(), geoid);
		}
		
			return salesPosGeoIds;
		}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while fetching existing Geo Ids", re);
			throw new OpservDataAccessException("This issue occurred while fetching existing Geo Ids", re);
		}
	}

	@Override
	public List<SalesPosition> findMirroredSPsWithZip(List<SalesPosition> spList,
			PostalCode pc,UserDetails userDetails) throws OpservDataAccessException{
		try{
			short tenantId = userDetails.getTenantId();
			//System.out.println("Sales position list");
			List<Long> salesPosIDList = new ArrayList<Long>();
			for(SalesPosition sp: spList){
				salesPosIDList.add(sp.getId());
			}

			List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntsBySPId(salesPosIDList, pc.getCode(), tenantId);
			List<SalesPosition> salesPosList = salesPositionModelAssembler.mapZipTerrEntityToSalesPos(tTerrZipAlgmntList, tenantId);
			return salesPosList;
		}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while fetching mirrored Sps with zip", re);
			throw new OpservDataAccessException("This issue occurred while fetching mirrored Sps with zip", re);
		}
	}

	@Override
	public Integer getNewGeoID(UserDetails userDetails)throws OpservDataAccessException {
		try{
			short tenantId = userDetails.getTenantId();
			Object geoObj = tSalesPosGeoUnitDAO.getMaxGeoID(tenantId);
			List<Integer> id = (ArrayList)geoObj;
			return id.get(0) + 1;
		}
		catch (RuntimeException re) {
			LOGGER.error("This issue occurred while generating new GeoId", re);
			throw new OpservDataAccessException("This issue occurred while generating new GeoId", re);
		}
	}
	/**
	 * @param salesPos
	 * @param userDetails
	 * @return
	 * @throws AlignmentServiceException
	 */
	@Override
	public Long getZipCount(SalesPosition salesPos, UserDetails userDetails)throws OpservDataAccessException{
		try {
		GeographyAlignment geographyAlignment = new GeographyAlignment();
		TTerrZipAlgmnt terrZipAlgmnt = new TTerrZipAlgmnt();
		geographyAlignment.setSalesPosition(salesPos);
		geographyAlignment.setTenantId(userDetails.getTenantId());

		terrZipAlgmnt = geographyAlignmentEntityAssembler.mapGeoAlignModelToEntity(geographyAlignment);
		if (terrZipAlgmnt != null) {
			LOGGER.info("***********************DB CALL TO TERR ZIP ALGMNT TO FETCH ZipCount*****************************");
	  return tTerrZipAlgmntDAO.
					countTTerrZipAlgmntsCount(terrZipAlgmnt.getTSalesPos().getSalesPosId(), terrZipAlgmnt.getTSalesPos().getTAlgmntSalesHier().getSalesHierId(), terrZipAlgmnt.getTenantId());
			
		}
		
		
	}catch (RuntimeException re) {
		LOGGER.error("This issue occurred while Fetching ZipCount for a Sales Position",re);
		throw new OpservDataAccessException("This issue occurred while Fetching ZipCount for a Sales Position",re);
	}
		return null;
}
	/*@Override
	public Integer updateEndDateForZipAlgmnt(SalesPosition salesPosition,
			PostalCode postalCd, UserDetails userDetails,Integer geoId,Date endDate) {
		Long saleHierId = salesPosition.getHierarchy().getId();
		Long salePosId = salesPosition.getId();
		String zipID = postalCd.getCode();
		short tenantId = userDetails.getTenantId();
		//Integer geoId = 0;
		List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntList(saleHierId,salePosId,zipID, tenantId,geoId);
		Integer geoID = null;
		
		for (TTerrZipAlgmnt tTerrZipAlgmnt : tTerrZipAlgmntList) {
			geoID = tTerrZipAlgmnt
					.getTSalesPosGeoUnit()
					.getTSalesPosGeoUnitId().getGeoId();
			tTerrZipAlgmnt.setEffEndDt(endDate);
			tTerrZipAlgmnt.setUpdateDt(new Date());
			tTerrZipAlgmnt.setUpdatedBy(userDetails
					.getUserId());
			tTerrZipAlgmntDAO
					.updateTTerrZipAlgmnt(tTerrZipAlgmnt);

		}
		return geoID;
	}*/

	@Override
	public TChngreqOffline saveGeoAlgmntIntoTempChngReq(ChangeRequest chngRequest, GeographyAlignment sourceGeoAlign,
			GeographyAlignment targetGeoAlign,
			List<CustomerAlignment> custAligns, String comment, UserDetails userDetails)throws OpservDataAccessException {
		try{
			//Populating zip blob
			ZipBlob zipBlob = new ZipBlob();
			zipBlob.setSrcGeographyAlignment(sourceGeoAlign);
			zipBlob.setTargetGeographyAlignment(targetGeoAlign);
			zipBlob.setCustomerAlignmentList(custAligns);
			zipBlob.setComments(comment);
			zipBlob.setUserDetails(userDetails);


			String blobJson = JSONUtil.toJSON(zipBlob);

			TChngreqOffline tChngreqOffline = new TChngreqOffline();
			tChngreqOffline.setStatusId(ChangeRequestStatusType.PENDING_FOR_SUBMISSION.getId());
			tChngreqOffline.setFailedReason("");
			tChngreqOffline.setTriggerId(ChangeRequestTriggerType.PUSH_ZIP.getId());
			tChngreqOffline.setStatusId(ChangeRequestOfflineStatusType.INITIATED.getId());



			TChngReq tChngReq = tChngReqDAO.findTChngReqById(chngRequest.getId());
			tChngreqOffline.setTChngReq(tChngReq);
			tChngreqOffline.setReqDetail(blobJson);
			tChngreqOffline.setTenantId(userDetails.getTenantId());
			tChngreqOffline.setCreatedBy(userDetails.getUserId());
			tChngreqOffline.setCreateDt(new Date());

			TChngreqOffline tempChngreq = tChngreqOfflineDAO.createTChngreqOffline(tChngreqOffline);
			return tempChngreq;
		}catch (RuntimeException re) {
			LOGGER.error("This issue occurred while saving geo alignment into temp change req table",re);
			throw new OpservDataAccessException("This issue occurred while saving geo alignment into temp change req table",re);
		}
	}
	
	/**
	  * Gets the postal codes by sales position.
	  *
	  * @param salesPosition the sales position
	  * @param userDetails the user details
	  * @return the postal codes by sales position
	  * @throws OpservDataAccessException the opserv data access exception
	  */
	@Override
	public List<PostalCode> getPostalCodesBySalesPosition(
			SalesPosition salesPosition, UserDetails userDetails)
			throws OpservDataAccessException {
		try{
			List<PostalCode> postalCodes = null;
			List<Long> salesPosIdList = new ArrayList<Long>();
			salesPosIdList.add(salesPosition.getId());
			List<Long> salesHierIdList = new ArrayList<Long>();
			salesHierIdList.add(salesPosition.getHierarchy().getId());

			List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.findTTerrZipAlgmntBySpShIdList(salesPosIdList, salesHierIdList, userDetails.getTenantId());
			if(null != tTerrZipAlgmntList && !tTerrZipAlgmntList.isEmpty()){
				postalCodes = new ArrayList<PostalCode>();
				for(TTerrZipAlgmnt terrZipAlgmnt : tTerrZipAlgmntList){
					PostalCode postalCode = new PostalCode();
					postalCode.setCode(terrZipAlgmnt.getPostalCd());
					postalCode.setGeoCode(terrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId());
					postalCodes.add(postalCode);
				}
			}
			return postalCodes;
		}
		catch(RuntimeException e){
			LOGGER.error("Error occured during fetching zip codes", e);
			throw new OpservDataAccessException("Error occured during fetching zip codes", e);
		}
	}

	@Override
	public Long getZipCountForSpList(List<SalesPosition> salesPositions,
			UserDetails userDetails) throws OpservDataAccessException {
		try{
			List<Long> spIdList = new ArrayList<Long>();
			for (SalesPosition salesPosition : salesPositions) {
				if (null != salesPosition.getId()
						&& salesPosition.getId() != 0) {
					spIdList.add(salesPosition.getId());
				}
			}
			return tTerrZipAlgmntDAO.getZipCountForSpList(spIdList, userDetails.getTenantId());
			}catch(RuntimeException ex){
				LOGGER.info("*****************ERROR WHILE FETCHING ZIP COUNT FOR SALES POSITIONS*****************");
				LOGGER.error("Error while fetching zip count for sales positions", ex);
				throw new OpservDataAccessException("Error while fetching zip count for sales positions", ex);
			}
	}

	@Override
	public boolean getSPDataFrPostalCd(String postalCode, Long spId, Long shId,
			UserDetails userDetails) throws OpservDataAccessException {
		try {
			LOGGER.info("***********************getSPDataFrPostalCd*****************************");

			List<TTerrZipAlgmnt> activeTTerrZipAlgmntList = tTerrZipAlgmntDAO
					.getActiveTTerrZipAlgmntList(shId, spId, postalCode,
							userDetails.getTenantId());
			if (activeTTerrZipAlgmntList != null && activeTTerrZipAlgmntList.size() != 0) {
				return true;
			}
		} catch (RuntimeException re) {
			LOGGER.error("This issue is occurred while Fetching PostalCodes for Aligned SalesPosition",re);
			throw new OpservDataAccessException("This issue is occurred while Fetching PostalCodes for Aligned SalesPosition",re);
		}
		return false;
	}

	@Override
	public List<SalesPosition> filterActiveSPsContaingZip(List<SalesPosition> salesPosList, String postalCode,UserDetails userDetails) throws OpservDataAccessException{
		List<Long> mirrorSrcSPIds = new ArrayList<Long>();
		try{
			Iterator<SalesPosition> spIter = salesPosList.iterator();
			SalesPosition salesPos = null;
			for(SalesPosition mirrorSrcSP : salesPosList){
				mirrorSrcSPIds.add(mirrorSrcSP.getId());
			}
			List<Long> activaSpIds = tTerrZipAlgmntDAO.getActiveTTerrZipAlgmntsByPostalCdSalesPos(mirrorSrcSPIds,postalCode, userDetails.getTenantId());
			
			while(spIter.hasNext()){
				salesPos = spIter.next();
				if(!activaSpIds.contains(salesPos.getId())){
					LOGGER.info(" Removed Mirror Source SP : "+salesPos.getId());
					spIter.remove();
				}
				
			}
			 return salesPosList;	
		}catch(RuntimeException re){
			LOGGER.error("Exception occurred while getting the active SP for zip movement",re);
			throw new OpservDataAccessException("Exception occurred while getting the active SP for zip movement",re);
		}
		
	}


	@Override
	public Integer getCustomerCategory(Integer custID , UserDetails userDetails) throws OpservDataAccessException{
		try {
			TCust tCust = tCustDAO.findTCustById(custID);
			if(('Y' == tCust.getActiveFlag() || 'y' == tCust.getActiveFlag()) && (userDetails.getTenantId().equals(tCust.getTenantId()))){
				return tCust.getCategoryId();
			}
			return null;
		} catch(RuntimeException e){
			LOGGER.error("Error while fetching customer category"+e.getMessage());
			throw new OpservDataAccessException("Error while fetching customer category",e);
		}
	} 
	
	@Override
	public void updateTTerrZipAlgmntStatus(Long salesPosId, String postalCd,Long salesHierId,Integer geoId, Integer aprStsId, char flag, UserDetails userDetails) throws OpservDataAccessException{
		try {
			short tenantId = userDetails.getTenantId();
			LOGGER.debug("ZIPMVMT::Entered inside updateTTerrZipAlgmntStatus: flag->"+flag+" aprStsId->"+aprStsId);
			List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.fetchTTerrZipAlgmntByChangeRequest(geoId,postalCd,salesPosId,salesHierId,tenantId);
			if(tTerrZipAlgmntList!=null && tTerrZipAlgmntList.size()>0){
				LOGGER.debug("ZIPMVMT::Entered inside updateTTerrZipAlgmntStatus: tTerrZipAlgmntList->"+tTerrZipAlgmntList.size());
			}
			for (TTerrZipAlgmnt tTerrZipAlgmnt : tTerrZipAlgmntList) {
				geoId = tTerrZipAlgmnt
						.getTSalesPosGeoUnit()
						.getTSalesPosGeoUnitId().getGeoId();
				tTerrZipAlgmnt
						.setAssignedFlag(flag);
				tTerrZipAlgmnt
						.setActiveFlag(flag);
				tTerrZipAlgmnt.setUpdateDt(new Date());
				tTerrZipAlgmnt.setUpdatedBy(userDetails
						.getUserId());
				tTerrZipAlgmnt.setStatusId(aprStsId);
				tTerrZipAlgmntDAO
						.updateTTerrZipAlgmnt(tTerrZipAlgmnt);

			}
		} catch(RuntimeException e){
			LOGGER.error("Error updation of t_terr_zip_algmnt"+e.getMessage());
			throw new OpservDataAccessException("Error updation of t_terr_zip_algmnt",e);
		}
	}
	
	
	@Override
	public boolean updateZipAlgmntStatus(
			TTerrZipAlgmntChngReqDet zipChangeRequest, Integer statusId,
			UserDetails userDetails) throws OpservDataAccessException{
		LOGGER.debug("ZIPMVMT::Entered inside updateZipAlgmntStatus: zipChangeRequest->"+zipChangeRequest.toString()+" status id->"+statusId);
		try {
			List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.fetchTTerrZipAlgmntByChangeRequest(zipChangeRequest.getGeoId(),
					zipChangeRequest.getPostalCd(), zipChangeRequest.getSalesPosId(), zipChangeRequest.getSalesHierId(), zipChangeRequest.getTenantId());
			if (null != tTerrZipAlgmntList && tTerrZipAlgmntList.size() > 0) {
				TTerrZipAlgmnt tTerrZipAlgmnt = tTerrZipAlgmntList.get(0);
				SalesPosition zipSourceSalesPosition = new SalesPosition();
				if (null != zipChangeRequest.getAlgmntFlag() && zipChangeRequest.getAlgmntFlag().equals(CommonConstants.SOURCE)) {
					tTerrZipAlgmnt.setActiveFlag(CommonConstants.ACTIVE_N);
					
					//deactivateZipAlgmnt : Sheel
					SalesPosition salesPos = new SalesPosition();
					salesPos.setId(zipChangeRequest.getSalesPosId());
					SalesOrgHierarchy hierarchy  = new SalesOrgHierarchy();
					hierarchy.setId(zipChangeRequest.getSalesHierId());
					salesPos.setHierarchy(hierarchy);
					PostalCode pc = new PostalCode();
					pc.setCode(zipChangeRequest.getPostalCd());
					pc.setGeoCode(zipChangeRequest.getGeoId());
					LOGGER.debug("ZIPMVMT::CHANGING SOURCE STATUS");
					//this.deactivateZipAlgmnt(salesPos,
					//		pc, StatusType.APPROVED, 'Y', userDetails); 
					this.updateTTerrZipAlgmntStatus(zipChangeRequest.getSalesPosId(), zipChangeRequest.getPostalCd(),zipChangeRequest.getSalesHierId(),zipChangeRequest.getGeoId(), statusId,'N',userDetails);
					//zipSourceSalesPosition.setId(tTerrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getSalesPosId());

				} else {
					tTerrZipAlgmnt.setActiveFlag(CommonConstants.ACTIVE_Y);
					tTerrZipAlgmnt.setAssignedFlag(CommonConstants.ACTIVE_Y);
					LOGGER.debug("ZIPMVMT::CHANGING TARGET STATUS");
					this.updateTTerrZipAlgmntStatus(zipChangeRequest.getSalesPosId(), zipChangeRequest.getPostalCd(),zipChangeRequest.getSalesHierId(),zipChangeRequest.getGeoId(), statusId,'Y',userDetails);
				}
				zipSourceSalesPosition.setId(tTerrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getSalesPosId());
				tTerrZipAlgmnt.setStatusId(statusId);
				
				
				//tTerrZipAlgmntDAO.updateTTerrZipAlgmnt(tTerrZipAlgmnt);
				LOGGER.debug("ZIPMVMT::CHANGING GEOALIGN STATUS sales pos -> "+zipSourceSalesPosition.getId());
				customerAlignmentDAOService.updateGeoCustAlignmentIdBySalesPostion(zipSourceSalesPosition, userDetails);
				//zipAlignMap.put(zipChangeRequestMapKey, zipChangeRequest.getGeoId());
				return true;
			}
			return false;
		} catch(RuntimeException e){
			LOGGER.error("Error during updating alignment status list"+e.getMessage());
			throw new OpservDataAccessException("Error during updating alignment status list",e);
		}
	}
	
	
	@Override
	public Integer updateSrcZipAlgmntStatus(TTerrZipAlgmntChngReqDet zipChangeRequest , Integer rejctStsId, UserDetails userDetails)throws OpservDataAccessException{
		try {
			short tenantId = userDetails.getTenantId();
			
			LOGGER.debug("ZIPMVMT:In updateSrcZipAlgmntStatus function zipChangeRequest->", zipChangeRequest);
			LOGGER.debug("ZIPMVMT:In updateSrcZipAlgmntStatus function rejctStsId->", rejctStsId );
			Integer geoId = null;
			List<TTerrZipAlgmnt> tTerrZipAlgmntList = tTerrZipAlgmntDAO.fetchTTerrZipAlgmntByChangeRequest(zipChangeRequest.getGeoId(),
					zipChangeRequest.getPostalCd(), zipChangeRequest.getSalesPosId(), zipChangeRequest.getSalesHierId(), zipChangeRequest.getTenantId());
			if(tTerrZipAlgmntList!=null && tTerrZipAlgmntList.size()>0){
				LOGGER.debug("ZIPMVMT:SIZE of list is ->"+tTerrZipAlgmntList.size());
			}
			for (TTerrZipAlgmnt tTerrZipAlgmnt : tTerrZipAlgmntList) {
				geoId = tTerrZipAlgmnt
						.getTSalesPosGeoUnit()
						.getTSalesPosGeoUnitId().getGeoId();
				tTerrZipAlgmnt
						.setAssignedFlag('Y');
				tTerrZipAlgmnt
						.setActiveFlag('Y');
				tTerrZipAlgmnt.setUpdateDt(new Date());
				tTerrZipAlgmnt.setUpdatedBy(userDetails
						.getUserId());
				tTerrZipAlgmnt.setStatusId(rejctStsId);
				tTerrZipAlgmntDAO
						.updateTTerrZipAlgmnt(tTerrZipAlgmnt);

			}
			return geoId;
		} catch(RuntimeException e){
			LOGGER.error("Error during source update"+e.getMessage());
			throw new OpservDataAccessException("Error during source update",e);
		}
	}

	@Override
	public Integer newTargetGeoID(UserDetails userDetails)throws OpservDataAccessException {
		try {
			short tenantId = userDetails.getTenantId();
			Object geoObj = tSalesPosGeoUnitDAO.getMaxGeoID(tenantId);
			List<Integer> id = (ArrayList)geoObj;
			return id.get(0) + 1;
		} catch(RuntimeException e){
			LOGGER.error("Error during fetching geo id"+e.getMessage());
			throw new OpservDataAccessException("Error during fetching geo id",e);
		}
	}

	@Override
	public Integer insertTTerrZipAlgmnt(GeographyAlignment targetGeoAlign,
			PostalCode postalCd, UserDetails userDetails, Integer geoID)
			throws OpservDataAccessException {
		LOGGER.info("ZIPMVMT: inside insertUpdateZipAlgmnt function");
		try {
			SalesPosition targetSalesPos = targetGeoAlign.getSalesPosition();
			Long saleHierId = targetSalesPos.getHierarchy().getId();
			Long salePosId = targetSalesPos.getId();
			Integer geoId = 0;
			short tenantId = userDetails.getTenantId();
			//Set<String> zipSet = new HashSet<String>();
			
			TTerrZipAlgmnt tTerrZipAlgmnt = new TTerrZipAlgmnt();
			TSalesPosGeoUnitId tSalesPosGeoUnitId = new TSalesPosGeoUnitId();
			
			tSalesPosGeoUnitId.setGeoId(geoID);
			tSalesPosGeoUnitId.setSalesHierId(saleHierId);
			tSalesPosGeoUnitId.setSalesPosId(salePosId);
			TSalesPosGeoUnit tSalesPosGeoUnitById = tSalesPosGeoUnitDAO.findTSalesPosGeoUnitById(tSalesPosGeoUnitId);
			
			if(tSalesPosGeoUnitById!=null){

				tTerrZipAlgmnt.setTSalesPosGeoUnit(tSalesPosGeoUnitById);
				
				
				// assigning the pointZipFlag and CountryId in targetGeoAlign
				tTerrZipAlgmnt.setCountryId(Integer.parseInt(targetGeoAlign.getCountryCode()));
				if(targetGeoAlign.isPointZipFlag()){
					tTerrZipAlgmnt.setPointZipFlag(CommonConstants.ACTIVE_Y);
				}else{
					tTerrZipAlgmnt.setPointZipFlag(CommonConstants.ACTIVE_N);
				}
					
				
				tTerrZipAlgmnt.setPostalCd(postalCd.getCode());
				tTerrZipAlgmnt.setCreatedBy(userDetails.getUserId());
				tTerrZipAlgmnt.setCreateDt(new Date());
				tTerrZipAlgmnt.setAssignedFlag('N');
				tTerrZipAlgmnt.setTenantId(tenantId);
				tTerrZipAlgmnt.setActiveFlag('N');
				tTerrZipAlgmnt.setEffStartDt(targetGeoAlign.getStartDate());
				tTerrZipAlgmnt.setEffEndDt(targetGeoAlign.getEndDate());
				tTerrZipAlgmnt.setStatusId(StatusType.PENDING_APPROVAL.getId());
				/*if (pointzipFlag != null
						&& pointzipFlag.equals("Y")) {
					newtTerrZipAlgmnt
							.setPointZipFlag(Constant.ACTIVE_FLAG);
				} else if (pointzipFlag != null
						&& pointzipFlag.equals("N")) {
					newtTerrZipAlgmnt
							.setPointZipFlag(Constant.DEACTIVE_FLAG);
				}*/
				List<TTerrZipAlgmnt> tTerrZipAlgmntForComb = tTerrZipAlgmntDAO.getTTerrZipAlgmntsByPostalCd(saleHierId,salePosId,postalCd.getCode(),userDetails.getTenantId());
					TTerrZipAlgmnt createTTerrZipAlgmnt = new TTerrZipAlgmnt();
					if (tTerrZipAlgmntForComb.size() == 0) {
						createTTerrZipAlgmnt = tTerrZipAlgmntDAO
								.createTTerrZipAlgmnt(tTerrZipAlgmnt);
						
//						geoId = createTTerrZipAlgmnt.getTSalesPosGeoUnit().getTSalesPosGeoUnitId().getGeoId();
						geoId = createTTerrZipAlgmnt.getGeoZipId();
					} else {
						for(TTerrZipAlgmnt tTerrZipAlgmntTemp : tTerrZipAlgmntForComb){
							if(tTerrZipAlgmntTemp.getActiveFlag() == ApplicationConstant.FLAG_YES){
								//tTerrZipAlgmntTemp.setActiveFlag('Y');
								//tTerrZipAlgmntTemp.setAssignedFlag('Y');
								//tTerrZipAlgmntTemp.setUpdateDt(new Date());
								//tTerrZipAlgmntTemp.setUpdatedBy(userDetails.getUserId());
								if(!validateEffectiveDates(tTerrZipAlgmntTemp,targetGeoAlign)){
									LOGGER.info("ZIPMVMT: after validateEffectiveDates function error ");
									throw new OpservDataAccessException(
											"The ZIP is already assigned to this SalesPosition in this time period. Please change the Start & End dates");
								}
							}
						}
						geoId = this.insertTargetZip(targetGeoAlign,userDetails,postalCd);
						
					}
			
			}else{
				geoId = this.insertTargetZip(targetGeoAlign,userDetails,postalCd);
			}
			
			
			return geoId;
		}catch(RuntimeException e){
			LOGGER.error("Error during zip alignment update:::"+e.getMessage());
			throw new OpservDataAccessException("Error during Alignment update",e);
		}
	}


	@Override
	@Transactional
	public GeographyAlignment getSourceMirrorGeographyAlignment(
			SalesPosition sourceSP, String code, Short tenantId)
			throws OpservDataAccessException {
		
		GeographyAlignment geographyAlignment = new GeographyAlignment();
		try{
			if(null!=sourceSP && null!=code){
				long spId = sourceSP.getId();
				geographyAlignment =  geographyAlignmentModelAssembler.mapTTerrZipAlgmntToModel(tTerrZipAlgmntDAO.fetchSourceMirrorGeoAlgmnt(spId, code, tenantId));
			}
			return geographyAlignment;
		}catch(RuntimeException re){
			LOGGER.error("Exception occured while getting source mirror geography alignment");
			throw new OpservDataAccessException("Exception occured while getting source mirror geography alignment", re);
		}
		
	}

	@Override
	public GeographyAlignment getGeoAlignment( String postalCode,
			Integer geoZipId, long targetSPId, Short tenantId)
			throws OpservDataAccessException {
		try{

			TTerrZipAlgmnt terrZipAlgmnt = tTerrZipAlgmntDAO.getTTerrZipAlgmnts(postalCode, geoZipId, targetSPId, tenantId);
			if(terrZipAlgmnt != null){
				return geographyAlignmentModelAssembler.mapTTerrZipAlgmntToModel(terrZipAlgmnt);
			}else{
				LOGGER.error("Cannot find existing zip Algmnt for PostalCode: "+postalCode+" geoZipId:"+geoZipId+" targetSPId:"+targetSPId+" tenantId:"+tenantId);
				WORKLOG.error("zip Algmnt doesn't exist for PostalCode: "+postalCode+" geoZipId:"+geoZipId+" targetSPId:"+targetSPId+" tenantId:"+tenantId);
				throw new OpservDataAccessException("Cannot find existing zip Algmnt for PostalCode: "+postalCode+", geoZipId:"+geoZipId+", targetSPId:"+targetSPId+", tenantId:"+tenantId);
			}
	}catch(RuntimeException e){
		LOGGER.error("Error during fetching zipAlignment :::"+e.getMessage());
		throw new OpservDataAccessException("Error during fetching zipAlignment",e);
	}
	}

	
}