package com.github.dengqiao.rpc.example;


public class SoServiceImpl implements SoService {

	public So getSoById(Long id) {
		So so = So.createSo();
		so.setId(id);
		return so;
	}

	public So getSoException(Long id){
		throw new NullPointerException("getSoException throw exception");
	}

}
