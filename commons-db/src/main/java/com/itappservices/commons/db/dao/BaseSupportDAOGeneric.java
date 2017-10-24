/**
 * 
 */
package com.itappservices.commons.db.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import com.itappservices.commons.db.BasePojoGeneric;
import com.itappservices.commons.db.dao.exception.DaoException;

/**
 * @author irepan
 * @param <D>
 *
 */
public interface BaseSupportDAOGeneric<T extends BasePojoGeneric<D>, D extends Serializable> extends Serializable {
	/**
	 * loads <T> using it's id
	 * @param id <T> id
	 * @return
	 * @throws DaoException
	 */
	public T getFromId(D id) throws DaoException;
	/**
	 * Inserta the <T> and returns the updated dao
	 * @param dao
	 * @return
	 * @throws DaoException
	 */
	public T insert(T dao) throws DaoException;
	/**
	 * Updates <T> and returns the updates dao
	 * @param dao
	 * @return
	 * @throws DaoException
	 */
	public T update(T dao) throws DaoException;
	/**
	 * Deletes the dao from disk (if inherits from VirtualDeletable it will do a logical delete)
	 * @param dao
	 * @throws DaoException
	 */
	public void delete(T dao) throws DaoException;
	/**
	 * Gets all <T> from database
	 * @return
	 * @throws DaoException
	 */
	public List<T> getAll() throws DaoException;
	/**
	 * Selects all <T> by using a HQL query
	 * @param query
	 * @return
	 * @throws DaoException
	 */
	public List<T> select(String query) throws DaoException;
	/**
	 * Selects all <T> by using HQL query using the parameters in the map
	 * @param query
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public List<T> select(String query, Object... params) throws DaoException;
	/**
	 * Selects all<T> by using a set of criteria parameters
	 * @param criteria
	 * @return
	 * @throws DaoException
	 */
	public List<T> select(Map<String, Object> criteria) throws DaoException;
	/**
	 * Returns the count of all of the records
	 * @return
	 * @throws DaoException
	 */
	public Long count() throws DaoException;
	/**
	 * Returns the count of all of the records
	 * @param includeDeleted
	 * @return
	 * @throws DaoException
	 */
	public Long count(boolean includeDeleted) throws DaoException;
	/**
	 * Returns the count of the records that matches the HQL query
	 * using the given parameters
	 * @param HQL
	 * @param params
	 * @return
	 * @throws DaoException
	 */
	public Long count(String HQL, Object... params) throws DaoException;
	/**
	 * Returns the Generic class name
	 * @return <T> name
	 */
	public String getClassName();
	/**
	 * Gets the Criteria to use for the queries,
	 * for complex classes override this method and add aliases
	 * @return {@link Criteria}
	 */
	public DetachedCriteria getCriteria();
}
