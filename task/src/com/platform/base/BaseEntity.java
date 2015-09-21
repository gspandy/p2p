package com.platform.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.platform.util.StringUtil;

public abstract class BaseEntity implements Serializable {

	private String json;
	
	private Map<String, Object> fieldsMap = null;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
	public Map<String, Object> initJsonMap(){
		if(fieldsMap == null){
			fieldsMap = JSONObject.parseObject(getJson());
			if(fieldsMap == null){
				fieldsMap = new HashMap<String, Object>();
			}
		}
		return fieldsMap;
	}
	
	public String getField(String field){
		initJsonMap();
		String result = (String) fieldsMap.get(field);
		return result == null ? "" : result;
	}
	
	public void addField(String field, String value){
		if(StringUtil.isNotBlank(field) && StringUtil.isNotBlank(value)){
			initJsonMap();
			fieldsMap.put(field, value);
			setJson(JSONObject.toJSONString(fieldsMap));
		}
	}
	
	public void addField(String field, String value, String json){
		if(StringUtil.isNotBlank(field) && StringUtil.isNotBlank(value)){
			Map<String, Object> map = JSONObject.parseObject(json);
			if(map == null){
				map = new HashMap<String, Object>();
			}
			map.put(field, value);
			setJson(JSONObject.toJSONString(map));
		}
	}

}
