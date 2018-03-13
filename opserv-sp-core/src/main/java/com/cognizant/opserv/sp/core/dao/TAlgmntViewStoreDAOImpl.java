package com.cognizant.opserv.sp.core.dao;


import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.InvalidQueryException;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.query.QueryGeneratorForSimpleView;
import com.cognizant.peg.core.common.PaginationInfo;
import com.cognizant.peg.core.dao.GenericDAO;

/**
 * Class provides API implementation for TAlgmntViewStoreDAO.
 * 
 * @author Cognizant Technology Solutions
 * @version 1.0
 */
@Repository("tAlgmntViewStoreDAO ")
public class TAlgmntViewStoreDAOImpl implements TAlgmntViewStoreDAO {

	/**
	 * instantiate Logger Object
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(TAlgmntViewStoreDAOImpl.class);

	@Autowired
	private GenericDAO genericDAO;

	public GenericDAO getGenericDAO() {
		return genericDAO;
	}

	public void setGenericDAO(final GenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}

	@Override
	public List<Object []> findTCustAlgmntViewStoreByNativeQuery(String tableEntityName, List<String> selectParameterList, Map<String, String> headerInfoQueryGeneration, IQuery queryStructure) throws InvalidQueryException {
		LOGGER.info("=========== Count TCustAlgmntViewStore ===========");
		Query query = new Query();
		
		query.select(selectParameterList.toString().substring(1, (selectParameterList.toString().length() - 1)));
		query.from(tableEntityName);
		query.criteria(queryStructure.getCriteria());
		LOGGER.info("query orderBy clause :: "+query.getOrderBy());
		if(null != query.getOrderBy()){
			LOGGER.info("Not null query orderBy clause :: "+query.getOrderBy().toString());
		}
		query.orderBy(queryStructure.getOrderBy());
		
		if(null == queryStructure.getPagination()){
			PaginationInfo paginationInfo = new PaginationInfo(0, 10);
			query.pagination(paginationInfo);
		}
		if(null != queryStructure.getPagination() && null != Integer.toString(queryStructure.getPagination().getStartIndex()) && null != Integer.toString(queryStructure.getPagination().getMaxRows())){
			query.pagination(queryStructure.getPagination());
		}
		
		IQuery iQuery = query;
		
		QueryGeneratorForSimpleView queryGeneratorForSimpleView = new QueryGeneratorForSimpleView(headerInfoQueryGeneration);
		String execQuery = queryGeneratorForSimpleView.getQuery(iQuery);
		
		//tableEntityName = "t_cust_algmnt_view_store";		
		//native query goes here 
		
		//String selectClause = selectParameterList.toString().substring(1, (selectParameterList.toString().length() - 1));
		
		//String custAlgmntViewListQuery = String.format("SELECT %s",selectClause,"%s FROM %s",tableEntityName);
		//String custAlgmntViewListQuery = "SELECT "+selectClause+" FROM "+tableEntityName;
		
		//genericDAO.findByNativeQueryMultiCond(custAlgmntViewListQuery, null, maxresult, index);
		LOGGER.info("ExecQuery :: "+execQuery);
		return genericDAO.findByNativeQuery(execQuery);
	}

	@Override
	public BigInteger findResultCountBasedOnSearchCriteria(String entityTableName, IQuery queryStructure, Map<String, String> headerInfoQueryGeneration) throws InvalidQueryException {
		LOGGER.info("=========== Inside findResultCountBasedOnSearchCriteria ===========");
		
		Query query = new Query();
		
		query.select("COUNT(*)");
		query.from(entityTableName);
		query.criteria(queryStructure.getCriteria());
		
		IQuery iQuery = query;
		QueryGeneratorForSimpleView queryGeneratorForSimpleView = new QueryGeneratorForSimpleView(headerInfoQueryGeneration);
		String execQuery = queryGeneratorForSimpleView.getQuery(iQuery);
		
		return genericDAO.countByNativeQuery(execQuery);
	}

}
