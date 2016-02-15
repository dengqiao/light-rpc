package com.github.dengqiao.rpc.utils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class ExceptionUtils {
	
	public static boolean isNetworkException(Throwable exception) {
		if(exception.getCause() == null){
			return false;
		}
		if(exception.getCause() instanceof ConnectException){
			return true;
		}
		if(exception.getCause() instanceof SocketException){
			return true;
		}
		if(exception.getCause() instanceof SocketTimeoutException){
			return true;
		}
		if(exception.getCause() instanceof IOException){
			return true;
		}
		return false;
	}

}
