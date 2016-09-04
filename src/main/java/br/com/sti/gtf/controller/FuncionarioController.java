package br.com.sti.gtf.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;

import br.com.sti.gtf.bean.Funcionario;
import br.com.sti.gtf.enums.EmployeeStatus;
import br.com.sti.gtf.enums.Role;
import br.com.sti.gtf.repository.FuncionarioRepository;
import br.com.sti.gtf.session.FuncionarioSession;
import static br.com.sti.gtf.util.Utilities.cpf;
import static br.com.sti.gtf.util.Utilities.mail;
import static br.com.sti.gtf.util.Utilities.sha512;

/**
 *
 * @author Phelipe Melanias
 */
@Controller @Path("/funcionario")
public class FuncionarioController extends MainController {

    private final FuncionarioSession funcionarioSession;
    private final FuncionarioRepository funcionarioRepository;

    /**
     * @deprecated CDI eyes only
     */
    protected FuncionarioController() {
        this(null, null, null, null, null);
    }

    @Inject
    public FuncionarioController(Result result, Validator validator, Environment environment,
                                 FuncionarioSession funcionarioSession,
                                 FuncionarioRepository funcionarioRepository) {
        super(result, validator, environment);

        this.funcionarioSession = funcionarioSession;
        this.funcionarioRepository = funcionarioRepository;

    }

    @Get
    public List<Funcionario> list() {
        result.include("title", "Funcionário");
        result.include("subTitle", "Lista de funcionários");
        result.include("editTitle", "Editar funcionário");
        result.include("viewTitle", "Informações do funcionário");

        return funcionarioRepository.listAllOrderByIdAsc();
    }

    @Get("/view/{id}")
    public Funcionario view(Long id) {
        return funcionarioRepository.find(id);
    }

    @Get("/add")
    public void addForm() {
        result.include("title", "Funcionário");
        result.include("subTitle", "Cadastrar funcionário");

        //Enumerations
        result.include("roles", Role.getAll());
        result.include("status", EmployeeStatus.getAll());
    }

    @Transactional
    @Post("/add")
    public void add(Funcionario funcionario) {
        //CPF
        if (StringUtils.isBlank(funcionario.getCpf())) {
            validator.add(new I18nMessage("funcionario.cpf", "cpf.em.branco"));
        } else if (cpf(funcionario.getCpf())) {
            validator.ensure(funcionarioRepository.isUniqueCPF(funcionario), new I18nMessage("funcionario.cpf", "cpf.existente"));
        } else {
            validator.add(new I18nMessage("funcionario.cpf", "cpf.invalido"));
        }

        //Nome
        if (StringUtils.isBlank(funcionario.getNome())) {
            validator.add(new I18nMessage("funcionario.nome", "nome.em.branco"));
        } else {
            validator.ensure(funcionarioRepository.isUniqueName(funcionario), new I18nMessage("funcionario.nome", "nome.existente"));
        }

        //E-mail
        if (StringUtils.isBlank(funcionario.getEmail())) {
            validator.add(new I18nMessage("funcionario.email", "email.em.branco"));
        } else {
            validator.ensure(mail(funcionario.getEmail()), new I18nMessage("funcionario.email", "email.invalido"));
        }

        //Exibir form com os erros de validação
        validator.onErrorRedirectTo(this).addForm();

        //Definir data de cadastro
        funcionario.setData(new Date());

        //Definir e criptografar a senha
        funcionario.setSenha(sha512(environment.get("default_password")));

        //Salvar
        funcionarioRepository.persist(funcionario);
        result.include("successMessage", "Funcionário cadastrado com sucesso.");

        result.redirectTo(this).addForm();
    }

    @Get("/edit/{id}")
    public Funcionario editForm(Long id) {
        Funcionario funcionario = funcionarioRepository.find(id);

        if (funcionario == null) {
            result.redirectTo(this).list();
        }

        //Title and subtitle
        result.include("title", "Funcionário");
        result.include("subTitle", "Editar funcionário");


        //Enumerations
        result.include("roles", Role.getAll());
        result.include("status", EmployeeStatus.getAll());

        return funcionario;
    }

