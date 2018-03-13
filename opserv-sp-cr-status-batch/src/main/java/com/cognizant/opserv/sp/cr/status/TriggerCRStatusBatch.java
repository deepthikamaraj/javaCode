package com.cognizant.opserv.sp.cr.status;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cognizant.opserv.common.CommonPropertiesUtil;
import com.cognizant.opserv.sp.batch.dao.TenantSchemaDAO;
import com.cognizant.opserv.sp.cr.status.service.CRService;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.tenant.util.BatchMultiTenancyUtil;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * ****************************************************************************.
 *
 * @class TriggerCRStatusBatch
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 20/07/2016
 * 
 * ***************************************************************************
 */
public class TriggerCRStatusBatch {
	
	@Autowired
	@Qualifier("cancelCRStatusService")
	private CRService cancelCRStatusService;
	
	@Autowired
	private TenantSchemaDAO tenantSchemaDAO;

	/** The Constant LOGGER. */
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TriggerCRStatusBatch.class);
	
	
	/** The context has all the services of Abstract Application Context */
	private static AbstractApplicationContext context = null;
	
	public static void main(String args[]){
		System.setProperty("opserv.config.file", "C:\\opserv\\config\\opserv-config.properties");
		System.setProperty("appType", "standalone");
		System.setProperty("targetDataSource", "app");
		System.setProperty("appName", "SpTriggerSPBatch");
		
		try{
			LOGGER.info("Start of SP TriggerCRStatusBatch Batch");
			context = new ClassPathXmlApplicationContext("applicationContext-cr-status.xml");
			
			TriggerCRStatusBatch triggerCRStatusBatch = (TriggerCRStatusBatch)context.getBean("triggerCRStatusBatch");
			triggerCRStatusBatch.updateStatus();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} 
//		finally {
//			BatchMultiTenancyUtil.clearTenantContext();
//		}
		System.out.println("strtd");
		//context.close();
	}
	
	private void updateStatus(){
		
		while(true){
			try{
				
				List<UserDetails> userDetailsList = null;
				if(CommonPropertiesUtil.isMultiTenancyEnabled()) {
					userDetailsList = tenantSchemaDAO.getUserdetails();
				}else{
					userDetailsList = new ArrayList<UserDetails>();
					UserDetails defaultUserDetails = new UserDetails();
					defaultUserDetails.setTenantCode("am");
					defaultUserDetails.setTenantId((short)1);
					userDetailsList.add(defaultUserDetails);
				}
				
				if(null != userDetailsList && !userDetailsList.isEmpty()){
					for(UserDetails userDetails : userDetailsList){
						if(null != userDetails && null != userDetails.getTenantId() && null != userDetails.getTenantCode()){
							BatchMultiTenancyUtil.setTenantContext(userDetails.getTenantCode());
							cancelCRStatusService.cancleCRStatus(userDetails.getTenantId());
							cancelCRStatusService.resendCRMsg(userDetails.getTenantId());
							cancelCRStatusService.deleteVoidCR(userDetails.getTenantId());
							BatchMultiTenancyUtil.clearTenantContext();
						}
					}
				}
				
			}catch(Exception e){
				e.printStackTrace();
			} finally {
				BatchMultiTenancyUtil.clearTenantContext();
			}
			try {
				Thread.currentThread().sleep(180000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
