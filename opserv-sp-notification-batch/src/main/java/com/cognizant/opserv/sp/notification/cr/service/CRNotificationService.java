package com.cognizant.opserv.sp.notification.cr.service;

import java.util.List;

import javax.jms.JMSException;

import com.cognizant.opserv.sp.model.cr.ChangeRequest;
import com.cognizant.peg.core.exception.BusinessException;
import com.cognizant.peg.notification.core.common.NotifyMessage;
import com.cognizant.peg.notification.core.common.NotifyServiceException;
/**
 * ****************************************************************************.
 *
 * @interface CRNotificationService to configure CR notification message
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 28/09/2016
 * ***************************************************************************
 */
public interface CRNotificationService {

	/**
	 * Configure message.
	 *
	 * @param changeRequest the change request
	 * @param tenantId the tenant id
	 * @param crType the cr type
	 * @return the list
	 * @throws NotifyServiceException the notify service exception
	 * @throws JMSException the jMS exception
	 * @throws BusinessException the business exception
	 */
	public List<NotifyMessage>  configureMessage(ChangeRequest changeRequest,Short tenantId,String crType)throws NotifyServiceException, JMSException, BusinessException;;
}
