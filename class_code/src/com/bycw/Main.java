package com.bycw;

import com.bycw.logtest.TestLogger;
import com.bycw.netty.NettySocketServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bycw.database.JDBCWrapper;
import com.bycw.jprotobuf.JProtoBufWrapper;
import com.bytw.utils.*;


public class Main {
	public static void main(String args[]) {
		Logger m_logger=LoggerFactory.getLogger(Main.class);
		
		
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
		
		EncyptWrapper md5wrapper=new EncyptWrapper();
		md5wrapper.TestEncyptWrapper();
		
		JDBCWrapper jdbcWrapper=new JDBCWrapper();
		jdbcWrapper.TestJDBCWrapper();
		
	}
}


