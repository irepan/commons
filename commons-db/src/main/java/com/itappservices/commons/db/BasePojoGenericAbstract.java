package com.itappservices.commons.db;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.jboss.logging.Logger;

import com.itappservices.commons.db.dao.exception.DaoException;

public class BasePojoGenericAbstract<T> implements BasePojoGeneric<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4984926791393143741L;
	protected static final Logger log = Logger.getLogger(BasePojoGenericAbstract.class);

	protected T id;
	@Override
	public T getId() {
		return id;
	}

	@Override
	public void setId(T id) {
		this.id=id;
	}
	/*	public long getLastTS();
	public void setLastTS(long lastTS);
	*/
	@Override 
	public String toString(){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(Feature.FAIL_ON_EMPTY_BEANS, false);
		String result = "";
		try {
			result = mapper.writeValueAsString(this);
		} catch (Throwable e) {
			e.printStackTrace();
			log.error("Error mapping class",e);
		}
		return result;
	}

	@Override
	public void prepare() {
	}

	@Override
	public void preInsert() throws DaoException {
		prepare();
	}

	@Override
	public void preUpdate() throws DaoException {
		prepare();
	}

	@Override
	public void preDelete() throws DaoException {
		prepare();
	}

	@Override
	public void postInsert() throws DaoException {
	}

	@Override
	public void postUpdate() throws DaoException {
	}

	@Override
	public void postDelete() throws DaoException {
	}

}
