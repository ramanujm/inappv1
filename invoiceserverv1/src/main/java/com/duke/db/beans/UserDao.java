package com.duke.db.beans;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
  
  @Autowired
  private SessionFactory sessionFactory;
  

  private Session getSession() {
    return sessionFactory.getCurrentSession();
  }

  public void save(Login login) {
    System.out.println("Saving inside the login...");
	  getSession().save(login);
    return;
  }
  
  public void getUser(String email) {
	    System.out.println("Saving inside the login...");
		//  getSession().get;
	    return;
	  }
  
} // class UserDao
