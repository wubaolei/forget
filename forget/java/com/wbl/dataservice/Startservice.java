package com.wbl.dataservice;

import javax.jws.soap.SOAPBinding;
import javax.xml.ws.Endpoint;

public class Startservice
{
	// 使用webservice同意发布数据访问接口中间件
	@SOAPBinding(style=SOAPBinding.Style.RPC)
	
	public static void main(String[] args)
	{
		String http = "http://127.0.0.1:8989/userdataservice/user";
		
		Noteservice implementor = new Noteservice();
		
		Endpoint.publish(http, implementor);
		
		System.out.println("Startservice 服务发布。。。");
	}
}
