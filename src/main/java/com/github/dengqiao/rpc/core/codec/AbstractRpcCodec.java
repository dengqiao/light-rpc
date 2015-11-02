package com.github.dengqiao.rpc.core.codec;

import java.io.InputStream;
import java.io.OutputStream;

import com.github.dengqiao.rpc.core.RpcException;

public abstract class AbstractRpcCodec implements RpcCodec {
	
	public byte[] encode(Object request) throws RpcException {
		try {
			return doEncode(request);
		}catch(Exception e){
			throw new RpcException(this.getClass() +" encode exception ",e);
		}
	}
	
	public Object decode(byte[] input) throws RpcException {
		try {
			return doDecode(input);
		}catch(Exception e){
			throw new RpcException(this.getClass() +" decode exception ",e);
		}
	}
	
	public void encode(Object request,OutputStream outPut) throws RpcException {
		try {
			doEncode(request,outPut);
		}catch(Exception e){
			throw new RpcException(this.getClass() +" encode exception ",e);
		}
	}
	
	public Object decode(InputStream is)throws RpcException{
		try {
			return doDecode(is);
		}catch(Exception e){
			throw new RpcException(this.getClass() +" decode exception ",e);
		}
	}
	
	public abstract byte[] doEncode(Object request) throws Exception ;
	
	public abstract Object doDecode(byte[] input) throws Exception ;
	
	public abstract void doEncode(Object request,OutputStream outPut) throws Exception ;
	
	public abstract Object doDecode(InputStream input) throws Exception ;

}
