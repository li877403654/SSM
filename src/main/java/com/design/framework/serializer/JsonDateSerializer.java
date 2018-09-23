package com.design.framework.serializer;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.design.framework.utils.DateUtil;

/**
 * string  to datetime
 * @author JohnDeng
 * @dataTime 2018年3月26日上午11:38:55
 *
 */
public class JsonDateSerializer extends JsonSerializer<Date> {  
	   @Override  
	   public void serialize(Date date, JsonGenerator gen, SerializerProvider provider)  
	           throws IOException, JsonProcessingException {  
	       gen.writeString(DateUtil.date2Str(date, DateUtil.FORMAT_YMDHMS));  
	   }  
	} 
