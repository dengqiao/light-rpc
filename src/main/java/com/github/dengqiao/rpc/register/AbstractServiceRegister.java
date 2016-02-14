package com.github.dengqiao.rpc.register;

import org.apache.commons.lang.StringUtils;

import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.utils.IpUtils;
import com.github.dengqiao.rpc.utils.ServicePathUtils;

public abstract class AbstractServiceRegister implements ServiceRegister{
	
	private ServiceProfile serviceProfile;
	
	//服务注册路径，默认基于应用的维度来注册，业界一般基于服务的维度（也就是具体到服务接口级别）来注册，基于应用维度注册服务可以降低后端存储压力，提高性能
	protected String getChildPath() {
		StringBuilder sb = new StringBuilder(100);
		return sb.append(ServicePathUtils.getServicePath(serviceProfile))
				.append("/").append(IpUtils.getLocalIp())
				.append(":").append(serviceProfile.getServerPort())
				.toString();
	}

	//服务注册路径对应的值
	protected String getChildPathValue() {
		StringBuilder sb = new StringBuilder();
		sb.append(IpUtils.getLocalIp()).append(":")
				.append(this.serviceProfile.getServerPort()).append("/");	
		if(StringUtils.isNotBlank(this.serviceProfile.getServerContextPath())){
			sb.append(this.serviceProfile.getServerContextPath()).append("/");
		}
		sb.append(this.serviceProfile.getServiceUrlPrefix()).toString();
		return sb.toString();
	}

	public ServiceProfile getServiceProfile() {
		return serviceProfile;
	}

	public void setServiceProfile(ServiceProfile serviceProfile) {
		this.serviceProfile = serviceProfile;
	}
	
}
