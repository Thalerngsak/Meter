package com.pwa.data.model.head;

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

public class HeadDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(HeadDAO.class);
	// property constants
	public static final String BR = "br";
	public static final String ZN = "zn";
	public static final String RTE = "rte";
	public static final String TOTNEW = "totnew";
	public static final String TOTREC = "totrec";
	public static final String TOTREAD = "totread";
	public static final String READERID = "readerid";
	public static final String ASSIGNDATE = "assigndate";
	public static final String SENDFLAG = "sendflag";

	public void save(Head transientInstance) {
		log.debug("saving Head instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void saveOrUpdate(Head transientInstance) {
		log.debug("saving Head instance");
		try {
			getSession().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
	public void delete(Head persistentInstance) {
		log.debug("deleting Head instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Head findById(com.pwa.data.model.head.HeadId id) {
		log.debug("getting Head instance with id: " + id);
		try {
			Head instance = (Head) getSession().get("com.pwa.data.model.head.Head", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Head instance) {
		log.debug("finding Head instance by example");
		try {
			List results = getSession().createCriteria("com.pwa.data.model.head.Head").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Head instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Head as model where model." + propertyName + " = ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBR(Object br) {
		return findByProperty(BR, br);
	}

	public List findByZN(Object zn) {
		return findByProperty(ZN, zn);
	}

	public List findByRTE(Object rte) {
		return findByProperty(RTE, rte);
	}

	public List findByTotNew(Object totNew) {
		return findByProperty(TOTNEW, totNew);
	}

	public List findByTotRe(Object totRec) {
		return findByProperty(TOTREC, totRec);
	}

	public List findByTotRead(Object totRead) {
		return findByProperty(TOTREAD, totRead);
	}

	public List findByReaderId(Object readerId) {
		return findByProperty(READERID, readerId);
	}
	public List findByAssignDate(Object assignDate) {
		return findByProperty(ASSIGNDATE, assignDate);
	}

	public List findBySendFlag(Object sendFlag) {
		return findByProperty(SENDFLAG, sendFlag);
	}
	public List findAll() {
		log.debug("finding all Head instances");
		try {
			String queryString = "from Head";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Head merge(Head detachedInstance) {
		log.debug("merging Head instance");
		try {
			Head result = (Head) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Head instance) {
		log.debug("attaching dirty Head instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Head instance) {
		log.debug("attaching clean Head instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}