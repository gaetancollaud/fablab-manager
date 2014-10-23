package net.collaud.fablab.api.dao.impl;

import java.util.List;
import net.collaud.fablab.api.data.AbstractDataEO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gaetan
 * @param <T>
 */
public abstract class AbstractDAO<T extends AbstractDataEO> {

	private final Class<T> entityClass;
	
	
	@Autowired
	private SessionFactory sessionFactory;
//
	public AbstractDAO(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	protected Query createQuery(String query){
		return getSession().createQuery(query);
	}
	
	protected String getEOName(){
		return entityClass.getSimpleName();
	}
	
	protected List<T> findAll(){
		return createQuery("FROM "+getEOName()).list();
	}
	
	protected T save(T entity){
		getSession().saveOrUpdate(entity);
		return entity;
	}
	
//	
//	//@PersistenceContext
//	private EntityManager em;
	
//
//	protected EntityManager getEntityManager(){
//		return em;
//	}
//
//	public T create(T entity) {
//		getEntityManager().persist(entity);
//		return entity;
//	}
//
//	public T edit(T entity) {
//		return getEntityManager().merge(entity);
//	}
//
//	public void remove(T entity) {
//		getEntityManager().remove(getEntityManager().merge(entity));
//	}
//
//	public T find(Object id) {
//		return getEntityManager().find(entityClass, id);
//	}
//
//	public List<T> findAll() {
//		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//		cq.select(cq.from(entityClass));
//		return getEntityManager().createQuery(cq).getResultList();
//	}
//
//	public int count() {
//		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//		javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
//		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
//		javax.persistence.Query q = getEntityManager().createQuery(cq);
//		return ((Long) q.getSingleResult()).intValue();
//	}

}
