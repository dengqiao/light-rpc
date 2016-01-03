package com.github.dengqiao.rpc.core.codec;

import java.util.Date;
import java.util.UUID;

import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.codec.impl.HessianCodec;
import com.github.dengqiao.rpc.core.codec.impl.JavaCodec;
import com.github.dengqiao.rpc.example.So;

public class CodecTest {

	public static void main(String[] args) {
		
		RpcRequest request = new RpcRequest();
		request.setServiceFullName("userService");
		request.setRequestId(UUID.randomUUID().toString());
		request.setRequestTime(new Date());
		request.setMethodName("getUserById");
		request.setClientAppName("3rd/b2b2c");
		request.setArgs(new Object[]{So.createSo()});
		System.out.println("RpcRequest with so args test");
		count(10,request);
		count(100000,request);
	}
	
	private static void count(int count,RpcRequest request){
		hessian(count,request);
		java(count,request);
	}
	
	private static void hessian(int count,RpcRequest request) {
		try{
			long start = System.currentTimeMillis();
			HessianCodec hessionCodec = new HessianCodec();
			int length = 0;
			for(int i=0;i<count;i++){
				length = hessionCodec.encode(request).length ;
			}
			System.out.println("hessian cost "+(System.currentTimeMillis() - start)+" ,byte length "+length+ " ,count "+count);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void java(int count,RpcRequest request) {
		try{
			long start = System.currentTimeMillis();
			JavaCodec javaCodec = new JavaCodec();
			int length = 0;
			for(int i=0;i<count;i++){
				length = javaCodec.encode(request).length ;
			}
			System.out.println("javaCodec cost "+(System.currentTimeMillis() - start)+" ,byte length "+length+ " ,count "+count);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
