package com.pwa.data.model.config;

/**
 * Config entity. @author MyEclipse Persistence Tools
 */
public class Config extends AbstractConfig implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public Config() {
	}

	/** minimal constructor */
	public Config(String br) {
		super(br);
	}

	/** full constructor */
	public Config(String br, String head, String telno, String lowtype1, String lowtype2, String lowtype3, String bankdate, String paydate) {
		super(br, head, telno, lowtype1, lowtype2, lowtype3, bankdate, paydate);
	}

}
