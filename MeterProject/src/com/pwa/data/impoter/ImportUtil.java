package com.pwa.data.impoter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.pwa.common.CUtil;
import com.pwa.common.DateUtil;
import com.pwa.common.Env;
import com.pwa.data.model.detail.Detail;

public class ImportUtil {
	static Logger log = Logger.getLogger(ImportUtil.class);

	public static String[] splitBySC(String str) {
		String del = ";";
		String[] ss = str.split(del);

		for (int i = 0; i < ss.length; i++) {
			ss[i] = ss[i].trim();
			if ("".equals(ss[i])) {
				ss[i] = null;
			}
		}

		return ss;
	}
	public static String[] splitByComma(String str) {
		String del = ",";
		String[] ss = str.split(del);

		for (int i = 0; i < ss.length; i++) {
			//System.out.println(" ["+i+"] "+ ss[i]);
			ss[i] = ss[i].replaceAll("\"", "");
			ss[i] = ss[i].trim();
			if ("".equals(ss[i])) {
				ss[i] = null;
			}
		}

		return ss;
	}
	// "xxxx" "yyyy" --> xxx,yyy
	public static String[] splitByDQ(String str) {
		ArrayList r = new ArrayList();
		String del = "\"";
		int start = 0;
		int end;
		// substring util can't find "
		boolean isEnd = false;
		for (int i = 1; !isEnd; i++) {

			start = str.indexOf(del, start) + 1; // go x
			end = str.indexOf(del, start);
			if (start == 0 || end == -1) { // start+1 already
				isEnd = true;
				// System.out.println(i+": ["+start+","+end+"]  "+":end");
			}
			if (!isEnd) {
				String part = str.substring(start, end);
				// System.out.println(i+": ["+start+","+end+"]  "+part);
				r.add(part);
				start = end + 1;
			}
		}
		// System.out.println(r);
		return (String[]) r.toArray(new String[] {});
	}

	/**
	 * default format =dd/MM/yyyy locale=US
	 */
	public static Date toDate(String s) throws Exception {
		Date r = null;
		if (!CUtil.isBlank(s)) {
			s = s.trim();
			try {
				r = DateUtil.parseEngDateFromFile(s);
			} catch (Exception e) {
				log.error("Can't parse " + s + " to Date");
				throw e;
			}
		}
		// System.out.println(r);
		return r;
	}

	public static Date toDate(String s, String pattern, Locale locale) throws Exception {
		Date r = null;
		if (!CUtil.isBlank(s)) {
			s = s.trim();
			try {
				r = new SimpleDateFormat(pattern, locale).parse(s);
			} catch (Exception e) {
				log.error("Can't parse " + s + " to Date");
				throw e;
			}
		}
		// System.out.println(r);
		return r;
	}

	public static double toDouble(String s) throws Exception {
		double r = 0;
		if (!CUtil.isBlank(s)) {
			s = s.trim();
			try {
				BigDecimal d = new BigDecimal(s);
				r = d.doubleValue();
			} catch (Exception e) {
				log.error("Can't parse " + s + " to Date");
				throw e;
			}
		}
		// System.out.println(r);
		return r;
	}

	public static int toInt(String s) throws Exception {
		int r = 0;
		if (!CUtil.isBlank(s)) {
			s = s.trim();
			try {
				BigDecimal d = new BigDecimal(s);
				r = d.intValue();
			} catch (Exception e) {
				log.error("Can't parse " + s + " to Date");
				throw e;
			}
		}
		// System.out.println(r);
		return r;
	}

	// still not implement
	public static String toStr(String s) {
		if ("".equals(s)) {
			return null;
		}
		return s;
	}
	
	/**
	 * set common body detail
	 */
	public static int setInputDetail(String[] parts, Detail r, int i) throws Exception {
		return setInputDetail(parts, r, i, false);
	}
	
