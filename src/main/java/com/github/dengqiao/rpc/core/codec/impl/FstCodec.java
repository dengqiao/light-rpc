package com.github.dengqiao.rpc.core.codec.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.github.dengqiao.rpc.core.codec.AbstractRpcCodec;
import com.github.dengqiao.rpc.core.codec.RpcCodec;

import de.ruedigermoeller.serialization.FSTConfiguration;
import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.FSTObjectOutput;

public class FstCodec extends AbstractRpcCodec implements RpcCodec {

	static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();
	
	@Override
	public byte[] doEncode(Object request) throws Exception {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		doEncode(request,stream);
		return stream.toByteArray();
	}

	@Override
	public Object doDecode(byte[] input) throws Exception {
		return doDecode(new ByteArrayInputStream(input));
	}

	@Override
	public void doEncode(Object request, OutputStream outPut) throws Exception {
		FSTObjectOutput out = conf.getObjectOutput(outPut);
		out.writeObject(request);
	}

	@Override
	public Object doDecode(InputStream input) throws Exception {
		FSTObjectInput in = conf.getObjectInput(input);
		return in.readObject();
	}

}
