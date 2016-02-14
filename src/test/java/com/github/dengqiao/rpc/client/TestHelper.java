package com.github.dengqiao.rpc.client;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.codec.impl.FstCodec;

public class TestHelper {
	
	public static ClientProfile getClientProfile(){
		ClientProfile clientProfile = new ClientProfile();
		clientProfile.setServiceAppName("user");
		clientProfile.setServiceVersion("0.01");
		clientProfile.setRpcCodec(new FstCodec());
		clientProfile.setClientAppName("test");
		return clientProfile;
	}
	
	public static ServiceProfile getServiceProfile(){
		ServiceProfile  sp = new ServiceProfile();
		sp.setServiceAppName("user");
		sp.setServiceVersion("0.01");
		sp.setRpcCodec(new FstCodec());
		sp.setServerContextPath("user");
		sp.setServerPort("8080");
		sp.setServiceUrlPrefix("rpc");
		return sp;
	}

}
