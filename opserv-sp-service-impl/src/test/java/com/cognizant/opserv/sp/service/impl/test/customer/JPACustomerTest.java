package com.cognizant.opserv.sp.service.impl.test.customer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.cognizant.opserv.query.AdvanceSearchContext;
import com.cognizant.opserv.query.ExpressionCriteria;
import com.cognizant.opserv.query.IExpressionCriteria;
import com.cognizant.opserv.query.IQuery;
import com.cognizant.opserv.query.InvalidQueryException;
import com.cognizant.opserv.query.Order;
import com.cognizant.opserv.query.Query;
import com.cognizant.opserv.query.QueryGeneratorForSimpleView;
import com.cognizant.opserv.query.SearchEntity;
import com.cognizant.opserv.sp.core.entity.TChngReq;
import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.exception.AdvanceSearchServiceException;
import com.cognizant.opserv.sp.exception.CustomerServiceException;
import com.cognizant.opserv.sp.model.Customer;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.customer.CustomerService;
//import com.cognizant.opserv.sp.service.internal.TransactionTest;
import com.cognizant.opserv.sp.service.search.AdvanceSearchService;
import com.cognizant.peg.core.dao.JPAQueryGenerator;

public class JPACustomerTest extends BaseTest {

	@Autowired
	private JPAQueryGenerator jpaQg;
	
	@Autowired
	private CustomerService customerService; 
	
	@Autowired
	private AdvanceSearchService advanceSearchService;
	
/*	@Autowired
	private TransactionTest txTest;
*/	
	@Value("${temp.sp.db.url}")
	private String url;
	
