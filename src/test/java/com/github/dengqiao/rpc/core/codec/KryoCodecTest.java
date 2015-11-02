package com.github.dengqiao.rpc.core.codec;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.codec.impl.KryoCodec;
import com.github.dengqiao.rpc.example.SoService;

public class KryoCodecTest extends TestCase {
	
	@Test
	public void testCodec(){
		KryoCodec codec = new KryoCodec();
		RpcRequest request = new RpcRequest();
		request.setServiceFullName(SoService.class.getName());
		request.setMethodName("getSoById");
		request.setArgs(new Object[]{1L});
		
		byte[] bytes = codec.encode(request);
		RpcRequest obj = (RpcRequest)codec.decode(bytes);
		Assert.assertEquals(request.getMethodName(), obj.getMethodName());
	}
	
	@Test
	public void testCodecInput(){
		KryoCodec codec = new KryoCodec();
		RpcRequest request = new RpcRequest();
		request.setServiceFullName(SoService.class.getName());
		request.setMethodName("getSoById");
		request.setArgs(new Object[]{1L});
		
		final byte[] bytes = codec.encode(request);
		ExecutorService es =   Executors.newFixedThreadPool(1);
		Future<RpcRequest> future = es.submit(new Callable<RpcRequest>() {

			public RpcRequest call() throws Exception {
				KryoCodec codec = new KryoCodec();
				return (RpcRequest)codec.decode(bytes);
			}
		});
		try {
			RpcRequest request0 = future.get();
			Assert.assertEquals(request.getMethodName(), request0.getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("decode exception ");
		}
	}

}
