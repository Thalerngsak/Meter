package com.pwa.common;

import java.io.Serializable;
import java.util.ArrayList;

import com.pwa.common.exception.CMException;

public class MessageList implements Serializable {

	private ArrayList msgList;

	
	public MessageList() {
		msgList = new ArrayList();
	}

	public void addMessage(Exception e) {
		
		if (e instanceof CMException) {
			CMException cm = (CMException) e;
			MessageList msgList = cm.getMsgList();

			if (!msgList.isEmpty()) { // in case Exception contain msgList so add all it
				ArrayList mList = msgList.getMsgList();
				this.msgList.addAll(mList);
			} else { // add one
				MessageObj obj = new MessageObj(e);
				this.msgList.add(obj);
			}
		} else {
			MessageObj obj = new MessageObj(e);
			msgList.add(obj);
		}
	}

	public void addFirstMessage(Exception e) {
		MessageObj obj = new MessageObj(e);
		msgList.add(0, obj);
	}

	public void addMessage(MessageList msgList) {
		if (msgList == null)
			return;
		ArrayList ml = msgList.getMsgList();
		if (ml != null)
			this.msgList.addAll(ml);
	}

	public void addMessage(String type, String param) {
		MessageObj obj = new MessageObj(type, param);
		msgList.add(obj);

	}

	public void addFirstMessage(String type, String param) {
		MessageObj obj = new MessageObj(type, param);
		msgList.add(0, obj);

	}

	public boolean isContainError() {
		for (int i = 0; i < msgList.size(); i++) {
			MessageObj o = (MessageObj) msgList.get(i);
			if (o.getType().startsWith("E")) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return msgList.isEmpty();
	}

	public ArrayList getMsgList() {
		return msgList;
	}

	public void setMsgList(ArrayList msgList) {
		this.msgList = msgList;
	}

}