	@SuppressWarnings("unchecked")
	//@Test
	public void testGenericDAO() {
		
		IExpressionCriteria ct = ExpressionCriteria.createEqualToCriteria("customerMiddleName","LLOYD");
		IExpressionCriteria ct1 = ExpressionCriteria.createEqualToCriteria("customerLastName","SMITH");
		IExpressionCriteria ct2 = ExpressionCriteria.createEqualToCriteria("customerFirstName","WINSTON");
		IExpressionCriteria state = ExpressionCriteria.createEqualToCriteria("state","WA");
		IExpressionCriteria primaryAddress = ExpressionCriteria.createEqualToCriteria("isPrimaryAddress",new Character('Y'));
		//IExpressionCriteria finalCriteria = ((ct.or(ct1)).and(ct2)).or(primaryAddress.and(state));
		IExpressionCriteria finalCriteria = ct1.or(primaryAddress.and(state));

				
		IQuery q = new Query().from("CustomerAdvancedSearch").criteria(finalCriteria);
	
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		try {
			AdvanceSearchContext advSrch = advanceSearchService.getAdvanceSearchContext("CustomerAdvancedSearch", userDetails);
			q.from(advSrch);
		} catch (AdvanceSearchServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		TypedQuery typedQuery;
		try {
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("As typed query "+typedQuery.unwrap(TCust.class));
			List<TCust> custs = typedQuery.getResultList();
			for(TCust cust : custs) { 
				System.out.println(cust.getCustId()+"<<-->>"+cust.getCustMiddleName());
			}
		} catch (InvalidQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		QueryGeneratorForSimpleView critEval = new QueryGeneratorForSimpleView();
		try {
			System.out.println(" As jdbc query = "+critEval.getQuery(q));
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void advanceSearchTest() {
		
		try {
			System.out.println(url);
			UserDetails userDetails = new UserDetails();
			userDetails.setTenantId((short)1);
			AdvanceSearchContext advSrch = advanceSearchService.getAdvanceSearchContext("CustomerAdvancedSearch", userDetails);
			if(advSrch != null) {
				SearchEntity s = advSrch.getSearchEntity("Customer Address");
				System.out.println(s != null ? s.getLogicalName() : null);
				s = advSrch.getSearchEntityContainingAttribute("state"); 
				System.out.println(s != null ? s.getLogicalName()+"-"+s.getFqEntityClassName() : null);
				s = advSrch.getSearchEntityContainingAttribute("cu"); 
				System.out.println(s != null ? s.getLogicalName()+"-"+s.getFqEntityClassName() : null);
				s = advSrch.getSearchEntityContainingAttribute("customerLastName"); 
				System.out.println(s != null ? s.getLogicalName()+"-"+s.getFqEntityClassName() : null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGenericDAO1() {
		
		AdvanceSearchContext advSrch =  new AdvanceSearchContext("testGISCustomer");
		
		SearchEntity tCustAlignment = new SearchEntity("customerAlignment",TCustAlgmnt.class);

		SearchEntity tCust = new SearchEntity("customer",TCust.class);
		tCust.joinWith(tCustAlignment);
		tCust.joinBy("tCust");
		tCust.fetchWithParent(true);

		SearchEntity tCustAddress = new SearchEntity("custAddress",TCustAddr.class);
		tCustAddress.joinWith(tCust);
		tCustAddress.joinBy("tCustAddrss");
		tCustAddress.fetchWithParent(true);

		advSrch.addSearchEntity(tCustAlignment);
		advSrch.addSearchEntity(tCust);
		advSrch.addSearchEntity(tCustAddress);

        List<Long> spIdList = new ArrayList<Long>();
        spIdList.add(396l);
        spIdList.add(397l);
        spIdList.add(398l);
        spIdList.add(394l);

        List<Long> shIdList = new ArrayList<Long>();
        shIdList.add(5l);
        shIdList.add(4l);
		
		
		IExpressionCriteria spIds = ExpressionCriteria.createInCriteria("salesPosId", spIdList);
		IExpressionCriteria shIds = ExpressionCriteria.createInCriteria("salesHierId", shIdList);
		
		IExpressionCriteria targetFlag = ExpressionCriteria.createEqualToCriteria("targetFlag",'Y');
		IExpressionCriteria activeFlag = ExpressionCriteria.createEqualToCriteria("activeFlag",'Y');
        
		IExpressionCriteria custActiveFlag = ExpressionCriteria.createEqualToCriteria("customer.activeFlag",'Y');
		IExpressionCriteria pPrimaryAddress = ExpressionCriteria.createEqualToCriteria("custAddress.prAddrFlag",'Y');
		
		IExpressionCriteria crit = spIds.or(shIds.and(targetFlag,activeFlag,custActiveFlag,pPrimaryAddress));

		IQuery q = new Query().from(advSrch).criteria(crit);
		TypedQuery typedQuery;
		try {
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("As typed query "+typedQuery.unwrap(TCustAlgmnt.class));
			List<TCustAlgmnt> custs = typedQuery.getResultList();
			for(TCustAlgmnt cust : custs) { 
				System.out.println(cust.getTCust().getCustCd());
			}
		} catch (InvalidQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	//@Test
	public void testCustomerAdvanceSearch() {
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		AdvanceSearchContext advSrch = null;
		try {
			advSrch = advanceSearchService.getAdvanceSearchContext("CustomerAdvancedSearch", userDetails);
		} catch (AdvanceSearchServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		
		/*
		advSrch =  new AdvanceSearchContext("testGISCustomer");
		SearchEntity tCust = new SearchEntity("Cust",TCust.class);
		SearchEntity tCustAddress = new SearchEntity("CustAddr",TCustAddr.class);
		
		tCustAddress.joinWith(tCust);
		tCustAddress.joinBy("tCustAddrss");
		tCustAddress.fetchWithParent(true);
		
		
		advSrch.addSearchEntity(tCust);
		advSrch.addSearchEntity(tCustAddress);
		*/

		IExpressionCriteria custActiveFlag = ExpressionCriteria.createEqualToCriteria("custActiveFlag",'Y');
		IExpressionCriteria pPrimaryAddress = ExpressionCriteria.createEqualToCriteria("isPrimaryAddress",'Y');
		//IExpressionCriteria state = ExpressionCriteria.createEqualToCriteria("state","WA");
		//pPrimaryAddress.and(state);
		
		IExpressionCriteria crit = ExpressionCriteria.createLikeCriteria("customerLastName", "MI");
		crit.and(custActiveFlag,pPrimaryAddress);
		
		Order order1 = new Order();
		order1.setDescending(true);
		order1.setAttributeName("customerLastName");
		
		Order order2 = new Order();
		order2.setAttributeName("state");
		
		
		IQuery q = new Query().from(advSrch).criteria(crit).orderBy(order1,order2);
		TypedQuery typedQuery;

		try {
			q.isCountQuery(true);
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("As typed query 1 "+typedQuery.unwrap(TCust.class));
			System.out.println("count = "+typedQuery.getSingleResult());
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}

		for(SearchEntity searchEntity : advSrch.getSearchEntities()) {
			if(searchEntity.getJoinBy() != null) {
				searchEntity.fetchWithParent(true);
			}
		}
		
		
		try {
			q.isCountQuery(false);
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("As typed query 2 "+typedQuery.unwrap(TCust.class));
			List<TCust> custs = typedQuery.getResultList();
			System.out.println("size : "+custs != null ? custs.size() : -1);
			for(TCust cust : custs) { 
				System.out.println(cust.getCustCd()+" "+cust.getCustLastName()+" "+cust.getTCustAddrss().iterator().next().getState());
			}
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
		
	}

	//@Test
	public void testNewCustomerAdvanceSearch() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		AdvanceSearchContext advSrch = null;
		
		

		advSrch =  new AdvanceSearchContext("testAdvanceSearchCustomer");
		SearchEntity tCust = new SearchEntity("customer",TCust.class);
		SearchEntity tCustAddress = new SearchEntity("customerAddress",TCustAddr.class);
		
		tCustAddress.joinWith(tCust);
		tCustAddress.joinBy("tCustAddrss");
		tCustAddress.fetchWithParent(true);
		
		advSrch.addSearchEntity(tCust);
		advSrch.addSearchEntity(tCustAddress);

		IExpressionCriteria controlCrit = ExpressionCriteria.createEqualToCriteria("customer.tenantId", (short)1);
		IExpressionCriteria custActiveFlag = ExpressionCriteria.createEqualToCriteria("customer.activeFlag",'Y');
		IExpressionCriteria pPrimaryAddress = ExpressionCriteria.createEqualToCriteria("customerAddress.prAddrFlag",'Y');
		//IExpressionCriteria custLastName = ExpressionCriteria.createLikeCriteria("customer.custLastName","MI");
		
		IExpressionCriteria custLastName = ExpressionCriteria.createEqualToCriteria("customer.custLastName","SMITH");
		IExpressionCriteria custFirstName = ExpressionCriteria.createLikeCriteria("customer.custFirstName","RO");

		
		
		controlCrit.and(custActiveFlag,pPrimaryAddress);
		controlCrit.and(custLastName,custFirstName);
		
		Order order1 = new Order();
		order1.setDescending(true);
		order1.setAttributeName("customer.custLastName");
		
		Order order2 = new Order();
		order2.setAttributeName("customerAddress.state");
		
		
		IQuery q = new Query().from(advSrch).criteria(controlCrit).orderBy(order1,order2);
		TypedQuery typedQuery;

		try {
			q.isCountQuery(true);
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("As typed query 1 "+typedQuery.unwrap(TCust.class));
			System.out.println("count = "+typedQuery.getSingleResult());
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}

		for(SearchEntity searchEntity : advSrch.getSearchEntities()) {
			if(searchEntity.getJoinBy() != null) {
				searchEntity.fetchWithParent(true);
			}
		}
		
		
		try {
			q.isCountQuery(false);
			typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("As typed query 2 "+typedQuery.unwrap(TCust.class));
			List<TCust> custs = typedQuery.getResultList();
			System.out.println("size : "+custs != null ? custs.size() : -1);
			for(TCust cust : custs) { 
				System.out.println(cust.getCustCd()+" "+cust.getCustLastName()+" "+cust.getTCustAddrss().iterator().next().getState());
			}
		} catch (InvalidQueryException e) {
			e.printStackTrace();
		}
		
	}

	
	//@Test
	public void testServicesCustomerAdvanceSearch() {
	
		System.out.println("****************************************");
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		IExpressionCriteria custLastName = ExpressionCriteria.createEqualToCriteria("customer.custLastName","SMITH");
		IExpressionCriteria custFirstName = ExpressionCriteria.createLikeCriteria("customer.custFirstName","RO");

		Order order1 = new Order();
		order1.setDescending(true);
		order1.setAttributeName("customer.custLastName");
		
		Order order2 = new Order();
		order2.setAttributeName("customerAddress.state");

		IQuery q = new Query().criteria(custLastName.and(custFirstName)).orderBy(order1,order2);
		Long count = -1L;
		try {
			count = customerService.getCustomerCount(q, userDetails);
			System.out.println("count = "+count);
			List<Customer> custs = customerService.searchCustomers(q, userDetails);
			for(Customer c : custs) {
				System.out.println("Customer :  "+c.getLastName()+", "+c.getFirstName()+" State : "+c.getAddresses().get(0).getState()+" Contact : "+c.getContacts().get(0).getId());
			}
			
		} catch (CustomerServiceException e) {
			e.printStackTrace();
		}
		
		
	}

	@Test
	public void testTransaction() {
		try {
			System.out.println("Test tx");
			//txTest.callService();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testCRListing() {
	
		System.out.println("****************************************");
		
		UserDetails userDetails = new UserDetails();
		userDetails.setTenantId((short)1);
		
		List<Long> crIdList = new ArrayList<Long>();
        crIdList.add(40L);
        crIdList.add(41L);
        
		IExpressionCriteria crIds = ExpressionCriteria.createInCriteria("chngReqId", crIdList);
		IExpressionCriteria tenantId = ExpressionCriteria.createEqualToCriteria("tenantId", (short)1);
		crIds.and(tenantId);
		
		AdvanceSearchContext advSrch =  new AdvanceSearchContext("crListing");
		SearchEntity searchEntity = new SearchEntity("cr",TChngReq.class);
		advSrch.addSearchEntity(searchEntity);

		IQuery q = new Query().from(advSrch).criteria(crIds);
		try {
			TypedQuery typedQuery = (TypedQuery)jpaQg.getQuery(q);
			System.out.println("CR Listing Query = "+typedQuery.unwrap(TChngReq.class));
			List<TChngReq> tChngReqs = typedQuery.getResultList();
			for(TChngReq tChngReq : tChngReqs) {
				System.out.println("CR = "+tChngReq.getChngReqId()+" Status = "+tChngReq.getStatusId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	

	
}
