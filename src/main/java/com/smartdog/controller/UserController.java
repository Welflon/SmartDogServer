package com.smartdog.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.smartdog.model.User;

@Transactional
@Repository
public class UserController {
	
	@PersistenceContext
	private EntityManager em;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	public User findUserById (int id) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM UserModel u WHERE u.id=?1", User.class);
		query.setParameter(1, id);
		User model = query.getSingleResult();
		if (model == null) {
			logger.warn("User with id " + id + " not found.");
			return null;
		} else {
			logger.info("User found " + model);
			return model;
		}
		
	}

	public void insertUser (User model) {
		logger.info("User " + model + " adding");
		em.persist(model);
		logger.info("User " + model + " added.");
	}
	
	public void updateUser (User model) {
		logger.info("User " + model + " updating.");
		em.merge(model);
		logger.info("User " + model + " updated.");
	}
	
	public void removeUser (User model) {
		logger.info("User " + model + " removing.");
		em.remove(model);
		logger.info("User " + model + " removed.");
	}	
	
}