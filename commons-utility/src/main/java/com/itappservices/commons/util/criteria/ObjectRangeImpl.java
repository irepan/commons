/**
 * 
 */
package com.itappservices.commons.util.criteria;

/**
 * @author Irepan
 *
 */
public class ObjectRangeImpl<T> implements ObjectRange<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 108673252091471258L;
	protected T start = null;
	protected T end = null;
	/**
	 * 
	 */
	public ObjectRangeImpl() {
		super();
	}
	public ObjectRangeImpl(T start, T end){
		this();
		this.setStart(start);
		this.setEnd(end);
	}

	/* (non-Javadoc)
	 * @see mx.gob.sep.util.ObjectRange#getStart()
	 */
	@Override
	public T getStart() {
		return start;
	}

	/* (non-Javadoc)
	 * @see mx.gob.sep.util.ObjectRange#getEnd()
	 */
	@Override
	public T getEnd() {
		return end;
	}

	/* (non-Javadoc)
	 * @see mx.gob.sep.util.ObjectRange#setStart(java.lang.Object)
	 */
	@Override
	public void setStart(T start) {
		this.start = start;
		@SuppressWarnings("unchecked")
		Comparable<T> startComp = (Comparable<T>) this.start;
		if (null != this.end && startComp.compareTo(this.end)>0){
			T aux = this.end;
			this.end = this.start;
			this.start = aux;
		}
	}

	/* (non-Javadoc)
	 * @see mx.gob.sep.util.ObjectRange#setEnd(java.lang.Object)
	 */
	@Override
	public void setEnd(T end) {
		this.end = end;
	}
	/**
	 * Crea una instancia de {@link ObjectRange}
	 * @param clazz la clase de la que se pretende crear el  {@link ObjectRange}
	 * @return
	 */
	public static <T> ObjectRange<T> newInstance(Class<T> clazz){
		if (!Comparable.class.isAssignableFrom(clazz)){
			throw new IllegalArgumentException("La clase no hereda de comparable");
		}
		return new ObjectRangeImpl<T>();
	}
	/**
	 * Crea una instancia de {@link ObjectRange}
	 * @param clazz la clase de la que se pretende crear el  {@link ObjectRange}
	 * @param start <{@link T}> el inicio del rango 
	 * @param end <{@link T}> fin del rango
	 * @return ObjectRange<T> {@link ObjectRange}<{@link T}>
	 */
	public static <T> ObjectRange<T> newInstance(Class<T> clazz, T start, T end){
		if (!Comparable.class.isAssignableFrom(clazz)){
			throw new IllegalArgumentException("La clase no hereda de comparable");
		}
		return new ObjectRangeImpl<T>(start, end);
	}

}
