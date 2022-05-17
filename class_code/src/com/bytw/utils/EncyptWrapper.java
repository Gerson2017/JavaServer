package com.bytw.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;



public class EncyptWrapper {
	Logger m_logger=LoggerFactory.getLogger(EncyptWrapper.class);
	
	public void TestEncyptWrapper() {
		
		String password="1234567";
		
		String mdsPassword=DigestUtils.md5Hex(password);

		String sha1Password=DigestUtils.sha1Hex(password);
		
		String sha256Password=DigestUtils.sha256Hex(password);
		
		m_logger.info("mdsPassword "+mdsPassword+" sha1Password "+sha1Password+" sha256Password "+sha256Password);
		
		
		//base64 二进制转文本  文本转二进制
		String base64Str=Base64.encodeBase64String(password.getBytes());//使用原字符串的base64码
		
		m_logger.info("base64Str "+base64Str);
		
		String base64DecodeString=new String(Base64.decodeBase64(base64Str));
		
		m_logger.info("base64DecodeString "+base64DecodeString);
	}

}
