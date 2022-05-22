package com.bytw.anotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TestAnnotation{
	Logger m_logger=LoggerFactory.getLogger(TestAnnotation.class);
	
	public void TestAnotatonUse() {
		m_logger.info("Hello Test Anotation");
		
		//类的注解
		NetMessage netMessage=new NetMessage();
		TypeMeta netmessageMeta=NetMessage.class.getAnnotation(TypeMeta.class);
		m_logger.info(netmessageMeta.Desc()+" "+netmessageMeta.cmd()+" "+netmessageMeta.module());
		
		//字段的注解
		try {
			Field valuefield=NetMessage.class.getField("value");
			FieldMeta fMeta=valuefield.getAnnotation(FieldMeta.class);
			
			m_logger.info("fieldmeta desic "+fMeta.desic());
		} catch (Exception e) {
			// TODO: handle exception
			m_logger.error(e.getMessage());
		}
		
		
		//方法的注解
		try {
			Method methodfield=NetMessage.class.getMethod("TestMethod");
			MethodMeta methodMeta=methodfield.getAnnotation(MethodMeta.class);
			
			m_logger.info("methodMeta desic "+methodMeta.desic());
		} catch (Exception e) {
			// TODO: handle exception
			m_logger.error(e.getMessage());
		}
		
	}
}