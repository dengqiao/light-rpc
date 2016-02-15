package com.github.dengqiao.rpc.client;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.core.requestHandler.RequestHandler;
import com.github.dengqiao.rpc.locate.ServiceLocator;
import com.github.dengqiao.rpc.utils.ExceptionUtils;
import com.github.dengqiao.rpc.utils.IpUtils;
import com.github.dengqiao.rpc.utils.ServicePathUtils;

public abstract class AbstractProxyFactoryBean {
	
	private static final Logger logger = LoggerFactory.getLogger(AbstractProxyFactoryBean.class);
	
	private Class<?> serviceInterface;
	
	private ClientProfile clientProfile;
	
	private ServiceLocator serviceLocator;
	
	private RequestHandler requestHandler;
	
	public void init(){
		if(serviceInterface == null){
			throw new RuntimeException("AbstractProxyFactoryBean serviceInterface is null");
		}
		if(clientProfile == null){
			throw new RuntimeException("AbstractProxyFactoryBean clientProfile is null");
		}
		if(StringUtils.isEmpty(clientProfile.getClientAppName())){
			throw new RuntimeException("AbstractProxyFactoryBean clientProfile clientAppName is empty");
		}
		if(StringUtils.isEmpty(clientProfile.getServiceAppName())){
			throw new RuntimeException("AbstractProxyFactoryBean clientProfile serviceAppName is empty");
		}
		if(StringUtils.isEmpty(clientProfile.getGroupName())){
			throw new RuntimeException("AbstractProxyFactoryBean clientProfile groupName is empty");
		}
		if(StringUtils.isEmpty(clientProfile.getServiceVersion())){
			throw new RuntimeException("AbstractProxyFactoryBean clientProfile serviceVersion is empty");
		}
		if(clientProfile.getRpcCodec()==null){
			throw new RuntimeException("AbstractProxyFactoryBean clientProfile rpcCodec is null");
		}
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
		try{
			byte[] byteRequest = clientProfile.getRpcCodec().encode(rpcRequest);
			byte[] response = null;
			try{
				response = doRequest(rpcRequest,byteRequest);
			}catch(RpcException e){
				if(ExceptionUtils.isNetworkException(e)){
					response =  retryRequest(rpcRequest,byteRequest);
				}
				throw e;
			}
			return (RpcResponse)clientProfile.getRpcCodec().decode(response);
		}catch(Throwable e){
			RpcResponse errRes = new RpcResponse();
			errRes.setException(e);
			return errRes;
		}
	}
	
	private byte[] retryRequest(RpcRequest rpcRequest,byte[] byteRequest){
		int retryCount = 1;
		while(retryCount< this.clientProfile.getRetryCount()){ 
			try{
				return doRequest(rpcRequest,byteRequest);
			}catch(Throwable e){
				logger.error("retryRequest error , retryCount "+retryCount ,e);
			}
			retryCount++;
		}
		throw new RpcException("request execute  "+ retryCount+" ,retry fail!!!");
	}
	
	protected byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		String serviceUrl = serviceLocator.select();
		if(!StringUtils.isEmpty(serviceUrl)){
			throw new RpcException("serviceZkPath "+ ServicePathUtils.getServicePath(clientProfile)+" dose not exist online service");
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

	

	public ClientProfile getClientProfile() {
		return clientProfile;
	}

	public void setClientProfile(ClientProfile clientProfile) {
		this.clientProfile = clientProfile;
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
