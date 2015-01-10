package net.collaud.fablab.api.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import net.collaud.fablab.api.data.AbstractDataEO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <T>
 */
public abstract class AbstractDAO<T extends AbstractDataEO> {

	private final Class<T> entityClass;
	
	@Autowired
	private SessionFactory sessionFactory;
	
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
	
	protected T getById(Integer id){
		return (T) getSession().get(entityClass, id);
	}
	
	protected List<T> findAllEntities(){
		return createQuery("FROM "+getEOName()).list();
	}
	
	protected T saveEntity(T entity){
		return (T) getSession().merge(entity);
	}
	
	protected void removeEntity(T entity){
		getSession().delete(entity);
	}
	
	protected void removeEntityById(Integer id){
		Optional.ofNullable(getById(id)).ifPresent(obj -> getSession().delete(obj));
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
