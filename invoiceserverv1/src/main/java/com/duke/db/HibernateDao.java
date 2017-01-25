package com.duke.db;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateDao {

	  
	  @Autowired
	  private SessionFactory sessionFactory;
	  
	  
	  
	  public <T> T save(final T o){
	      return (T) getCurrentSession().save(o);
	    }


	    public void delete(final Object object){
	      getCurrentSession().delete(object);
	    }

	    /***/
	    public <T> T get(final Class<T> type, final Long id){
	      return (T) getCurrentSession().get(type, id);
	    }

	    /***/
	    public <T> T merge(final T o)   {
	      return (T) getCurrentSession().merge(o);
	    }

	    /***/
	    public <T> void saveOrUpdate(final T o){
	      getCurrentSession().saveOrUpdate(o);
	    }

	    public <T> List<T> getAll(final Class<T> type) {
	      final Session session = getCurrentSession();
	      final Criteria crit = session.createCriteria(type);
	  return crit.list();
	    }
	  
	  
	    public <T>  T getByField(String fieldName, Object value , final Class<T> type) {
		      final Session session = getCurrentSession();
		      final Criteria crit = session.createCriteria(type);
		      crit.add(Restrictions.eq(fieldName, value));
		      return crit.list().isEmpty()  ? null : (T)crit.list().get(0); // return the first element.
		   
	    }
	  
	
		   
	  private Session getCurrentSession() {
	    return sessionFactory.getCurrentSession();
	  }
	  
	  
}
