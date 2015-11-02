package com.github.dengqiao.rpc.client;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.codec.impl.KryoCodec;
import com.github.dengqiao.rpc.example.SoService;

public class JdkRpcProxyFactoryTest extends TestCase{
	
	public void setUp(){
		JdkRpcProxyFactoryBean.getProxyMap().clear();
	}
	
	@Test
	public void testCreateProxy(){
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setClientAppName("test");
		ServiceProfile serviceProfile = new ServiceProfile();
		serviceProfile.setServiceAppName("user");
		serviceProfile.setRpcCodec(new KryoCodec());
		SoService soService = 
				(SoService)JdkRpcProxyFactoryExtend.create(JdkRpcProxyFactoryExtend.class,
						SoService.class, clientProfile, serviceProfile);
		Assert.assertTrue(soService.getSoById(1L).getId().equals(1L));
	}
	

}
