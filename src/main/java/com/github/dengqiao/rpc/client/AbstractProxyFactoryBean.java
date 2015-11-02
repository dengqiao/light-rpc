package com.github.dengqiao.rpc.client;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.IpUtils;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.ZkPathUtils;
import com.github.dengqiao.rpc.core.requestHandler.RequestHandler;

public abstract class AbstractProxyFactoryBean {
	
	private Class<?> serviceInterface;
	
	private Object serviceProxy;
	
	private ClientProfile clientProfile;
	
	private ServiceProfile servicePrifile;
	
	private ServiceLocator serviceLocator;
	
	private RequestHandler requestHandler;
	
	protected void initServiceLocator(){
		this.setServiceLocator(new ServiceLocator(ZkPathUtils.getServiceZkPath(servicePrifile, serviceInterface)));
	}
	
	protected RpcRequest createRpcRequest(Method method,Object[] args) {
		RpcRequest rpcRequest = new RpcRequest();
		rpcRequest.setServiceFullName(serviceInterface.getName());
		rpcRequest.setMethodName(method.getName());
		rpcRequest.setArgs(args);
		rpcRequest.setRequestTime(new Date());
		rpcRequest.setRequestId(UUID.randomUUID().toString());
		rpcRequest.setClientIp(IpUtils.getLocalIp());
		rpcRequest.setClientAppName(clientProfile.getClientAppName());
		return rpcRequest;
	}
	
	protected RpcResponse executeRpcRequest(RpcRequest rpcRequest){
		byte[] byteRequest = servicePrifile.getRpcCodec().encode(rpcRequest);
		byte[] response = doRequest(rpcRequest,byteRequest);
		return (RpcResponse)servicePrifile.getRpcCodec().decode(response);
	}
	
	protected byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		String serviceUrl = serviceLocator.select();
		if(!StringUtils.hasLength(serviceUrl)){
			throw new RpcException("serviceZkPath "+ ZkPathUtils.getServiceZkPath(servicePrifile, serviceInterface)+" dose not exist online service");
		}
		return requestHandler.doRequest(rpcRequest, byteRequest, clientProfile,serviceUrl);
	}
	
	protected ClassLoader getBeanClassLoader(){
		return ClassUtils.getDefaultClassLoader();
	}

	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public Object getServiceProxy() {
		return serviceProxy;
	}

	public void setServiceProxy(Object serviceProxy) {
		this.serviceProxy = serviceProxy;
	}

	public ClientProfile getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(ClientProfile clientProfile) {
		this.clientProfile = clientProfile;
	}

	public ServiceProfile getServicePrifile() {
		return servicePrifile;
	}

	public void setServicePrifile(ServiceProfile servicePrifile) {
		this.servicePrifile = servicePrifile;
	}

	public ServiceLocator getServiceLocator() {
		return serviceLocator;
	}

	public void setServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}

	public RequestHandler getRequestHandler() {
		return requestHandler;
	}

	public void setRequestHandler(RequestHandler requestHandler) {
		this.requestHandler = requestHandler;
	}
	
	

}
