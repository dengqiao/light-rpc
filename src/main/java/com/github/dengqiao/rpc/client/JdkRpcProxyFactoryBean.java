package com.github.dengqiao.rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.requestHandler.impl.HttpRequestHandler;

public class JdkRpcProxyFactoryBean extends AbstractProxyFactoryBean implements InvocationHandler {

	private static Map<Class<?>,Object> proxyMap = new ConcurrentHashMap<Class<?>,Object>();
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		RpcResponse reponse = executeRpcRequest(createRpcRequest(method,args));
		if(reponse.getResponse()!=null){
			return reponse.getResponse();
		}
		throw reponse.getException();
	}
	
	public Object createProxy(Class<?> clazz,ClientProfile clientProfile,ServiceProfile serviceProfile) {
		this.setServiceInterface(clazz);
		this.setClientProfile(clientProfile);
		this.setServicePrifile(serviceProfile);
		initServiceLocator();
		this.setRequestHandler(new HttpRequestHandler());
		return Proxy.newProxyInstance(getBeanClassLoader(), new Class<?>[]{clazz}, this);
	}
	
	public static Object create(Class<?> clazz,ClientProfile clientProfile,ServiceProfile serviceProfile){
		return create(JdkRpcProxyFactoryBean.class,clazz,clientProfile,serviceProfile);
	}
	
	public static Object create(Class<? extends JdkRpcProxyFactoryBean> jdkRpcProxyFactoryBean ,
			Class<?> clazz,ClientProfile clientProfile,ServiceProfile serviceProfile){
		Object proxy = proxyMap.get(clazz);
		if(proxy == null ){
			synchronized (JdkRpcProxyFactoryBean.class) {
				proxy = proxyMap.get(clazz);
				if(proxy != null){
					return proxy;
				}
				try {
					proxy = jdkRpcProxyFactoryBean.newInstance().createProxy(clazz,clientProfile,serviceProfile);
				} catch (Exception e) {
					throw new RpcException("create proxy exception ", e);
				}
				proxyMap.put(clazz, proxy);
				return proxy;
			}
		}
		return proxy;
	}

	public static Map<Class<?>, Object> getProxyMap() {
		return proxyMap;
	}

	public static void setProxyMap(Map<Class<?>, Object> proxyMap) {
		JdkRpcProxyFactoryBean.proxyMap = proxyMap;
	}

}
