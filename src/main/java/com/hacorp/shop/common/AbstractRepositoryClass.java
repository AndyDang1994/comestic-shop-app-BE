/**
 * 
 */
package com.hacorp.shop.common;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;


/**
 * @author shds01
 *
 */

public abstract class AbstractRepositoryClass {

	protected final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public Environment env;

	@PersistenceContext(unitName = "entityManager")
	public EntityManager entityManager;

	@Autowired
	private RepositoryManagerService repositoryManagerService;

	@Autowired
	public MysqlNamedQueries mysqlNamedQueries;

	/**
	 * @return the repositoryManagerService
	 */
	public RepositoryManagerService getRepositoryManagerService() {
		return repositoryManagerService;
	}

	/**
	 * @param repositoryManagerService the repositoryManagerService to set
	 */
	public void setRepositoryManagerService(
			@Qualifier("repositoryManagerService") RepositoryManagerService repositoryManagerService) {
		this.repositoryManagerService = repositoryManagerService;
	}

}
