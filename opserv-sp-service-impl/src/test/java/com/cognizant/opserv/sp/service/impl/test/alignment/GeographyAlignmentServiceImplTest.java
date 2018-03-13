package com.cognizant.opserv.sp.service.impl.test.alignment;

import java.text.ParseException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.AffiliationServiceException;
import com.cognizant.opserv.sp.exception.AlignmentServiceException;
import com.cognizant.opserv.sp.exception.CallPlanServiceException;
import com.cognizant.opserv.sp.exception.ChangeRequestServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.exception.MetricViolationException;
import com.cognizant.opserv.sp.exception.SalesPositionServiceException;
import com.cognizant.opserv.sp.exception.ViewServiceException;
import com.cognizant.opserv.sp.model.Alignment;
import com.cognizant.opserv.sp.model.BusinessUnit;
import com.cognizant.opserv.sp.model.CustomerAlignment;
import com.cognizant.opserv.sp.model.GeoCustomerAlignment;
import com.cognizant.opserv.sp.model.GeographyAlignment;
import com.cognizant.opserv.sp.model.PostalCode;
import com.cognizant.opserv.sp.model.SalesOrgHierarchy;
import com.cognizant.opserv.sp.model.SalesPosition;
import com.cognizant.opserv.sp.model.SalesTeam;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.opserv.sp.service.alignment.GeographyAlignmentService;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

public class GeographyAlignmentServiceImplTest extends BaseTest{
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(GeographyAlignmentServiceImplTest.class);
	
	@Autowired
	 GeographyAlignmentService geographyAlignmentService;
		
		
		
		@Test
		public void getAllPostalCodesTest() throws AlignmentServiceException{
			try{
				UserDetails userDetails= new UserDetails();
				userDetails.setTenantId((short) 1);
				Alignment alignment=new Alignment();
				SalesTeam salesTeam=new SalesTeam();
				BusinessUnit businessUnit= new BusinessUnit();
				businessUnit.setId((long) 10);
				salesTeam.setId((long) 26);
				alignment.setId((long) 18);
				salesTeam.setBusinessUnit(businessUnit);
				alignment.setSalesTeam(salesTeam);
				String postalCode = "9";
				
				
		        List<PostalCode> allPostalCodes = geographyAlignmentService.getAllPostalCodes(postalCode,alignment,userDetails);
		        Assert.assertNull(allPostalCodes);
		       // LOGGER.info(allPostalCodes.get(0).getCode());
			}catch (AlignmentServiceException e) {
				LOGGER.info(" Msg :: " + e.getMessage());
			}
			

		}
		
		@Test
		public void getAlignedSalesPositionInfoForZip() throws AlignmentServiceException{
			try{
				UserDetails userDetails= new UserDetails();
				userDetails.setTenantId((short) 1);
				Alignment alignment=new Alignment();
				SalesTeam salesTeam=new SalesTeam();
				BusinessUnit businessUnit= new BusinessUnit();
				businessUnit.setId((long) 10);
				salesTeam.setId((long) 26);
				alignment.setId((long) 18);
				salesTeam.setBusinessUnit(businessUnit);
				alignment.setSalesTeam(salesTeam);
				PostalCode postalCode = new PostalCode();
				postalCode.setCode("53091");
				
		        SalesPosition allPostalCodes = geographyAlignmentService.getAlignedSalesPositionInfoForZip(postalCode, userDetails);
		        Assert.assertNotNull(allPostalCodes);
		        System.out.println(allPostalCodes.getId());
		       // LOGGER.info(allPostalCodes.get(0).getCode());
			}catch (AlignmentServiceException e) {
				LOGGER.info(" Msg :: " + e.getMessage());
			}
			

		}
		
		@Test
		public void getAllGeographyAlignmentsTest() throws AlignmentServiceException{
			try{
				SalesPosition salesPosition = new SalesPosition();
				SalesOrgHierarchy hierarchy= new SalesOrgHierarchy();
				
				salesPosition.setId((long) 217);
				
				hierarchy.setId((long) 5);
				salesPosition.setHierarchy(hierarchy);
				salesPosition.getHierarchy().getId();
				UserDetails details = new UserDetails();
				details.setTenantId((short) 1);
				
	            List<GeoCustomerAlignment> geoal = geographyAlignmentService.getAllGeographyAlignments(salesPosition, details);
	            //System.out.println(geoal.get(0).getId());
	            Assert.assertNotNull(geoal);
	            
			          
			}catch (AlignmentServiceException e) {
				LOGGER.info(" Msg :: " + e.getMessage());
			}
			
	}
		
