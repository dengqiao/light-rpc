package com.github.dengqiao.rpc.locate;

import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.locate.impl.ZkServiceLocator;


/**
 * @author dengqiao
 * 服务定位抽象基类，随机调度算法
 */
public abstract class ServiceLocator implements InitializingBean{

	private static final Logger logger = LoggerFactory.getLogger(ZkServiceLocator.class);
	
	private ClientProfile  clientProfile;
	
	private volatile TreeMap<Integer,String> serviceUrlMap = new TreeMap<Integer,String>();
	
	private AtomicInteger counter = new AtomicInteger();
	
	
	//具体实现类必须监听服务实现变化事件，然后调用此方法更新服务列表
	public synchronized void  updateServiceUrlList(List<String> dataList){
		if(logger.isInfoEnabled()){
			logger.info("updateServiceUrlList "+ JSON.toJSONString(dataList));
		}
		TreeMap<Integer,String> serviceUrlMap = new TreeMap<Integer,String>();
		for(int i = 0;i<dataList.size();i++){
			serviceUrlMap.put(i+1, dataList.get(i));
		}
		this.serviceUrlMap = serviceUrlMap;
	}
	
	
	public String select(){
		int key = counter.getAndIncrement();
		TreeMap<Integer,String> serviceUrlMap0 = serviceUrlMap;
		int totalSize = serviceUrlMap.size();
		if (totalSize ==0){
			return null;
		}
		int realPos = key % totalSize + 1;
		if (key > Integer.MAX_VALUE / 2) {
			counter.set(0);
		}
		return serviceUrlMap0.get(realPos);
	}

	public TreeMap<Integer, String> getServiceUrlMap() {
		return serviceUrlMap;
	}

	public void setServiceUrlMap(TreeMap<Integer, String> serviceUrlMap) {
		this.serviceUrlMap = serviceUrlMap;
	}


	public ClientProfile getClientProfile() {
		return clientProfile;
	}


	public void setClientProfile(ClientProfile clientProfile) {
		this.clientProfile = clientProfile;
	}
	
}
