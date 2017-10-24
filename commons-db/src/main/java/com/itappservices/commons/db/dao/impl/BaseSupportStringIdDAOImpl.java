package com.itappservices.commons.db.dao.impl;

import com.itappservices.commons.db.BasePojoStringId;
import com.itappservices.commons.db.dao.BaseSupportStringIdDAO;

public abstract class BaseSupportStringIdDAOImpl <T extends BasePojoStringId> extends BaseSupportDAOGenericImpl<T, String> implements BaseSupportStringIdDAO<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3026636451511966097L;

}
