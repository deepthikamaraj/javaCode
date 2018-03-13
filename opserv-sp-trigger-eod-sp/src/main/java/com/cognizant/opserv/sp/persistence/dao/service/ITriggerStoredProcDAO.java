package com.cognizant.opserv.sp.persistence.dao.service;

import com.cognizant.peg.core.exception.OpservDataAccessException;

public interface ITriggerStoredProcDAO {
	
	/**
	 * @throws OpservDataAccessException
	 */
	void triggerStoredProc() throws OpservDataAccessException;

}
