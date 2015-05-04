package com.pwa.data.model.detail;

import java.util.Date;

/**
 * AbstractDetail entity provides the base persistence definition of the Detail entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractDetail implements java.io.Serializable {

	// Fields

	private DetailId id;
	private String type;
	private String seq;
	private String name;
	private String addr;
	//private String custstat;
	private String mtrmkcode;
	private String metersize;
	private String meterno;
	private Double nortrfwt;
	private Double discntamt;
	private Double srvfee;
	private Double vat;
	private Double tottrfwt;
	//private Integer nomonth;
	private Double billamt;
	//private String novat;
	private String oldtype;
	private String usetype;
	private Date lstmtrddt;
	private Integer lstmtrcnt;
	private Integer avgwtusg;
	private Date prsmtrrddt;
	private Integer prsmtrcnt;
	//private Integer prswtusg;
	private String comment;
	private String commentdec;
	private String time;
	//private String discntcode;
	private String readflag;
	private String mlocation;
	private String mincharge;
	//private Integer noofhouse;
	private String printflag;
	private String billflag;
	private String billsend;
	private String invoicecnt;
	private String mtrstat;
	private String newread;
	private String codeid;
	//private Integer est;
	//private Integer waterest;
	//private Integer discntbath;
	private Integer unitdiscnt;
	//private Integer oldmtrusg;
	private String chkdigit;
	private Double alltoprice;
	private Integer notimes;
	private String hln;
	private String okread;
	private Date trandate;
    //Add New Field
	private String prsmtrrddty;
	private String prsmtrrddtm;
	private String prsmtrrddtd;
	private Integer newcons;
	private String latitude;
	private String longitude;
	private String invflag;
	private String docdate;
	private Integer noprint;
	private Integer noclear;
	private String allsubusg;
	//Add AssignImport
	private Date bgncustdt;
	private String accode;
	private String controlmtr;
	private String novat;
	private String discntcode;
	private String pwa_flag;
	private String discntsold;
	private String bigmtrno;
	private Double smcnt;
	private Double subdiscn;
	private String custstat;
	private Integer nomonth;
	private Integer oldmtrusg;
	private Integer noofhouse;
	private Integer est;
	private Integer waterest;
	private String taxno;
	private String branch;
	// Constructors

	/** default constructor */
	public AbstractDetail() {
	}

	/** minimal constructor */
	public AbstractDetail(DetailId id) {
		this.id = id;
	}

	/** full constructor */
	public AbstractDetail(DetailId id, String type, String seq, String name, String addr, String mtrmkcode, String metersize,
			String meterno, Double nortrfwt, Double discntamt, Double srvfee, Double vat, Double tottrfwt, Double billamt,
			String oldtype, String usetype, Date lstmtrddt,Integer lstmtrcnt, Integer avgwtusg, Date prsmtrrddt, Integer prsmtrcnt,
			Integer newcons,String comment, String commentdec, String time, String readflag, String mlocation, String mincharge,
			String billflag, String billsend, String invoicecnt, String mtrstat, String newread, String codeid,
			Integer est, Integer unitdiscnt, String chkdigit, Double alltoprice,
			Integer notimes, String hln, String okread, Date trandate) {
		this.id = id;
		this.type = type;
		this.seq = seq;
		this.name = name;
		this.addr = addr;
		//this.custstat = custstat;
		this.mtrmkcode = mtrmkcode;
		this.metersize = metersize;
		this.meterno = meterno;
		this.nortrfwt = nortrfwt;
		this.discntamt = discntamt;
		this.srvfee = srvfee;
		this.vat = vat;
		this.tottrfwt = tottrfwt;
		//this.nomonth = nomonth;
		this.billamt = billamt;
		//this.novat = novat;
		this.oldtype = oldtype;
		this.usetype = usetype;
		this.lstmtrddt = lstmtrddt;
		this.lstmtrcnt = lstmtrcnt;
		this.avgwtusg = avgwtusg;
		this.prsmtrrddt = prsmtrrddt;
		this.prsmtrcnt = prsmtrcnt;
		//this.prswtusg = prswtusg;
		this.newcons = newcons;
		this.comment = comment;
		this.commentdec = commentdec;
		this.time = time;
		//this.discntcode = discntcode;
		this.readflag = readflag;
		this.mlocation = mlocation;
		this.mincharge = mincharge;
		//this.noofhouse = noofhouse;
		//this.printflag = printflag;
		this.billflag = billflag;
		this.billsend = billsend;
		this.invoicecnt = invoicecnt;
		this.mtrstat = mtrstat;
		this.newread = newread;
		this.codeid = codeid;
		//this.est = est;
		//this.waterest = waterest;
		//this.discntbath = discntbath;
		this.unitdiscnt = unitdiscnt;
		//this.oldmtrusg = oldmtrusg;
		this.chkdigit = chkdigit;
		this.alltoprice = alltoprice;
		this.notimes = notimes;
		this.hln = hln;
		this.okread = okread;
		this.trandate = trandate;
	}

	// Property accessors

	public DetailId getId() {
		return this.id;
	}

	public void setId(DetailId id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeq() {
		return this.seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

//	public String getCuststat() {
//		return this.custstat;
//	}
//
//	public void setCuststat(String custstat) {
//		this.custstat = custstat;
//	}

	public String getMtrmkcode() {
		return this.mtrmkcode;
	}

	public void setMtrmkcode(String mtrmkcode) {
		this.mtrmkcode = mtrmkcode;
	}

	public String getMetersize() {
		return this.metersize;
	}

	public void setMetersize(String metersize) {
		this.metersize = metersize;
	}

	public String getMeterno() {
		return this.meterno;
	}

	public void setMeterno(String meterno) {
		this.meterno = meterno;
	}

	public Double getNortrfwt() {
		return this.nortrfwt;
	}

	public void setNortrfwt(Double nortrfwt) {
		this.nortrfwt = nortrfwt;
	}

	public Double getDiscntamt() {
		return this.discntamt;
	}

	public void setDiscntamt(Double discntamt) {
		this.discntamt = discntamt;
	}

	public Double getSrvfee() {
		return this.srvfee;
	}

	public void setSrvfee(Double srvfee) {
		this.srvfee = srvfee;
	}

	public Double getVat() {
		return this.vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	public Double getTottrfwt() {
		return this.tottrfwt;
	}

	public void setTottrfwt(Double tottrfwt) {
		this.tottrfwt = tottrfwt;
	}

//	public Integer getNomonth() {
//		return this.nomonth;
//	}
//
//	public void setNomonth(Integer nomonth) {
//		this.nomonth = nomonth;
//	}

	public Double getBillamt() {
		return this.billamt;
	}

	public void setBillamt(Double billamt) {
		this.billamt = billamt;
	}
//
//	public String getNovat() {
//		return this.novat;
//	}
//
//	public void setNovat(String novat) {
//		this.novat = novat;
//	}

	public String getOldtype() {
		return this.oldtype;
	}

	public void setOldtype(String oldtype) {
		this.oldtype = oldtype;
	}

	public String getUsetype() {
		return this.usetype;
	}

	public void setUsetype(String usetype) {
		this.usetype = usetype;
	}

	public Date getLstmtrddt() {
		return this.lstmtrddt;
	}

	public void setLstmtrddt(Date lstmtrddt) {
		this.lstmtrddt = lstmtrddt;
	}

	public Integer getLstmtrcnt() {
		return this.lstmtrcnt;
	}

	public void setLstmtrcnt(Integer lstmtrcnt) {
		this.lstmtrcnt = lstmtrcnt;
	}

	public Integer getAvgwtusg() {
		return this.avgwtusg;
	}

	public void setAvgwtusg(Integer avgwtusg) {
		this.avgwtusg = avgwtusg;
	}

	public Date getPrsmtrrddt() {
		return this.prsmtrrddt;
	}

	public void setPrsmtrrddt(Date prsmtrrddt) {
		this.prsmtrrddt = prsmtrrddt;
	}

	public Integer getPrsmtrcnt() {
		return this.prsmtrcnt;
	}

	public void setPrsmtrcnt(Integer prsmtrcnt) {
		this.prsmtrcnt = prsmtrcnt;
	}

//	public Integer getPrswtusg() {
//		return this.prswtusg;
//	}
//
//	public void setPrswtusg(Integer prswtusg) {
//		this.prswtusg = prswtusg;
//	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentdec() {
		return this.commentdec;
	}

	public void setCommentdec(String commentdec) {
		this.commentdec = commentdec;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

//	public String getDiscntcode() {
//		return this.discntcode;
//	}
//
//	public void setDiscntcode(String discntcode) {
//		this.discntcode = discntcode;
//	}

	public String getReadflag() {
		return this.readflag;
	}

	public void setReadflag(String readflag) {
		this.readflag = readflag;
	}

	public String getMlocation() {
		return this.mlocation;
	}

	public void setMlocation(String mlocation) {
		this.mlocation = mlocation;
	}

	public String getMincharge() {
		return this.mincharge;
	}

	public void setMincharge(String mincharge) {
		this.mincharge = mincharge;
	}

//	public Integer getNoofhouse() {
//		return this.noofhouse;
//	}
//
//	public void setNoofhouse(Integer noofhouse) {
//		this.noofhouse = noofhouse;
//	}
//
//	public String getPrintflag() {
//		return this.printflag;
//	}
//
//	public void setPrintflag(String printflag) {
//		this.printflag = printflag;
//	}

	public String getBillflag() {
		return this.billflag;
	}

	public void setBillflag(String billflag) {
		this.billflag = billflag;
	}

	public String getBillsend() {
		return this.billsend;
	}

	public void setBillsend(String billsend) {
		this.billsend = billsend;
	}

	public String getInvoicecnt() {
		return this.invoicecnt;
	}

	public void setInvoicecnt(String invoicecnt) {
		this.invoicecnt = invoicecnt;
	}

	public String getMtrstat() {
		return this.mtrstat;
	}

	public void setMtrstat(String mtrstat) {
		this.mtrstat = mtrstat;
	}

	public String getNewread() {
		return this.newread;
	}

	public void setNewread(String newread) {
		this.newread = newread;
	}

	public String getCodeid() {
		return this.codeid;
	}

	public void setCodeid(String codeid) {
		this.codeid = codeid;
	}
//
//	public Integer getEst() {
//		return this.est;
//	}
//
//	public void setEst(Integer est) {
//		this.est = est;
//	}
//
//	public Integer getWaterest() {
//		return this.waterest;
//	}
//
//	public void setWaterest(Integer waterest) {
//		this.waterest = waterest;
//	}
//
//	public Integer getDiscntbath() {
//		return this.discntbath;
//	}
//
//	public void setDiscntbath(Integer discntbath) {
//		this.discntbath = discntbath;
//	}

	public Integer getUnitdiscnt() {
		return this.unitdiscnt;
	}

	public void setUnitdiscnt(Integer unitdiscnt) {
		this.unitdiscnt = unitdiscnt;
	}

//	public Integer getOldmtrusg() {
//		return this.oldmtrusg;
//	}
//
//	public void setOldmtrusg(Integer oldmtrusg) {
//		this.oldmtrusg = oldmtrusg;
//	}

	public String getChkdigit() {
		return this.chkdigit;
	}

	public void setChkdigit(String chkdigit) {
		this.chkdigit = chkdigit;
	}

	public Double getAlltoprice() {
		return this.alltoprice;
	}

	public void setAlltoprice(Double alltoprice) {
		this.alltoprice = alltoprice;
	}

	public Integer getNotimes() {
		return this.notimes;
	}

	public void setNotimes(Integer notimes) {
		this.notimes = notimes;
	}

	public String getHln() {
		return this.hln;
	}

	public void setHln(String hln) {
		this.hln = hln;
	}

	public String getOkread() {
		return this.okread;
	}

	public void setOkread(String okread) {
		this.okread = okread;
	}

	public Date getTrandate() {
		return this.trandate;
	}

	public void setTrandate(Date trandate) {
		this.trandate = trandate;
	}

	public String getPrsmtrrddty() {
		return prsmtrrddty;
	}

	public void setPrsmtrrddty(String prsmtrrddty) {
		this.prsmtrrddty = prsmtrrddty;
	}

	public String getPrsmtrrddtm() {
		return prsmtrrddtm;
	}

	public void setPrsmtrrddtm(String prsmtrrddtm) {
		this.prsmtrrddtm = prsmtrrddtm;
	}

	public String getPrsmtrrddtd() {
		return prsmtrrddtd;
	}

	public void setPrsmtrrddtd(String prsmtrrddtd) {
		this.prsmtrrddtd = prsmtrrddtd;
	}

	public Integer getNewcons() {
		return newcons;
	}

	public void setNewcons(Integer newcons) {
		this.newcons = newcons;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getInvflag() {
		return invflag;
	}

	public void setInvflag(String invflag) {
		this.invflag = invflag;
	}

	public String getDocdate() {
		return docdate;
	}

	public void setDocdate(String docdate) {
		this.docdate = docdate;
	}

	public Integer getNoprint() {
		return noprint;
	}

	public void setNoprint(Integer noprint) {
		this.noprint = noprint;
	}

	public Integer getNoclear() {
		return noclear;
	}

	public void setNoclear(Integer noclear) {
		this.noclear = noclear;
	}

	public String getAllsubusg() {
		return allsubusg;
	}

	public void setAllsubusg(String allsubusg) {
		this.allsubusg = allsubusg;
	}

	public Date getBgncustdt() {
		return bgncustdt;
	}

	public void setBgncustdt(Date bgncustdt) {
		this.bgncustdt = bgncustdt;
	}

	public String getAccode() {
		return accode;
	}

	public void setAccode(String accode) {
		this.accode = accode;
	}

	public String getControlmtr() {
		return controlmtr;
	}

	public void setControlmtr(String controlmtr) {
		this.controlmtr = controlmtr;
	}

	public String getNovat() {
		return novat;
	}

	public void setNovat(String novat) {
		this.novat = novat;
	}

	public String getDiscntcode() {
		return discntcode;
	}

	public void setDiscntcode(String discntcode) {
		this.discntcode = discntcode;
	}

	public String getPwa_flag() {
		return pwa_flag;
	}

	public void setPwa_flag(String pwa_flag) {
		this.pwa_flag = pwa_flag;
	}

	public String getDiscntsold() {
		return discntsold;
	}

	public void setDiscntsold(String discntsold) {
		this.discntsold = discntsold;
	}

	public String getBigmtrno() {
		return bigmtrno;
	}

	public void setBigmtrno(String bigmtrno) {
		this.bigmtrno = bigmtrno;
	}

	public Double getSmcnt() {
		return smcnt;
	}

	public void setSmcnt(Double smcnt) {
		this.smcnt = smcnt;
	}

	public Double getSubdiscn() {
		return subdiscn;
	}

	public void setSubdiscn(Double subdiscn) {
		this.subdiscn = subdiscn;
	}

	public String getCuststat() {
		return custstat;
	}

	public void setCuststat(String custstat) {
		this.custstat = custstat;
	}

	public Integer getNomonth() {
		return nomonth;
	}

	public void setNomonth(Integer nomonth) {
		this.nomonth = nomonth;
	}

	public Integer getOldmtrusg() {
		return oldmtrusg;
	}

	public void setOldmtrusg(Integer oldmtrusg) {
		this.oldmtrusg = oldmtrusg;
	}

	public Integer getNoofhouse() {
		return noofhouse;
	}

	public void setNoofhouse(Integer noofhouse) {
		this.noofhouse = noofhouse;
	}

	public Integer getEst() {
		return est;
	}

	public void setEst(Integer est) {
		this.est = est;
	}

	public Integer getWaterest() {
		return waterest;
	}

	public void setWaterest(Integer waterest) {
		this.waterest = waterest;
	}

	public String getTaxno() {
		return taxno;
	}

	public void setTaxno(String taxno) {
		this.taxno = taxno;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

    public String getPrintflag() {
        return printflag;
    }

    public void setPrintflag(String printflag) {
        this.printflag = printflag;
    }

}