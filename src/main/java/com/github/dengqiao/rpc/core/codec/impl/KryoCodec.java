package com.github.dengqiao.rpc.core.codec.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.github.dengqiao.rpc.core.RpcRequest;
import com.github.dengqiao.rpc.core.RpcResponse;
import com.github.dengqiao.rpc.core.codec.AbstractRpcCodec;
import com.github.dengqiao.rpc.core.codec.RpcCodec;

public class KryoCodec extends AbstractRpcCodec implements RpcCodec {
	
	private static final ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>() {
		protected Kryo initialValue() {
			Kryo kryo = new Kryo();
			kryo.register(RpcRequest.class);
			kryo.register(RpcResponse.class);
			kryo.register(ArrayList.class);
			kryo.register(HashMap.class);
			kryo.register(BigDecimal.class);
			kryo.register(Object[].class);
			kryo.register(Object.class);
			kryo.register(Date.class);
			kryo.register(Long.class);
			kryo.register(Integer.class);
			kryo.setRegistrationRequired(false);
			kryo.setReferences(false);
			return kryo;
		}
	};

	@Override
	public byte[] doEncode(Object request) throws Exception {
		Output output = new Output(new ByteArrayOutputStream());
		kryos.get().writeClassAndObject(output, request);
		return output.toBytes();
	}

	@Override
	public Object doDecode(byte[] input) throws Exception {
		return doDecode(new ByteArrayInputStream(input));
	}

	@Override
	public void doEncode(Object request, OutputStream outPut) throws Exception {
		Output objectOutPut = new Output(outPut);
		kryos.get().writeClassAndObject(objectOutPut, request);
	}

	@Override
	public Object doDecode(InputStream input0) throws Exception {
		Input input = new Input(input0);
		return kryos.get().readClassAndObject(input);
	}

}
