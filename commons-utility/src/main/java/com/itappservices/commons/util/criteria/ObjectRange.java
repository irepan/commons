/**
 * 
 */
package com.itappservices.commons.util.criteria;

import java.io.Serializable;

/**
 * @author Irepan Ch&aacute;vez
 * Interfase para manejar un rango de b&uacute;squeda
 */
public interface ObjectRange<T> extends Serializable {
	/**
	 * regresa el objeto de inicio
	 * @return {@link <T>} inicio
	 */
	public T getStart();
	/**
	 * Regresa el final
	 * @return {@link <T>} fin
	 */
	public T getEnd();
	public void setStart(T start);
	public void setEnd(T end);
}
