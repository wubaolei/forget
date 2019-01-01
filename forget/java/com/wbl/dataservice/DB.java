package com.wbl.dataservice;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DB
{
	private Connection con;
	
	static String imgUrl = "";
	
	static
	{
		Properties p = new Properties();
		
		try
		{
			// ¶ÁÈ¡ÎÄ¼þ
			p.load(new FileReader("./src/Allurl/url.properties"));
			
			imgUrl = p.getProperty("imgUrl");
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	DB()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/life?user=root&password=&userUnicode=true&characterEncoding=UTF8";

			con = DriverManager.getConnection(url);
			
			// System.out.println(con);
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<DataBean> getDatas()
	{
		List<DataBean> list = new ArrayList<DataBean>();

		String sql = "select * from t_stus";

		try
		{
			PreparedStatement pstm = con.prepareStatement(sql);

			ResultSet rs = pstm.executeQuery();

			while (rs.next())
			{
				DataBean dab = new DataBean();

				dab.setRid(rs.getInt(1));

				dab.setName(rs.getString(2));

				list.add(dab);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if (null != con)
			{
				try
				{
					con.close();
				}
				catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return list;
	}
	
	
	
	public int getcheckuser(String username,String password)
	{
		String sql = "SELECT  COUNT(*) FROM t_user WHERE uname = ? AND upwd = ?";
		
		int count = 0;
		
		try
		{
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, username);
			pstm.setString(2, password);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}
	
	
	
	public List<DataBean> getMenuData()
	{
		String sql = "select * from t_menu";
		
		List<DataBean> list = new ArrayList<DataBean>();
		
		try
		{
			PreparedStatement pstm = con.prepareStatement(sql);
			
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next())
			{
				DataBean datab = new DataBean();
				
				datab.setTid(rs.getString(1));
				datab.setTname(rs.getString(2));
				datab.setTurl(rs.getString(3));
				datab.setTstats(rs.getString(4));
				datab.setImgpath(imgUrl+rs.getString(5));
				
				list.add(datab);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	public List<DataBean> getTwoData()
	{
		String sql = "SELECT amonthx,amonthy FROM t_antv";
		List<DataBean> list = new ArrayList<DataBean>();
		
		try
		{
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next())
			{
				DataBean datab = new DataBean();
				datab.setDatax(rs.getString(1));
				datab.setValue(rs.getInt(2));
				
				list.add(datab);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public List<DataBean> getThreeData()
	{
		String sql = "SELECT anamex,amonthx,amonthy FROM t_antv";
		
		List<DataBean> list = new ArrayList<DataBean>();
		
		try
		{
			PreparedStatement pstm = con.prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			while(rs.next())
			{
				DataBean datab = new DataBean();
				
				datab.setDataname(rs.getString(1));
				datab.setAmonthx(rs.getString(2));
				datab.setAmonthy(rs.getInt(3));
				
				list.add(datab);
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
}
