package com.github.dengqiao.rpc.core;


public class ServiceProfile extends BaseProfile{
	
	private String serverContextPath;
	
	private String serviceUrlPrefix;
	
	private String serverPort;

	public String getServerPort() {
		return serverPort;
	}

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}

	public String getServerContextPath() {
		return serverContextPath;
	}

	public void setServerContextPath(String serverContextPath) {
		this.serverContextPath = serverContextPath;
	}

	public String getServiceUrlPrefix() {
		return serviceUrlPrefix;
	}

	public void setServiceUrlPrefix(String serviceUrlPrefix) {
		this.serviceUrlPrefix = serviceUrlPrefix;
	}
	
}
