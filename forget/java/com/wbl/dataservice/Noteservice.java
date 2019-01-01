package com.wbl.dataservice;

import java.util.List;

import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.wbl.inter.Interservice;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 注解
 * @author 小赖赖
 */

@WebService
(portName="userservice",
serviceName="Noteservice",
targetNamespace="http://thzm.com/wsdl",
endpointInterface="com.wbl.inter.Interservice")
public class Noteservice implements Interservice
{
	@Override
	public String queryRoleData()
	{
		// TODO Auto-generated method stub
		DB db = new DB();
		
		List<DataBean> list = db.getDatas();
		
		System.out.println("数据长度为：" + list.size());
		
		System.out.println("Noteservice is queryRoleData start...");
		
		// json 是一个键值对的格式
		JSONArray array = new JSONArray();
		
		for(DataBean dat:list)
		{
			JSONObject obj = new JSONObject();
			
			obj.put("rid", dat.getRid());
			obj.put("name", dat.getName());
			array.add(obj);
		}

		// 返回一个string类型  tostring 数据格式转换
		return array.toString();
	}

	@Override
	public int checkuser(String username, String password)
	{
		// TODO Auto-generated method stub
		DB db = new DB();
		
		int count = db.getcheckuser(username, password);
		
		return count;
	}

	@Override
	public String queryMenuData()
	{
		// TODO Auto-generated method stub
		DB db = new DB();
		
		List<DataBean> list = db.getMenuData();
		
		// 用 fastjson 并转换成string类型
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
		
		return strJson;
	}

	
	
	@Override
	public String queryTwoData()
	{
		// TODO Auto-generated method stub
		
		DB db = new DB();
		List<DataBean> list = db.getTwoData();
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
		
		return strJson;
	}

	@Override
	public String queryThreeData()
	{
		// TODO Auto-generated method stub
		DB db = new DB();
		List<DataBean> list = db.getThreeData();
		String strJson = com.alibaba.fastjson.JSONArray.toJSONString(list);
		return strJson;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
