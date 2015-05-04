package com.pwa.data.model.detail;

import java.util.List;

import org.apache.log4j.Logger;

public class DetailDAOExt extends DetailDAO {
	private static final Logger log = Logger.getLogger(DetailDAOExt.class);

	public void saveOrUpdate(Detail transientInstance) {
		log.debug("saving Detail instance");
		try {
			getSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List findByReadTrandateAndCodeID(Detail din) {
		log.debug("findByReadTrandateAndCodeID");
		try {
			Detail d = new Detail();
			d.setReadflag("1");
			d.setTrandate(din.getTrandate());
			d.setCodeid(din.getCodeid());
			List list = findByExample(d);
			log.debug("findByReadTrandateAndCodeID success");
			return list;
		} catch (RuntimeException re) {
			log.error("findByReadTrandateAndCodeID failed", re);
			throw re;
		}
	}
}
