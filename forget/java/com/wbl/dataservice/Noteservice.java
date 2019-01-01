package com.wbl.dataservice;

import java.util.List;

import javax.jws.WebService;

import com.alibaba.fastjson.JSON;
import com.wbl.inter.Interservice;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * ע��
 * @author С����
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
		
		System.out.println("���ݳ���Ϊ��" + list.size());
		
		System.out.println("Noteservice is queryRoleData start...");
		
		// json ��һ����ֵ�Եĸ�ʽ
		JSONArray array = new JSONArray();
		
		for(DataBean dat:list)
		{
			JSONObject obj = new JSONObject();
			
			obj.put("rid", dat.getRid());
			obj.put("name", dat.getName());
			array.add(obj);
		}

		// ����һ��string����  tostring ���ݸ�ʽת��
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
		
		// �� fastjson ��ת����string����
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
