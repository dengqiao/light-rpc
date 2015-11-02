package com.github.dengqiao.rpc.client;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.github.dengqiao.rpc.core.ZKClientHelper;

public class ServiceLocatorTest extends TestCase {
	
	private TestingServer server;
	private CuratorFramework zkClient;
	private ServiceLocator sl;
	private String path = "/test/userService";
	
	@BeforeClass
	public void setUp() throws Exception{
		server = new TestingServer();
		server.start();
		zkClient = ZKClientHelper.getZKClient(server.getConnectString());
		sl = new ServiceLocator(path,zkClient);
		
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		server.stop();
	}
	
	public void testInitServiceUrlList() throws Exception{
		ZKClientHelper.ensure(zkClient, path);
		ZKClientHelper.createEphemeral(zkClient, path+"/" + "dq", "dengqiao".getBytes());
		Thread.sleep(1000);
		assertEquals(1, sl.getServiceUrlMap().size());
		assertEquals("dengqiao", sl.getServiceUrlMap().get(1));
	}
	

}
