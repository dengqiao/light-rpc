package com.github.dengqiao.rpc.example;

import java.util.List;

import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.codec.impl.FstCodec;
import com.github.dengqiao.rpc.server.AbstractRpcServlet;
import com.github.dengqiao.rpc.server.ServiceExporter;

public class SoRpcServlet extends AbstractRpcServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4584976078074654630L;
	
	public void registerServiceExporter(ServiceProfile sp, List<ServiceExporter> seList) throws Exception{
		ServiceExporter se = new ServiceExporter();
		se.setTarget(new SoServiceImpl());
		se.setServiceInterface(SoService.class);
		se.setServiceProfile(sp);
		se.init();
		seList.add(se);
	}
	
	public ServiceProfile  getServiceProfile(){
		ServiceProfile  sp = new ServiceProfile();
		sp.setServerContextPath("gos");
		sp.setServerPort("8080");
		sp.setServiceAppName("gos-query");
		sp.setServiceVersion("0.01");
		sp.setUrlPrefix("rpc");
		sp.setRpcCodec(new FstCodec());
		return sp;
	}

}
