package com.example.wechat.utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtil {

	@SuppressWarnings("finally")
	public static Map<String, String> getMapFromXml(String xmltext) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			Element root = getElementFromXml(xmltext);
			NodeList nodeList = root.getChildNodes();
			for (int i = 0; i < nodeList.getLength(); i++) {
				String name = nodeList.item(i).getNodeName();
				String value = nodeList.item(i).getNodeValue();
				map.put(name, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return map;
		}
	}

	public static Element getElementFromXml(String xmltext)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		StringReader sr = new StringReader(xmltext);
		InputSource is = new InputSource(sr);
		Document document = db.parse(is);
		return document.getDocumentElement();
	}
}
