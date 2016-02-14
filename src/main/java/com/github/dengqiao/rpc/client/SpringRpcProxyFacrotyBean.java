package com.github.dengqiao.rpc.client;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import com.github.dengqiao.rpc.core.RpcResponse;

@SuppressWarnings("rawtypes")
public class SpringRpcProxyFacrotyBean extends AbstractProxyFactoryBean implements FactoryBean,InitializingBean,MethodInterceptor{
	

	private Object serviceProxy;
	
	public Object getObject() throws Exception {
		return this.getServiceProxy();
	}
	
	public void afterPropertiesSet() throws Exception {
		this.setServiceProxy(new ProxyFactory(getServiceInterface(), this).getProxy(getBeanClassLoader()));
	}
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		RpcResponse reponse = executeRpcRequest(createRpcRequest(invocation.getMethod(),invocation.getArguments()));
		if(reponse.getResponse()!=null){
			return reponse.getResponse();
		}
		throw reponse.getException();
	}


	public Class getObjectType() {
		return this.getServiceInterface();
	}

	public boolean isSingleton() {
		return true;
	}

	public Object getServiceProxy() {
		return serviceProxy;
	}

	public void setServiceProxy(Object serviceProxy) {
		this.serviceProxy = serviceProxy;
	}
	
}
