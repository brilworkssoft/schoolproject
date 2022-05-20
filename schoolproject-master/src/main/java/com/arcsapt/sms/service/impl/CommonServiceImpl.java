package com.arcsapt.sms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arcsapt.sms.bean.AliasProp;
import com.arcsapt.sms.common.Constants;
import com.arcsapt.sms.repository.CommonDao;
import com.arcsapt.sms.service.CommonService;

@Service(value = Constants.SERVICE_COMMON)
public class CommonServiceImpl implements CommonService {

	@Resource(name = Constants.DAO_COMMON)
	private CommonDao commonDao;

	@Transactional
	public Object save(Object object) {
		return this.commonDao.save(object);
	}

	@Transactional
	public void update(Object object) {
		this.commonDao.update(object);
	}

	@Transactional
	public void delete(Class<?> target, Integer id) {
		this.commonDao.delete(target, id);
	}

	@Transactional
	public Object get(Class<?> target, Integer id) {
		return this.commonDao.get(target, id);
	}

	@Transactional
	public List<?> getList(Class<?> target) {
		return this.commonDao.getList(target);
	}

	@Transactional
	public List<?> getListByProperty(Class<?> target, String targetName,
			List<AliasProp> aliasProps, Criterion criterion,
			Projection projection, Order order, boolean isDistinct) {
		return this.commonDao.getListByProperty(target, targetName, aliasProps,
				criterion, projection, order, isDistinct);
	}
}
