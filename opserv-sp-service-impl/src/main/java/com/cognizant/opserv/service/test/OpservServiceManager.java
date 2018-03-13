package com.cognizant.opserv.service.test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;

import com.cognizant.peg.core.logger.OpservLogger;
import com.cognizant.peg.core.logger.OpservLoggerFactory;
import com.cognizant.peg.core.util.OpservApplicationContextUtil;

public class OpservServiceManager {

	private static final Map<String,Object> serviceMap = new HashMap<String,Object>();
	private static final Map<String,Service> serviceMetadata = new HashMap<String,Service>();
	private static final List<String> serviceInterfaceNames= new ArrayList<String>();
	private static final OpservLogger LOGGER = OpservLoggerFactory.getOpservLogger("SERVICE");
	public static void setServiceEnvironment(boolean startClean) {
		if(startClean) {
			try {
				cleanup();
			} catch(Exception e) {
				LOGGER.error("Error", e);
			}
		}
		//ApplicationContext context = (ClassPathXmlApplicationContext) new ClassPathXmlApplicationContext("s-test.xml");
		ApplicationContext context = OpservApplicationContextUtil.getApplicationContext();
		Map<String,Object> beanMap = context.getBeansWithAnnotation(org.springframework.stereotype.Service.class);
		Collection<Object> beans = beanMap.values();
		
		
		for(Object bean : beans) {
			Class serviceImplClass = AopUtils.getTargetClass(bean);
			Class serviceInterfaces[] = serviceImplClass.getInterfaces();
			for(Class serviceInterface : serviceInterfaces) {
				if(isValidServiceInterface(serviceInterface)) {
					
					Service service = new Service();
					String serviceInterfaceName = serviceInterface.getName().substring(serviceInterface.getName().lastIndexOf(".")+1);
					service.setServiceName(serviceInterfaceName);
					serviceMetadata.put(serviceInterfaceName,service);
					//LOGGER.info("****************	Service : "+serviceInterfaceName);
					serviceMap.put(serviceInterfaceName, bean);
					serviceInterfaceNames.add(serviceInterfaceName);
					Method[] serviceMethods = serviceInterface.getDeclaredMethods();
					Map<String,Integer> visitedMethods = new HashMap<String,Integer>();
					String methodFolderName = "";
					for(Method method : serviceMethods) {
						methodFolderName = method.getName();
						int count = 1;
						if(visitedMethods.containsKey(method.getName())) {
							count = visitedMethods.get(method.getName());
							methodFolderName = method.getName() + count;
							visitedMethods.put(method.getName(),++count);
						} else {
							visitedMethods.put(method.getName(), count);
						}
						ServiceMethod serviceMethod = new ServiceMethod(methodFolderName,method);
						service.addServiceMethod(serviceMethod);
					}
					checkAndCreateFolder(service);
				}
			}
		}
	
	}
	
	private static void cleanup() {
		File servicePath = new File(System.getProperty("service.path"));
		if(servicePath.exists() && servicePath.isDirectory()) {
			servicePath.delete();
			LOGGER.info("Services folder cleaned up");
		}
	}
	
	private static void checkAndCreateFolder(String serviceFolderName, ServiceMethod serviceMethod) {
		String methodDirName = System.getProperty("service.path")+"/"+serviceFolderName+"/"+serviceMethod.getFolderName();
		File methodDir = new File(methodDirName);
		if(!methodDir.exists()) {
			methodDir.mkdirs();
			LOGGER.info("Created dir : "+methodDirName);
		}
		
		for(String paramFileName : serviceMethod.getParamFileNames()){
			File paramFile = new File(methodDirName+"/"+paramFileName);
			if(!paramFile.exists()) {
				try {
					paramFile.createNewFile();
					LOGGER.info("Created parameter file  : "+paramFile.getName());
				} catch (IOException e) {
					LOGGER.error("Error", e);
				}
			}
		}
	}
	
	private static void checkAndCreateFolder(Service service) {
		for(ServiceMethod method : service.getServiceMethods()) {
			checkAndCreateFolder(service.getServiceName(),method);
		}
	}	
	
