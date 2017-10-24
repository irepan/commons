/**
 * 
 */
package com.itappservices.commons.util.criteria;

import java.util.Date;

/**
 * @author Irepan
 *
 */
public class DateOperation implements ObjectOperation<Date> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7067086500506429842L;
	private Date value;
	private CriteriaOperators operator = null;

	public DateOperation(String key, Date value, CriteriaOperators operator){
		super();
		setValue(value);
		setOperator(operator);
	}
	/*
	 * (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Date value) {
		this.value = value;
	}
	/*
	 * (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#getValue()
	 */
	@Override
	public Date getValue() {
		return this.value;
	}
	/*
	 * (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#setOperator(com.jrad.commons.util.CriteriaOperators)
	 */
	@Override
	public void setOperator(CriteriaOperators operator) {
		switch(operator){
		case ilike :
		case like  :
			throw new IllegalArgumentException("like and ilike operators are intented only for String values");
		case in:
			throw new IllegalArgumentException("The in operator is only intented for <T> array");
		default:
			break;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#getOperator()
	 */
	@Override
	public CriteriaOperators getOperator() {
		return this.operator;
	}
}
