package com.pwa.data.model.dbst44;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.pwa.data.model.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for Dbst44 entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of
 * these methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.pwa.data.model.dbst44.Dbst44
 * @author MyEclipse Persistence Tools
 */

public class Dbst44DAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(Dbst44DAO.class);
	// property constants
	public static final String MTRRDRNAME = "mtrrdrname";

	public void save(Dbst44 transientInstance) {
		log.debug("saving Dbst44 instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Dbst44 persistentInstance) {
		log.debug("deleting Dbst44 instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Dbst44 findById(java.lang.String id) {
		log.debug("getting Dbst44 instance with id: " + id);
		try {
			Dbst44 instance = (Dbst44) getSession().get("com.pwa.data.model.dbst44.Dbst44", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Dbst44 instance) {
		log.debug("finding Dbst44 instance by example");
		try {
			List results = getSession().createCriteria("com.pwa.data.model.dbst44.Dbst44").add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Dbst44 instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Dbst44 as model where model." + propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByMtrrdrname(Object mtrrdrname) {
		return findByProperty(MTRRDRNAME, mtrrdrname);
	}

	public List findAll() {
		log.debug("finding all Dbst44 instances");
		try {
			String queryString = "from Dbst44";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Dbst44 merge(Dbst44 detachedInstance) {
		log.debug("merging Dbst44 instance");
		try {
			Dbst44 result = (Dbst44) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Dbst44 instance) {
		log.debug("attaching dirty Dbst44 instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Dbst44 instance) {
		log.debug("attaching clean Dbst44 instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}