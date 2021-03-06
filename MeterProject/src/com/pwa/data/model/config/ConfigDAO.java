package com.pwa.data.model.config;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.pwa.data.model.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for Config entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of
 * these methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.pwa.data.model.config.Config
 * @author MyEclipse Persistence Tools
 */

public class ConfigDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(ConfigDAO.class);
	// property constants
	public static final String HEAD = "head";
	public static final String TELNO = "telno";
	public static final String LOWTYPE1 = "lowtype1";
	public static final String LOWTYPE2 = "lowtype2";
	public static final String LOWTYPE3 = "lowtype3";
	public static final String BANKDATE = "bankdate";
	public static final String PAYDATE = "paydate";

	public void save(Config transientInstance) {
		log.debug("saving Config instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Config persistentInstance) {
		log.debug("deleting Config instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Config findById(java.lang.String id) {
		log.debug("getting Config instance with id: " + id);
		try {
			Config instance = (Config) getSession().get("com.pwa.data.model.config.Config", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Config instance) {
		log.debug("finding Config instance by example");
		try {
			List results = getSession().createCriteria("com.pwa.data.model.config.Config").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Config instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Config as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHead(Object head) {
		return findByProperty(HEAD, head);
	}

	public List findByTelno(Object telno) {
		return findByProperty(TELNO, telno);
	}

	public List findByLowtype1(Object lowtype1) {
		return findByProperty(LOWTYPE1, lowtype1);
	}

	public List findByLowtype2(Object lowtype2) {
		return findByProperty(LOWTYPE2, lowtype2);
	}

	public List findByLowtype3(Object lowtype3) {
		return findByProperty(LOWTYPE3, lowtype3);
	}

	public List findByBankdate(Object bankdate) {
		return findByProperty(BANKDATE, bankdate);
	}

	public List findByPaydate(Object paydate) {
		return findByProperty(PAYDATE, paydate);
	}

	public List findAll() {
		log.debug("finding all Config instances");
		try {
			String queryString = "from Config";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Config merge(Config detachedInstance) {
		log.debug("merging Config instance");
		try {
			Config result = (Config) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Config instance) {
		log.debug("attaching dirty Config instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Config instance) {
		log.debug("attaching clean Config instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}