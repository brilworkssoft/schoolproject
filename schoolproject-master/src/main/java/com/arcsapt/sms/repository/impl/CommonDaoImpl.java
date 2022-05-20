package com.arcsapt.sms.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arcsapt.sms.bean.AliasProp;
import com.arcsapt.sms.common.Constants;
import com.arcsapt.sms.repository.CommonDao;


@Repository(value = Constants.DAO_COMMON)
public class CommonDaoImpl implements CommonDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Object save(Object object) {
		this.sessionFactory.getCurrentSession().save(object);
		return object;
	}

	@Override
	public void delete(Class<?> target, Integer id) {
		Object object = this.sessionFactory.getCurrentSession().get(target, id);
		if (null != object)
			this.sessionFactory.getCurrentSession().delete(object);
	}

	@Override
	public void update(Object object) {
		this.sessionFactory.getCurrentSession().update(object);
	}

	@Override
	public Object get(Class<?> target, Integer id) {
		Object object = this.sessionFactory.getCurrentSession().get(target, id);
		return object;
	}

	@Override
	public List<?> getList(Class<?> target) {
		Criteria objCriteria = null;
		objCriteria = this.sessionFactory.getCurrentSession().createCriteria(
				target);
		return objCriteria.list();
	}

	public List<?> getListByProperty(Class<?> target, String targetName,
			List<AliasProp> aliasProps, Criterion criterion,
			Projection projection, Order order, boolean isDistinct) {
		Criteria objCriteria = null;
		List<?> objListData = null;
		StringBuilder objSB = null;
		try {
			objSB = new StringBuilder();
			objCriteria = this.sessionFactory.getCurrentSession()
					.createCriteria(target, targetName);

			// Add alias
			if (null != aliasProps && aliasProps.size() > 0) {
				for (AliasProp aliasProp : aliasProps) {
					objSB.delete(0, objSB.length());
					objSB.append(aliasProp.getTargetTable());
					objSB.append(Constants.STRING_DOT);
					objSB.append(aliasProp.getTargetColumn());
					objCriteria.createAlias(objSB.toString(),
							aliasProp.getAliasName(), aliasProp.getJoinType());
				}
			}

			// add Criterion to Criteria
			if (null != criterion) {
				// add objCriterion Criteria
				objCriteria.add(criterion);
			}

			// check getProjectionProps is not null and is not empty
			if (null != projection) {
				// add projection to Criteria
				objCriteria.setProjection(projection);
				objCriteria.setResultTransformer(Transformers
						.aliasToBean(target));
			}

			// check getOrderProps is not null and is not empty
			if (null != order) {
				// add order to Criteria
				objCriteria.addOrder(order);
			}
			if (isDistinct)
				objCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			// get list from criteria
			objListData = objCriteria.list();
		} finally {
			if (null != objSB) {
				objSB.delete(0, objSB.length());
				objSB = null;
			}
		}
		return objListData;
	}
}