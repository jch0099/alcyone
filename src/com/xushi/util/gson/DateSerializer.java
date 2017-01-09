package com.xushi.util.gson;

import java.lang.reflect.Type;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.xushi.util.date.DateTimeUtil;

/**
 * date序列化
 * @author Administrator
 *
 */
public class DateSerializer implements JsonSerializer<Date> 
{
	public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
	{
	    return new JsonPrimitive(DateTimeUtil.formatDateTime(src));
	}
}
