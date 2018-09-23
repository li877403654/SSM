package com.design.framework.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.design.framework.enums.LogTypeEnums;

/**
 * 系统日志注解
 * 
 * @author John
 * @datatime 2017年12月1日上午10:16:14
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited()
public @interface SysLog {

	/**
	 * 类型 增删改查
	 * 
	 * @return
	 * @author John
	 * @datatime 2017年12月1日上午10:15:55
	 */
	LogTypeEnums operate() default LogTypeEnums.SELECT;

	/**
	 * 描述
	 * 
	 * @return
	 * @author John
	 * @datatime 2017年12月1日上午10:16:05
	 */
	String description() default "";

}
