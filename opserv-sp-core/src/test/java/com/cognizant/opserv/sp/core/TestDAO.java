package com.cognizant.opserv.sp.core;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cognizant.peg.core.exception.OpservDataAccessException;
import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext-persistence-test.xml" })
public class TestDAO {

private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger(TestDAO.class);	
	
	@Test
	@Rollback(false)
	public void testUpdateMeasureStatus() {
		try{
		System.out.println("Hi Reached here...");

		} catch(OpservDataAccessException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
	}
}
