/**
 * 
 */
package com.itappservices.commons.db.dao.exception;

/**
 * @author Irepan
 *
 */
public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6189664566302988738L;

	/**
	 * 
	 */
	public DaoException() {
		super();
	}

	/**
	 * @param arg0
	 */
	public DaoException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public DaoException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DaoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}
