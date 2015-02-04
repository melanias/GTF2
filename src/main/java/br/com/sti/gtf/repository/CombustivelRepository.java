package br.com.sti.gtf.repository;

import br.com.sti.gtf.bean.Combustivel;

/**
 *
 * @author Phelipe Melanias
 */
public interface CombustivelRepository extends Repository<Combustivel, Integer> {

    boolean isUniqueFuel(Combustivel combustivel);
}