/**
 * 
 */
package com.itappservices.commons.db;

import java.io.Serializable;

/**
 * @author irepan
 * Class to be referred by composite-id mapping classes
 */
public abstract class CompositeIdKey implements Serializable, Comparable<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6358180472201703138L;

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public abstract int compareTo(Object o);
	@Override
	public abstract int hashCode();
	@Override
	public abstract boolean equals(Object obj);
}
