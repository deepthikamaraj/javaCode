package com.cognizant.opserv.sp.service.base.test;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-servicetest.xml" })
public class BaseTest {

    @BeforeClass
	public static void setUp() {
    	
		Assert.assertTrue("----->SETUP<-----", true);
		System.setProperty("opserv.activeMQ.brokerURL", "tcp://localhost:61616");
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}
	
    @Test
	public void testSampleService() {
    	Assert.assertTrue("----->SETUP DONE<-----", true);
    	System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}

	@AfterClass
	public static void afterTest() {
		Assert.assertTrue("----->DESTROY<-----", true);
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
	}

}
