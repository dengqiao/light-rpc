package com.github.dengqiao.rpc.register.impl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.register.AbstractServiceRegister;
import com.github.dengqiao.rpc.utils.ServicePathUtils;
import com.github.dengqiao.rpc.utils.ZKClientUtils;

public class ZkServiceRegister extends AbstractServiceRegister implements InitializingBean{

	private static final Logger logger = LoggerFactory
			.getLogger(ZkServiceRegister.class);

	private CuratorFramework zkClient;
	
	private String zkConnStr;
	
	//是否初始化后自动注册,如果需要控制在web容器启动完成后在进行服务注册需要设置为false并且手动调用register方法
	private boolean autoRegister = true;
	
	public void afterPropertiesSet() throws Exception {
		zkClient = ZKClientUtils.getZKClient(zkConnStr);
		try {
			ZKClientUtils.ensure(zkClient, ServicePathUtils.getServicePath(this.getServiceProfile()));
		} catch (Exception e) {
			throw new RpcException("ServiceLocator init fail parentPath "
					+ ServicePathUtils.getServicePath(this.getServiceProfile()) + " " + e.getMessage(), e);
		}
		if(autoRegister){
			register();
		}
	}

	public  void  register() {
		zkClient.getConnectionStateListenable().addListener(
				new ConnectionStateListener() {

					public void stateChanged(CuratorFramework client,
							ConnectionState newState) {
						if (newState == ConnectionState.LOST) {
							logger.warn("ServiceRegister " + ServicePathUtils.getServicePath(ZkServiceRegister.this.getServiceProfile()) + " "
									+ newState);
							while (true) {
								try {
									if (client.getZookeeperClient()
											.blockUntilConnectedOrTimedOut()) {
										createEphemeralNode();
									}
								} catch (InterruptedException e) {
									e.printStackTrace();
									break;
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}
					}
				});
		createEphemeralNode();
	}

	public void unRegister() {
		try {
			ZKClientUtils.guaranteedDelete(zkClient, getChildPath());
			logger.info("ServiceRegister unRegister   " + getChildPath());
		} catch (Exception e) {
			logger.error("ServiceRegister unRegister  error " + getChildPath(),
					e);
		}
	}

	protected void createEphemeralNode() {
		try {
			if (ZKClientUtils.isExist(zkClient, getChildPath())) {
				unRegister();
			}
			ZKClientUtils.createEphemeral(zkClient, getChildPath(),
					getChildPathValue().getBytes());
			if (logger.isInfoEnabled()) {
				logger.info("ServiceRegister  register  path " + getChildPath()
						+ " value " + getChildPathValue());
			}
		} catch (Exception e) {
			logger.error("ServiceRegister createEphemeralNode error "
					+ getChildPath(), e);
		}

	}

	public CuratorFramework getZkClient() {
		return zkClient;
	}

	public String getZkConnStr() {
		return zkConnStr;
	}

	public void setZkConnStr(String zkConnStr) {
		this.zkConnStr = zkConnStr;
	}

	public boolean isAutoRegister() {
		return autoRegister;
	}

	public void setAutoRegister(boolean autoRegister) {
		this.autoRegister = autoRegister;
	}
}
