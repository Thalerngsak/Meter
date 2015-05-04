package com.pwa.common;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.pwa.common.exception.CMException;

/**
 * @author db2admin
 * @modify Kaita D.
 * 
 *         To change the template for this generated type comment go to Window - Preferences - Java - Code Style - Code Templates
 */
public class MessageObj implements Serializable {
static Logger log = Logger.getLogger(MessageObj.class);
	private String type;
	private String param;

	public MessageObj(Exception e) {
		this.type = "E";
		if (e instanceof CMException) {
			this.param = e.getMessage();
		} else {
			this.param = e.toString();
		}
		log.debug(type+":"+param);
	}

	public MessageObj(String type, String param) {
		this.type = type;
		this.param = param;
		log.debug(type+":"+param);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
