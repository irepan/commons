/**
 * 
 */
package com.itappservices.commons.util;

import java.io.Serializable;

/**
 * @author Irepan Ch&aacute;vez
 * Clase para manejo de mapas sobre los servicios web
 */
public class MapEntry implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7489317610692965050L;
	private String key;
	private Object value;
	/**
	 * 
	 */
	public MapEntry() {
		super();
	}
	public MapEntry(String key, Object value){
		this();
		setKey(key);
		setValue(value);
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

}
