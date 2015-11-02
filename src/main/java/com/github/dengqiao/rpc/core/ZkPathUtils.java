package com.github.dengqiao.rpc.core;

public class ZkPathUtils {
	
	public static  String getServiceZkPath(ServiceProfile profile,Class<?> serviceInterface)  {
		
		return "/"+ profile.getServiceAppName() +"/" + getServiceName(serviceInterface) ;
	}
	
	public static String getServiceName(Class<?> serviceInterface){
		return getServiceName(serviceInterface.getName());
	}
	
	public static String getServiceName(String serviceFullName) {
		int index = serviceFullName.lastIndexOf(".");
		String simpleServiceName = null;
		if(index==-1) {
			simpleServiceName =  serviceFullName.toLowerCase();
		}else {
			simpleServiceName =  serviceFullName.substring(index+1).toLowerCase();
		}
		return simpleServiceName;
	}

}
