package com.github.dengqiao.rpc.core;

import java.io.Serializable;
import java.util.Date;

public class RpcRequest implements Serializable {
	
	private static final long serialVersionUID = -2226632814980198240L;

	private String serviceFullName;
	
	private String methodName;
	
	private Object[] args;
	
	private String requestId;
	
	private String clientAppName;
	
	private Date requestTime;
	
	private String clientIp;

	public String getServiceFullName() {
		return serviceFullName;
	}

	public void setServiceFullName(String serviceFullName) {
		this.serviceFullName = serviceFullName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getClientAppName() {
		return clientAppName;
	}

	public void setClientAppName(String clientAppName) {
		this.clientAppName = clientAppName;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

}
