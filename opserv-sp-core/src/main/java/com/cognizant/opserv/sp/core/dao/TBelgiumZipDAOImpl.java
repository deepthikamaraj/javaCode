package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TBelgiumZip;
import com.cognizant.peg.core.dao.GenericDAO;

/**
 * Class provides API implementation for TBelgiumZipDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tBelgiumZipDAO")
public class TBelgiumZipDAOImpl implements TBelgiumZipDAO {


	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}


	private final Class<TBelgiumZip> clazz;

	public Class<TBelgiumZip> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TBelgiumZipDAOImpl() {
		super();
		this.clazz = TBelgiumZip.class;
	}
	
	
	public List<String> getTBelgiumZips(String query){		
		return genericDAO.findByNativeQuery(query);
	}
	
	
	public List<String> findTBelgiumZipsPolygon()
	{		
		
		return genericDAO.findByNativeQuery("select AsText(ST_Envelope(shape_polygon)) as shape from t_belgium_zip");
		
	}


}
