package com.platform.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XmlUtil {
	
	public static final Logger log = Logger.getLogger(XmlUtil.class);
	
	public static final String XML_LSIT = "list";
	public static final String XML_HEADER = "header";
	public static final String XML_CONTEXT = "context";
	public static final String XML_ITEM = "item";

	public static Element getRootElementFromXml(String xml){
		try {
			Document doc = DocumentHelper.parseText(xml);
			return doc.getRootElement();
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	public static Map<String, String> convertXml2Map(String xml){
		try {
			Element root = getRootElementFromXml(xml);
			
			Map<String, String> map = new HashMap<String, String>();
			List<Element> elements = root.elements();
			for(Element e : elements){
				map.put(e.getName(), e.getText());
			}
			return map;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	// for bss
	public static Map<String, Object> convertBssXml2Map(String xml){
		try {
			Element root = getRootElementFromXml(xml);
			
			// 主表
			Map<String, Object> mainMap = new HashMap<String, Object>();
			List<Element> mains = root.elements();
			Element header = null;
			Element data = null;
			for(Element e : mains){
				if(XML_HEADER.equals(e.getName())){
					header = e;
				}else {
					data = e;
				}
			}
			
			// header
			Map<String, String> headerMap = new HashMap<String, String>();
			if(header != null){
				List<Element> headers = header.elements();
				for(Element e : headers){
					headerMap.put(e.getName(), e.getText());
				}
				mainMap.put(XML_HEADER, headerMap);
			}
			
			// 主表
			List<Element> datas = data.elements();
			Element cong = null;
			for(Element e : datas){
				if(XML_ITEM.equals(e.getName())){
					cong = e;
				}else {
					mainMap.put(e.getName(), e.getText());
				}
			}

			// 从表
			List<Map> congList = new ArrayList<Map>();
			List<Element> congs = cong.elements();
			for(Element e : congs){
				List<Element> items = e.elements();
				Map<String, String> congMap = new HashMap<String, String>();
				for(Element i : items){
					congMap.put(i.getName(), i.getText());
				}
				congList.add(congMap);
			}
			mainMap.put(XML_LSIT, congList);
			return mainMap;
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
	
	
	// for bss
	public static String convertBssMap2Xml(Map<String, Object> map){
		try {
			Document document = DocumentHelper.createDocument();
			Element root = DocumentHelper.createElement("sendmessage");
			document.setRootElement(root);
			
			// header
			Element header = DocumentHelper.createElement(XML_HEADER);
			root.add(header);
			Map<String, String> headerMap = (Map<String, String>) map.get(XML_HEADER);
			if(headerMap != null){
				for(String key : headerMap.keySet()){
					Element e = DocumentHelper.createElement(key);
					e.setText((String) headerMap.get(key));
					header.add(e);
				}
			}
			
			// 主表
			Element table = DocumentHelper.createElement(XML_CONTEXT);
			root.add(table);
			for(String key : map.keySet()){
				if(!XML_LSIT.equals(key) && !XML_HEADER.equals(key)){
					Element e = DocumentHelper.createElement(key);
					if(map.get(key) != null){
						e.setText(String.valueOf(map.get(key)));
					}
					table.add(e);
				}
			}
			
			// 从表
			List<Map<String, String>> congList = (List<Map<String, String>>) map.get(XML_LSIT);
			if(congList != null){
				Element item = DocumentHelper.createElement(XML_ITEM);
				table.add(item);
				for(Map<String, String> m : congList){
					for(String key : m.keySet()){
						Element e = DocumentHelper.createElement(key);
						e.setText((String) m.get(key));
						item.add(e);
					}
				}
			}
			return document.asXML();
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
}
