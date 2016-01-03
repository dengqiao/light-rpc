package com.github.dengqiao.rpc.client;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.codec.impl.FstCodec;
import com.github.dengqiao.rpc.example.So;
import com.github.dengqiao.rpc.example.SoService;

public class JdkRpcProxyFactoryTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClientProfile cp = new ClientProfile();
		cp.setClientAppName("test");
		cp.setServiceAppName("gos-query");
		cp.setServiceVersion("0.01");
		cp.setRpcCodec(new FstCodec());
		cp.setReadTimeout(30000);
		SoService service = 
				(SoService)JdkRpcProxyFactoryBean.create(SoService.class, cp);
		So so = service.getSoById(2L);
		System.out.println(JSON.toJSONString(so));
		
	}

}
