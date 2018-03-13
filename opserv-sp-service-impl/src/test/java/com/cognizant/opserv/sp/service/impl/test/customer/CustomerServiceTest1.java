package com.cognizant.opserv.sp.service.impl.test.customer;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.opserv.sp.core.entity.TCust;
import com.cognizant.opserv.sp.core.entity.TCustAddr;
import com.cognizant.opserv.sp.core.entity.TCustAlgmnt;
import com.cognizant.opserv.sp.service.base.test.BaseTest;
import com.cognizant.opserv.sp.service.customer.CustomerService;
import com.cognizant.peg.core.dao.GenericDAO;

public class CustomerServiceTest1 extends BaseTest{
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	//@Qualifier("newGenericDAO")
	private GenericDAO genericDAO;
	
/*	@Test
	public void getCustomerDetailsTest() throws CustomerServiceException{
		Customer customer = customerService.getCustomerDetails(1003l, new UserDetails());
		Assert.notNull(customer);
	}
	
	@Test
	public void searchCustomersByCriteriaTest() throws CustomerServiceException{
		SearchCriteria criteria = new SearchCriteria();
		criteria.addLike("custName", "%HOSPITAL%");
		criteria.addLimit(0, 12);
		UserDetails userDtls = new UserDetails();
		userDtls.setTenantId((short)1);
		List<Customer> customerList = customerService.searchCustomersByCriteria(criteria, null, userDtls);
		Assert.notEmpty(customerList);
	}
	
	@Test
	public void getCustomerCountByCriteriaTest() throws CustomerServiceException{
		SearchCriteria criteria = new SearchCriteria();
		criteria.addLike("custName", "%HOSPITAL%");
		SearchCriteria criteria2 = new SearchCriteria();
		criteria2.addEqualTo("custTypeId", 9);
		criteria.addAndCriteria(criteria2);
		Long customerCount = customerService.getCustomerCountByCriteria(criteria, new UserDetails());
		Assert.notNull(customerCount);
	}
*/	
	/*
	@Test
	public void testGenericDAO() {
		
		SearchCriteria control1 = new SearchCriteria();
		control1.addEqualTo("tenantId",(short)1);
		
		SearchCriteria custNameCriteria1 = new SearchCriteria();
		custNameCriteria1.addLike("custName", "%HOSPITAL%");
		
		SearchCriteria stateCriteria1 = new SearchCriteria();
		stateCriteria1.addEqualTo("com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.state", "WA");
		stateCriteria1.addAndCriteria(control1);
		
		SearchCriteria isPrimaryAddrCriteria1 = new SearchCriteria();
		isPrimaryAddrCriteria1.addEqualTo("com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.prAddrFlag", 'Y');
		isPrimaryAddrCriteria1.addAndCriteria(stateCriteria1);
		custNameCriteria1.addAndCriteria(isPrimaryAddrCriteria1);
		
		
		SearchCriteria control2 = new SearchCriteria();
		control2.addEqualTo("tenantId",(short)1);
		
		SearchCriteria custNameCriteria2 = new SearchCriteria();
		custNameCriteria2.addLike("custName", "%MEDICAL%");
		
		SearchCriteria stateCriteria2 = new SearchCriteria();
		stateCriteria2.addEqualTo("com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.state", "LA");
		stateCriteria2.addAndCriteria(control2);
		
		SearchCriteria isPrimaryAddrCriteria2 = new SearchCriteria();
		isPrimaryAddrCriteria2.addEqualTo("com.cognizant.opserv.sp.core.entity.TCustAddr:tCustAddrss.prAddrFlag", 'Y');
		isPrimaryAddrCriteria2.addAndCriteria(stateCriteria2);
		custNameCriteria2.addAndCriteria(isPrimaryAddrCriteria2);
		
		custNameCriteria1.addORCriteria(custNameCriteria2);
		System.out.println(custNameCriteria1.getOperator().size());
		System.out.println(custNameCriteria1.getOperant().size());
		
		


		
		//List<TCust> custs = genericDAO.search(TCust.class, custNameCriteria1);
		//System.out.println("Size = "+custs != null ? custs.size() : 0);
	}
	*/
	
