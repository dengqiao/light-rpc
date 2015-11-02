package com.github.dengqiao.rpc.core.requestHandler;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.RpcRequest;

public interface RequestHandler {
	
	public byte[] doRequest(RpcRequest rpcRequest,byte[] byteRequest,ClientProfile clientProfile,String serviceUrl);

}
