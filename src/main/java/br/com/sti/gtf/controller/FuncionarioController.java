package br.com.sti.gtf.controller;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.environment.Environment;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.sti.gtf.bean.Funcionario;
import br.com.sti.gtf.enums.EmployeeStatus;
import br.com.sti.gtf.enums.Role;
import br.com.sti.gtf.repository.FuncionarioRepository;
import br.com.sti.gtf.session.FuncionarioSession;
import br.com.sti.gtf.util.Utilities;
import static br.com.sti.gtf.util.Utilities.cpf;
import static br.com.sti.gtf.util.Utilities.mail;
import static br.com.sti.gtf.util.Utilities.sha512;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;

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
        result.include("title", "Funcionários");
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
        if (funcionario.getCpf() == null || funcionario.getCpf().isEmpty()) {
            validator.add(new SimpleMessage("funcionario.cpf", "Informe o CPF do funcionário."));
        } else if (!cpf(funcionario.getCpf())) {
            validator.ensure(cpf(funcionario.getCpf()), new SimpleMessage("funcionario.cpf", "CPF inválido."));
        } else {
            validator.ensure(funcionarioRepository.isUniqueCPF(funcionario), new SimpleMessage("funcionario.cpf", "Já existe um funcionário com este CPF."));
        }

        //Nome
        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            validator.add(new SimpleMessage("funcionario.nome", "Informe o nome do funcionário."));
        } else {
            validator.ensure(funcionarioRepository.isUniqueName(funcionario), new SimpleMessage("funcionario.nome", "Já existe um funcionário com este nome."));
        }

        //E-mail
        if (funcionario.getEmail() != null && !funcionario.getEmail().isEmpty()) {
            validator.ensure(mail(funcionario.getEmail()), new SimpleMessage("funcionario.email", "E-mail inválido."));
        }


        if (validator.hasErrors()) {
            //Title and subtitle
            result.include("title", "Funcionários");
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
        funcionario.setStatus(old.getStatus());

        //Nome
        if (funcionario.getNome() == null || funcionario.getNome().isEmpty()) {
            validator.add(new SimpleMessage("funcionario.nome", "Informe o seu nome."));
        } else {
            validator.ensure(funcionarioRepository.isUniqueName(funcionario), new SimpleMessage("funcionario.nome", "Já existe um funcionário com este nome."));
        }

        //E-mail
        if (funcionario.getEmail() != null && !funcionario.getEmail().isEmpty()) {
            validator.ensure(mail(funcionario.getEmail()), new SimpleMessage("funcionario.email", "O e-mail informado não é válido."));
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
    public void password(Funcionario funcionario) {
        //Validar a senha se necessário
        if (funcionario.getSenha() != null && !funcionario.getSenha().isEmpty()) {
            //Senha
            validator.ensure(funcionario.getSenha().length() > 5, new SimpleMessage("funcionario.senha", "A nova senha deve ter no mínimo 6 caracteres."));

            //Confirmação da senha
            if (funcionario.getSenha().length() > 5) {
                if (funcionario.getCheckPassword() == null || funcionario.getCheckPassword().isEmpty()) {
                    validator.add(new SimpleMessage("funcionario.checkPassword", "Confirme a nova senha."));
                } else {
                    validator.ensure(funcionario.getSenha().equals(funcionario.getCheckPassword()), new SimpleMessage("funcionario.checkPassword", "A senha de confirmação não confere com a senha informada."));
                }
            }
        } else {
            validator.add(new SimpleMessage("funcionario.senha", "Informe uma nova senha."));
        }

        if (validator.hasErrors()) {
            result.include("title", "Perfil");
            result.include("subTitle", "Alterar senha");
        }

        //Exibir form com os erros de validação
        validator.onErrorUsePageOf(this).editPassword();

        //Alterar a senha
        Funcionario f = funcionarioRepository.find(funcionarioSession.getId());
        f.setSenha(Utilities.sha512(funcionario.getSenha()));

        //Salvar alterações
        funcionarioRepository.merge(f);
        result.include("successMessage", "Senha alterada com sucesso.");

        result.redirectTo(this).editPassword();
    }
}