package com.cognizant.opserv.sp.core.dao;

import java.util.List;

import com.cognizant.opserv.sp.core.entity.TUsrMtrPref;

/**
 * Interface represents API's of TUsrMtrPref DAO.
 * 
 * @author JCoE team
 * @version 1.0
 * 
 */
public interface TUsrMtrPrefDAO {

	List<Integer> getTUsrMtrPrefsByTMtrAndUsrId(Integer roleId, Integer userId, Short tenantId, Long salesPosId, Long salesHierId, List<Integer> metricIds);

	List<Integer> getUsrMtrPrefIds(Integer usrId, Integer roleId, Long spId,Short tId);

	TUsrMtrPref createTUsrMtrPref(TUsrMtrPref tUsrMtrPref);

	TUsrMtrPref updateTUsrMtrPref(TUsrMtrPref tUsrMtrPref);	

	void inActivateUsrMtrPrefsForSP(Integer usrId, Integer roleId, Long spId, Short tId);

	List<Object[]> getUsrMtrPrefIdPrimaryIds(Integer usrId, Integer roleId,Long spId, Short tId);

	TUsrMtrPref findTMtrById(Long seqNumber);
	

}
