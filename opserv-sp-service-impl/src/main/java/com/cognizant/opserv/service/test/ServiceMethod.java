package com.cognizant.opserv.service.test;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;


public class ServiceMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4573448566146955559L;
	private String folderName;
	private Method method;
	private List<String> paramFiles;
	private List<Class> paramTypes;

	public ServiceMethod(String folderName, Method method) {
		this.folderName = folderName;
		this.method = method;
		determineParamFileNames();
	}

	public String getDisplayName() {
		Class[] params = method.getParameterTypes();
		String paramStr = "";
		for(Class param : params) {
			paramStr += param.getSimpleName()+",";
		}
		if(paramStr.endsWith(",")) {
			paramStr = paramStr.substring(0, paramStr.length()-1);
		}
		paramStr = "(" + paramStr + ")";
		return method.getName()+paramStr;
	}
	
	private void determineParamFileNames() {
		paramFiles = new LinkedList<String>();
		paramTypes = new LinkedList<Class>();
		Class[] pTypes = method.getParameterTypes();
		String paramStr = "";
		int count = 1;
		for(Class paramType : pTypes) {
			paramFiles.add("param"+count+"_"+paramType.getSimpleName()+".json");
			paramTypes.add(paramType);
			++count;
		}
	}
	
	public List<String> getParamFileNames() {
		return paramFiles;
	}
	
	public List<Class> getParamTypes() {
		return paramTypes;
	}

	public String getFolderName() {
		return folderName;
	}

	public Method getMethod() {
		return method;
	}
	
	public String getServiceMethodName() {
		return method.getName();
	}
	
	public Integer getNoOfInputParameters() {
		if(paramFiles != null) {
			return paramFiles.size();
		} 
		return 0;
	}

	
}
