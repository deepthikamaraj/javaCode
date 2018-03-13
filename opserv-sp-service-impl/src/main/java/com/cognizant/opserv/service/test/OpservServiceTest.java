package com.cognizant.opserv.service.test;





public class OpservServiceTest {

	public static void main(String[] args) {

		System.setProperty("service.path","c:/services");
		
		System.setProperty("opserv.config.file", "c:/opserv/config/opserv-config.properties");
		System.setProperty("log4j.configurationFile","file:///c:/opserv/config/service-log-config.xml");
		System.setProperty("org.jboss.logging.provider","slf4j");
		
		System.out.println("********* Begin **********");
		boolean startClean = false;
		OpservServiceManager.setServiceEnvironment(startClean);		
		OpservServiceManager.invoke("EmployeeAlignmentService","getAllEmployeeAlignments");
		OpservServiceManager.invoke("EmployeeAlignmentService","getAllEmployeeAlignmentsBySalesPostion");
		System.out.println("********* Complete **********");
		
	}
	
}
