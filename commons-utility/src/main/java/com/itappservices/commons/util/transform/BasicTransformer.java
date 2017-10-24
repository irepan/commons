/**
 * 
 */
package com.itappservices.commons.util.transform;

/**
 * @author Irepan
 * @param <R>
 *
 */
public class BasicTransformer<O, D> extends Transformer<O, D> {

	/**
	 * 
	 */
	public BasicTransformer() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jrad.commons.transform.Transformer#TransformFromOrigin(java.lang.
	 * Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public D transformFromOrigin(O value) throws Exception {
		return (D) value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jrad.commons.transform.Transformer#TransformToOrigin(java.lang.
	 * Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public O transformToOrigin(D value) throws Exception {
		return (O) value;
	}

}
