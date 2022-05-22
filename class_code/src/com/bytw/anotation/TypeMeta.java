package com.bytw.anotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


//注解就是类似于C#里面的特性 可以使用依赖注入获取注解里面的值

//@interface 表示是注解
@Documented
@Target(ElementType.TYPE)//用了描述一个类 接口或者Enum
@Retention(RetentionPolicy.RUNTIME)//运行时依然存在
@Inherited
public @interface  TypeMeta{
	String Desc() default "BaseClass";
	short module() default 0;
	short cmd() default 0;
	
}