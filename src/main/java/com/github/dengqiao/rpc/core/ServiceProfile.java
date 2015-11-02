package com.github.dengqiao.rpc.core;

import com.github.dengqiao.rpc.core.codec.RpcCodec;

public class ServiceProfile {
	
	private RpcCodec rpcCodec;
	
	private String serviceAppName;
	
	private String serverContextPath;
	
	private String urlPrefix;
	
	private String serverPort;

	public RpcCodec getRpcCodec() {
		return rpcCodec;
	}

	public void setRpcCodec(RpcCodec rpcCodec) {
		this.rpcCodec = rpcCodec;
	}

	public String getServiceAppName() {
		return serviceAppName;
	}

	public void setServiceAppName(String serviceAppName) {
		this.serviceAppName = serviceAppName;
	}

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

	public String getUrlPrefix() {
		return urlPrefix;
	}

	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	
}
