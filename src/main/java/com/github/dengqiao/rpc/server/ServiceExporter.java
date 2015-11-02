package com.github.dengqiao.rpc.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.ZkPathUtils;

public class ServiceExporter implements InitializingBean{
	
	private Object target;
	private Class<?> serviceInterface;
	private ServiceProfile serviceProfile;
	private ServiceRegister serviceRegister;
	private Map<String,Method> methodMap = new HashMap<String,Method>();
	
	public void afterPropertiesSet() throws Exception {
		serviceRegister = new ServiceRegister(ZkPathUtils.getServiceZkPath(serviceProfile, serviceInterface),serviceProfile);
		serviceRegister.register();
		initMethodMap();
	}
	
	public void init() throws Exception {
		afterPropertiesSet();
	}
	
	protected void initMethodMap(){
		Method[] methods = serviceInterface.getDeclaredMethods();
		for(Method method : methods){
			methodMap.put(method.getName(), method);
		}
	}


	public RpcResponse invoke(RpcRequest request) {
		RpcResponse res = new RpcResponse();
		try{
			if(methodMap.get(request.getMethodName())==null){
				throw new RpcException("service has no exist method "+JSON.toJSONString(request) );
			}
			res.setResponse(methodMap.get(request.getMethodName()).invoke(target, request.getArgs()));
			return res;
		}catch(InvocationTargetException e ){
			res.setException(e.getCause());
		}catch(Throwable e){
			res.setException(new RpcException("service invoke error "+ExceptionUtils.getFullStackTrace(e)));
		}
		return res;
	}
	
	public String getServiceName(){
		return ZkPathUtils.getServiceName(this.serviceInterface);
	}


	public ServiceRegister getServiceRegister() {
		return serviceRegister;
	}


	public void setServiceRegister(ServiceRegister serviceRegister) {
		this.serviceRegister = serviceRegister;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public ServiceProfile getServiceProfile() {
		return serviceProfile;
	}

	public void setServiceProfile(ServiceProfile serviceProfile) {
		this.serviceProfile = serviceProfile;
	}

	public Map<String, Method> getMethodMap() {
		return methodMap;
	}

	
}
