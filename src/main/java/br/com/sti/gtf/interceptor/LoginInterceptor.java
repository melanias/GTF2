package br.com.sti.gtf.interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.sti.gtf.controller.LoginController;

import br.com.sti.gtf.session.FuncionarioSession;

/**
 *
 * @author Phelipe Melanias
 */
@Intercepts(after=SetupInterceptor.class)
public class LoginInterceptor {

    @Inject private Result result;
    @Inject private HttpServletRequest request;
    @Inject private FuncionarioSession funcionarioSession;

    @Accepts
    public boolean accepts() {
        return !funcionarioSession.isLoggedIn();
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        String uri = request.getRequestURI();

        if (uri.contains("/setup") ||  uri.contains("/login")) {
            stack.next();
        } else {
            result.redirectTo(LoginController.class).loginForm();
        }
    }
}
