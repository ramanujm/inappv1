package com.duke.db.beans;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;




//Imports ...

@Transactional
public interface Dao extends CrudRepository<Login, Long> {

/**
* This method will find an User instance in the database by its email.
* Note that this method is not implemented and its working code will be
* automagically generated from its signature by Spring Data JPA.
*/
public Login findByEmail(String email);

}