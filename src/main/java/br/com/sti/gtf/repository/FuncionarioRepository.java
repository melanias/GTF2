package br.com.sti.gtf.repository;

import br.com.sti.gtf.bean.Funcionario;

/**
 *
 * @author phelipe
 */
public interface FuncionarioRepository extends Repository<Funcionario, Long> {

    boolean hasEmployees();

    boolean isUniqueCPF(Funcionario funcionario);
    boolean isUniqueName(Funcionario funcionario);

    Funcionario authenticate(String cpf, String senha);
}