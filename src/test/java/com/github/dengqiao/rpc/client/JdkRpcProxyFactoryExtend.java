package com.github.dengqiao.rpc.client;

import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.example.So;

public class JdkRpcProxyFactoryExtend extends JdkRpcProxyFactoryBean {
	
	public byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest){
		RpcResponse response = new RpcResponse();
		So so = So.createSo();
		Long id = (Long)rpcRequest.getArgs()[0];
		so.setId(id);
		response.setResponse(so);
		return this.getClientProfile().getRpcCodec().encode(response);
		
	}

}
