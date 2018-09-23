package com.design.framework.serializer;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.design.framework.utils.DateUtil;
/**
 * yyyy-MM-dd HH:mm:ss 转 Date 
 * @author JohnDeng
 * @datatime 2018年5月17日下午3:17:35
 */
public class DateJsonDeserializer extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		return DateUtil.str2Date(jp.getText(),DateUtil.FORMAT_YMDHMS);
	}

}
