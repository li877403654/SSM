package com.design.framework.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * JSON工具类
 * @author JohnDeng
 * @datatime 2018年5月2日下午5:18:02
 */
public class JsonUtils {

	/**
	 * 根据前台的JSON，获取某个参数的值
	 * 
	 * @param json
	 *            请求的JSON
	 * @param param
	 *            要获取的参数
	 * @return
	 */
	public static Object getParamObject(JSONObject json, String param) {
		
		return json.get(param);
	}

	public static String getParamToString(JSONObject json, String param) {

		return json.getString(param);
	}

	/**
	 * json对象转单对象
	 * 
	 * @param json
	 * @param obj
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toBean(JSONObject json, T obj){
		try {
			if(json!=null){
				obj = (T) JSON.parseObject(json.toJSONString(), obj.getClass());
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public static Object toBean(JSONObject json, Class<?> clazz){
		try {
			if(json!=null){
				Object	obj = JSON.parseObject(json.toJSONString(), clazz);
				return obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * json数组转List<Object>
	 * 
	 * @param jsonArray
	 * @param obj
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <E> List<E> toBeanList(JSONArray jsonArray, E obj) {
		List<E> list = (List<E>) JSON.parseArray(jsonArray.toJSONString(), obj.getClass());
		return list;
	}

	/**
	 * 从Json对象里获取Map
	 * 
	 * @param jsonStr
	 * @return Map
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> getMapFromJsObject(String jsonStr) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		JSONObject jObject=null;
		if (null != jsonStr && !"".equals(jsonStr)) {
			try {
				jObject = JSON.parseObject(jsonStr);
				reMap = JSON.parseObject(jObject.toJSONString(), Map.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return reMap;
	}

	/**
	 * json对象是否包含key并且值非空
	 * 
	 * @param jsonobject
	 * @param obj
	 * @return
	 */
	public static boolean hasKey(JSONObject jsonobject, String key) {
		return jsonobject != null && jsonobject.containsKey(key) && StringUtils.isNotBlank(jsonobject.getString(key));
	}
	
	/**
	 * JSON是否含有指定参数,没有返回false
	 * @author JohnDeng
	 * 2017年11月10日下午5:25:23
	 * @param jsonObject
	 * @param paramName
	 * @return
	 */
	public static boolean containsKeys(JSONObject jsonObject,String ... paramName){
		if(jsonObject!=null && paramName!=null ){
			for(String key: paramName){
				if(!jsonObject.containsKey(key)){
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
}
