package com.github.dengqiao.rpc.server;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.curator.test.TestingServer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.client.TestHelper;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.example.So;
import com.github.dengqiao.rpc.example.SoService;
import com.github.dengqiao.rpc.example.SoServiceImpl;
import com.github.dengqiao.rpc.register.impl.ZkServiceRegister;
import com.github.dengqiao.rpc.utils.ZKClientUtils;

public class ServiceExporterTest extends TestCase {
	
	private TestingServer server;
	private ZkServiceRegister sr;
	
	private ServiceExporter exporter;
	
	@BeforeClass
	public void setUp() throws Exception{
		
		exporter = new ServiceExporter();
		server = new TestingServer();
		server.start();
		sr = new ZkServiceRegister();
		sr.setServiceProfile(TestHelper.getServiceProfile());
		sr.setZkConnStr(server.getConnectString());
		sr.init();
		
		exporter.setTarget(new SoServiceImpl());
		exporter.setServiceInterface(SoService.class);
		exporter.setServiceProfile(TestHelper.getServiceProfile());
		exporter.initMethodMap();
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		server.stop();
		ZKClientUtils.clearZkClient();
	}
	
	@Test
	public void testInvoke(){
		RpcRequest request = new RpcRequest();
		request.setMethodName("getSoById");
		request.setArgs(new Object[]{2L});
		RpcResponse res = (RpcResponse)exporter.invoke(request);
		So so = (So)res.getResponse();
		Assert.isTrue(so.getId().equals(2L),
				"exporter.invoke return "+ JSON.toJSONString(res)
				);
		
	}
	
	@Test
	public void testInvokeThrowException(){
		RpcRequest request = new RpcRequest();
		request.setMethodName("getSoException");
		request.setArgs(new Object[]{2L});
		RpcResponse res = exporter.invoke(request);
		Assert.isTrue(res.getException() instanceof NullPointerException);
	}

}
