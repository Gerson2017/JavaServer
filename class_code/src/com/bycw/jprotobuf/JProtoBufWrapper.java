package com.bycw.jprotobuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.bjf.remoting.protobuf.Codec;//编码解码对象
import com.baidu.bjf.remoting.protobuf.ProtobufIDLProxy;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;//注解
import com.bycw.jprotobuf.*;


public class JProtoBufWrapper{
	
	
	Logger m_logger=LoggerFactory.getLogger(JProtoBufWrapper.class);
	
	public void TestProtoBuf() {
		ReqAccountLogin req=new ReqAccountLogin();
		req.accountId=10000;
		req.password="gerson123";
		
		
		Codec<ReqAccountLogin> code= ProtobufProxy.create(ReqAccountLogin.class);
		byte[] res=null;
		try {
			//序列化
			res=code.encode(req);
		} catch (Exception e) {
			m_logger.error(e.toString());
		}
		
		try {
			ReqAccountLogin login=code.decode(res);
			
			m_logger.info(login.accountId +": "+login.password);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}