package com.github.dengqiao.rpc.server;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.util.Assert;

import com.github.dengqiao.rpc.client.ServiceLocator;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.ZKClientHelper;

public class ServiceRegisterTest extends TestCase {
	
	private TestingServer server;
	private CuratorFramework zkClient;
	private ServiceLocator sl;
	private ServiceRegister sg;
	private String path = "/test/userService";
	
	@BeforeClass
	public void setUp() throws Exception{
		server = new TestingServer();
		server.start();
		zkClient = ZKClientHelper.getZKClient(server.getConnectString());
		//zkClient = ZKClientHelper.getZKClient("192.168.59.103:2181");
		sl = new ServiceLocator(path,zkClient);
		ServiceProfile  sp = new ServiceProfile();
		sp.setServerContextPath("gos");
		sp.setServerPort("8080");
		sp.setServerContextPath("gos-query");
		sg = new ServiceRegister(path, sp,zkClient);
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		server.stop();
	}
	
	public void testRegister() throws Exception{
		System.out.println("testRegister start");
		sg.register();
		Thread.sleep(1000L);
		Assert.notNull(sl.select());
	}
	
	public void testUnRegister2() throws Exception{
		System.out.println("testUnRegister2 start");
		sg.register();
		Thread.sleep(1000L);
		sg.unRegister();
		Thread.sleep(3000L);
		Assert.isNull(sl.select());
		
		sg.register();
		Thread.sleep(1000L);
		Assert.notNull(sl.select());
	}
	
	

}