	//@Test
	public void JPAQuery() {
		
		EntityManager em = genericDAO.getEntityManagerFromJPA();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TCust> criteriaQuery = criteriaBuilder.createQuery(TCust.class);
		Root<TCust> cust = criteriaQuery.from(TCust.class);
		Join<TCust,TCustAddr> addrs = cust.join("tCustAddrss"); //Step 2
		Path<Object> state = addrs.get("state");
		Path<Object> primaryAddress = addrs.get("prAddrFlag");
		
        Predicate pCustName1 = criteriaBuilder.like(cust.<String>get("custName"),"%HOSPITAL%"); //Step 4
        Predicate pState1 = criteriaBuilder.equal(state,"WA"); //Step 44
        Predicate pPrimaryAddress1 = criteriaBuilder.equal(primaryAddress,'Y'); //Step 44
        Predicate custPredicate1 = criteriaBuilder.and(pCustName1,pState1,pPrimaryAddress1); //Step 4
        
        Predicate pCustName2 = criteriaBuilder.like(cust.<String>get("custName"),"%MEDICAL%"); //Step 4
        Predicate pState2 = criteriaBuilder.equal(state,"LA"); //Step 44
        Predicate pPrimaryAddress2 = criteriaBuilder.equal(primaryAddress,'Y'); //Step 44
        Predicate custPredicate2 = criteriaBuilder.and(pCustName2,pState2,pPrimaryAddress2) ; //Step 4
        
        Predicate p1 = criteriaBuilder.or(custPredicate1,custPredicate2);
        Predicate p2 = criteriaBuilder.equal(cust.<String>get("tenantId"),"2"); //Step 4
        
        criteriaQuery.where(criteriaBuilder.or(p1,p2)); //Step 5
        criteriaQuery.select(cust);
        
		TypedQuery<TCust> typedQuery = em.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(50);
		List<TCust> custs = typedQuery.getResultList();
		for(TCust c : custs) {
			TCustAddr a = c.getTCustAddrss().iterator().next();
			System.out.println(c.getCustName()+" "+a.getState());
		}
		System.out.println("Size = "+custs != null ? custs.size() : 0);

		
	}

	
	@Test
	public void JPAQuery2() {
		
        long s = System.currentTimeMillis();
		
        List<Long> spIdList = new ArrayList<Long>();
        spIdList.add(396l);
        spIdList.add(397l);
        spIdList.add(398l);
        spIdList.add(394l);

        List<Long> shIdList = new ArrayList<Long>();
        shIdList.add(5l);
        shIdList.add(4l);

		
		EntityManager em = genericDAO.getEntityManagerFromJPA();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TCustAlgmnt> criteriaQuery = criteriaBuilder.createQuery(TCustAlgmnt.class);
		Root<TCustAlgmnt> custAlignment = criteriaQuery.from(TCustAlgmnt.class);
/*		Join<TCustAlgmnt,TCust> cust = custAlignment.join("tCust");
		Join<TCust,TCustAddr> addrs = cust.join("tCustAddrss"); 
*/		
		//Fetch fCust = custAlignment.fetch("tCust");
		Fetch fCustAddr = custAlignment.fetch("tCust").fetch("tCustAddrss");
		
		
		Predicate spIds = custAlignment.<Long>get("salesPosId").in(spIdList);
		Predicate shIds = custAlignment.<Long>get("salesHierId").in(shIdList);
		
        Predicate targetFlag = criteriaBuilder.equal(custAlignment.<String>get("targetFlag"),'Y');
        Predicate activeFlag = criteriaBuilder.equal(custAlignment.<String>get("activeFlag"),'Y');
        
/*        Predicate custActiveFlag = criteriaBuilder.equal(cust.get("activeFlag"),'Y');
        Predicate pPrimaryAddress = criteriaBuilder.equal(addrs.get("prAddrFlag"),'Y');
*/        
        
        
        
        criteriaQuery.where(criteriaBuilder.and(spIds,shIds,targetFlag,activeFlag));//,custActiveFlag,pPrimaryAddress)); //Step 5
        criteriaQuery.select(custAlignment);
        //criteriaQuery.multiselect(custAlignment.alias("cAlign"),custAlignment.get("tCust").alias("tCust"),cust.get("tCustAddrss").alias("tCustAddrss"));
        //criteriaQuery.multiselect(c);

        
		TypedQuery<TCustAlgmnt> typedQuery = em.createQuery(criteriaQuery);
		List<TCustAlgmnt> custAlgns = typedQuery.getResultList();
		for(TCustAlgmnt cAlg : custAlgns) {
			TCust c1 = cAlg.getTCust();
			System.out.println(c1.getCustName()+", "+c1.getTCustAddrss().iterator().next().getState());
		}
		
		
/*        try {
        	TypedQuery<Tuple> typedQuery = em.createQuery(criteriaQuery);
	        List<Tuple> custAlgns = typedQuery.getResultList();
			for(Tuple obj : custAlgns) {
				TCustAlgmnt cAlign = (TCustAlgmnt)obj.get("cAlign");
//				TCust c1 = cAlign.getTCust();//(TCust)obj.get("cust");
//				TCustAddr addr = c1.getTCustAddrss().iterator().next();//(TCustAddr)obj.get("custAddrs");
				
				TCust c1 = (TCust)obj.get("cust");
				TCustAddr addr = (TCustAddr)obj.get("custAddrs");

				System.out.println(c1.getCustName()+", "+addr.getState());
			}	        
	        long e = System.currentTimeMillis();
			System.out.println("alignment = "+custAlgns != null ? custAlgns.size() : -1);
			System.out.println(" took time in ms = "+(e - s));
        } catch(Exception e) {
        	e.printStackTrace();
        }
*/		
		
	}
	
	//@Test
	public void JPAQueryCustomer() {
		
		EntityManager em = genericDAO.getEntityManagerFromJPA();
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<TCust> criteriaQuery = criteriaBuilder.createQuery(TCust.class);
		Root<TCust> cust = criteriaQuery.from(TCust.class);
		//Join<TCust,TCustAddr> addrs = cust.join("tCustAddrss"); //Step 2
		//Path<Object> state = addrs.get("state");
		
        Predicate pCustName1 = criteriaBuilder.like(cust.<String>get("custName"),"%HOSPITAL%"); //Step 4
        Predicate pState1 = criteriaBuilder.equal(cust.<String>get("tCustAddrss.state"),"WA"); //Step 44
        Predicate custPredicate1 = criteriaBuilder.and(pCustName1,pState1); //Step 4
        
        Predicate p1 = criteriaBuilder.or(custPredicate1);
        
        criteriaQuery.where(p1); //Step 5
        criteriaQuery.select(cust);
        
		TypedQuery<TCust> typedQuery = em.createQuery(criteriaQuery);
		typedQuery.setFirstResult(0);
		typedQuery.setMaxResults(50);
		List<TCust> custs = typedQuery.getResultList();
		for(TCust c : custs) {
			TCustAddr a = c.getTCustAddrss().iterator().next();
			System.out.println(c.getCustName()+" "+a.getState());
		}
		System.out.println("Size = "+custs != null ? custs.size() : 0);

		
	}
	
	
	
}
