package com.bycw.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;

public class JDBCWrapper{
	
	Logger m_logger=LoggerFactory.getLogger(JDBCWrapper.class);
	
	
	
	public void TestJDBCWrapper() 
	{
		
		Connection connection=null;
		
		//1 链接数据库
		// end
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//反射？ 使用forName直接实例化了一个com.mysql.jdbc.Driver实例
		} catch (Exception e) {
			m_logger.error("TestJDBCWrapper "+e.toString());
		}
		
		try {
			//url 数据库的链接的地址 端口 参数 数据库 编码格式 时区
			// 用户名和密码
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/class_test?characterEncoding=utf8&useSSL=false&serverTimeZone=UTC","root","123456");
		} catch (Exception e) {
			m_logger.error(e.getMessage());
		}
		
		m_logger.info("Connect DataBase Success");
		//发送命令给数据库服务器 让服务器操作数据库 进行增删改查
		String sqlString="insert into user_test (uname,uscore) VALUES(\"gerson\",77777)";
		
		//增
		try {
			PreparedStatement pStatement=connection.prepareStatement(sqlString);//这个可以批量执行
			pStatement.execute();
			//执行完后还需要释放
			if (pStatement!=null) {
				pStatement.close();
			}
			
//			Statement psmStatement= connection.createStatement();
//			psmStatement.execute(sqlString);
		} catch (Exception e) {
			m_logger.error(e.getMessage());
		}
		
		//查 从哪张表里
		sqlString="select * from user_tets where id=45";
		try {
			PreparedStatement pstm=connection.prepareStatement(sqlString);
			ResultSet rSet=	pstm.executeQuery();//查询结果返回
			
			while (rSet.next()) {
				rSet.getString("uname");
				rSet.getInt("uscore");
			}
			
			//释放 rSet
			if (rSet!=null) {
				rSet.close();
			}
			//释放 pstm
			if (pstm!=null) {
				pstm.close();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//断开数据库链接
		
		if (connection!=null) {
			try {
				connection.close();
			} catch (Exception e) {
				m_logger.error(e.getMessage());
			}
		}
	}
	
	
}