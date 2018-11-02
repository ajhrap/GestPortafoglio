package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Titolo;

@Repository("titoloDao")
public class TitoloDaoImpl extends AbstractDao<Integer, Titolo> implements TitoloDao {

	public Titolo findById(int id) {
		return getByKey(id);
	}

	public void saveTitolo(Titolo titolo) {
		persist(titolo);
	}

	public void deleteTitoloByIsin(String isin) {
		Query query = getSession().createSQLQuery("delete from Titolo where isin = :isin");
		query.setString("isin", isin);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Titolo> findAllTitoli() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("denominazione"));
		return (List<Titolo>) criteria.list();
	}

	public Titolo findTitoloByIsin(String isin) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("isin", isin));
		return (Titolo) criteria.uniqueResult();
	}
}
