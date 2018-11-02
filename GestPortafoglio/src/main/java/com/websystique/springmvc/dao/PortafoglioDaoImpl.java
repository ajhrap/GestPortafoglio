package com.websystique.springmvc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Portafoglio;
import com.websystique.springmvc.service.PortafoglioService;

@Repository("portafoglioDao")
public class PortafoglioDaoImpl extends AbstractDao<Integer, Portafoglio> implements PortafoglioDao {

	@Autowired
	PortafoglioService portafoglioService;

	public Portafoglio findById(int id) {
		return getByKey(id);
	}

	public void savePortafoglio(Portafoglio portafoglio) {
		persist(portafoglio);
	}

	public void deletePortafoglioById(Integer portafoglioId) {
		Portafoglio portafoglio = portafoglioService.findById(portafoglioId);
		getSession().delete(portafoglio);
//		Query query = getSession().createSQLQuery("delete from Portafoglio where portafoglioId = :portafoglioId");
//		query.setInteger("portafoglioId", portafoglioId);
//		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Portafoglio> findAllPortafogli() {
		Criteria criteria = createEntityCriteria();
		criteria.addOrder(Order.asc("dataAcquisto"));
		return (List<Portafoglio>) criteria.list();
	}

	public Portafoglio findPortafoglioByTitolo(Integer titoloId) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("titolo.titoloId", titoloId));
		return (Portafoglio) criteria.uniqueResult();
	}
}
