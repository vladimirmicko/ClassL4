package com.demo.vlada.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.vlada.dao.FileDao;
import com.demo.vlada.dto.PersistedFileDto;
import com.demo.vlada.entities.PersistedFile;

@Repository
public class FileDaoImpl implements FileDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void saveOrUpdate(PersistedFile file) {
		sessionFactory.getCurrentSession().saveOrUpdate(file);
	}
	
	@Override
	@Transactional
	public PersistedFile getPersistedFileById(Integer id) {
		return (PersistedFile)sessionFactory.getCurrentSession().createCriteria(PersistedFile.class).add(Restrictions.idEq(id)).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PersistedFile> getFiles() {
		return (List<PersistedFile>)sessionFactory.getCurrentSession().createCriteria(PersistedFile.class).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PersistedFileDto> getDtoFiles() {
		return (List<PersistedFileDto>)sessionFactory.getCurrentSession().createCriteria(PersistedFile.class).setProjection(
                Projections.projectionList()
                .add(Projections.property("id"), "id")
                .add(Projections.property("name"), "fileName")).setResultTransformer(Transformers.aliasToBean(PersistedFileDto.class)).list();
	}

}
