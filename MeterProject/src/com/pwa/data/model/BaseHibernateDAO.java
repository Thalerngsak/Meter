package com.pwa.data.model;

import org.hibernate.CacheMode;
import org.hibernate.Session;

import com.HibernateSessionFactory;

/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	public Session getSession() {
		HibernateSessionFactory.getSession().setCacheMode(CacheMode.REFRESH);
		return HibernateSessionFactory.getSession();
		 
	}
	
}