    @Transactional
    @Post("/edit/{funcionario.id}")
    public void edit(Funcionario funcionario, boolean resetPasswd) {
        //CPF
        if (StringUtils.isBlank(funcionario.getCpf())) {
            validator.add(new I18nMessage("funcionario.cpf", "cpf.em.branco"));
        } else if (cpf(funcionario.getCpf())) {
            validator.ensure(funcionarioRepository.isUniqueCPF(funcionario), new I18nMessage("funcionario.cpf", "cpf.existente"));
        } else {
            validator.add(new I18nMessage("funcionario.cpf", "cpf.invalido"));
        }

        //Nome
        if (StringUtils.isBlank(funcionario.getNome())) {
            validator.add(new I18nMessage("funcionario.nome", "nome.em.branco"));
        } else {
            validator.ensure(funcionarioRepository.isUniqueName(funcionario), new I18nMessage("funcionario.nome", "nome.existente"));
        }

        //E-mail
        if (StringUtils.isBlank(funcionario.getEmail())) {
            validator.add(new I18nMessage("funcionario.email", "email.em.branco"));
        } else {
            validator.ensure(mail(funcionario.getEmail()), new I18nMessage("funcionario.email", "email.invalido"));
        }


        if (validator.hasErrors()) {
            //Title and subtitle
            result.include("title", "Funcionário");
            result.include("subTitle", "Editar funcionário");

            //Enumerations
            result.include("roles", Role.getAll());
            result.include("status", EmployeeStatus.getAll());
        }

        //Exibir form com os erros de validação
        validator.onErrorUsePageOf(this).editForm(funcionario.getId());

        //Resetar a senha se necessário
        if (resetPasswd) {
            funcionario.setSenha(sha512(environment.get("default_password")));
        } else {
            funcionario.setSenha(funcionarioRepository.find(funcionario.getId()).getSenha());
        }

        //Salvar alterações
        funcionarioRepository.merge(funcionario);
        result.include("successMessage", "Funcionário alterado com sucesso.");

        result.redirectTo(this).editForm(funcionario.getId());
    }

    @Get("/account")
    public Funcionario editAccount() {
        result.include("title", "Perfil");
        result.include("subTitle", "Alterar conta");

        return funcionarioRepository.find(funcionarioSession.getId());
    }

    @Transactional
    @Post("/account")
    public void account(Funcionario funcionario) {
        //Recuperar alguns dados do funcionário
        Funcionario old = funcionarioRepository.find(funcionarioSession.getId());

        //Definir os dados recuperados no funcionário
        funcionario.setId(old.getId());
        funcionario.setCpf(old.getCpf());
        funcionario.setSenha(old.getSenha());
        funcionario.setPerfil(old.getPerfil());
        funcionario.setStatus(old.getStatus());

        //Nome
        if (StringUtils.isBlank(funcionario.getNome())) {
            validator.add(new I18nMessage("funcionario.nome", "nome.em.branco"));
        } else {
            validator.ensure(funcionarioRepository.isUniqueName(funcionario), new I18nMessage("funcionario.nome", "nome.existente"));
        }

        //E-mail
        if (StringUtils.isBlank(funcionario.getEmail())) {
            validator.add(new I18nMessage("funcionario.email", "email.em.branco"));
        } else {
            validator.ensure(mail(funcionario.getEmail()), new I18nMessage("funcionario.email", "email.invalido"));
        }

        if (validator.hasErrors()) {
            result.include("title", "Perfil");
            result.include("subTitle", "Alterar conta");
        }

        //Exibir form com os erros de validação
        validator.onErrorUsePageOf(this).editAccount();

        //Salvar alterações
        funcionarioRepository.merge(funcionario);

        //Atualizar informações na sessão do funcionário
        funcionarioSession.login(funcionario);

        result.include("successMessage", "Informações da conta salvas com sucesso.");
        result.redirectTo(this).editAccount();
    }

    //__________________________________________________________________________
    @Get("/password")
    public void editPassword() {
        result.include("title", "Perfil");
        result.include("subTitle", "Alterar senha");
    }

    //__________________________________________________________________________
    @Transactional
    @Post("/password")
    public void password(Funcionario funcionario, String currentPassword) {
        Funcionario f = funcionarioRepository.find(funcionarioSession.getId());

        //Validar senha atual
        validator.ensure(StringUtils.isNotBlank(currentPassword), new I18nMessage("currentPassword", "currentPassword.em.branco"));

        if (!validator.hasErrors()) {
            validator.ensure(f.getSenha().equals(sha512(currentPassword)), new I18nMessage("currentPassword", "currentPassword.incorreta"));
        }

        //Validar a nova senha
        if (!validator.hasErrors()) {
            validator.ensure(StringUtils.length(funcionario.getSenha()) > 5, new I18nMessage("funcionario.senha", "senha.tamanho.invalido", 6));
        }

        if (!validator.hasErrors()) {
            validator.addIf(funcionario.getSenha().equals(environment.get("default_password")), new I18nMessage("funcionario.senha", "senha.invalida"));
        }

        //Validar a confirmação da nova senha
        if (!validator.hasErrors()) {
            if (StringUtils.isBlank(funcionario.getCheckPassword())) {
                validator.add(new I18nMessage("funcionario.checkPassword", "checkPassword.em.branco"));
            } else {
                validator.ensure(funcionario.getSenha().equals(funcionario.getCheckPassword()), new I18nMessage("funcionario.checkPassword", "checkPassword.diferente"));
            }
        }

        if (validator.hasErrors()) {
            result.include("title", "Perfil")
                  .include("subTitle", "Alterar senha");
        }

        //Exibir form com os erros de validação
        validator.onErrorUsePageOf(this).editPassword();

        //Alterar a senha
        f.setSenha(sha512(funcionario.getSenha()));

        //Salvar alterações
        funcionarioRepository.merge(f);
        result.include("successMessage", "Senha alterada com sucesso.");

        result.redirectTo(this).editPassword();
    }
}