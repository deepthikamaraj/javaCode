package com.cognizant.opserv.sp.core.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.peg.core.dao.GenericDAO;

/**
 * Class provides API implementation for TPrdSalesTeamDAO.
 * 
 * @author Cognizant Technology Solutions
 * @version OpServ 3.0
 * @since 29/04/2016
 */
public class TPrdSalesTeamDAOImpl implements TPrdSalesTeamDAO{


	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}
}
