package com.arcsapt.sms.repository;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

import com.arcsapt.sms.bean.AliasProp;


public interface CommonDao {
	public Object save(Object object);

	public void update(Object object);

	public void delete(Class<?> target, Integer id);

	public Object get(Class<?> target, Integer id);

	public List<?> getList(Class<?> target);

	public List<?> getListByProperty(Class<?> target, String targetName,
			List<AliasProp> aliasProps, Criterion criterion,
			Projection projection, Order order, boolean isDistinct);
}
