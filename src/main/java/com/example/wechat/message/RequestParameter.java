package com.example.wechat.message;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.example.wechat.utils.InputStreamUtil;

public class RequestParameter {
	private String signature;
	private String openid;
	private String nonce;
	private String timestamp;
	private String encrypt_type;
	private String msg_signature;
	private String postData;

	public RequestParameter(HttpServletRequest request) {
		this.signature = request.getParameter("signature");
		this.openid = request.getParameter("openid");
		this.nonce = request.getParameter("nonce");
		this.timestamp = request.getParameter("timestamp");
		this.encrypt_type = request.getParameter("encrypt_type");
		this.msg_signature = request.getParameter("msg_signature");
		try {
			this.setPostData(InputStreamUtil.convertStreamToString(request.getInputStream()));
		} catch (IOException e) {
			System.out.println("Get input parameter error.");
		}
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getEncryptType() {
		return encrypt_type;
	}

	public void setEncryptType(String encrypt_type) {
		this.encrypt_type = encrypt_type;
	}

	public String getMsgSignature() {
		return msg_signature;
	}

	public void setMsgSignature(String msg_signature) {
		this.msg_signature = msg_signature;
	}

	public String getPostData() {
		return postData;
	}

	public void setPostData(String postData) {
		this.postData = postData;
	}

}
