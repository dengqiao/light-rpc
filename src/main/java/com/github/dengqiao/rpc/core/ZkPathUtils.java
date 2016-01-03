package com.github.dengqiao.rpc.core;

public class ZkPathUtils {
	
	public static  String getServiceZkPath(BaseProfile baseProfile , Class<?> serviceInterface)  {
		StringBuilder sb = new StringBuilder(50);
		sb.append("/").append(baseProfile.getServiceAppName())
		.append("/").append(baseProfile.getGroupName())
		.append("/").append(baseProfile.getServiceVersion());
		return sb.toString();
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
