package br.com.sti.gtf.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

import br.com.sti.gtf.repository.CorRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class IndexController extends MainController {

    private final CorRepository corRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected IndexController() {
        this(null, null);
    }

    @Inject
    public IndexController(Result result, CorRepository corRepository) {
        super(result);

        this.corRepository = corRepository;
    }

    @Get(value={"", "/"})
    public void template() {
        result.include("title", "GTF")
                .include("subTitle", "Gestão Total de Frota");
    }

    @Get("/index")
    public void index() {
        result.include("title", "GTF")
                .include("totalColors", corRepository.totalRecords());
    }
}