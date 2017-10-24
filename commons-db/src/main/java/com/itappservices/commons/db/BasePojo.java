/**
 * 
 */
package com.itappservices.commons.db;

import java.io.Serializable;

/**
 * @author Irepan
 * Interfase base para los objetos de base de datos
 */
public interface BasePojo extends Serializable, BasePojoGeneric<Long> {
	@Override
	public Long getId();
	@Override
	public void setId(Long id);
}
