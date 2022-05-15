package com.bytw.utils;
import java.io.File;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class XMLUTILS{
	
	private Logger m_logger=LoggerFactory.getLogger(XMLUTILS.class);
	
	public void TestXML() 
	{
		XMLServer serverinfo=null;
		Serializer xmlserlize=new Persister();
		try {
			serverinfo=xmlserlize.read(XMLServer.class, new File("configs/server.xml") );
		} catch (Exception e) {
			m_logger.error(e.toString());
		}

		m_logger.info("redis url ="+serverinfo.redisUrl);
	}
	
	
}