package br.com.sti.gtf.repository;

import br.com.sti.gtf.bean.Cor;

/**
 *
 * @author Phelipe Melanias
 */
public interface CorRepository extends Repository<Cor, Integer> {

    boolean isUniqueColor(Cor cor);
}