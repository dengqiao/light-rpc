package com.github.dengqiao.rpc.core;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

public class IpUtils {
	private static final Logger log = Logger.getLogger(IpUtils.class);
	
	private static String serverIp = "";
	
	//dengqiao 2015-08-20 update local ip cache
	static {
		try {
			serverIp = "" + getLocalIPList().get(0);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	/**
	 * 获取本地ip
	 * @return 本地ip
	 */
	public static String getLocalIp() {
		return serverIp;
	}
	
	private static List<String> getLocalIPList() {
		List<String> res1 = new ArrayList<String>();
		try {
			Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
			
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ids = ni.getInetAddresses();
				while (ids.hasMoreElements()) {
					InetAddress ip = ids.nextElement();
					if(! (ip.isLoopbackAddress() || (ip.getHostAddress().indexOf(':') >= 0) ))
					{
						res1.add(ip.getHostAddress());
					}
				}
			}
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
		return res1;
	}
	
}
