package com;

import java.io.Serializable;

public class PairValueDesc  implements Serializable{
	private String value;
	private String desc;


	public PairValueDesc(String value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
