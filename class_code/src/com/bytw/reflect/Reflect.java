package com.bytw.reflect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Reflect{

	
	Logger m_logger=LoggerFactory.getLogger(Reflect.class);
	
	public void PublicTestMethod() {
		m_logger.info("PublicTestMethod");
	}
	
	private void PrivateTestMethod() {
		m_logger.info("PrivateTestMethod");
	}
	
	
	
	public int m_TestPublicInt;
	
	private int m_testPrivateInt;
	
}


