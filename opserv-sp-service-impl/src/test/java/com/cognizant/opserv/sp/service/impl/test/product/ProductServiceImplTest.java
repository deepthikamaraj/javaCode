package com.cognizant.opserv.sp.service.impl.test.product;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.opserv.sp.common.util.DateUtil;
import com.cognizant.opserv.sp.exception.ProductServiceException;
import com.cognizant.opserv.sp.model.Product;
import com.cognizant.opserv.sp.model.auth.UserDetails;
import com.cognizant.opserv.sp.service.product.ProductService;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class ProductServiceImplTest{// extends BaseTest {
	
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(ProductServiceImplTest.class);
	
	@Autowired
	ProductService productService;
	
	 @BeforeClass
		public static void setUp() {
	    	
			Assert.assertTrue("----->SETUP<-----", true);
			System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
			System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
		}
	
	@Test
	public void getProductDetails() throws ProductServiceException
	{
	Product p = new Product();
	UserDetails userDts = new UserDetails();
	userDts.setTenantId(Short.parseShort("1"));
	
	p= productService.getProductDetails(1L,userDts);
	if(p!= null){
		
		LOGGER.debug(p.getCode()+"@@@@@@@@@Code@@@@@@@@@@"+p.getEndDate());
	
	Assert.assertNotNull(p);
	}
	
	}
	
	
	
	@Test
	public void getAllProductDetails() throws ProductServiceException
	{
	List<Long> s = new ArrayList<Long>();
	UserDetails userDts = new UserDetails();
	s.add(1L);
	userDts.setTenantId((short)1);
	List<Product> p1 = productService.getProductDetails(s,userDts);
	
	for(Product pp:p1){
		
		LOGGER.debug(pp.getCode()+"---------------"+pp.getDescription());
		
		Assert.assertNotNull(p1);
		
	}
	
	}
	
	
	@Test
	public void createNewProductTest() throws ProductServiceException
	{
		Product product = productService.createProduct(getProduct(), userDetails());
		LOGGER.info("Newly created Product Id :: " + product.getId()); 
	}
	
	@Test
	public void updateProductDetailsTest() throws ProductServiceException
	{
		productService.updateProduct(getProduct(), userDetails());
	}
	
	public static Product getProduct(){
		Product product = new Product();
		product.setId(271L);
		product.setName("updateProduct");
		product.setCode("updateCode");
		product.setActive(true);
		product.setCreatedBy(1);
		product.setCreatedDate(DateUtil.getCurrentDate());
		product.setStartDate(DateUtil.getCurrentDate());
		product.setEndDate(null);
		product.setTenantId((short)1);
		return product;
	}
	public static UserDetails userDetails(){
		UserDetails details = new UserDetails();
		details.setTenantId((short)1);
		return details;
	}
	
}