		/*@Test
		public void movePostalCodeTest() throws AlignmentServiceException{
			
			try {
				UserDetails userDetails = new UserDetails();
				userDetails.setTenantId((short) 1);
				userDetails.setUserId(1);
				userDetails.setStaffId(80);
				userDetails.setTenantCode("am");

				Alignment alignment=new Alignment();
				SalesTeam salesTeam=new SalesTeam();
				BusinessUnit businessUnit= new BusinessUnit();
				businessUnit.setId((long) 2);
				salesTeam.setId((long) 7);
				alignment.setId((long) 7);
				salesTeam.setBusinessUnit(businessUnit);
				alignment.setSalesTeam(salesTeam);
				PostalCode postalCode = new PostalCode();
				postalCode.setCode("03804");
				postalCode.setGeoCode(10047L);
				
				SalesPosition srcsalesPosition = new SalesPosition();
				SalesOrgHierarchy hierarchy= new SalesOrgHierarchy();
				
				srcsalesPosition.setId((long) 174);
				
				hierarchy.setId((long) 5);
				srcsalesPosition.setHierarchy(hierarchy);
				srcsalesPosition.getHierarchy().getId();
				
				//174 - 5 ----- 7/2/7
				
				//260 - 5
				
				srcsalesPosition.setAlignmment(alignment);
				
				SalesPosition trgSalesPosition = new SalesPosition();
				SalesOrgHierarchy trgHierarchy= new SalesOrgHierarchy();
				
				trgSalesPosition.setId((long) 175);
				
				trgHierarchy.setId((long) 5);
				trgSalesPosition.setHierarchy(trgHierarchy);
				trgSalesPosition.getHierarchy().getId();
				trgSalesPosition.setAlignmment(alignment);
				
				GeographyAlignment srcGeoAlign = new GeographyAlignment();
				GeographyAlignment trgGeoAlign = new GeographyAlignment();
				
				//srcGeoAlign.setId(10047L);
				srcGeoAlign.setSalesPosition(srcsalesPosition);
				srcGeoAlign.setPostalCode(postalCode);
				trgGeoAlign.setSalesPosition(trgSalesPosition);
				PostalCode postalCodeTar = new PostalCode();
				postalCodeTar.setCode("03804");
				//postalCode.setGeoCode(10047L);
				trgGeoAlign.setPostalCode(postalCodeTar);
				
				List<CustomerAlignment> custAligns = new ArrayList<CustomerAlignment>();
				CustomerAlignment orgCustAlgn = new CustomerAlignment();
				Customer oldCustomer = new Customer();
				orgCustAlgn.setCustomer(oldCustomer);
				custAligns.add(orgCustAlgn);
				CustomerAlignment orgCustAlgn = new CustomerAlignment();
				Customer oldCustomer = new Customer();
				oldCustomer.setId(1184669L);
				orgCustAlgn.setId(953L);
				orgCustAlgn.setCustomer(oldCustomer);
				orgCustAlgn.setSalesPosition(srcsalesPosition);
				orgCustAlgn.setAffBasedAssignment(false);
				orgCustAlgn.setCustomerTargeted(true);
				orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
				orgCustAlgn.setCompAligned(false);
				orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate("11-05-2050"));
				orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate("11-05-2051"));
				custAligns.add(orgCustAlgn);
				
				
				trgGeoAlign.setStartDate(DateUtil.getDefaultFormatDate("11-05-2030"));
				trgGeoAlign.setEndDate(DateUtil.getDefaultFormatDate("11-05-2031"));
				
				
				Long crID = geographyAlignmentService.movePostalCodes(srcGeoAlign, trgGeoAlign, custAligns, "Move", userDetails);
				System.out.println("crID>>"+crID);
			} catch (MetricViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CustomerServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AffiliationServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SalesPositionServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ChangeRequestServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
		@Test
		public void movePostalCodeTest() throws AlignmentServiceException, ViewServiceException{
			
			try {
				UserDetails userDetails = new UserDetails();
				userDetails.setTenantId((short) 1);
				userDetails.setUserId(1);
				userDetails.setStaffId(80);
				userDetails.setTenantCode("am");
				
				ChangeRequest changeRequest = new ChangeRequest();
				changeRequest.setId(380l);

				Alignment alignment=new Alignment();
				SalesTeam salesTeam=new SalesTeam();
				BusinessUnit businessUnit= new BusinessUnit();
				businessUnit.setId((long) 2);
				salesTeam.setId((long) 7);
				alignment.setId((long) 7);
				salesTeam.setBusinessUnit(businessUnit);
				alignment.setSalesTeam(salesTeam);
				PostalCode postalCode = new PostalCode();
				postalCode.setCode("03804");
				postalCode.setGeoCode(10047L);
				
				SalesPosition srcsalesPosition = new SalesPosition();
				SalesOrgHierarchy hierarchy= new SalesOrgHierarchy();
				
				srcsalesPosition.setId((long) 174);
				
				hierarchy.setId((long) 5);
				srcsalesPosition.setHierarchy(hierarchy);
				srcsalesPosition.getHierarchy().getId();
				
				//174 - 5 ----- 7/2/7
				
				//260 - 5
				
				srcsalesPosition.setAlignmment(alignment);
				
				SalesPosition trgSalesPosition = new SalesPosition();
				SalesOrgHierarchy trgHierarchy= new SalesOrgHierarchy();
				
				trgSalesPosition.setId((long) 175);
				
				trgHierarchy.setId((long) 5);
				trgSalesPosition.setHierarchy(trgHierarchy);
				trgSalesPosition.getHierarchy().getId();
				trgSalesPosition.setAlignmment(alignment);
				
				GeographyAlignment srcGeoAlign = new GeographyAlignment();
				GeographyAlignment trgGeoAlign = new GeographyAlignment();
				
				//srcGeoAlign.setId(10047L);
				srcGeoAlign.setSalesPosition(srcsalesPosition);
				srcGeoAlign.setPostalCode(postalCode);
				trgGeoAlign.setSalesPosition(trgSalesPosition);
				srcGeoAlign.setCountryCode("1");
				//PostalCode postalCodeTar = new PostalCode();
				//postalCodeTar.setCode("70121");
				//postalCode.setGeoCode(10160L);
				trgGeoAlign.setPostalCode(postalCode);
				List<CustomerAlignment> custAligns = null;
				/*custAligns = new ArrayList<CustomerAlignment>();
				CustomerAlignment orgCustAlgn = new CustomerAlignment();
				Customer oldCustomer = new Customer();
				oldCustomer.setId(1184669L);
				orgCustAlgn.setId(953L);
				orgCustAlgn.setCustomer(oldCustomer);
				orgCustAlgn.setSalesPosition(srcsalesPosition);
				orgCustAlgn.setAffBasedAssignment(false);
				orgCustAlgn.setCustomerTargeted(true);
				orgCustAlgn.setPriority(PriorityType.valueOf("TIER_1"));
				orgCustAlgn.setCompAligned(false);
				orgCustAlgn.setStartDate(DateUtil.getDefaultFormatDate("11-07-2024"));
				orgCustAlgn.setEndDate(DateUtil.getDefaultFormatDate("11-06-2024"));
				custAligns.add(orgCustAlgn);*/
				
				
				trgGeoAlign.setStartDate(DateUtil.getDefaultFormatDate("11-01-2026"));
				trgGeoAlign.setEndDate(DateUtil.getDefaultFormatDate("11-03-2026"));
				
				
				geographyAlignmentService.movePostalCodes(changeRequest,srcGeoAlign, trgGeoAlign, custAligns, "Move SP", userDetails);

			} catch (MetricViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CustomerServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AffiliationServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SalesPositionServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ChangeRequestServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CallPlanServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	@Test
	public void getPostalCodesBySalesPositionTest() throws AlignmentServiceException{
		SalesPosition salesPosition = new SalesPosition();
		salesPosition.setId(396L);
		SalesOrgHierarchy hierarchy = new SalesOrgHierarchy();
		hierarchy.setId(5L);
		salesPosition.setHierarchy(hierarchy);
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short) 1);
		List<PostalCode> postalCodes = geographyAlignmentService.getPostalCodesBySalesPosition(salesPosition, userDetails);
		Assert.assertNotNull(postalCodes);
	}	
}
