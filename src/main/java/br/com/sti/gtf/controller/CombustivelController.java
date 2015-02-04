package br.com.sti.gtf.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;

import br.com.sti.gtf.bean.Combustivel;
import br.com.sti.gtf.repository.CombustivelRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class CombustivelController extends MainController {

    @Inject
    private CombustivelRepository repository;

    /**
     * @deprecated CDI eyes only
     */
    protected CombustivelController() {
        super(null, null);
    }

    @Inject
    public CombustivelController(Result result, Validator validator) {
        super(result, validator);
    }


    @Get("/combustivel")
    public List<Combustivel> list() {
        result.include("title", "Combustível");
        result.include("subTitle", "Gerenciamento de combustível");
        result.include("addTitle", "Cadastrar combustível");
        result.include("editTitle", "Editar combustível");

        return repository.listAllOrderedByField("nome");
    }

    @Get("/combustivel/add")
    public void addForm() {
        result.include("title", "Combustível");
        result.include("subTitle", "Cadastrar combustível");
    }

    @Transactional
    @Post("/combustivel/add")
    public void add(@Valid Combustivel combustivel) {
        validator.onErrorRedirectTo(this).addForm();

        //Salvar
        repository.persist(combustivel);
        result.include("successMessage", "Combustível cadastrado com sucesso.");

        result.redirectTo(this).addForm();
    }

    @Get("/combustivel/edit/{id}")
    public Combustivel editForm(Integer id) {
        Combustivel combustivel = repository.find(id);

        if (combustivel == null) {
            result.redirectTo(this).list();
        }

        result.include("title", "Combustível");
        result.include("subTitle", "Editar combustível");

        return combustivel;
    }

    @Transactional
    @Post("/combustivel/edit/{combustivel.id}")
    public void edit(@Valid Combustivel combustivel) {
        if (validator.hasErrors()) {
            result.include("title", "Combustível");
            result.include("subTitle", "Editar combustível");
        }

        validator.onErrorUsePageOf(this).editForm(combustivel.getId());

        //Salvar alterações
        repository.merge(combustivel);
        result.include("successMessage", "Combustível alterado com sucesso.");

        result.redirectTo(this).editForm(combustivel.getId());
    }
}