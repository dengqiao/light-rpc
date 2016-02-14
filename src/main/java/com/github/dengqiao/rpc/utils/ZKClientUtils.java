package com.github.dengqiao.rpc.utils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.EnsurePath;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class ZKClientUtils {
	private static final Logger logger = LoggerFactory.getLogger(ZKClientUtils.class);
	private static volatile CuratorFramework _zkClient = null;
	private static Object lock = new Object();
	
	public static CuratorFramework getZKClient(String zkConnStr){
		if( _zkClient == null){
			synchronized (lock) {
				if( _zkClient != null){
					return _zkClient;
				}
				if(!StringUtils.hasLength(zkConnStr)){
					zkConnStr = System.getProperty("zkConnStr");
				}
				_zkClient =  getZKClient0(zkConnStr);
			}
		}
		return _zkClient;
	}
	
	private static CuratorFramework getZKClient0(String zkConnStr){
		if (!StringUtils.hasLength(zkConnStr)){
			throw new RuntimeException("can not found zkConnStr from env!!!!!!!!!!!!!!");
		}
		// these are reasonable arguments for the ExponentialBackoffRetry. 
		// The first retry will wait 1 second - the second will wait up to 2 seconds - the
		// third will wait up to 4 seconds.
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
		// The simplest way to get a CuratorFramework instance. This will use default values.
		// The only required arguments are the connection string and the retry policy
		CuratorFramework client =  CuratorFrameworkFactory.newClient(zkConnStr, retryPolicy);
		if (client == null) {
			throw new RuntimeException("can not create CuratorFramework zkConnStr "+ zkConnStr +" !!!!!!!!!!");
		}
		try{
			client.start();
			boolean ok = client.blockUntilConnected(5, TimeUnit.SECONDS);
			if(!ok) {
				logger.error("zk server "+ zkConnStr  +" connected fail !!!");
			}
		}catch(Exception e){
			logger.error("zk client init fail ",e);
		}
		
		return client;
		
	}
	
	public static void closeZkClient(CuratorFramework client){
		CloseableUtils.closeQuietly(client);
	}

	public static void create(CuratorFramework client, String path,
			byte[] payload) throws Exception {
		// this will create the given ZNode with the given data
		client.create().forPath(path, payload);
	}

	public static void createEphemeral(CuratorFramework client, String path,
			byte[] payload) throws Exception {
		// this will create the given EPHEMERAL ZNode with the given data
		client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
	}

	public static String createEphemeralSequential(CuratorFramework client,
			String path, byte[] payload) throws Exception {
		// this will create the given EPHEMERAL-SEQUENTIAL ZNode with the given
		// data using Curator protection.
		return client.create().withProtection()
				.withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
				.forPath(path, payload);
	}

	public static void setData(CuratorFramework client, String path,
			byte[] payload) throws Exception {
		// set data for the given node
		client.setData().forPath(path, payload);
	}
	
	public static String getData(CuratorFramework client, String path)throws Exception{
		return new String(client.getData().forPath(path),"UTF-8");
	}

	public static void setDataAsync(CuratorFramework client, String path,
			byte[] payload, CuratorListener listener) throws Exception {
		client.getCuratorListenable().addListener(listener);
		// set data for the given node asynchronously. The completion
		// notification
		// is done via the CuratorListener.
		client.setData().inBackground().forPath(path, payload);
	}

	public static void setDataAsyncWithCallback(CuratorFramework client,
			BackgroundCallback callback, String path, byte[] payload)
			throws Exception {
		// this is another method of getting notification of an async completion
		client.setData().inBackground(callback).forPath(path, payload);
	}

	public static void delete(CuratorFramework client, String path)
			throws Exception {
		// delete the given node
		client.delete().forPath(path);
	}

	public static void guaranteedDelete(CuratorFramework client, String path)
			throws Exception {
		// delete the given node and guarantee that it completes
		client.delete().guaranteed().forPath(path);
	}

	public static List<String> watchedGetChildren(CuratorFramework client,
			String path,CuratorListener listener) throws Exception {
		client.getCuratorListenable().addListener(listener);
		/**
		 * Get children and set a watcher on the node. The watcher notification
		 * will come through the CuratorListener (see setDataAsync() above).
		 */
		return client.getChildren().watched().forPath(path);
	}

	public static List<String> watchedGetChildren(CuratorFramework client,
			String path, Watcher watcher) throws Exception {
		/**
		 * Get children and set the given watcher on the node.
		 */
		return client.getChildren().usingWatcher(watcher).forPath(path);
	}
	
	public static List<String> getChildren(CuratorFramework client,String path)throws Exception{
		return client.getChildren().forPath(path);
	}
	
	public static boolean isExist(CuratorFramework client,String path)throws Exception {
		return client.checkExists().forPath(path) != null;
	}
	
	public static void ensure(CuratorFramework client,String path)throws Exception {
		EnsurePath ensurePath = client.newNamespaceAwareEnsurePath(path);
		ensurePath.ensure(client.getZookeeperClient());
	}
	
	public static void clearZkClient(){
		if(_zkClient!=null){
			_zkClient.close();
		}
		_zkClient = null;
	}

}
