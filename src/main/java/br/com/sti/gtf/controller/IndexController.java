package br.com.sti.gtf.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class IndexController extends MainController {

    /**
     * @deprecated CDI eyes only
     */
    protected IndexController() {
        this(null);
    }

    @Inject
    public IndexController(Result result) {
        super(result);
    }

    @Get("/")
    public void index() {
        result.include("title", "GTF");
        result.include("subTitle", "Gestão Total de Frota");
    }
}