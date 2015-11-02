package com.github.dengqiao.rpc.client;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.ZKClientHelper;

public class ServiceLocator {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceLocator.class);
	
	private CuratorFramework zkClient;
	
	private String parentPath;
	
	private volatile TreeMap<Integer,String> serviceUrlMap = new TreeMap<Integer,String>();
	
	private AtomicInteger counter = new AtomicInteger();
	
	public ServiceLocator(String parentPath){
		this(parentPath,ZKClientHelper.getZKClient());
	}
	
	public ServiceLocator(String parentPath,CuratorFramework zkClient){
		this.parentPath = parentPath;
		this.zkClient   = zkClient;
		try {
			ZKClientHelper.ensure(zkClient, parentPath);
			initServiceUrlList();
		} catch (Exception e) {
			throw new RpcException("ServiceLocator init fail parentPath "+parentPath ,e);
		}
		
	}
	
	public void initServiceUrlList() throws Exception{
		Watcher watcher = new Watcher(){
			public void process(WatchedEvent event) {
				logger.info("zk watcher receive event "+ event.toString());
				try{
					//processZKChildren(ZKClientHelper.getChildren(zkClient, parentPath));
					initServiceUrlList();
				}catch(Exception e){
					logger.error("watcher processZKChildren exception", e);
				}
			}
		};
		List<String> childrenKey = ZKClientHelper.watchedGetChildren(zkClient, parentPath, watcher);
		if(logger.isInfoEnabled()){
			logger.info("initServiceUrlList get  childrenKey "+ JSON.toJSONString(childrenKey));
		}
		processZKChildren(childrenKey);
	}
	
	public void processZKChildren(List<String> childrenKey)throws Exception{
		List<String> dataList = new ArrayList<String>();
		for(String child : childrenKey) {
			dataList.add(ZKClientHelper.getData(zkClient, parentPath+"/"+child));
		}
		updateServiceUrlList(dataList);
	}
	
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
	
}
