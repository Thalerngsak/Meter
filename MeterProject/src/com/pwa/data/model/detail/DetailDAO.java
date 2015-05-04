package com.pwa.data.model.detail;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.pwa.data.model.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for Detail entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of
 * these methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.pwa.data.model.detail.Detail
 * @author MyEclipse Persistence Tools
 */

public class DetailDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(DetailDAO.class);
	// property constants
	public static final String TYPE = "type";
	public static final String SEQ = "seq";
	public static final String NAME = "name";
	public static final String ADDR = "addr";
	public static final String CUSTSTAT = "custstat";
	public static final String MTRMKCODE = "mtrmkcode";
	public static final String METERSIZE = "metersize";
	public static final String METERNO = "meterno";
	public static final String NORTRFWT = "nortrfwt";
	public static final String DISCNTAMT = "discntamt";
	public static final String SRVFEE = "srvfee";
	public static final String VAT = "vat";
	public static final String TOTTRFWT = "tottrfwt";
	public static final String NOMONTH = "nomonth";
	public static final String BILLAMT = "billamt";
	public static final String NOVAT = "novat";
	public static final String OLDTYPE = "oldtype";
	public static final String USETYPE = "usetype";
	public static final String LSTMTRCNT = "lstmtrcnt";
	public static final String AVGWTUSG = "avgwtusg";
	public static final String PRSMTRCNT = "prsmtrcnt";
	public static final String PRSWTUSG = "prswtusg";
	public static final String COMMENT = "comment";
	public static final String COMMENTDEC = "commentdec";
	public static final String TIME = "time";
	public static final String DISCNTCODE = "discntcode";
	public static final String READFLAG = "readflag";
	public static final String MLOCATION = "mlocation";
	public static final String MINCHARGE = "mincharge";
	public static final String NOOFHOUSE = "noofhouse";
	public static final String PRINTFLAG = "printflag";
	public static final String BILLFLAG = "billflag";
	public static final String BILLSEND = "billsend";
	public static final String INVOICECNT = "invoicecnt";
	public static final String MTRSTAT = "mtrstat";
	public static final String NEWREAD = "newread";
	public static final String CODEID = "codeid";
	public static final String EST = "est";
	public static final String WATEREST = "waterest";
	public static final String DISCNTBATH = "discntbath";
	public static final String UNITDISCNT = "unitdiscnt";
	public static final String OLDMTRUSG = "oldmtrusg";
	public static final String CHKDIGIT = "chkdigit";
	public static final String ALLTOPRICE = "alltoprice";
	public static final String NOTIMES = "notimes";
	public static final String HLN = "hln";
	public static final String OKREAD = "okread";

	public void save(Detail transientInstance) {
		log.debug("saving Detail instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Detail persistentInstance) {
		log.debug("deleting Detail instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Detail findById(com.pwa.data.model.detail.DetailId id) {
		log.debug("getting Detail instance with id: " + id);
		try {
			Detail instance = (Detail) getSession().get("com.pwa.data.model.detail.Detail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Detail instance) {
		log.debug("finding Detail instance by example");
		try {
			List results = getSession().createCriteria("com.pwa.data.model.detail.Detail").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	public List findByExample(DetailHistory instance) {
		log.debug("finding Detail instance by example");
		try {
			List results = getSession().createCriteria("com.pwa.data.model.detail.Detail").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Detail instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Detail as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByType(Object type) {
		return findByProperty(TYPE, type);
	}

	public List findBySeq(Object seq) {
		return findByProperty(SEQ, seq);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByAddr(Object addr) {
		return findByProperty(ADDR, addr);
	}

	public List findByCuststat(Object custstat) {
		return findByProperty(CUSTSTAT, custstat);
	}

	public List findByMtrmkcode(Object mtrmkcode) {
		return findByProperty(MTRMKCODE, mtrmkcode);
	}

	public List findByMetersize(Object metersize) {
		return findByProperty(METERSIZE, metersize);
	}

	public List findByMeterno(Object meterno) {
		return findByProperty(METERNO, meterno);
	}

	public List findByNortrfwt(Object nortrfwt) {
		return findByProperty(NORTRFWT, nortrfwt);
	}

	public List findByDiscntamt(Object discntamt) {
		return findByProperty(DISCNTAMT, discntamt);
	}

	public List findBySrvfee(Object srvfee) {
		return findByProperty(SRVFEE, srvfee);
	}

	public List findByVat(Object vat) {
		return findByProperty(VAT, vat);
	}

	public List findByTottrfwt(Object tottrfwt) {
		return findByProperty(TOTTRFWT, tottrfwt);
	}

	public List findByNomonth(Object nomonth) {
		return findByProperty(NOMONTH, nomonth);
	}

	public List findByBillamt(Object billamt) {
		return findByProperty(BILLAMT, billamt);
	}

	public List findByNovat(Object novat) {
		return findByProperty(NOVAT, novat);
	}

	public List findByOldtype(Object oldtype) {
		return findByProperty(OLDTYPE, oldtype);
	}

	public List findByUsetype(Object usetype) {
		return findByProperty(USETYPE, usetype);
	}

	public List findByLstmtrcnt(Object lstmtrcnt) {
		return findByProperty(LSTMTRCNT, lstmtrcnt);
	}

	public List findByAvgwtusg(Object avgwtusg) {
		return findByProperty(AVGWTUSG, avgwtusg);
	}

	public List findByPrsmtrcnt(Object prsmtrcnt) {
		return findByProperty(PRSMTRCNT, prsmtrcnt);
	}

	public List findByPrswtusg(Object prswtusg) {
		return findByProperty(PRSWTUSG, prswtusg);
	}

	public List findByComment(Object comment) {
		return findByProperty(COMMENT, comment);
	}

	public List findByCommentdec(Object commentdec) {
		return findByProperty(COMMENTDEC, commentdec);
	}

	public List findByTime(Object time) {
		return findByProperty(TIME, time);
	}

	public List findByDiscntcode(Object discntcode) {
		return findByProperty(DISCNTCODE, discntcode);
	}

	public List findByReadflag(Object readflag) {
		return findByProperty(READFLAG, readflag);
	}

	public List findByMlocation(Object mlocation) {
		return findByProperty(MLOCATION, mlocation);
	}

	public List findByMincharge(Object mincharge) {
		return findByProperty(MINCHARGE, mincharge);
	}

	public List findByNoofhouse(Object noofhouse) {
		return findByProperty(NOOFHOUSE, noofhouse);
	}

	public List findByPrintflag(Object printflag) {
		return findByProperty(PRINTFLAG, printflag);
	}

	public List findByBillflag(Object billflag) {
		return findByProperty(BILLFLAG, billflag);
	}

	public List findByBillsend(Object billsend) {
		return findByProperty(BILLSEND, billsend);
	}

	public List findByInvoicecnt(Object invoicecnt) {
		return findByProperty(INVOICECNT, invoicecnt);
	}

	public List findByMtrstat(Object mtrstat) {
		return findByProperty(MTRSTAT, mtrstat);
	}

	public List findByNewread(Object newread) {
		return findByProperty(NEWREAD, newread);
	}

	public List findByCodeid(Object codeid) {
		return findByProperty(CODEID, codeid);
	}

	public List findByEst(Object est) {
		return findByProperty(EST, est);
	}

	public List findByWaterest(Object waterest) {
		return findByProperty(WATEREST, waterest);
	}

	public List findByDiscntbath(Object discntbath) {
		return findByProperty(DISCNTBATH, discntbath);
	}

	public List findByUnitdiscnt(Object unitdiscnt) {
		return findByProperty(UNITDISCNT, unitdiscnt);
	}

	public List findByOldmtrusg(Object oldmtrusg) {
		return findByProperty(OLDMTRUSG, oldmtrusg);
	}

	public List findByChkdigit(Object chkdigit) {
		return findByProperty(CHKDIGIT, chkdigit);
	}

	public List findByAlltoprice(Object alltoprice) {
		return findByProperty(ALLTOPRICE, alltoprice);
	}

	public List findByNotimes(Object notimes) {
		return findByProperty(NOTIMES, notimes);
	}

	public List findByHln(Object hln) {
		return findByProperty(HLN, hln);
	}

	public List findByOkread(Object okread) {
		return findByProperty(OKREAD, okread);
	}

	public List findAll() {
		log.debug("finding all Detail instances");
		try {
			String queryString = "from Detail";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Detail merge(Detail detachedInstance) {
		log.debug("merging Detail instance");
		try {
			Detail result = (Detail) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Detail instance) {
		log.debug("attaching dirty Detail instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Detail instance) {
		log.debug("attaching clean Detail instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}