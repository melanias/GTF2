package br.com.sti.gtf.controller;

import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.Validator;

/**
 *
 * @author Phelipe Melanias
 */
public abstract class MainController {

    protected Result result;
    protected Validator validator;
    protected Environment environment;
    protected HttpServletRequest request;

    public MainController(Result result) {
        this.result = result;
    }

    public MainController(Result result, Validator validator) {
        this.result = result;
        this.validator = validator;
    }

    public MainController(Result result, Validator validator, Environment environment) {
        this.result = result;
        this.validator = validator;
        this.environment = environment;
    }

    public MainController(Result result, Validator validator, HttpServletRequest request) {
        this.result = result;
        this.validator = validator;
        this.request = request;
    }

    public MainController(Result result, Validator validator, Environment environment, HttpServletRequest request) {
        this.result = result;
        this.validator = validator;
        this.environment = environment;
        this.request = request;
    }
}