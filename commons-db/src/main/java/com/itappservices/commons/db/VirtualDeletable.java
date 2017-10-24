/**
 * 
 */
package com.itappservices.commons.db;

/**
 * @author Irepan
 * Interfase base para los objetos de base de datos que 
 * se van a eliminar l&oacute;gicamente
 */
public interface VirtualDeletable {
	/**
	 * Regresa verdadero si el registro est&aacute; activo
	 * falso si est&aacute; inactivo
	 * @return active
	 */
	public boolean isActive();
	/**
	 * Set the active flag
	 * @param active
	 */
	public void setActive(boolean active);
}
