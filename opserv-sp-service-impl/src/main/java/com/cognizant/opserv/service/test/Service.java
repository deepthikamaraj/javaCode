package com.cognizant.opserv.service.test;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Service implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7357614763867382404L;
	private String serviceName;
	private List<ServiceMethod> serviceMethods = new ArrayList<ServiceMethod>();

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public List<ServiceMethod> getServiceMethods() {
		return serviceMethods;
	}

	public void setServiceMethods(List<ServiceMethod> serviceMethods) {
		this.serviceMethods = serviceMethods;
	}
	
	public void addServiceMethod(ServiceMethod method) {
		serviceMethods.add(method);
	}
	
	public void removeServiceMethod(ServiceMethod method) {
		serviceMethods.remove(method);
	}	
	
	public ServiceMethod getServiceMethod(String folderName) {
		for(ServiceMethod serviceMethod : serviceMethods) {
			if(serviceMethod.getFolderName().equalsIgnoreCase(folderName)) {
				return serviceMethod;
			}
		}
		return null;
	}
	
	
}
