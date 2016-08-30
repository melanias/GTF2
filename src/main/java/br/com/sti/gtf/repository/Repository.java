package br.com.sti.gtf.repository;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Phelipe Melanias
 */
public interface Repository<E, I extends Serializable> {

    E find(I id);

    E find(String field, String value);

    List<E> findAll(String field, String value);

    E merge(E entity);

    void remove(E entity);

    void refresh(E entity);

    void persist(E entity);

    Long totalRecords();

    Long totalRecords(String field);

    List<E> listAll();

    List<E> listAllOrderByIdAsc();

    List<E> listAllOrderByIdDesc();

    List<E> listAllOrderedByField(String field);
}