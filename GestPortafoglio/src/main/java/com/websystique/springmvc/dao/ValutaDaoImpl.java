package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Valuta;

@Repository("valutaDao")
public class ValutaDaoImpl extends AbstractDao<Integer, Valuta> implements ValutaDao {

	public Valuta findById(int id) {
		return getByKey(id);
	}

	public void saveValuta(Valuta valuta) {
		persist(valuta);
	}

	public void deleteValutaByCodice(String codice) {
		Query query = getSession().createSQLQuery("delete from Valuta where codice = :codice");
		query.setString("codice", codice);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Valuta> findAllValute() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("codice"));
		return (List<Valuta>) criteria.list();
	}

	public Valuta findValutaByCodice(String codice) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("codice", codice));
		return (Valuta) criteria.uniqueResult();
	}
}
