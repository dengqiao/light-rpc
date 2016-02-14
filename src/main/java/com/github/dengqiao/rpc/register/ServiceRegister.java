package com.github.dengqiao.rpc.register;

import com.github.dengqiao.rpc.core.ServiceProfile;

public  interface ServiceRegister {
	
	public void register() ;
	
	public void unRegister();
	
	public void setServiceProfile(ServiceProfile serviceProfile);

}
