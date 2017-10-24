/**
 * 
 */
package com.itappservices.commons.util.transform;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Irepan
 *
 */
public abstract class Transformer<O, D> {

	/**
	 * 
	 */
	protected Comparator<O> originComparator = null;
	protected Comparator<D> destinyComparator = null;
	private boolean keepNulls = false;

	public Transformer() {
	}

	public abstract D transformFromOrigin(O value) throws Exception;

	public abstract O transformToOrigin(D value) throws Exception;;

	public Collection<D> castFromOrigin(Collection<O> collection) throws Exception {
		List<D> result = new ArrayList<D>(0);
		Iterator<O> it = collection.iterator();
		while (it.hasNext()) {
			D value = transformFromOrigin(it.next());
			if (keepNulls || (!keepNulls && value != null)) {
				result.add(value);
			}
		}
		return result;
	}

	public Collection<D> castSortFromOrigin(Collection<O> collection) throws Exception {
		List<D> result = (List<D>) castFromOrigin(collection);
		if (null != getDestinyComparator()) {
			Collections.sort(result, getDestinyComparator());
		}
		return result;
	}

	public Collection<O> castToOrigin(Collection<D> collection) throws Exception {
		List<O> result = new ArrayList<O>(0);
		Iterator<D> it = collection.iterator();
		while (it.hasNext()) {
			O value = transformToOrigin(it.next());
			if (keepNulls || (!keepNulls && value != null)) {
				result.add(value);
			}
		}
		return result;
	}

	public Collection<O> castSortToOrigin(Collection<D> collection) throws Exception {
		List<O> result = (List<O>) castToOrigin(collection);
		if (null != getOriginComparator()) {
			Collections.sort(result, getOriginComparator());
		}
		return result;
	}

	private Comparator<O> getOriginComparator() {
		return originComparator;
	}

	private Comparator<D> getDestinyComparator() {
		return destinyComparator;
	}

	protected boolean isKeepNulls() {
		return keepNulls;
	}

	protected void setKeepNulls(boolean keepNulls) {
		this.keepNulls = keepNulls;
	}
}
