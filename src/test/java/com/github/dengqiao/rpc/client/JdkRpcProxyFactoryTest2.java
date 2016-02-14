package com.github.dengqiao.rpc.client;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.codec.impl.FstCodec;
import com.github.dengqiao.rpc.example.So;
import com.github.dengqiao.rpc.example.SoService;
import com.github.dengqiao.rpc.locate.ServiceLocator;
import com.github.dengqiao.rpc.locate.impl.ZkServiceLocator;

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
		ServiceLocator sl = new ZkServiceLocator();
		sl.setClientProfile(TestHelper.getClientProfile());
		SoService service = 
				(SoService)JdkRpcProxyFactoryBean.create(SoService.class, sl);
		So so = service.getSoById(2L);
		System.out.println(JSON.toJSONString(so));
		
	}

}
