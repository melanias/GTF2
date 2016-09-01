package br.com.sti.gtf.repository.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.com.sti.gtf.bean.Funcionario;
import br.com.sti.gtf.enums.EmployeeStatus;
import br.com.sti.gtf.repository.FuncionarioRepository;
import br.com.sti.gtf.repository.GenericRepository;
import br.com.sti.gtf.util.Utilities;

/**
 *
 * @author Phelipe Melanias
 */
public class FuncionarioRepositoryImpl extends GenericRepository<Funcionario, Long> implements FuncionarioRepository {

    @Inject
    public FuncionarioRepositoryImpl(EntityManager em) {
        super(em);
    }

    @Override
    public boolean hasEmployees() {
        return !listAll().isEmpty();
    }

    @Override
    public boolean isUniqueCPF(Funcionario funcionario) {
        return (funcionario.getId() == null) ? em.createQuery("FROM Funcionario f WHERE f.cpf = ?1")
                                                 .setParameter(1, funcionario.getCpf())
                                                 .getResultList().isEmpty()
                                             : em.createQuery("FROM Funcionario f WHERE f.id <> ?1 AND f.cpf = ?2")
                                                 .setParameter(1, funcionario.getId())
                                                 .setParameter(2, funcionario.getCpf())
                                                 .getResultList().isEmpty();
    }

    @Override
    public boolean isUniqueName(Funcionario funcionario) {
        return (funcionario.getId() == null) ? em.createQuery("FROM Funcionario f WHERE lower(f.nome) = ?1")
                                                 .setParameter(1, funcionario.getNome().toLowerCase())
                                                 .getResultList().isEmpty()
                                             : em.createQuery("FROM Funcionario f WHERE f.id <> ?1 AND lower(f.nome) = ?2")
                                                 .setParameter(1, funcionario.getId())
                                                 .setParameter(2, funcionario.getNome().toLowerCase())
                                                 .getResultList().isEmpty();
    }

    @Override
    public Funcionario authenticate(String cpf, String senha) {
        try {
            return (Funcionario) em.createQuery("FROM Funcionario f WHERE f.cpf = ?1 AND f.senha = ?2 AND f.status = ?3")
                                   .setParameter(1, cpf)
                                   .setParameter(2, Utilities.sha512(senha))
                                   .setParameter(3, EmployeeStatus.ATIVO)
                                   .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}