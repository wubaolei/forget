package com.wbl.inter;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://thzm.com/wsdl")
public interface Interservice
{
	@WebMethod
	public String queryRoleData();
	
	@WebMethod
	public int checkuser(String username,String password);
	
	@WebMethod
	public String queryMenuData();
	
	
	
	@WebMethod
	public String queryTwoData();
	
	
	@WebMethod
	public String queryThreeData();
}
