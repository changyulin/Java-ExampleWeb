package com.example.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AccessVerification extends HttpServlet {

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Access begin!");
		// 微信加密签名
		String signature = request.getParameter("signature");
		System.out.println("signature:" + signature);
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		System.out.println("timestamp:" + timestamp);
		// 随机数
		String nonce = request.getParameter("nonce");
		System.out.println("nonce:" + nonce);
		// 随机字符串
		String echostr = request.getParameter("echostr");
		System.out.println("echostr:" + echostr);

		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map<String, String> dataMap = parseXml(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		/*SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		System.out.println("post data:");
		System.out.println(document.asXML());
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());*/

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

}