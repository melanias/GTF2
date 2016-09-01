package br.com.sti.gtf.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.sti.gtf.controller.SetupController;

import br.com.sti.gtf.repository.FuncionarioRepository;

/**
 *
 * @author Phelipe Melanias
 */
@Intercepts
public class SetupInterceptor {

    @Inject private Result result;
    @Inject private HttpServletRequest request;
    @Inject private FuncionarioRepository funcionarioRepository;

    @Accepts
    public boolean accepts() {
        return !funcionarioRepository.hasEmployees();
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        if (request.getRequestURI().contains("/setup")) {
            stack.next();
        } else {
            result.redirectTo(SetupController.class).setupForm();
        }
    }
}