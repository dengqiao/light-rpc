### light-rpc
light-rpc是一个java分布式rpc框架，基于zookeeper进行进行服务注册和发现，方便扩展，支持多种序列化协议，如java原生，hessian,FST等。
默认使用http进行传输协议，数据传输协议可自由扩展。

### 安装与运示例代码
安装步骤<br>
 - 下载light-rpc
```
     git clone https://github.com/dengqiao/light-rpc.git
```
 - 导入下载的maven项目代码到eclipse中<br>
 
 - 扩展AbstractRpcServlet
```
    package com.github.dengqiao.rpc.example;

    import java.util.List;
    
    import com.github.dengqiao.rpc.core.ServiceProfile;
    import com.github.dengqiao.rpc.core.codec.impl.FstCodec;
    import com.github.dengqiao.rpc.server.AbstractRpcServlet;
    import com.github.dengqiao.rpc.server.ServiceExporter;
    
    public class SoRpcServlet extends AbstractRpcServlet {
      //注册ServiceExporter到seList，该例子使用java直接初始化对象，
         也可以实现从spring容器中获取ServiceExporter Bean
    	public void registerServiceExporter(ServiceProfile sp, 
    	           List<ServiceExporter> seList) throws Exception{
    		ServiceExporter se = new ServiceExporter();
    		se.setTarget(new SoServiceImpl());
    		se.setServiceInterface(SoService.class);
    		se.setServiceProfile(sp);
    		se.init();
    		seList.add(se);
    	}
    	//返回rpc服务配置信息
    	public ServiceProfile  getServiceProfile(){
    		ServiceProfile  sp = new ServiceProfile();
    		//设置tomcat等容器的应用相对路径
    		sp.setServerContextPath("gos");
    		//设置tomcat等容器的网络端口
    		sp.setServerPort("8080");
    		//设置应用名称，用于服务注册时zookeeper路径
    		sp.setServiceAppName("gos-query");
    		//设置web.xml中AbstractRpcServlet请求的url前缀
    		sp.setUrlPrefix("rpc");
    		//设置服务端序列化实现
    		sp.setRpcCodec(new FstCodec());
    		return sp;
    	}
    
    }
```
- 启动zookeeper服务<br>
- mvn package打包项目代码，发布到tomcat中，注意ServiceProfile 中ServerContextPath配置正确，
   并且添加jvm 参数-DzkConnStr对应的zookeeper连接信息到tomcat启动参数中
  
-客服端代码示例如下
```
     package com.github.dengqiao.rpc.client;

import com.alibaba.fastjson.JSON;
import com.github.dengqiao.rpc.core.ClientProfile;
import com.github.dengqiao.rpc.core.ServiceProfile;
import com.github.dengqiao.rpc.core.codec.impl.FstCodec;
import com.github.dengqiao.rpc.example.So;
import com.github.dengqiao.rpc.example.SoService;

public class JdkRpcProxyFactoryTest {

	public static void main(String[] args) {
	 //同服务端配置
		ServiceProfile  sp = new ServiceProfile();
		sp.setServerContextPath("gos");
		sp.setServerPort("8080");
		sp.setServiceAppName("gos-query");
		sp.setRpcCodec(new FstCodec());
		
		ClientProfile cp = new ClientProfile();
		cp.setClientAppName("test");
		cp.setReadTimeout(30000);
		SoService service = 
				(SoService)JdkRpcProxyFactoryBean.create(SoService.class, cp, sp);
		So so = service.getSoById(2L);
		System.out.println(JSON.toJSONString(so));
		
	}

}
```
注意在运行此客户端代码时也需要添加jvm 参数-DzkConnStr对应的zookeeper连接信息






