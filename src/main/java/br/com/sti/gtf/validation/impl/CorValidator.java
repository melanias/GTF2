package br.com.sti.gtf.validation.impl;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.sti.gtf.bean.Cor;
import br.com.sti.gtf.repository.CorRepository;
import br.com.sti.gtf.validation.CorValidation;

/**
 *
 * @author Phelipe Melanias
 */
public class CorValidator implements ConstraintValidator<CorValidation, Cor> {

    @Inject
    private CorRepository repository;

    @Override
    public void initialize(CorValidation constraintAnnotation) {}

    @Override
    public boolean isValid(Cor cor, ConstraintValidatorContext context) {
        if (cor == null || repository == null) {
            return true;
        }

        //Desativar a mensagem default
        context.disableDefaultConstraintViolation();

        boolean nome = ((cor.getNome() == null) ? true : repository.isUniqueColor(cor));

        if (!nome) {
            context.buildConstraintViolationWithTemplate("{cor.nome.NotUnique}").addPropertyNode("nome").addConstraintViolation();
        }

        return nome;
    }
}