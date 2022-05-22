package com.bycw;

import com.bycw.logtest.TestLogger;
import com.bycw.netty.NettySocketServer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bycw.database.JDBCWrapper;
import com.bycw.database.RedisCluster;
import com.bycw.http.HttpCommandManager;
import com.bycw.jobs.CronSchedulerHelper;
import com.bycw.jprotobuf.JProtoBufWrapper;
import com.bytw.anotation.TestAnnotation;
import com.bytw.reflect.Reflect;
import com.bytw.utils.*;


public class Main {
	
	static Logger m_logger=LoggerFactory.getLogger(Main.class);
	public static void main(String args[]) {
		
		
		
		// loggerTest.testLog4jLog();		
		// loggerTest.testSlf4jLog();
		
//		try {
//			new NettySocketServer().startServer(6080);
//		}
//		catch(Exception e) {
//			
//		}
//		Timer mtimer=new Timer("MyTimer");
//		
//		mtimer.schedule(()->{
//			m_logger.info("shot time");
//		}, 1000);
//		
//		mtimer.schedule(() -> {
//	    	m_logger.info("long task");
//        }, 5000);
//
//		mtimer.scheduleAtFixedRate(() -> {
//        	m_logger.info("frame task");
//        }, 0, 1000);
//		
//		
//		JProtoBufWrapper m_jProtoBufWrapper=new JProtoBufWrapper();
//		m_jProtoBufWrapper.TestProtoBuf();
//		
//		
//		
//		XMLUTILS xmlserver=new XMLUTILS();
//		xmlserver.TestXML();
//		
//		PropertiesUtil propertiesUtil=new PropertiesUtil();
//		propertiesUtil.TestProperies();
		
//		EncyptWrapper md5wrapper=new EncyptWrapper();
//		md5wrapper.TestEncyptWrapper();
//		
//		//MySql
//		JDBCWrapper jdbcWrapper=new JDBCWrapper();
//		jdbcWrapper.TestJDBCWrapper();
		
		//Redis
//		RedisCluster redisclient=new RedisCluster();
//		redisclient.Init();
//		redisclient.TestRedis();
//		redisclient.Destory();
		
		//Anotation
//		TestAnnotation aniAnotation=new TestAnnotation();
//		aniAnotation.TestAnotatonUse();
		
		//反射
		//TestRelfect();
		
		//Job
//		try {
//			CronSchedulerHelper.initAndStart();
//		}catch(SchedulerException e) {
//			m_logger.info("error" + e.toString());
//		}
//		
//		try {
//			new NettySocketServer().startServer(6080);
//		}
//		catch(Exception e) {
//		}
		
		//http
//		try {
//			HttpUtil.main(new String[] {"aa"});
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		
		HttpCommandManager.getInstance().initialize("com.bycw.http.CommandHandler");
	}
	
	//字段 方法获取
	 static void TestRelfect() {
		 Class<?> cls=Reflect.class;//类型描述  包含类型方法 数据成员
		 
		 try {
			Object object=cls.newInstance();//实例化了一个 cls所描述的类型的实例
			//获取私有方法
			Method privateMethod=cls.getDeclaredMethod("PrivateTestMethod");
			privateMethod.setAccessible(true);//添加调用私有方法权限
			privateMethod.invoke(object);
			//获取公有方法
			Method publicMethod=cls.getMethod("PublicTestMethod");//从cls的描述中获取方法对象
		
			publicMethod.invoke(object);
			//获取方法组
			Method[] methods=cls.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				methods[i].setAccessible(true);
				methods[i].invoke(object);//添加调用私有方法权限
			}
			
			//获取公有数据字段
		Field publicField=	cls.getField("m_TestPublicInt");
		m_logger.info("Public field "+publicField.getInt(object));
		
		//获取私有数据字段
		try {
			Field priField=	cls.getDeclaredField("m_testPrivateInt");
			priField.setAccessible(true);//添加调用私有字段权限
			m_logger.info("Private field "+priField.getInt(object));
		} catch (Exception e) {
			m_logger.error(e.getMessage());
		}
	
			
		} catch (Exception e) {
			// TODO: handle exception
			m_logger.error(e.getMessage());
		}
		 
	 }
}


