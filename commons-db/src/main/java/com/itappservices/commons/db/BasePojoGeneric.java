package com.itappservices.commons.db;

import java.io.Serializable;

import com.itappservices.commons.db.dao.exception.DaoException;

public interface BasePojoGeneric<T> extends Serializable {
	public T getId();
	public void setId(T id);
	public void prepare() throws DaoException;
	public void preInsert() throws DaoException;
	public void preUpdate() throws DaoException;
	public void preDelete() throws DaoException;
	public void postInsert() throws DaoException;
	public void postUpdate() throws DaoException;
	public void postDelete() throws DaoException;
}
