package com.itappservices.commons.db;

import java.io.Serializable;

public interface BasePojoStringId extends Serializable, BasePojoGeneric<String> {
	@Override
	public String getId();
	@Override
	public void setId(String id);
}
