package br.com.sti.gtf.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Phelipe Melanias
 */
public abstract class GenericRepository<E, I extends Serializable> implements Repository<E, I> {

    private final Class<E> classe;
    protected final EntityManager em;

    @SuppressWarnings("unchecked")
    public GenericRepository(EntityManager em) {
        this.em = em;
        this.classe  = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public E find(I id) {
        try {
            return em.find(classe, id);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public E find(String field, String value) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            c.select(root).where(cb.equal(root.get(field), value));

            return em.createQuery(c).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<E> findAll(String field, String value) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            c.select(root).where(cb.equal(root.get(field), value));

            return em.createQuery(c).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public E merge(E entity) {
        try {
            return em.merge(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void remove(E entity) {
        try {
            em.remove(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void refresh(E entity) {
        try {
            em.refresh(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void persist(E entity) {
        try {
            em.persist(entity);
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAll() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);
            c.select(root);

            return em.createQuery(c).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Long totalRecords() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> c = cb.createQuery(Long.class);

            Root<E> root = c.from(classe);

            c.select(cb.count(root));

            return em.createQuery(c).getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public Long totalRecords(String field) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> c = cb.createQuery(Long.class);

            Root<E> root = c.from(classe);

            c.select(cb.countDistinct(root.get(field)));

            return em.createQuery(c).getSingleResult();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAllOrderByIdAsc() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            return em.createQuery(c.orderBy(cb.asc(root.get("id")))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAllOrderByIdDesc() {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            return em.createQuery(c.orderBy(cb.desc(root.get("id")))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public List<E> listAllOrderedByField(String field) {
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<E> c = cb.createQuery(classe);

            Root<E> root = c.from(classe);

            return em.createQuery(c.orderBy(cb.asc(root.get(field)))).getResultList();
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }
}