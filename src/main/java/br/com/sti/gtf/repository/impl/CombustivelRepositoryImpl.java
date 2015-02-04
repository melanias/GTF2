package br.com.sti.gtf.repository.impl;

import br.com.sti.gtf.bean.Combustivel;
import br.com.sti.gtf.repository.CombustivelRepository;
import br.com.sti.gtf.repository.GenericRepository;

/**
 *
 * @author Phelipe Melanias
 */
public class CombustivelRepositoryImpl extends GenericRepository<Combustivel, Integer> implements CombustivelRepository {

    public CombustivelRepositoryImpl() {
        super(Combustivel.class);
    }

    @Override
    public boolean isUniqueFuel(Combustivel combustivel) {
        return combustivel.getId() == null ? em.createQuery("FROM Combustivel c WHERE LOWER(c.nome) = ?1")
                                               .setParameter(1, combustivel.getNome().toLowerCase())
                                               .getResultList().isEmpty()
                                           : em.createQuery("FROM Combustivel c WHERE c.id <> ?1 AND LOWER(c.nome) = ?2")
                                               .setParameter(1, combustivel.getId())
                                               .setParameter(2, combustivel.getNome().toLowerCase())
                                               .getResultList().isEmpty();
    }
}