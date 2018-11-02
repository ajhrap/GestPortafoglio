package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Mercato;

@Repository("mercatoDao")
public class MercatoDaoImpl extends AbstractDao<Integer, Mercato> implements MercatoDao {

	public Mercato findById(int id) {
		return getByKey(id);
	}

	public void saveMercato(Mercato mercato) {
		persist(mercato);
	}

	public void deleteMercatoByCodice(String codice) {
		Query query = getSession().createSQLQuery("delete from Mercato where codice = :codice");
		query.setString("codice", codice);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Mercato> findAllMercati() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("codice"));
		return (List<Mercato>) criteria.list();
	}

	public Mercato findMercatoByCodice(String codice) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("codice", codice));
		return (Mercato) criteria.uniqueResult();
	}
}
