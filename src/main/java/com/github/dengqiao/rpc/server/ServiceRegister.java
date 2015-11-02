package com.github.dengqiao.rpc.server;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.dengqiao.rpc.core.IpUtils;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.ZKClientHelper;

public class ServiceRegister {

	private static final Logger logger = LoggerFactory
			.getLogger(ServiceRegister.class);

	private CuratorFramework zkClient;

	private String parentPath;

	private ServiceProfile serviceProfile;

	public ServiceRegister(String parentPath, ServiceProfile serviceProfile) {
		this(parentPath, serviceProfile, ZKClientHelper.getZKClient());
	}

	public ServiceRegister(String parentPath, ServiceProfile serviceProfile,
			CuratorFramework zkClient) {
		this.parentPath = parentPath;
		this.serviceProfile = serviceProfile;
		this.zkClient = zkClient;
		try {
			ZKClientHelper.ensure(zkClient, parentPath);
		} catch (Exception e) {
			throw new RpcException("ServiceLocator init fail parentPath "
					+ parentPath + " " + e.getMessage(), e);
		}
	}

	public void register() {
		zkClient.getConnectionStateListenable().addListener(
				new ConnectionStateListener() {

					public void stateChanged(CuratorFramework client,
							ConnectionState newState) {
						if (newState == ConnectionState.LOST) {
							logger.warn("ServiceRegister " + parentPath + " "
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
			ZKClientHelper.guaranteedDelete(zkClient, getChildPath());
			logger.info("ServiceRegister unRegister   " + getChildPath());
		} catch (Exception e) {
			logger.error("ServiceRegister unRegister  error " + getChildPath(),
					e);
		}
	}

	protected void createEphemeralNode() {
		try {
			if (ZKClientHelper.isExist(zkClient, getChildPath())) {
				unRegister();
			}
			ZKClientHelper.createEphemeral(zkClient, getChildPath(),
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

	protected String getChildPath() {
		StringBuilder sb = new StringBuilder();
		return sb.append(parentPath).append("/").append(IpUtils.getLocalIp())
				.append(":").append(this.serviceProfile.getServerPort())
				.toString();
	}

	protected String getChildPathValue() {
		StringBuilder sb = new StringBuilder();
		return sb.append(IpUtils.getLocalIp()).append(":")
				.append(this.serviceProfile.getServerPort()).append("/")
				.append(this.serviceProfile.getServerContextPath()).append("/")
				.append(this.serviceProfile.getUrlPrefix()).toString();
	}

	public CuratorFramework getZkClient() {
		return zkClient;
	}

	public void setZkClient(CuratorFramework zkClient) {
		this.zkClient = zkClient;
	}

	public String getParentPath() {
		return parentPath;
	}

	public void setParentPath(String parentPath) {
		this.parentPath = parentPath;
	}

}
