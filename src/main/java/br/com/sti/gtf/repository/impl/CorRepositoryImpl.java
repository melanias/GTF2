package br.com.sti.gtf.repository.impl;

import br.com.sti.gtf.bean.Cor;
import br.com.sti.gtf.repository.CorRepository;
import br.com.sti.gtf.repository.GenericRepository;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author Phelipe Melanias
 */
public class CorRepositoryImpl extends GenericRepository<Cor, Integer> implements CorRepository {

    @Inject
    public CorRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public boolean isUniqueColor(Cor cor) {
        return cor.getId() == null ? em.createQuery("FROM Cor c WHERE LOWER(c.nome) = ?1")
                                       .setParameter(1, cor.getNome().toLowerCase())
                                       .getResultList().isEmpty()
                                   : em.createQuery("FROM Cor c WHERE c.id <> ?1 AND LOWER(c.nome) = ?2")
                                       .setParameter(1, cor.getId())
                                       .setParameter(2, cor.getNome().toLowerCase())
                                       .getResultList().isEmpty();
    }
}