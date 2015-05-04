package com.pwa.web.view;

import com.pwa.data.model.head.AbstractHead;

public class CheckPlanView extends AbstractHead {
	private String prsmtrrddtStr;
	private String custcode;
	private String mtrrdrname;
	private String diff; // abnormal
	
	/**
	 * 
	 */
	public CheckPlanView() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public CheckPlanView(String id, String br, String zn, String rte,
			String totnew, String totrec, String totread, String readerid,
			String assigndate, String sendflag) {
		super(id, br, zn, rte, totnew, totrec, totread, readerid, assigndate, sendflag);
		// TODO Auto-generated constructor stub
	}
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
	 * @param prsmtrrddtStr
	 * @param custcode
	 * @param mtrrdrname
	 * @param diff
	 */
	public CheckPlanView(String id, String br, String zn, String rte,
			String totnew, String totrec, String totread, String readerid,
			String assigndate, String sendflag, String prsmtrrddtStr,
			String custcode, String mtrrdrname, String diff) {
		super(id, br, zn, rte, totnew, totrec, totread, readerid, assigndate,
				sendflag);
		this.prsmtrrddtStr = prsmtrrddtStr;
		this.custcode = custcode;
		this.mtrrdrname = mtrrdrname;
		this.diff = diff;
	}
	/**
	 * @return the prsmtrrddtStr
	 */
	public String getPrsmtrrddtStr() {
		return prsmtrrddtStr;
	}
	/**
	 * @param prsmtrrddtStr the prsmtrrddtStr to set
	 */
	public void setPrsmtrrddtStr(String prsmtrrddtStr) {
		this.prsmtrrddtStr = prsmtrrddtStr;
	}
	/**
	 * @return the custcode
	 */
	public String getCustcode() {
		return custcode;
	}
	/**
	 * @param custcode the custcode to set
	 */
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}
	/**
	 * @return the mtrrdrname
	 */
	public String getMtrrdrname() {
		return mtrrdrname;
	}
	/**
	 * @param mtrrdrname the mtrrdrname to set
	 */
	public void setMtrrdrname(String mtrrdrname) {
		this.mtrrdrname = mtrrdrname;
	}
	/**
	 * @return the diff
	 */
	public String getDiff() {
		return diff;
	}
	/**
	 * @param diff the diff to set
	 */
	public void setDiff(String diff) {
		this.diff = diff;
	}
	

}
