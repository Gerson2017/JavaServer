package com.bycw.jprotobuf;
import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ReqAccountLogin {
	
	//order 字段的顺序
	//require true 字段必须要有值 false 字段可以没有数据
	//fieldType 可以重定向数据的描述类型
	@Protobuf(order = 1,required = true,fieldType = FieldType.INT64)
	public long  accountId;
	
	@Protobuf(order =2,required = true)
	public String password;

}
