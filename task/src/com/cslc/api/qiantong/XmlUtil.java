package com.cslc.api.qiantong;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.platform.util.StringUtil;

public class XmlUtil {
	
	public static final Logger log = Logger.getLogger(XmlUtil.class);

	public static Element getRootElementFromXml(String xml){
		try {
			Document doc = DocumentHelper.parseText(xml);
			return doc.getRootElement();
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	public static List<Map<String, String>> convertRequestXml2Maps(String xml){
		if(StringUtil.isBlank(xml)){
			return null;
		}
		try {
			Element root = getRootElementFromXml(xml);
			List<Map<String, String>> listMap =new ArrayList<Map<String, String>>();
			List<Element> mains = root.elements();
			for(Element e : mains){
				Map<String, String> mainMap = new HashMap<String, String>();
				if("status".equals(e.getName())){
					if(Integer.parseInt(e.getText())==0){
						return null;
					}
					if(Integer.parseInt(e.getText())<0){
						log.info("sms query qiantong error is ".concat(e.getText()));
						return null;
					}
				}else{
					if("sms".equals(e.getName())) {
						List<Element> results = e.elements();
						for(Element f : results){
							mainMap.put(f.getName(), f.getText());
						}
					}else{
						mainMap.put(e.getName(), e.getText());
					}
					listMap.add(mainMap);
				}
			}
			return listMap;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	/**
	 * 仅适用于对单条发送短信返回的内容进行解析
	 * @param xml
	 * @return
	 */
	public static Map<String, String> convertResponseXml2Map(String xml){
		if(StringUtil.isBlank(xml)){
			return null;
		}
		
		try {
			Element root = getRootElementFromXml(xml);
			
			Map<String, String> mainMap = new HashMap<String, String>();
			List<Element> mains = root.elements();
			for(Element e : mains){
				if("sms".equals(e.getName())) {
					List<Element> results = e.elements();
					for(Element f : results){
						mainMap.put(f.getName(), f.getText());
					}
				}else{
					mainMap.put(e.getName(), e.getText());
				}
			}
			
			return mainMap;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
}
