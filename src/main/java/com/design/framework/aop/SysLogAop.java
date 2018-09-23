package com.design.framework.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.design.framework.annotion.SysLog;
import com.design.framework.enums.LogTypeEnums;

/**
 * 系统日志AOP
 * @author JohnDeng
 * @datatime 2018年9月3日下午9:05:03
 */
@Component
@Aspect
public class SysLogAop {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpServletRequest request;
	
	@Pointcut("@annotation(com.design.framework.annotion.SysLog)")
	public void sysLogPointcut() {

	}

	@Before("sysLogPointcut()")
	public void Before(JoinPoint jp) {
		logger.debug("------------------方法执行之前start------------------");
		logger.debug("------------------方法名：" + jp.getSignature().getName() + "------------------");
		logger.debug("------------------Class：" + jp.getSignature().getClass().getName() + "------------------");
		logger.debug("------------------DeclaringTypeName：" + jp.getSignature().getDeclaringTypeName()+ "------------------");
		logger.debug("------------------DeclaringType：" + jp.getSignature().getDeclaringType() + "------------------");
		logger.debug("------------------Modifiers：" + jp.getSignature().getModifiers() + "------------------");
		logger.debug("------------------方法执行之前end------------------");
	}

	@After("sysLogPointcut()")
	public void service(JoinPoint jp) throws Exception {
		logger.debug("------------------方法执行之后start------------------");
		
		
		
		logger.debug("------------------方法执行之后end------------------");
	}

	
	
	/**
	 * 获取SysLog注解description
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月1日下午12:17:22
	 */
	private String getMthodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SysLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取SysLog注解type
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Exception
	 * @author John
	 * @datatime 2017年12月1日下午12:17:02
	 */
	private LogTypeEnums getMthodTableType(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		LogTypeEnums tableType = null;
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class<?>[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					tableType = method.getAnnotation(SysLog.class).operate();
					break;
				}
			}
		}
		return tableType;
	}

}
