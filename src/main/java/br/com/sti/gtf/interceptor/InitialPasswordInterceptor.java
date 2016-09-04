package br.com.sti.gtf.interceptor;

import java.util.Arrays;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.com.caelum.vraptor.validator.I18nMessage;

import br.com.sti.gtf.controller.FuncionarioController;
import br.com.sti.gtf.repository.FuncionarioRepository;
import br.com.sti.gtf.session.FuncionarioSession;
import static br.com.sti.gtf.util.Utilities.sha512;

/**
 *
 * @author Phelipe Melanias
 */
@Intercepts(after=LoginInterceptor.class)
public class InitialPasswordInterceptor {

    @Inject private Result result;
    @Inject private ResourceBundle bundle;
    @Inject private Environment environment;
    @Inject private HttpServletRequest request;
    @Inject private FuncionarioSession funcionarioSession;
    @Inject private FuncionarioRepository funcionarioRepository;


    @Accepts
    public boolean accepts() {
        if (funcionarioSession.isLoggedIn()) {
            String password = funcionarioRepository.find(funcionarioSession.getId()).getSenha();

            return password.equals(sha512(environment.get("default_password")));
        }

        return false;
    }

    @AroundCall
    public void intercept(SimpleInterceptorStack stack) {
        String uri = request.getRequestURI();

        if (uri.contains("/logout") || uri.contains("/password")) {
            stack.next();
        } else {
            result.redirectTo(FuncionarioController.class).editPassword();
        }

        //Mensagem de alerta
        I18nMessage warning = new I18nMessage("password", "password.default.unchanged");
        warning.setBundle(bundle);

        result.include("warningMessage", Arrays.asList(warning).get(0));
    }
}