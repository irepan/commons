/**
 * 
 */
package com.itappservices.commons.util.transform;

import java.io.Serializable;

/**
 * Parse and Stringify interface
 * Used to transform a String to a Class 
 * and to Stringify a class
 * @author Irepan Chavez
 *
 */
public interface String2ClassParser<T> extends Serializable {
	/**
	 * Will return a class T from a string source
	 * @param source
	 * @return
	 */
	public T parse(String source) throws ParseException;
	/**
	 * Will return a string from a T source
	 * @param source
	 * @return
	 */
	public String stringify(T source) throws ParseException;
}
