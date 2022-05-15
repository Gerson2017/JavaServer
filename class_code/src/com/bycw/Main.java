package com.bycw;

import com.bycw.logtest.TestLogger;
import com.bycw.netty.NettySocketServer;
import com.bycw.jprotobuf.JProtoBufWrapper;
import com.bytw.utils.*;


public class Main {
	public static void main(String args[]) {
		// TestLogger loggerTest = new TestLogger();
		
		// loggerTest.testLog4jLog();		
		// loggerTest.testSlf4jLog();
		
//		try {
//			new NettySocketServer().startServer(6080);
//		}
//		catch(Exception e) {
//			
//		}
		
		JProtoBufWrapper m_jProtoBufWrapper=new JProtoBufWrapper();
		m_jProtoBufWrapper.TestProtoBuf();
		
		
		
		XMLUTILS xmlserver=new XMLUTILS();
		xmlserver.TestXML();
		
		PropertiesUtil propertiesUtil=new PropertiesUtil();
		propertiesUtil.TestProperies();
		
	}
}


