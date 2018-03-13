package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsrMtrPref;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

/**
 * Class provides API implementation for TUsrMtrPrefDAO.
 * 
 * @author JCoE team
 * @version 1.0
 */
@Repository("tUsrMtrPrefDAO")
public class TUsrMtrPrefDAOImpl implements TUsrMtrPrefDAO {

	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TUsrMtrPrefDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}	

	private final Class<TUsrMtrPref> clazz;

	public Class<TUsrMtrPref> getClazz() {
		return clazz;
	}

	public TUsrMtrPrefDAOImpl() {
		super();
		this.clazz = TUsrMtrPref.class;
	}

	/**
	 * Stores a new TUsrMtrPref entity object in to the persistent store
	 * 
	 * @param tUsrMtrPref
	 *            TUsrMtrPref Entity object to be persisted
	 * @return tUsrMtrPref Persisted TUsrMtrPref object
	 */
	@Override
	public TUsrMtrPref createTUsrMtrPref(final TUsrMtrPref tUsrMtrPref) {
		LOGGER.info("=========== Create TUsrMtrPref ===========");
		return genericDAO.store(tUsrMtrPref);
	}

	/**
	 * Updates a TUsrMtrPref entity object in to the persistent store
	 * 
	 * @param tUsrMtrPref
	 *            TUsrMtrPref Entity object to be updated
	 * @return tUsrMtrPref Persisted TUsrMtrPref object
	 */
	@Override
	public TUsrMtrPref updateTUsrMtrPref(final TUsrMtrPref tUsrMtrPref) {
		LOGGER.info("=========== Update TUsrMtrPref ===========");
		return genericDAO.update(tUsrMtrPref);
	}	

	/**
	 * Count TUsrMtrPref based on given search criteria using JPA named Query.
	 * The search criteria is of TSalesPos type.
	 * 
	 * @param searchFilter
	 *            The query criteria and search filter conditions are set
	 * @return  List<TUsrMtrPref> list of TUsrMtrPrefs if it exists against given
	 *         criteria. Returns null if not found
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Integer> getTUsrMtrPrefsByTMtrAndUsrId(Integer roleId, Integer userId, Short tenantId, Long salesPosId, Long salesHierId, List<Integer>metricIds) {
		List paramList = new ArrayList();

		paramList.add(roleId);//userDetails.getRoleId());
		paramList.add(userId);//userDetails.getUserId());
		paramList.add(salesPosId);
		paramList.add(salesHierId);
		paramList.add(tenantId);
		paramList.add(metricIds);
		List<Integer> list= genericDAO.findEntitiesByNamedQueryMultiCond("getMtrActiveFlag", paramList, 0, -1);

		return list; 
	}



	@Override
	public List<Integer> getUsrMtrPrefIds(Integer usrId,Integer roleId,Long spId,Short tId){	
		List<Object> params = new ArrayList<Object>();
		params.add(usrId);
		params.add(roleId);
		params.add(spId);
		params.add(tId);
		List<Integer> list= genericDAO.findEntitiesByNamedQueryMultiCond("GetUsrMtrPrefIdsByUsrRolSp", params, 0, -1);
		return list;
	}
	
	@Override
	public void inActivateUsrMtrPrefsForSP(Integer usrId,Integer roleId,Long spId,Short tId){	
		List<Object> params = new ArrayList<Object>();
		params.add(usrId);
		params.add(roleId);
		params.add(spId);
		params.add(tId);
		genericDAO.updateEntitiesByNamedQueryMultiCond("InactivateUsrMtrPrefsForSP", params, 0, -1);
	}
	
	@Override
	public List<Object[]> getUsrMtrPrefIdPrimaryIds(Integer usrId,Integer roleId,Long spId,Short tId){	
		List<Object> params = new ArrayList<Object>();
		params.add(usrId);
		params.add(roleId);
		params.add(spId);
		params.add(tId);
		return genericDAO.findEntitiesByNamedQueryMultiCond("GetUsrMtrPrefIdPrimaryIdsByUsrRolSp", params, 0, -1);		 
	}
	
	@Override
	public TUsrMtrPref findTMtrById(final Long seqNumber) {
		LOGGER.info("find TMtr instance with seqNumber: " + seqNumber);
		return genericDAO.get(clazz, seqNumber);
	}
	
}
