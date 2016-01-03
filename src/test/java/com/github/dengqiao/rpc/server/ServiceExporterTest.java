package com.github.dengqiao.rpc.server;

import junit.framework.TestCase;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.test.TestingServer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.ZKClientHelper;
import com.github.dengqiao.rpc.core.codec.impl.HessianCodec;
import com.github.dengqiao.rpc.example.So;
import com.github.dengqiao.rpc.example.SoService;
import com.github.dengqiao.rpc.example.SoServiceImpl;

public class ServiceExporterTest extends TestCase {
	
	private TestingServer server;
	private ServiceRegister sr;
	private CuratorFramework zkClient;
	private String path = "/test/userService";
	
	private ServiceExporter exporter;
	
	@BeforeClass
	public void setUp() throws Exception{
		
		exporter = new ServiceExporter();
		server = new TestingServer();
		server.start();
		zkClient = ZKClientHelper.getZKClient(server.getConnectString());
		ServiceProfile  sp = new ServiceProfile();
		sp.setServerContextPath("gos");
		sp.setServerPort("8080");
		sp.setServerContextPath("gos-query");
		sp.setRpcCodec(new HessianCodec());
		sr = new ServiceRegister(path, sp,zkClient);
		exporter.setTarget(new SoServiceImpl());
		exporter.setServiceInterface(SoService.class);
		exporter.setServiceProfile(sp);
		exporter.setServiceRegister(sr);
		exporter.initMethodMap();
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