	/**
	 * set common body detail (specific isCallFromAssignImporter for date format)
	 */
	public static int setInputDetail(String[] parts, Detail r, int i, boolean isCallFromAssignImporter) throws Exception {
		r.setSeq(parts[i++]); //5 
		r.setAddr(parts[i++]);//6
		r.setUsetype(parts[i++]);//7
		r.setMtrmkcode(parts[i++]);//8
		r.setMetersize(parts[i++]);//9
		//r.setCuststat(parts[i++]);
		r.setMeterno(parts[i++]);//10
		r.setSrvfee(toDouble(parts[i++]));//11
		r.setNortrfwt(toDouble(parts[i++]));//12
		r.setBillamt(toDouble(parts[i++]));//13
		r.setVat(toDouble(parts[i++]));//14
		r.setTottrfwt(toDouble(parts[i++]));//15
		r.setDiscntamt(toDouble(parts[i++]));//16
		r.setAlltoprice(ImportUtil.toDouble(parts[i++]));//17
		r.setPrsmtrrddty(parts[i++]);//18
		r.setPrsmtrrddtm(parts[i++]);//19
		r.setPrsmtrrddtd(parts[i++]);//20
		r.setPrsmtrcnt(toInt(parts[i++]));//21
		r.setNewcons(toInt(parts[i++]));//22
		r.setComment(parts[i++]);//23
		r.setCommentdec(parts[i++]);//24
		r.setTime(parts[i++]);//25
		r.setReadflag(parts[i++]);//26
		r.setMlocation(parts[i++]);//27
		r.setMincharge(parts[i++]);//28
		r.setBillflag(toStr(parts[i++]));//29
		r.setBillsend(parts[i++]);//30
		r.setMtrstat(parts[i++]);//31
		r.setNewread((parts[i++])); // 32 char1
		r.setCodeid(parts[i++]);//33
		r.setNotimes(ImportUtil.toInt(parts[i++]));//34
		r.setUnitdiscnt(toInt(parts[i++]));//35
		r.setHln(parts[i++]);//36
		r.setOkread(parts[i++]);//37
		r.setInvoicecnt(parts[i++]);//38
		r.setLatitude(parts[i++]);//39
		r.setLongitude(parts[i++]);//40
		r.setLstmtrcnt(toInt(parts[i++]));//41
		r.setAvgwtusg(toInt(parts[i++]));//42
		//r.setNomonth(toInt(parts[i++]));
		//r.setNovat(parts[i++]);
		r.setOldtype(parts[i++]);//43
		r.setName(parts[i++]);//44
		r.setInvflag(parts[i++]);//45
		if (isCallFromAssignImporter) { //46
			r.setLstmtrddt(toDate(parts[i++],"yyMMdd",Env.LOCALE_TH));
		} else {
			r.setLstmtrddt(toDate(parts[i++],"yyMMdd",Env.LOCALE_TH));
		}
		r.setDocdate(parts[i++]);//47
		r.setNoprint(toInt(parts[i++]));//48
		r.setNoclear(toInt(parts[i++]));//49
		r.setAllsubusg(parts[i++]);
		if(r.getPrsmtrrddtd() != null && r.getPrsmtrrddtm() !=null && r.getPrsmtrrddty() != null ){
		   r.setPrsmtrrddt(toDate(r.getPrsmtrrddtd()+r.getPrsmtrrddtm()+r.getPrsmtrrddty(),"ddMMyy",Env.LOCALE_TH));
		}
		//r.setPrswtusg(toInt(parts[i++]));
		//r.setDiscntcode(parts[i++]);
		//r.setNoofhouse(toInt(parts[i++]));
		//r.setPrintflag(parts[i++]);
		//r.setEst(toInt(parts[i++]));
		//r.setWaterest(toInt(parts[i++]));
		//r.getId().setBillmonth(parts[i++]); // pk
		//r.setDiscntbath(toInt(parts[i++]));
		
		//r.setOldmtrusg(toInt(parts[i++]));
		
		return i;
	}
}
