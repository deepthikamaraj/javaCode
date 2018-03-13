package com.cognizant.opserv.sp.persistence.dao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.query.SearchEntity;
import com.cognizant.opserv.query.SearchEntityAttribute;
import com.cognizant.opserv.sp.core.entity.TBussAttr;
import com.cognizant.opserv.sp.core.entity.TBussEntity;
import com.cognizant.opserv.sp.core.entity.TBussObj;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.persistence.dao.service.AdvanceSearchDAOService;
import com.cognizant.peg.core.common.SearchCriteria;
import com.cognizant.peg.core.dao.GenericDAO;
import com.cognizant.peg.core.exception.OpservDataAccessException;

@Component("advanceSearchDAOService")
public class AdvanceSearchDAOServiceImpl implements AdvanceSearchDAOService {

	@Autowired
	private GenericDAO genericDAO;

	@Override
	public AdvanceSearchContext getAdvanceSearchContext(String bussinessObjectName, UserDetails userDetails) throws OpservDataAccessException {
		
		AdvanceSearchContext advanceSearchCtx = new AdvanceSearchContext(bussinessObjectName);
		
		SearchCriteria tenantCrit = new SearchCriteria();
		tenantCrit.addEqualTo("tenantId",userDetails.getTenantId());
		
		SearchCriteria criteria = new SearchCriteria();
		criteria.addEqualTo("bussObjName", bussinessObjectName);
		criteria.addAndCriteria(tenantCrit);
		criteria.setChildAttributeList(new String[]{"tBussEntitiess","tBussAttrss"});
		
		List<TBussObj> tBussObjs = genericDAO.search(TBussObj.class, criteria);
		TBussObj tbussObj = null;
		if(tBussObjs != null && !tBussObjs.isEmpty()) {
			if(tBussObjs.size()==1) {
				tbussObj = tBussObjs.get(0);
			} else {
				throw new OpservDataAccessException("Bussiness Object is not unique. Cannot determine the advance search context due to ambiguity.");
			}
		} else {
			return null;
		}

		try {
			
			Set<TBussAttr> bussAttrList = tbussObj.getTBussAttrss();
			Map<Integer,SearchEntity> srchEntityMap = new HashMap<Integer,SearchEntity>(); 
			if (null != bussAttrList && !bussAttrList.isEmpty()) {
				for(TBussAttr tBussAttr : bussAttrList) {
					TBussEntity tBussEntity = tBussAttr.getTBussEntity();
					SearchEntity searchEntity = advanceSearchCtx.getSearchEntity(tBussEntity.getEntityAliasName());
					if(searchEntity == null) {
						searchEntity = new SearchEntity(tBussEntity.getEntityAliasName(),tBussEntity.getEntityName());
						searchEntity.setSearchEntityId(tBussEntity.getEntityId());
						srchEntityMap.put(tBussEntity.getEntityId(), searchEntity);
						searchEntity.setParentSearchEntity(srchEntityMap.get(tBussEntity.getPrnEntityId()));
						searchEntity.setJoinBy(tBussEntity.getRefCond());
						advanceSearchCtx.addSearchEntity(searchEntity);
					}
					if(searchEntity.getParentSearchEntity() == null) {
						searchEntity.setParentSearchEntity(srchEntityMap.get(tBussEntity.getPrnEntityId()));
					}
					SearchEntityAttribute searchEntityAttr = new SearchEntityAttribute(tBussAttr.getAttrAliasName(), tBussAttr.getAttrName(), tBussAttr.getAttrDataType());
					searchEntity.addAttribute(searchEntityAttr);
				}
			}
			return advanceSearchCtx;
		} catch (RuntimeException rte) {
			throw new OpservDataAccessException("Exception occured in getAdvanceSearchContext of AdvanceSearchDAOServiceImpl :: "+rte.getMessage());
		}
		
		
	}

}
