
package com.pwa.common.exception;

import com.pwa.common.MessageList;


public class CMException extends Exception{

	
	private java.lang.String type = "";
	private java.lang.String param = "";
	private MessageList msgList= new MessageList();

	public CMException(String type, String param) {
		super(param);
		this.type = type;
		this.param = param;
	}
	
	public CMException(MessageList msgList) {
		this.setMsgList(msgList);
	}

	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.String getParam() {
		return param;
	}

	public void setParam(java.lang.String param) {
		this.param = param;
	}

	public void setMsgList(MessageList msgList) {
		this.msgList = msgList;
	}

	public MessageList getMsgList() {
		return msgList;
	}



	
}
