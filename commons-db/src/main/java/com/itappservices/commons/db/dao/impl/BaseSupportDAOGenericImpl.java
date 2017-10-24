package com.itappservices.commons.db.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.itappservices.commons.db.BasePojoGeneric;
import com.itappservices.commons.db.VirtualDeletable;
import com.itappservices.commons.db.dao.BaseSupportDAOGeneric;
import com.itappservices.commons.db.dao.exception.DaoException;
import com.itappservices.commons.util.criteria.DateOperation;
import com.itappservices.commons.util.criteria.DateRange;
import com.itappservices.commons.util.criteria.ObjectOperation;
import com.itappservices.commons.util.criteria.ObjectRange;

public class BaseSupportDAOGenericImpl<T extends BasePojoGeneric<D>, D extends Serializable>
		extends HibernateDaoSupport implements BaseSupportDAOGeneric<T, D> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9169535652856842858L;
	protected boolean virtualDeletable;
	protected final Class<T> genericType;
	protected final String RECORD_COUNT_HQL;
	protected static final String WHERE_ID = " WHERE t.id = ?";
	protected String SELECT_HQL;

	/**
	 * 
	 */
	protected String getSelectFromId() {
		return this.SELECT_HQL + WHERE_ID;
	}

	@SuppressWarnings("unchecked")
	public BaseSupportDAOGenericImpl() {
		super();
		this.genericType = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		this.RECORD_COUNT_HQL = "SELECT COUNT(*) FROM "
				+ this.genericType.getName();
		this.SELECT_HQL = String
				.format("FROM %s t", this.genericType.getName());
		virtualDeletable = VirtualDeletable.class.isAssignableFrom(genericType);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.atlas.api.dao.BaseSupportDAO#getFromId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getFromId(D id) throws DaoException {
		try {
			return (T) getHibernateTemplate().get(this.genericType.getName(),
					id);
		} catch (Exception e) {
			throw new DaoException("Error while getting "
					+ this.genericType.getName(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.itappservices.commons.db.dao.BaseSupportDAOGeneric#insert(com.
	 * itappservices.commons.db.BasePojoGeneric)
	 */
	@Override
	public T insert(T dao) throws DaoException {
		try {
			dao.preInsert();
			getHibernateTemplate().save(dao);
			dao.postInsert();
			return dao;
		} catch (Exception e) {
			throw new DaoException("Error inserting "
					+ this.genericType.getName(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.itappservices.commons.db.dao.BaseSupportDAOGeneric#update(com.
	 * itappservices.commons.db.BasePojoGeneric)
	 */
	@Override
	public T update(T dao) throws DaoException {
		try {
			dao.preUpdate();
			getHibernateTemplate().update(dao);
			dao.postUpdate();
			return dao;
		} catch (Exception e) {
			throw new DaoException("Error updating "
					+ this.genericType.getName(), e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.itappservices.commons.db.dao.BaseSupportDAOGeneric#delete(com.
	 * itappservices.commons.db.BasePojoGeneric)
	 */
	@Override
	public void delete(T dao) throws DaoException {
		try {
			dao.preDelete();
			if (VirtualDeletable.class.isAssignableFrom(dao.getClass())) {
				((VirtualDeletable) dao).setActive(false);
				getHibernateTemplate().save(dao);
			} else {
				getHibernateTemplate().delete(dao);
			}
			dao.postDelete();
		} catch (Exception e) {
			throw new DaoException("Error deleting "
					+ this.genericType.getName(), e);
		}
	}

	@Override
	public Long count(boolean includeDeleted) throws DaoException {
		DetachedCriteria crit = newCriteria().setProjection(
				Projections.rowCount());
		return DataAccessUtils.longResult(getHibernateTemplate()
				.findByCriteria(crit));
	}

	public DetachedCriteria newCriteria(Order... orderBy) {
		DetachedCriteria criteria = DetachedCriteria
				.forEntityName(this.genericType.getName());
		if (orderBy != null) {
			for (Order order : orderBy) {
				criteria.addOrder(order);
			}
		}
		return criteria;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.itappservices.commons.db.dao.BaseSupportDAOGeneric#count()
	 */
	@Override
	public Long count() throws DaoException {
		try {
			return DataAccessUtils.longResult(getHibernateTemplate().find(
					this.RECORD_COUNT_HQL));
		} catch (Exception e) {
			throw new DaoException("Error counting "
					+ this.genericType.getName(), e);
		}
	}

	@Override
	public Long count(String HQL, Object... params) throws DaoException {
		try {
			return DataAccessUtils.longResult(getHibernateTemplate().find(
					this.RECORD_COUNT_HQL + " " + HQL.trim(), params));
		} catch (Exception e) {
			throw new DaoException("Error counting "
					+ this.genericType.getName(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() throws DaoException {
		List<T> result = new ArrayList<T>(0);
		try {
			result = (List<T>) getHibernateTemplate().find(this.SELECT_HQL);
		} catch (Exception e) {
			throw new DaoException("Error getting all of "
					+ this.genericType.getName(), e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.itappservices.commons.db.dao.BaseSupportDAOGeneric#select(java.lang
	 * .String)
	 */
	@Override
	public List<T> select(String HQL) throws DaoException {
		List<T> result = select(HQL, new HashMap<String, Object>());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.itappservices.commons.db.dao.BaseSupportDAOGeneric#select(java.lang
	 * .String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> select(String HQL, Object... params) throws DaoException {
		List<T> result = new ArrayList<T>(0);
		try {
			result = (List<T>) getHibernateTemplate().find(
					String.format("%s %s%s", this.SELECT_HQL.trim(), HQL.trim()
							.toLowerCase().startsWith("where") ? "" : "WHERE ",
							HQL.trim()), params);
		} catch (Exception e) {
			throw new DaoException("Error selecting "
					+ this.genericType.getName(), e);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.itappservices.commons.db.dao.BaseSupportDAOGeneric#getClassName()
	 */
	@Override
	public String getClassName() {
		return this.genericType.getName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.itappservices.commons.db.dao.BaseSupportDAOGeneric#select(java.util
	 * .Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> select(Map<String, Object> criteria) throws DaoException {
		if (null == criteria) {
			throw new IllegalArgumentException(
					"criteria parameter cannot be null");
		}
		List<T> result = new ArrayList<T>(0);
		try {
			DetachedCriteria crit = this.getCriteria();
			Iterator<Entry<String, Object>> iterator = criteria.entrySet()
					.iterator();
			while (iterator.hasNext()) {
				Entry<String, Object> entry = iterator.next();
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof DateRange) {
					DateRange dr = (DateRange) value;
					Calendar sd = Calendar.getInstance();
					sd.setTime(dr.getStart());
					Calendar et = Calendar.getInstance();
					et.setTime(dr.getEnd());
					et.set(Calendar.HOUR_OF_DAY, 23);
					et.set(Calendar.MINUTE, 59);
					et.set(Calendar.SECOND, 59);
					et.set(Calendar.MILLISECOND, 999);
					crit.add(Restrictions.between(key, sd.getTime(),
							et.getTime()));
				} else if (ObjectRange.class.isAssignableFrom(value.getClass())) {
					ObjectRange<?> or = (ObjectRange<?>) value;
					if (or.getStart() instanceof String) {
						throw new IllegalArgumentException(
								"String objects cannot be used as an ObjectRange");
					}
					crit.add(Restrictions.between(entry.getKey(),
							or.getStart(), or.getEnd()));
				} else if (value instanceof DateOperation) {
					DateOperation dop = (DateOperation) value;
					Calendar edt = Calendar.getInstance();
					edt.setTime(dop.getValue());
					edt.set(Calendar.HOUR_OF_DAY, 23);
					edt.set(Calendar.MINUTE, 59);
					edt.set(Calendar.SECOND, 59);
					edt.set(Calendar.MILLISECOND, 999);
					Calendar sdt = Calendar.getInstance();
					sdt.setTime(dop.getValue());
					sdt.set(Calendar.HOUR_OF_DAY, 0);
					sdt.set(Calendar.MINUTE, 0);
					sdt.set(Calendar.SECOND, 0);
					sdt.set(Calendar.MILLISECOND, 0);
					switch (dop.getOperator()) {
					case eq:
						crit.add(Restrictions.between(key, sdt.getTime(),
								edt.getTime()));
						break;
					case gt:
						crit.add(Restrictions.gt(key, edt.getTime()));
						break;
					case gteq:
						crit.add(Restrictions.ge(key, sdt.getTime()));
						break;
					case lt:
						crit.add(Restrictions.lt(key, sdt.getTime()));
						break;
					case lteq:
						crit.add(Restrictions.le(key, edt.getTime()));
						break;
					default:
						break;
					}
				} else if (ObjectOperation.class.isAssignableFrom(value
						.getClass())) {
					ObjectOperation<?> oo = (ObjectOperation<?>) value;
					Object val = oo.getValue();
					switch (oo.getOperator()) {
					case eq:
						crit.add(Restrictions.eq(key, val));
						break;
					case gteq:
						crit.add(Restrictions.ge(key, val));
						break;
					case gt:
						crit.add(Restrictions.gt(key, val));
						break;
					case lt:
						crit.add(Restrictions.lt(key, val));
						break;
					case lteq:
						crit.add(Restrictions.le(key, val));
						break;
					case in:
						if (val.getClass().isArray()) {
							Object[] array = (Object[]) val;
							crit.add(Restrictions.in(key, array));
						} else if (val instanceof List) {
							List<?> list = (List<?>) val;
							crit.add(Restrictions.in(key, list));
						} else {
							throw new IllegalArgumentException(
									"it must be an array or a List for the in operator");
						}
						break;
					default:
						break;
					}
				}
			}
			result = (List<T>) getHibernateTemplate().findByCriteria(crit);

		} finally {
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.itappservices.commons.db.dao.BaseSupportDAOGeneric#getCriteria()
	 */
	@Override
	public DetachedCriteria getCriteria() {
		return DetachedCriteria.forEntityName(this.genericType.getName());
	}

}
