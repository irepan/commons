package com.itappservices.commons.db.dao;

import java.io.Serializable;

import com.itappservices.commons.db.BasePojoStringId;

public interface BaseSupportStringIdDAO<T extends BasePojoStringId> extends Serializable, BaseSupportDAOGeneric<T,String>{
}
