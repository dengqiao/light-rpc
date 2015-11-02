package com.github.dengqiao.rpc.core;

public class RpcException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8777390699360099147L;
	

	public RpcException(Throwable root) {
		super(root);
	}
	
	public RpcException(String message) {
		super(message);
	}
	
	public RpcException(String message,Throwable root) {
		super(message,root);
	}

}
