package com.pwa.data.model.head;

/**
 * Config entity. @author MyEclipse Persistence Tools
 */
public class Head extends AbstractHead implements java.io.Serializable {

	/**
	 * @param id
	 * @param br
	 * @param zn
	 * @param rte
	 * @param totnew
	 * @param totrec
	 * @param totread
	 * @param readerid
	 * @param assigndate
	 * @param sendflag
	 */
	public Head(String id, String br, String zn, String rte, String totnew,
			String totrec, String totread, String readerid, String assigndate,
			String sendflag) {
		super(id, br, zn, rte, totnew, totrec, totread, readerid, assigndate, sendflag);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	public Head() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructors

	
}
