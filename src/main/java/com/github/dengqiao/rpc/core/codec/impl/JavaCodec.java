package com.github.dengqiao.rpc.core.codec.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import com.github.dengqiao.rpc.core.codec.AbstractRpcCodec;
import com.github.dengqiao.rpc.core.codec.RpcCodec;

public class JavaCodec extends AbstractRpcCodec implements RpcCodec {

	public byte[] doEncode(Object request) throws Exception {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		ObjectOutputStream output = new ObjectOutputStream(byteArray);
		output.writeObject(request);
		output.flush();
		output.close();
		return byteArray.toByteArray(); 
	}

	public Object doDecode(byte[] input) throws Exception {
		ObjectInputStream objectIn = new ObjectInputStream(new ByteArrayInputStream(input));
		Object resultObject = objectIn.readObject();
		objectIn.close();
		return resultObject;
	}
	
	public Object doDecode(InputStream input) throws Exception {
		ObjectInputStream objectIn = new ObjectInputStream(input);
		Object resultObject = objectIn.readObject();
		objectIn.close();
		return resultObject;
	}

	@Override
	public void doEncode(Object request, OutputStream outPut) throws Exception {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(outPut);
		objectOutputStream.writeObject(request);
		objectOutputStream.flush();
		objectOutputStream.close(); 
	}

}
