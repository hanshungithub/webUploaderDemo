package cn.hassan.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.apache.struts2.ServletActionContext.getResponse;

/**
 * json数据互转工具类。使用jackson API。
 * @author Sam
 * @version 1.0
 * @created 2011-9-8 下午01:20:16
 * @fileName com.weqia.common.util.JsonUtil.java
 */
public class JsonUtil {
	private static final Log log = LogFactory.getLog(JsonUtil.class);

	/**
	 * 
	 * @Description 把object转换成JSON格式的字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Writer writer = new StringWriter();
		String result = "";
		try {
			
			ObjectMapper om = new ObjectMapper(); 
			om.writeValue(writer, obj);
			result = writer.toString();
			if (log.isDebugEnabled()) {
				System.out.println(result);
			}
		} catch (Exception e) {
			if (log.isDebugEnabled()) {
				log.debug(e.getMessage(), e);
			}
		} finally {
			if(writer != null){
				try {
					writer.close();
					writer = null;
				} catch (IOException e) {
					if (log.isDebugEnabled()) {
						log.debug(e.getMessage(), e);
					}
				}
			}
		}
		
		return result;
	}
	
	/**
	 * 把JSON格式的字符串转换成对应的对象实例
	 * @param json
	 * @param clas
	 * @return
	 */
	public static <T> T toObject(String json, Class<T> clas){
		if (json == null) {
			return null;
		}
		T obj = null;
		try {
			ObjectMapper om = new ObjectMapper();
			om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
			obj = om.readValue(json, clas);
		} catch (JsonParseException e) {
			log.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
		return obj;
	}

	/**
	 * JSON 转 object 忽略object缺少json中字段的错误
	 * @param json
	 * @param clazz
	 * @param <T>
	 * @return
	 */
	public static <T> T toObjectIgnore(String json,Class<T> clazz){
		T obj = null;
		try {
			ObjectMapper om = new ObjectMapper();
			om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
			om.getDeserializationConfig().set(
					org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			obj = om.readValue(json, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return obj;
	}

	public static <T> T toObjectWithUrlDecode(String urlDecodeJson,Class<T> clazz){
//		String json = URLDecoderUtil.decode(urlDecodeJson, "UTF-8");
		String json = urlDecodeJson.replaceAll("%26", "&").replaceAll("%3d", "=");
		return toObject(json,clazz);
	}

	public static <T> List<T> toListThrows(String json, Class<T> clazz) throws Exception{
		List<T> list = null;
		try {
			ObjectMapper om = new ObjectMapper();
			om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			list = om.readValue(json, new TypeReference<List<T>>() {});
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	public static <T> List<T> toList(String json, Class<T> clazz) {
		try {
			return toListThrows(json, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

	public static <T> List<T> toArrayList(String json,Class<T> clazz) {
		if (json == null) {
			return null;
		}
		ObjectMapper om = new ObjectMapper();
		JavaType javaType = om.getTypeFactory().constructParametricType(ArrayList.class, clazz);
		try{
			return om.readValue(json, javaType);
		}catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return Collections.emptyList();
	}

	/**
	 * 把JSON格式的字符串转换成对应的对象实例 如果失败会抛出异常
	 * @param json
	 */
	public static <T> T toObjectThrows(String json, Class<T> clazz) throws Exception{
		T obj = null;
		try {
			ObjectMapper om = new ObjectMapper();
			om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
			obj = om.readValue(json, clazz);
		} catch (Exception e) {
			throw e;
		}
		return obj;
	}
	
	/**
	 * 把JSON格式的字符串转换成对应的对象实例  不显示异常
	 * @param json
	 * @param clas
	 * @return
	 */
	public static <T> T toObjectHide(String json, Class<T> clas){
		T obj = null;
		try {
			ObjectMapper om = new ObjectMapper();
			om.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
			obj = om.readValue(json, clas);
		} catch (JsonParseException e) {
			
		} catch (JsonMappingException e) {
			
		} catch (IOException e) {
			
		}
		
		return obj;
	}

	public static void write(String content) {
		try {
			getResponse().setContentType(
					"application/json,text/javascript,text/html;charset=UTF-8");
			getResponse().setCharacterEncoding("utf-8");
			getResponse().getWriter().write(content);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

}
