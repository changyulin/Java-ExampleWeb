package com.example.wechat;

import java.io.IOException;
import java.io.PrintWriter;

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
		// TODO 消息的接收、处理、响应
	}
}
