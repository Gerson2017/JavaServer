package com.bytw.utils;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.FileInputStream;

public class PropertiesUtil {
	Logger m_logger=LoggerFactory.getLogger(PropertiesUtil.class);
	
	public void TestProperies() {
		Properties properties=new Properties();
		try {
			properties.load(new FileInputStream("configs/server.properties"));
			
			
		} catch (Exception e) {
			m_logger.error(e.toString());
		}

		m_logger.info(properties.getProperty("serverVersion"));
		m_logger.info(properties.getProperty("info"));
	}
	
}
