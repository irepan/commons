/**
 * 
 */
package com.itappservices.commons.db.usertypes;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

/**
 * @author Irepan
 * 
 */
public class BooleanType implements UserType {

	private static final int[] SQL_TYPES = { Types.BOOLEAN };
	private static final Long OPC_YES = 1L;
	private static final Long OPC_NO = 0L;
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#assemble(java.io.Serializable, java.lang.Object)
	 */
	@Override
	public Object assemble(Serializable cached, Object owner)
			throws HibernateException {
		return cached;
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#deepCopy(java.lang.Object)
	 */
	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#disassemble(java.lang.Object)
	 */
	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable)value;
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#equals(java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return (x == y) || (x != null && y != null && (x.equals(y)));
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#hashCode(java.lang.Object)
	 */
	@Override
	public int hashCode(Object arg0) throws HibernateException {
		return arg0.hashCode();
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#isMutable()
	 */
	@Override
	public boolean isMutable() {
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
	 */
	@Override	
	public Object nullSafeGet(ResultSet rs, String[] names,
			SessionImplementor si, Object owner)
		throws HibernateException, SQLException {
		Boolean flag = false;
		String value = rs.getString(names[0]);
		if(null != value && 
				(value.equalsIgnoreCase("yes") || 
					value.equalsIgnoreCase("y") ||
					value.equalsIgnoreCase("true") ||
					value.equalsIgnoreCase("t"))
						){
			flag = true;
		}
		return flag;
	}
/*	
	@Override
	public void nullSafeSet(PreparedStatement arg0, Object arg1, int arg2,
			SessionImplementor arg3) throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
	}*/
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
	 */
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index,SessionImplementor si)
		throws HibernateException, SQLException {
		Boolean aux = (Boolean)value;
		if(null == aux){
			aux = Boolean.valueOf(false);
		}
		if(aux){
			st.setLong(index, OPC_YES);
		}
		else{
			st.setLong(index, OPC_NO);
		}
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#replace(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public Object replace(Object arg0, Object arg1, Object arg2)
			throws HibernateException {
		// TODO Auto-generated method stub
		return arg0;
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#returnedClass()
	 */
	@Override
	public Class<?> returnedClass() {
		return Boolean.class;
	}
	/*
	 * (non-Javadoc)
	 * @see org.hibernate.usertype.UserType#sqlTypes()
	 */
	@Override
	public int[] sqlTypes() {
		return SQL_TYPES;
	}

}
