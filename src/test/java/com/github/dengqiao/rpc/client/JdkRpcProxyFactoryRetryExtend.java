package com.github.dengqiao.rpc.client;

import java.net.ConnectException;

import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcRequest;

public class JdkRpcProxyFactoryRetryExtend extends JdkRpcProxyFactoryExtend {
	public static int retryCount = 0;
	
	public byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		retryCount ++;
		if(retryCount==3){
			return super.doRequest(rpcRequest,byteRequest);
		}
		throw new RpcException(new ConnectException("can not connection server"));
	}

}
