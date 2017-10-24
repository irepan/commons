/**
 * 
 */
package com.itappservices.commons.util.criteria;

import java.util.List;

/**
 * @author Irepan
 *
 */
public class ObjectOperationImpl<T> implements ObjectOperation<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1008412454711037390L;
	private T value;
	private CriteriaOperators operator;
	/**
	 * 
	 */
	public ObjectOperationImpl() {
		super();
	}
	public ObjectOperationImpl(String key, T value, CriteriaOperators operator){
		setValue(value);
		setOperator(operator);
	}

	/* (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(T value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#getValue()
	 */
	@Override
	public T getValue() {
		return this.value;
	}

	/* (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#setOperator(com.jrad.commons.util.CriteriaOperators)
	 */
	@Override
	public void setOperator(CriteriaOperators operator) {
		switch(operator){
		case ilike :
		case like  :
			if (! ( value instanceof String ) ){
				throw new IllegalArgumentException("like and ilike operators are intented only for String values");
			} else if ( value.getClass().isArray() ) {
				throw new IllegalArgumentException("This operator is not intented for array values");
			}
			break;
		case gt:
		case gteq:
		case lt:
		case lteq:
			if ( value instanceof String ){
				throw new IllegalArgumentException("This operator is not useful for String values");
			} else if ( value.getClass().isArray() ) {
				throw new IllegalArgumentException("This operator is not intented for array values");
			}
			break;
		case in:
			if ( (value instanceof List) && (! ( value.getClass().isArray() ) ) ){
				throw new IllegalArgumentException("The in operator is only intented for <T> array");
			}
			break;
		default:
			if ( value.getClass().isArray() ) {
				
			} else if ( value.getClass().isArray() ) {
				throw new IllegalArgumentException("This operator is not intented for array values");
			}

		}
		this.operator = operator;
	}

	/* (non-Javadoc)
	 * @see com.jrad.commons.util.ObjectOperation#getOperator()
	 */
	@Override
	public CriteriaOperators getOperator() {
		return this.operator;
	}
	public static <T> ObjectOperation<T> newInstance(Class<T> clazz){
		return new ObjectOperationImpl<T>();
	}
	public static <T> ObjectOperation<T> newInstance(Class<T> clazz, String key, T value, CriteriaOperators operator){
		return new ObjectOperationImpl<T>(key, value, operator);
	}
}
