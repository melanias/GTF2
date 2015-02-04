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

import br.com.sti.gtf.bean.Cor;
import br.com.sti.gtf.repository.CorRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class CorController extends MainController {

    @Inject
    private CorRepository repository;

    /**
     * @deprecated CDI eyes only
     */
    protected CorController() {
        super(null, null);
    }

    @Inject
    public CorController(Result result, Validator validator) {
        super(result, validator);
    }


    @Get("/cor")
    public List<Cor> list() {
        result.include("title", "Cor");
        result.include("subTitle", "Gerenciamento de cor");
        result.include("addTitle", "Cadastrar cor");
        result.include("editTitle", "Editar cor");

        return repository.listAllOrderedByField("nome");
    }

    @Get("/cor/add")
    public void addForm() {
        result.include("title", "Cor");
        result.include("subTitle", "Cadastrar cor");
    }

    @Transactional
    @Post("/cor/add")
    public void add(@Valid Cor cor) {
        validator.onErrorRedirectTo(this).addForm();

        //Salvar
        repository.persist(cor);
        result.include("successMessage", "Cor cadastrada com sucesso.");

        result.redirectTo(this).addForm();
    }

    @Get("/cor/edit/{id}")
    public Cor editForm(Integer id) {
        Cor cor = repository.find(id);

        if (cor == null) {
            result.redirectTo(this).list();
        }

        result.include("title", "Cor");
        result.include("subTitle", "Editar cor");

        return cor;
    }

    @Transactional
    @Post("/cor/edit/{cor.id}")
    public void edit(@Valid Cor cor) {
        if (validator.hasErrors()) {
            result.include("title", "Cor");
            result.include("subTitle", "Editar cor");
        }

        validator.onErrorUsePageOf(this).editForm(cor.getId());

        //Salvar alterações
        repository.merge(cor);
        result.include("successMessage", "Cor alterada com sucesso.");

        result.redirectTo(this).editForm(cor.getId());
    }
}