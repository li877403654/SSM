package com.design.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * 反射设置值工具类
 * @author JohnDeng
 * @datatime 2018年5月2日下午5:21:43
 */
public class MyBeanUtils {

	/**
	 * 获取set方法
	 * 
	 * @author JohnDeng 2017年11月9日下午3:05:12
	 * @param object
	 *            对象
	 * @param property
	 *            属性名
	 * @param value
	 *            属性值
	 * @return
	 */
	public static String setMethodName(Object object, String property, Object value) {
		String methodName = "set" + property.substring(0, 1).toUpperCase();
		if (property.length() > 1) {
			methodName += property.substring(1);
		}
		return methodName;
	}

	/**
	 * 获取get方法
	 * 
	 * @author JohnDeng 2017年11月9日下午3:04:55
	 * @param property
	 *            对象
	 * @return
	 */
	public static String getMethodName(String property) {
		String methodName = "get" + property.substring(0, 1).toUpperCase();
		if (property.length() > 1) {
			methodName += property.substring(1);
		}
		return methodName;
	}

	/**
	 * 获取@Id的值
	 * 
	 * @author JohnDeng 2017年11月9日下午3:21:46
	 * @param object
	 *            对象
	 * @return
	 */
	public static Object getIdValue(Object object) {
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				try {
					String methodName = MyBeanUtils.getMethodName(field.getName());
					Method method = clazz.getMethod(methodName);
					method.setAccessible(true);
					return method.invoke(object);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		return null;
	}

	/**
	 * 设置实体创建时间
	 * 
	 * @author JohnDeng 2017年11月9日下午4:24:04
	 * @param object
	 */
	public static void setCreateTimeValue(Object object) {
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			DateTimeFormat dateTimeFormat = field.getAnnotation(DateTimeFormat.class);
			if (dateTimeFormat != null) {
				String name = field.getName();
				if (name.equals("createTime")) {
					setProperty(object, name, new Date());
				}
			}
		}
	}

	/**
	 * 设置未删除
	 * 
	 * @author JohnDeng 2017年11月14日下午4:51:09
	 * @param object
	 */
	public static void setDelFlagValue(Object object) {
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			String name = field.getName();
			if (name.equals("delFlag")) {
				setProperty(object, name, 0);
			}
		}
	}

	

	/**
	 * 设置创建id值
	 * 
	 * @author JohnDeng 2017年11月14日下午2:12:27
	 * @param object
	 */
	public static void setCreateIdValue(Object object, String id) {
		String uuid = id;
		if (id == null || "".equals(id)) {
			uuid = UUID.randomUUID().toString();
		}
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			String name = field.getName();
			if (name.equals("createId")) {
				setProperty(object, name, uuid);
			}
		}
	}

	/**
	 * 设置修改id值
	 * 
	 * @author JohnDeng 2017年11月14日下午2:12:47
	 * @param object
	 */
	public static void setUpdateIdValue(Object object, String id) {
		String uuid = id;
		if (id == null || "".equals(id)) {
			uuid = UUID.randomUUID().toString();
		}
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			String name = field.getName();
			if (name.equals("updateId")) {
				setProperty(object, name, uuid);
			}
		}
	}

	
	/**
	 * 设置实体修改时间
	 * 
	 * @author JohnDeng 2017年11月9日下午4:24:30
	 * @param object
	 */
	public static void setupdateTimeValue(Object object) {
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			DateTimeFormat dateTimeFormat = field.getAnnotation(DateTimeFormat.class);
			if (dateTimeFormat != null) {
				String name = field.getName();
				if (name.equals("updateTime")) {
					setProperty(object, name, new Date());
				}
			}
		}
	}

	/**
	 * 设置id值为UUID
	 * 
	 * @author JohnDeng 2017年11月9日下午4:26:44
	 * @param object
	 */
	public static String setIdValue(Object object) {
		String uuid = UUID.randomUUID().toString();
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectUtils.getFields(clazz);
		for (Field field : fields) {
			Id id = field.getAnnotation(Id.class);
			if (id != null) {
				String name = field.getName();
				if (name.equals("id")) {
					setProperty(object, name, uuid);
				}
			}
		}
		return uuid;
	}

	/**
	 * 设置属性值
	 * 
	 * @author JohnDeng 2017年11月9日下午4:21:34
	 * @param object
	 *            对象
	 * @param property
	 *            属性名
	 * @param value
	 *            属性值
	 */
	public static void setProperty(Object object, String property, Object value) {
		String methodName = "set" + property.substring(0, 1).toUpperCase();
		if (property.length() > 1) {
			methodName += property.substring(1);
		}
		String[] methodNames = new String[1];
		methodNames[0] = methodName;
		setMethodInvoke(object, methodNames, value);
	}

	public static void setMethodInvoke(Object object, String[] methodNames, Object value) {
		Method[] methods = object.getClass().getMethods();
		for (String methodName : methodNames) {
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					try {
						method.invoke(object, new Object[] { value });
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static Object getProperty(Object object, String property) {
		String methodName = "get" + property.substring(0, 1).toUpperCase();
		if (property.length() > 1) {
			methodName += property.substring(1);
		}
		String[] methodNames = new String[2];
		methodNames[0] = methodName;
		return getMethodInvoke(object, methodNames);
	}

	public static Object getMethodInvoke(Object object, String[] methodNames) {
		Method[] methods = object.getClass().getMethods();
		for (String methodName : methodNames) {
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					try {
						return method.invoke(object, new Object[] {});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	public static <T> T newTclass(Class<T> clazz) throws Exception {
		return clazz.newInstance();
	}

	/**
	 * 获取实体注解的tableName
	 * 
	 * @param object
	 * @param name
	 * @return
	 * @author John
	 * @datatime 2017年11月30日上午11:44:10
	 */
	public static String getTableName(Object object) {
		Class<?> clazz = object.getClass();
		Table table = clazz.getAnnotation(Table.class);
		if (table != null) {
			Method[] met = table.annotationType().getDeclaredMethods();
			for (Method me : met) {
				if (!me.isAccessible()) {
					me.setAccessible(true);
				}
				try {
					if (me.getName().equals("name")) {
						return (String) me.invoke(table);
					}
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
