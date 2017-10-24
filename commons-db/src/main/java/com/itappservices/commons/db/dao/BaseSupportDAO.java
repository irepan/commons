package com.itappservices.commons.db.dao;
import java.io.Serializable;

import com.itappservices.commons.db.BasePojo;

/**
 * 
 */

/**
 * @author Irepan
 *
 */
public interface BaseSupportDAO<T extends BasePojo> extends Serializable, BaseSupportDAOGeneric<T,Long> {
}
