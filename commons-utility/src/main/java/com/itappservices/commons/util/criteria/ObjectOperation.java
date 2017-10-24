/**
 * 
 */
package com.itappservices.commons.util.criteria;

import java.io.Serializable;

/**
 * @author Irepan
 *
 */
public interface ObjectOperation<T> extends Serializable {
	/**
	 * Sets the operation's value
	 * @param value
	 */
	public void setValue(T value);
	/**
	 * Gets the operation's value
	 * @return
	 */
	public T getValue();
	/**
	 * Sets the operation to perform
	 * @param operator
	 */
	public void setOperator(CriteriaOperators operator);
	/**
	 * Gets the operation to perform
	 * @return
	 */
	public CriteriaOperators getOperator();
}
