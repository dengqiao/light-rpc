package com.github.dengqiao.rpc.register.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.zookeeper.KeeperException.NoNodeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.register.AbstractServiceRegister;
import com.github.dengqiao.rpc.utils.ServicePathUtils;
import com.github.dengqiao.rpc.utils.ZKClientUtils;

public class ZkServiceRegister extends AbstractServiceRegister{

	private static final Logger logger = LoggerFactory
			.getLogger(ZkServiceRegister.class);

	private CuratorFramework zkClient;
	
	private String zkConnStr;
	
	//是否初始化后自动注册,如果需要控制在web容器启动完成后在进行服务注册需要设置为false并且手动调用register方法
	private boolean autoRegister = true;
	
	private boolean init = false;
	
	public void init(){
		if(init){
			return;
		}
		if( StringUtils.isEmpty(zkConnStr )){
			zkConnStr = System.getProperty("zkConnStr");
		}
		if( StringUtils.isEmpty(zkConnStr )){
			throw new RpcException("ServiceLocator init fail not found zkConnStr from jvm arg -DzkConnStr");
		}
		zkClient = ZKClientUtils.getZKClient(zkConnStr);
		try {
			//从zk中获取服务分组名称
			initGroupName();
			ZKClientUtils.ensure(zkClient, ServicePathUtils.getServicePath(this.getServiceProfile()));
		} catch (Exception e) {
			throw new RpcException("ServiceLocator init fail parentPath "
					+ ServicePathUtils.getServicePath(this.getServiceProfile()) + " " + e.getMessage(), e);
		}
		init = true;
		if(autoRegister){
			register();
		}
	}
	
	private void initGroupName()throws Exception {
		String groupNameZkPath = ServicePathUtils.getServerGroupName(this.getServiceProfile());
		try {
			//从zk中获取服务分组名称
			String groupName = ZKClientUtils.getData(zkClient, groupNameZkPath);
			if(StringUtils.isNotEmpty(groupName)){
				this.getServiceProfile().setGroupName(groupName);
			}
		}catch(NoNodeException e){
			logger.info("groupNameZkPath "+ groupNameZkPath +" not exist");
		}
	}

	public  void  register() {
		if (!init){
			throw new RpcException("ServiceLocator not init ,can not register");
		}
		zkClient.getConnectionStateListenable().addListener(
				new ConnectionStateListener() {

					public void stateChanged(CuratorFramework client,
							ConnectionState newState) {
						if (newState == ConnectionState.LOST) {
							logger.error("ServiceRegister " + ServicePathUtils.getServicePath(ZkServiceRegister.this.getServiceProfile()) + " "
									+ newState);
							try {
								if (client.getZookeeperClient()
										.blockUntilConnectedOrTimedOut()) {
									logger.error("ServiceRegister  from listener "+getChildPath());
									createEphemeralNode();
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (Exception e) {
								e.printStackTrace();
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
