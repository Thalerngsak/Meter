package com.pwa.data.model.detail;

import java.util.List;

import org.apache.log4j.Logger;

public class DetailHistoryDAOExt extends DetailDAO {
	private static final Logger log = Logger.getLogger(DetailHistoryDAOExt.class);

	public void saveOrUpdate(DetailHistory transientInstance) {
		log.debug("saving Detail instance");
		try {
			getSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public List findByReadTrandateAndCodeID(DetailHistory din) {
		log.debug("findByReadTrandateAndCodeID");
		try {
			DetailHistory d = new DetailHistory();
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
