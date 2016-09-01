package br.com.sti.gtf.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import static org.apache.commons.lang3.StringUtils.isBlank;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

import br.com.sti.gtf.bean.Funcionario;
import br.com.sti.gtf.repository.FuncionarioRepository;
import br.com.sti.gtf.session.FuncionarioSession;
import static br.com.sti.gtf.util.Utilities.isNull;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class LoginController extends MainController {

    private final FuncionarioSession funcionarioSession;
    private final FuncionarioRepository funcionarioRepository;

    /**
     * @deprecated CDI eyes only.
     */
    protected LoginController() {
        this(null, null, null, null, null);
    }

    @Inject
    public LoginController(Result result, Validator validator, HttpServletRequest request,
                           FuncionarioSession funcionarioSession, FuncionarioRepository funcionarioRepository) {
        super(result, validator, request);
        this.funcionarioSession = funcionarioSession;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Get("/login")
    public void loginForm() {
        if (funcionarioSession.isLoggedIn()) {
            result.redirectTo(IndexController.class).index();
        }

        result.include("title", "Login");
    }

    @Post("/login")
    public void login(final String cpf, final String senha) {
        //Validar CPF e Senha
        if (isBlank(cpf) && isBlank(senha)) {
            validator.add(new I18nMessage("cpf", "cpf.e.senha.em.branco"));
        } else {
            validator.addIf(isBlank(cpf), new I18nMessage("cpf", "cpf.em.branco"));
            validator.addIf(isBlank(senha), new I18nMessage("senha", "senha.em.branco"));
        }

        //Exibir os erros de validação do CPF e da senha
        validator.onErrorRedirectTo(this).loginForm();

        //Validar login
        Funcionario funcionario = funcionarioRepository.authenticate((cpf == null ? "" : cpf.trim()), (senha == null ? "" : senha.trim()));
        validator.addIf(isNull(funcionario), new I18nMessage("login", "login.invalido"));

        validator.onErrorRedirectTo(this).loginForm();

        //Efetuar login caso não tenha ocorrido algum erro
        funcionarioSession.login(funcionario);

        result.redirectTo(IndexController.class).index();
    }

    @Get("/logout")
    public void logout() {
        request.getSession().invalidate();

        result.redirectTo(this).loginForm();
    }
}