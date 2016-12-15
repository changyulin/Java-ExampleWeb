package com.example.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.example.wechat.aes.AesException;
import com.example.wechat.aes.WXBizMsgCrypt;
import com.example.wechat.cons.Cons;
import com.example.wechat.message.RequestParameter;
import com.example.wechat.utils.InputStreamUtil;

public class AccessVerification extends HttpServlet {

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("++++++++doGet begin++++++++++");
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
		System.out.println("++++++++doGet end++++++++++");
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
			System.out.println("=======doPost begin=========");
			// String input =
			// InputStreamUtil.convertStreamToString(request.getInputStream());
			// System.out.println("input:" + input);

			Map parpMap = request.getParameterMap();
			for (Object key : parpMap.keySet()) {
				String name = key.toString();
				System.out.println(name + ":" + request.getParameter(name));
			}

			RequestParameter para = new RequestParameter(request);
			System.out.println("yuanwen: " + para.getPostData());
			WXBizMsgCrypt pc = new WXBizMsgCrypt(Cons.TOKEN, Cons.ENCODINGAESKEY, Cons.APPID);
			String mingwen = pc.decryptMsg(para.getMsgSignature(), para.getTimestamp(), para.getNonce(),
					para.getPostData());

			System.out.println("mingwen: " + mingwen);

			System.out.println("=======doPost end=========");
			PrintWriter out = response.getWriter();
			out.print("success");
			out.close();
			out = null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * public static void main(String[] args) throws Exception { String
	 * msgSignature = "8c61040f369656a6b5a89833d8aff93997be8576"; String
	 * timestamp = "1481794969"; String nonce = "1725222472"; String postData=
	 * "<xml>    <ToUserName><![CDATA[gh_711bf813ded0]]></ToUserName>    <Encrypt><![CDATA[FyP8LauhConxDGlWNUF4F3sXA1GTn2jjMMdd2dC9IFHJNrMZYUsgacJNtKUEXwdYOObqy2uavEYuq0lVEZXPW4+JIpH65i2v/ypc1lYWJKHPcocVL1c1mmwFE/8ShTx3Qt94GPbLFrCURvOddv3NsfcLFoKoHL5SqLTGRyPYuBMYpSGyuuGRXHtD9YOApwP00ZEZyOfJ4MtppPMYUjaUgwp/kmiGr4T4p3aNlasvzL53AR5JR8y0HdrKae2snEex1AMCv6FBIhbMARVnNmdoDo5+Uc/4QI3//QAI57QEH2mFABGYPlr3+CumfxuyoJlqL8xtIz/GyhJGoUAYRkPclxv24owkJeYbKerWZHTBgI0o/o03GAuhsU29NO+PVpWOtLHVmC+YnhvjQNjqJM7qpw07xHISqcIS+cMSEbXLVNjkL31Ybem5gIef6ksQGrXB17XbRARkJVjilzMc/gwO7rudOD75kdfRLDkaecO4xBcdwywLMVSiIJ5px6ofMBVr]]></Encrypt></xml>"
	 * ; WXBizMsgCrypt pc = new WXBizMsgCrypt(Cons.TOKEN, Cons.ENCODINGAESKEY,
	 * Cons.APPID); String mingwen = pc.decryptMsg(msgSignature, timestamp,
	 * nonce, postData); System.out.println("mingwen: " + mingwen); }
	 */

	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		InputStream inputStream = request.getInputStream();
		// 读取输入流
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		// SAXReader reader = new SAXReader();
		Document document = db.parse(inputStream);
		System.out.println("post data:");
		Element root = document.getDocumentElement();
		NodeList nodeList = root.getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			String name = nodeList.item(i).getNodeName();
			String value = nodeList.item(i).getNodeValue();
		}
		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

}
