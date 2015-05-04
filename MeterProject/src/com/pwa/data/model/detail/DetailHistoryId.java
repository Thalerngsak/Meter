package com.pwa.data.model.detail;

/**
 * DetailId entity. @author MyEclipse Persistence Tools
 */
public class DetailHistoryId extends AbstractDetailId implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public DetailHistoryId() {
	}

	/** full constructor */
	public DetailHistoryId(String br, String custcode, String rte,String revym) {
		super(br, custcode, rte,revym);
	}
}
