package br.com.sti.gtf.validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.sti.gtf.bean.Combustivel;
import br.com.sti.gtf.repository.CombustivelRepository;
import br.com.sti.gtf.validation.CombustivelValidation;

/**
 *
 * @author Phelipe Melanias
 */
public class CombustivelValidator implements ConstraintValidator<CombustivelValidation, Combustivel> {

    @Inject
    private CombustivelRepository repository;

    @Override
    public void initialize(CombustivelValidation constraintAnnotation) {}

    @Override
    public boolean isValid(Combustivel combustivel, ConstraintValidatorContext context) {
        if (combustivel == null || repository == null) {
            return true;
        }

        //Desativar a mensagem default
        context.disableDefaultConstraintViolation();

        boolean nome = ((combustivel.getNome() == null) ? true : repository.isUniqueFuel(combustivel));

        if (!nome) {
            context.buildConstraintViolationWithTemplate("{combustivel.nome.NotUnique}").addPropertyNode("nome").addConstraintViolation();
        }

        return nome;
    }
}