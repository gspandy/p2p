package com.platform.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class TemplateUtil {

	protected static Logger log = Logger.getLogger(TemplateUtil.class);
	
	public static String merge(String content, String[] fields, String[] params){
		if(fields == null || params == null){
			return content;
		}
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		for(int i = 0; i < fields.length; i ++){
			paramsMap.put(fields[i], params[i]);
		}
		return merge(content, paramsMap);
	}
	
	public static String merge(String content, Map<String, Object> paramsMap) {
		try {
			Configuration cfg = new Configuration();
			cfg.setTemplateLoader(new StringTemplateLoader(content));
			cfg.setDefaultEncoding("UTF-8");
			Template template = cfg.getTemplate("");
			StringWriter writer = new StringWriter();
			template.process(paramsMap, writer);
			return writer.toString();
		} catch (Exception e) {
			log.error(e);
		}
		return "";
	}
	
	public static void main(String[] args) {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("user", "Keven Chen");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", "aa");
		map.put("b", "bb");
		paramsMap.put("map", map);
		
		merge("欢迎：${user}，${map.a}", paramsMap);
	}

}

class StringTemplateLoader implements TemplateLoader {
	private static final String DEFAULT_TEMPLATE_KEY = "_default_template_key";
	private Map templates = new HashMap();

	public StringTemplateLoader(String defaultTemplate) {
		if (defaultTemplate != null && !defaultTemplate.equals("")) {
			templates.put(DEFAULT_TEMPLATE_KEY, defaultTemplate);
		}
	}

	public void AddTemplate(String name, String template) {
		if (name == null || template == null || name.equals("") || template.equals("")) {
			return;
		}
		if (!templates.containsKey(name)) {
			templates.put(name, template);
		}
	}

	public void closeTemplateSource(Object templateSource) throws IOException {

	}

	public Object findTemplateSource(String name) throws IOException {
		if (name == null || name.equals("")) {
			name = DEFAULT_TEMPLATE_KEY;
		}
		return templates.get(name);
	}

	public long getLastModified(Object templateSource) {
		return 0;
	}

	public Reader getReader(Object templateSource, String encoding) throws IOException {
		return new StringReader((String) templateSource);
	}

}
