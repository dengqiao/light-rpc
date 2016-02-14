package com.github.dengqiao.rpc.client;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.github.dengqiao.rpc.example.SoService;
import com.github.dengqiao.rpc.locate.ServiceLocator;
import com.github.dengqiao.rpc.locate.impl.ZkServiceLocator;

public class JdkRpcProxyFactoryTest extends TestCase{
	
	public void setUp(){
		JdkRpcProxyFactoryBean.getProxyMap().clear();
	}
	
	@Test
	public void testCreateProxy(){
		ServiceLocator sl = new ZkServiceLocator();
		sl.setClientProfile(TestHelper.getClientProfile());
		SoService soService = 
				(SoService)JdkRpcProxyFactoryExtend.create(JdkRpcProxyFactoryExtend.class,
						SoService.class, sl);
		Assert.assertTrue(soService.getSoById(1L).getId().equals(1L));
	}
	

}
