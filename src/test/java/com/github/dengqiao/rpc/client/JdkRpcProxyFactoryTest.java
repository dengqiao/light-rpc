package com.github.dengqiao.rpc.client;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.codec.impl.FstCodec;
import com.github.dengqiao.rpc.example.SoService;

public class JdkRpcProxyFactoryTest extends TestCase{
	
	public void setUp(){
		JdkRpcProxyFactoryBean.getProxyMap().clear();
	}
	
	@Test
	public void testCreateProxy(){
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setClientAppName("test");
		clientProfile.setServiceAppName("user");
		clientProfile.setServiceVersion("0.01");
		clientProfile.setRpcCodec(new FstCodec());
		SoService soService = 
				(SoService)JdkRpcProxyFactoryExtend.create(JdkRpcProxyFactoryExtend.class,
						SoService.class, clientProfile);
		Assert.assertTrue(soService.getSoById(1L).getId().equals(1L));
	}
	

}
