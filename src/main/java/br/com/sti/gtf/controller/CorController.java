package br.com.sti.gtf.controller;

import br.com.caelum.vraptor.Consumes;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import static br.com.caelum.vraptor.view.Results.json;
import static br.com.caelum.vraptor.view.Results.status;
import static br.com.caelum.vraptor.view.Results.representation;

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

    @Get("/main")
    public void main() {}

    @Get
    public void list() {
        result.include("title", "Cor")
              .include("subTitle", "Lista de cores")
              .include("editTitle", "Editar cor");
    }

    @Get(value={"", "/"})
    public void listAll() {
        result.use(json()).withoutRoot().from(repository.listAllOrderedByField("nome")).serialize();
    }

    @Get("/add")
    public void addForm() {
        result.include("title", "Cor")
              .include("subTitle", "Cadastrar cor");
    }

    @Transactional
    @Post(value={"", "/"})
    @Consumes("application/json")
    public void add(Cor cor) {
        //Validar nome
        if (StringUtils.isBlank(cor.getNome())) {
            validator.add(new I18nMessage("cor.nome", "cor.em.branco"));
        } else {
            validator.ensure(repository.isUniqueColor(cor), new I18nMessage("cor.nome", "cor.existente"));
        }

        //Exibir os erros de validaçõo
        validator.onErrorSendBadRequest();

        //Salvar
        repository.persist(cor);
        result.use(status()).ok();
    }

    @Get("/edit")
    public void editForm() {
        result.include("title", "Cor")
                .include("subTitle", "Editar cor");
    }

    @Get("/{id}")
    public void editForm(Integer id) {
        Cor cor = repository.find(id);

        if (cor == null) {
            result.use(status()).notFound();
        } else {
            result.use(json()).withoutRoot().from(cor).serialize();
        }
    }

    @Transactional
    @Put("/{cor.id}")
    @Consumes("application/json")
    public void update(Cor cor) {
        //Validar nome
        if (StringUtils.isBlank(cor.getNome())) {
            validator.add(new I18nMessage("cor.nome", "cor.em.branco"));
        } else {
            validator.ensure(repository.isUniqueColor(cor), new I18nMessage("cor.nome", "cor.existente"));
        }

        //Exibir os erros de validaçõo
        validator.onErrorSendBadRequest();

        //Salvar
        repository.merge(cor);
        result.use(status()).ok();
    }
}