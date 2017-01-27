package com.duke.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.duke.db.HibernateDao;

public class BaseService {

	 @Autowired
	 private HibernateDao hibenrateDao;

	public HibernateDao getHibenrateDao() {
		return hibenrateDao;
	}

	public void setHibenrateDao(HibernateDao hibenrateDao) {
		this.hibenrateDao = hibenrateDao;
	}

	 
	
}
