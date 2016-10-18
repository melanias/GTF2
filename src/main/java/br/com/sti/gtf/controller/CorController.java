package br.com.sti.gtf.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import static br.com.caelum.vraptor.view.Results.json;

import br.com.sti.gtf.bean.Cor;
import br.com.sti.gtf.repository.CorRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Controller @Path("/cor")
public class CorController extends MainController {

    private final CorRepository repository;

    /**
     * @deprecated CDI eyes only
     */
    protected CorController() {
        this(null, null, null);
    }

    @Inject
    public CorController(Result result, Validator validator, CorRepository repository) {
        super(result, validator);
        this.repository = repository;
    }

    @Get
    public void list() {
        result.include("title", "Cor")
              .include("subTitle", "Lista de cores")
              .include("editTitle", "Editar cor");
    }

    @Get
    @Path(value={"", "/"})
    public void listAll() {
        result.use(json()).withoutRoot().from(repository.listAllOrderedByField("nome")).serialize();
    }

    @Get("/add")
    public void addForm() {
        //Title and subtitle
        result.include("title", "Cor")
              .include("subTitle", "Cadastrar cor");
    }

    @Transactional
    @Post("/add")
    public void add(Cor cor) {
        //Validar nome
        if (StringUtils.isBlank(cor.getNome())) {
            validator.add(new I18nMessage("cor.nome", "cor.em.branco"));
        } else {
            validator.ensure(repository.isUniqueColor(cor), new I18nMessage("cor.nome", "cor.existente"));
        }

        //Exibir form com os erros de validação
        validator.onErrorRedirectTo(this).addForm();

        //Salvar
        repository.persist(cor);
        result.include("successMessage", "Cor cadastrada com sucesso.");

        result.redirectTo(this).addForm();
    }

    @Get("/edit/{id}")
    public Cor editForm(Integer id) {
        Cor cor = repository.find(id);

        if (cor == null) {
            result.redirectTo(this).list();
        }

        //Title and subtitle
        result.include("title", "Cor")
              .include("subTitle", "Editar cor");

        return cor;
    }

    @Transactional
    @Post("/edit/{cor.id}")
    public void edit(Cor cor) {
        //Validar nome
        if (StringUtils.isBlank(cor.getNome())) {
            validator.add(new I18nMessage("cor.nome", "cor.em.branco"));
        } else {
            validator.ensure(repository.isUniqueColor(cor), new I18nMessage("cor.nome", "cor.existente"));
        }

        if (validator.hasErrors()) {
            //Title and subtitle
            result.include("title", "Cor")
                  .include("subTitle", "Editar cor");
        }

        validator.onErrorUsePageOf(this).editForm(cor.getId());

        //Salvar alterações
        repository.merge(cor);
        result.include("successMessage", "Cor alterada com sucesso.");

        result.redirectTo(this).editForm(cor.getId());
    }
}