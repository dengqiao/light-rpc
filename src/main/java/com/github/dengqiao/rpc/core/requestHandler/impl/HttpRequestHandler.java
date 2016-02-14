package com.github.dengqiao.rpc.core.requestHandler.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.RpcException;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.requestHandler.RequestHandler;
import com.github.dengqiao.rpc.utils.ServicePathUtils;

public class HttpRequestHandler implements RequestHandler {

	public byte[] doRequest(RpcRequest rpcRequest, byte[] byteRequest,
			ClientProfile clientProfile, String serviceUrl) {
		if(!serviceUrl.startsWith("http")){
			serviceUrl = "http://" + serviceUrl;
		}
		serviceUrl = serviceUrl + "/" + ServicePathUtils.getServiceName(rpcRequest.getServiceFullName());
		HttpURLConnection httpConn = sendRequest(rpcRequest, byteRequest,
				clientProfile, serviceUrl);
		return getResponse(httpConn, rpcRequest, byteRequest, clientProfile);
	}

	protected HttpURLConnection sendRequest(RpcRequest rpcRequest,
			byte[] byteRequest, ClientProfile clientProfile, String serviceUrl) {

		HttpURLConnection httpConn = null;

		try {
			URL urlObj = new URL(serviceUrl);
			httpConn = (HttpURLConnection) urlObj.openConnection();
			int timeout = 3000;
			if (clientProfile!= null && clientProfile.getReadTimeout() > 0) {
				timeout = clientProfile.getReadTimeout();
			}
			httpConn.setReadTimeout(timeout);
			httpConn.setConnectTimeout(timeout);
			httpConn.setDoOutput(true);
			httpConn.setRequestMethod("POST");
			httpConn.getOutputStream().write(byteRequest);
			httpConn.getOutputStream().flush();

		} catch (Exception e) {
			throw new RpcException("open url Connection exception "
					+ serviceUrl, e);
		}
		return httpConn;
	}

	protected byte[] getResponse(HttpURLConnection httpConn,
			RpcRequest rpcRequest, byte[] byteRequest,
			ClientProfile clientProfile) {
		int code = 500;
		try {
			code = httpConn.getResponseCode();
		} catch (Exception e) {
			throw new RpcException("getResponseCode  exception ", e);
		}
		InputStream is = null;
		if (code != 200) {
			String content = null;
			try {
				is = httpConn.getInputStream();
				if (is != null) {
					content = IOUtils.toString(is);
				}
				is = httpConn.getErrorStream();
				if (is != null) {
					content = IOUtils.toString(is);
				}
			} catch (FileNotFoundException e) {
				throw new RpcException(String.valueOf(e));
			} catch (IOException e) {
				if (is == null)
					throw new RpcException(code + ": " + e, e);
			}
			if (is != null) {
				IOUtils.closeQuietly(is);
			}
			throw new RpcException(code + ": " + content);
		}
		try {
			is = httpConn.getInputStream();
		} catch (IOException e) {
			if (is == null)
				throw new RpcException(code + ": " + e, e);
		}
		try {
			return IOUtils.toByteArray(is);
		} catch (IOException e) {
			throw new RpcException("read InputStream exception ", e);
		} finally {
			if (is != null) {
				IOUtils.closeQuietly(is);
			}
		}
	}
	
	public static void main(String[] args){
		try {
			System.out.println(new String(new HttpRequestHandler().
					doRequest(null,new byte[0],null,"http://fanyi.youdao.com/"),"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
