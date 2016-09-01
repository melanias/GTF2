package br.com.sti.gtf.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

import br.com.sti.gtf.bean.Funcionario;
import br.com.sti.gtf.enums.EmployeeStatus;
import br.com.sti.gtf.enums.Role;
import br.com.sti.gtf.repository.FuncionarioRepository;
import br.com.sti.gtf.util.Utilities;

/**
 *
 * @author Phelipe Melanias
 */
@Controller
public class SetupController extends MainController {

    private final FuncionarioRepository funcionarioRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected SetupController() {
        this(null, null, null, null, null);
    }

    @Inject
    public SetupController(Result result, Validator validator,  Environment environment,
                           HttpServletRequest request, FuncionarioRepository funcionarioRepository) {
        super(result, validator, environment, request);
        this.funcionarioRepository = funcionarioRepository;
    }

    @Get("/setup")
    public void setupForm() {
        //Verificar se realmente é necessário executar o setup
        if (funcionarioRepository.hasEmployees()) {
            result.notFound();
        }

        result.include("title", "Setup");
    }

    @Transactional
    @Post("/setup")
    public void setup(Funcionario funcionario) {
        //CPF
        if (isBlank(funcionario.getCpf())) {
            validator.add(new I18nMessage("funcionario.cpf", "cpf.em.branco"));
        } else {
            validator.ensure(Utilities.cpf(funcionario.getCpf()), new I18nMessage("funcionario.cpf", "cpf.invalido"));
        }

        //Nome
        validator.ensure(isNotBlank(funcionario.getNome()), new I18nMessage("funcionario.nome", "nome.em.branco"));

        //Exibir form com os erros de validação
        validator.onErrorRedirectTo(this).setupForm();

        //Definir data de cadastro
        funcionario.setData(new Date());

        //Definir perfil
        funcionario.setPerfil(Role.ADMINISTRADOR);

        //Definir status
        funcionario.setStatus(EmployeeStatus.ATIVO);

        //Definir e criptografar a senha
        funcionario.setSenha(Utilities.sha512(environment.get("default_password")));

        //Salvar
        funcionarioRepository.persist(funcionario);

        //Redirecionar para a página de login
        result.redirectTo(LoginController.class).loginForm();
    }
}