	private static boolean isValidServiceInterface(Class serviceInterface) {
		
		try {
			Package pkg = serviceInterface.getPackage();
			if(pkg.getName().startsWith("com.cognizant.opserv.sp.service.") && !pkg.getName().contains(".internal")) {
				return true;
			} else {
				return false;
			}
		} catch(Exception e) {
			LOGGER.error("Error", e);
			return false;
		}
	}
	
	public static Object invoke(String serviceName, String methodName) {
		Object returnObject = null;
		LOGGER.info("Start to invoke the service "+serviceName+"."+methodName);
		ObjectMapper mapper = new ObjectMapper();
		Object targetObj = serviceMap.get(serviceName);
		if(targetObj == null) {
			//throw new RuntimeException("No bean found for service : "+serviceName);
			return ("No bean found for service : "+serviceName);
		}
		Service service = serviceMetadata.get(serviceName);
		if(service == null) {
			//throw new RuntimeException("No service found with name : "+serviceName);
			return ("No service found with name : "+serviceName);
		}
		ServiceMethod serviceMethod = service.getServiceMethod(methodName);
		Method m = null;
		if(serviceMethod != null) {
			m = serviceMethod.getMethod();
		}
		if(m == null) {
			//throw new RuntimeException("No method : "+methodName+" found in service : "+serviceName);
			return ("No method : "+methodName+" found in service : "+serviceName);
		}
		Object args[] =  new Object[serviceMethod.getNoOfInputParameters()];

		String physicalDirName = System.getProperty("service.path")+"/"+serviceName+"/"+serviceMethod.getFolderName();
		boolean errorInParameters = false;
		for(int i=0;i<serviceMethod.getNoOfInputParameters();i++) {
			try {
				args[i] = mapper.readValue(new File(physicalDirName+"/"+serviceMethod.getParamFileNames().get(i)), serviceMethod.getParamTypes().get(i));
			} catch (JsonParseException e) {
				errorInParameters=true;
			} catch (JsonMappingException e) {
				errorInParameters=true;
			} catch (IOException e) {
				errorInParameters=true;
			}
		}
		if(errorInParameters) {
			LOGGER.info("Error in execution - Missing or invalid parameter files");
			try {
				mapper.writeValue(new File(physicalDirName+"/output.json"), "Error in execution - Missing or invalid parameter files");
			} catch (JsonGenerationException e) {
				LOGGER.error("Error", e);
			} catch (JsonMappingException e) {
				LOGGER.error("Error", e);
			} catch (IOException e) {
				LOGGER.error("Error", e);
			}
			
			return "Cannot execute due to missing or invalid parameter files. Please check in folder : "+physicalDirName;
		}
		
		long startTime = 0;
		long endTime = 0;
		long timeTaken = 0;
		
		try {
			startTime = System.currentTimeMillis();
			returnObject = m.invoke(targetObj, args);
			endTime = System.currentTimeMillis();
			timeTaken = (long)((endTime - startTime));
			if(returnObject == null) {
				returnObject = new String("Return object is null.");
			} 
			//LOGGER.info(JSONUtil.toJSON(returnObject));
			LOGGER.info("End of Invocation of service "+serviceName+"."+methodName+" [Time taken in milliseconds = "+timeTaken+"]");
			
		} catch (IllegalAccessException e) {
			LOGGER.error("Error in invocation. Due to illegal access", e);
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error in invocation. Due to illegal argument", e);
		} catch (InvocationTargetException e) {
			returnObject = e.getTargetException();
			LOGGER.error("Error in invocation. Some business error",e.getTargetException());
		}
		if(returnObject != null) {
			try {
				mapper.writeValue(new File(physicalDirName+"/output.json"), returnObject);
				mapper.writeValue(new File(physicalDirName+"/perf.json"), "Time taken = "+timeTaken+" ms");
			} catch (JsonGenerationException e) {
				LOGGER.error("Error in generating json", e);
			} catch (JsonMappingException e) {
				LOGGER.error("Error in mapping json", e);
			} catch (IOException e) {
				LOGGER.error("Error in IO", e);
			}
		}

		return returnObject;
		
	}
	
	public static Map<String,Service> getServicesInformation() {
		return serviceMetadata;
	}
	public static List<String> getServiceInterfaceNames(){
		return serviceInterfaceNames;
	}

	
}
