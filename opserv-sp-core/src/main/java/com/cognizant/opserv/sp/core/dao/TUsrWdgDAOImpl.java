package com.cognizant.opserv.sp.core.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.sp.core.entity.TUsrWdg;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.common.SearchFilter;
import com.cognizant.peg.core.dao.GenericDAO;

/**
 * Class provides API implementation for TUsrWdgDAO.
 * 
 * @author Bhaskar Reddy Lacchannagari
 * @version 1.0
 */
@Repository("tUsrWdgDAO")
public class TUsrWdgDAOImpl implements TUsrWdgDAO {

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	private final Class<TUsrWdg> clazz;

	public Class<TUsrWdg> getClazz() {
		return clazz;
	}

	/**
	 * @generated
	 */
	public TUsrWdgDAOImpl() {
		super();
		this.clazz = TUsrWdg.class;
	}

	/**
	 * Stores a new TUsrWdg entity object in to the persistent store
	 * 
	 * @param tUsrWdg
	 *            TUsrWdg Entity object to be persisted
	 * @return tUsrWdg Persisted TUsrWdg object
	 */
	public TUsrWdg createTUsrWdg(final TUsrWdg tUsrWdg) {
		return genericDAO.store(tUsrWdg);
	}

	/**
	 * Updates a TUsrWdg entity object in to the persistent store
	 * 
	 * @param tUsrWdg
	 *            TUsrWdg Entity object to be updated
	 * @return tUsrWdg Persisted TUsrWdg object
	 */
	public TUsrWdg updateTUsrWdg(TUsrWdg tUsrWdg) {
		return genericDAO.update(tUsrWdg);
	}

	/**
	 * Retrieve an TUsrWdg object based on given TUsrWdg roleID.
	 * 
	 * @param roleID
	 *            the primary key value of the TUsrWdg Entity.
	 * @return an Object if it exists against given primary key. Returns null of
	 *         not found
	 */
	/*
	 * public TUsrWdg findTUsrWdgById(Integer roleID) { return
	 * genericDAO.get(clazz, roleID); }
	 */

	/*
	 * public List<TUsrWdg> findTUsrWdgsByIds(Integer roleId,Integer usrId) {
	 * List<Integer> paramList = new ArrayList<Integer>();
	 * paramList.add(roleId); paramList.add(usrId); List<TUsrWdg> usrWidgetList
	 * = genericDAO.findEntitiesByNamedQueryMultiCond("FindAllTUsrWdgs",
	 * paramList, 0,100); return usrWidgetList; }
	 */

	@Override
	public List<TUsrWdg> findTUsrWdgsByIds(SearchFilter<TUsrWdg> searchFilter) {

		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		final  TUsrWdg tUsrWdg = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tUsrWdg.getTUsrWdgId().getUsrId());
		paramList.add(tUsrWdg.getTUsrWdgId().getRoleId());
		paramList.add(tUsrWdg.getTenantId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrWdgByTUsrRoleIdAndUsrId", paramList, index, maxresult);
	}
	@Override
	public List<TUsrWdg> findTUsrWdgsByWdgIds(SearchFilter<TUsrWdg> searchFilter) {

		
		final PaginationInfo paginationInfo = searchFilter.getPaginationInfo();
		final int maxresult = paginationInfo.getMaxRows();
		final int index = paginationInfo.getStartIndex();

		final  TUsrWdg tUsrWdg = searchFilter.getEntityClass();
		List paramList = new ArrayList();
		paramList.add(tUsrWdg.getTUsrWdgId().getUsrId());
		paramList.add(tUsrWdg.getTUsrWdgId().getRoleId());
		paramList.add(tUsrWdg.getTenantId());
		paramList.add(tUsrWdg.getTUsrWdgId().getWdgId());
		return genericDAO.findEntitiesByNamedQueryMultiCond("FindTUsrWdgByTUsrRoleIdAndUsrIdAndWdgId", paramList, index, maxresult);
	}
}
