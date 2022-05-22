package com.bytw.anotation;


@TypeMeta(Desc = "NetMessage",cmd = 1,module = 1)
public class NetMessage {

	@FieldMeta(desic = "测试属性注解")
	public int value;
	
	@MethodMeta(desic = "测试方法注解")
	public void TestMethod() {
		
	}
	
}
