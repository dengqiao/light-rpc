package com.github.dengqiao.rpc.core.codec.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.github.dengqiao.rpc.core.codec.AbstractRpcCodec;
import com.github.dengqiao.rpc.core.codec.RpcCodec;

public class HessianCodec extends AbstractRpcCodec implements RpcCodec {

	public byte[] doEncode(Object request) throws Exception{
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		Hessian2Output output = new Hessian2Output(byteArray);
		output.writeObject(request);
		output.close();
		return byteArray.toByteArray();
	}

	public Object doDecode(byte[] bytes) throws Exception{
		Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(bytes));
		Object resultObject = input.readObject();
		input.close();
		return resultObject;
	}
	
	public Object doDecode(InputStream input) throws Exception{
		Hessian2Input hessian2Input = new Hessian2Input(input);
		Object resultObject = hessian2Input.readObject();
		hessian2Input.close();
		return resultObject;
	}

	@Override
	public void doEncode(Object request, OutputStream outPut) throws Exception {
		Hessian2Output objectOutput = new Hessian2Output(outPut);
		objectOutput.writeObject(request);
		objectOutput.close();
	}

}
