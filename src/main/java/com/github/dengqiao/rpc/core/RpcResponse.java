package com.github.dengqiao.rpc.core;

import java.io.Serializable;

public class RpcResponse implements Serializable{
	
	private static final long serialVersionUID = -8668813118294924795L;

	private Object response;
	
	private Throwable exception;

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

}
