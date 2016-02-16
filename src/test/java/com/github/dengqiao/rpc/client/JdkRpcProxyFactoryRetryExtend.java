package com.github.dengqiao.rpc.client;

import java.net.ConnectException;

import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.example.So;

public class JdkRpcProxyFactoryRetryExtend extends JdkRpcProxyFactoryExtend {
	public static int reqCount = 0;
	
	public byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		reqCount ++;
		if(reqCount==3){
			RpcResponse response = new RpcResponse();
			So so = So.createSo();
			Long id = (Long)rpcRequest.getArgs()[0];
			so.setId(id);
			response.setResponse(so);
			return this.getClientProfile().getRpcCodec().encode(response);
		}
		throw new RpcException(new ConnectException("can not connection server"));
	}

}
