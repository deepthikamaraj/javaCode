package com.cognizant.opserv.sp.service.common;

import java.util.List;
import java.util.Map;

import com.cognizant.opserv.common.domain.MessageTemplate;
import com.cognizant.opserv.sp.model.auth.UserDetails;
/**
 * ****************************************************************************.
 *
 * @Interface NotificationService contains notification services
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 30/06/2016
 * ***************************************************************************
 */
public interface NotificationService {
	
	

	/**
	 * Send email.
	 *
	 * @param toAddrs the to addrs
	 * @param ccAddrs the cc addrs
	 * @param bccAddrs the bcc addrs
	 * @param tmpId the tmp id
	 * @param args the args
	 * @param files the files
	 * @param userDetails the user details
	 */
	public void sendEmailNotification(List<Integer> toAddrs,List<Integer> ccAddrs,List<Integer> bccAddrs,MessageTemplate tmpId,Map<String,String> args,List<String> files,UserDetails userDetails);

}
