//package br.com.cassioliveira.ufcg.cdsa.uaeduc.controller;
//
//import br.com.cassioliveira.ufcg.cdsa.uaeduc.exception.NegocioException;
//import br.com.cassioliveira.ufcg.cdsa.uaeduc.model.Login;
//import br.com.cassioliveira.ufcg.cdsa.uaeduc.service.LoginService;
//import br.com.cassioliveira.ufcg.cdsa.uaeduc.util.jsf.FacesUtil;
//import java.io.Serializable;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import javax.enterprise.inject.Model;
//import javax.faces.application.FacesMessage;
//import javax.faces.application.NavigationHandler;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import lombok.Getter;
//import lombok.Setter;
//
///**
// *
// * @author cassio
// */
//@Model
//public class LoginBean implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Inject
//    @Getter
//    @Setter
//    private Login login;
//
//    @Inject
//    @Getter
//    @Setter
//    private LoginService loginServices;
//
//    @Inject
//    @Getter
//    @Setter
//    private Login selectedLogin;
//
//    @Getter
//    private final String loggedUser;
//
//    private List<Login> logins;
//
//    public LoginBean() {
//        this.loggedUser = (String) SecurityUtils.getSubject().getPrincipal();
//    }
//
//    @PostConstruct
//    public void init() {
//        this.logins = loginServices.findAll();
//    }
//
//    /**
//     * Chama um metodo do Service que faz a comunicaçao com a camada de
//     * persistencia para salvar as alteraçoes feitas em um cadastro existente ou
//     * salvar os dados de um novo cadastro.
//     *
//     */
//    public void save() throws NegocioException {
//        this.loginServices.save(login);
//        if (getEditing()) {
//            FacesUtil.mensagemSucesso("Cadastro de '" + login.getUserName() + "' atualizado com sucesso!");
//        } else {
//            FacesUtil.mensagemSucesso("Cadastro efetuado com sucesso!");
//        }
//        FacesUtil.redirecionaPara("SearchLogin.xhtml");
//        login = new Login();
//    }
//
//    /**
//     * Chama um metodo do Service que faz a comunicaçao com a camada de
//     * persistencia para excluir um cadastro existente.
//     *
//     */
//    public void remove() throws NegocioException {
//        this.loginServices.delete(selectedLogin);
//        FacesUtil.mensagemSucesso("Exclusão efetuada com sucesso!");
//    }
//
//    /**
//     * Metodo que verifica se o objeto esta nulo quando for recuperado. Se sim,
//     * refere-se a um novo cadastro, senao refere-se a um produto a ser editado
//     *
//     * @return
//     */
//    public boolean getEditing() {
//        return this.login.getId() != null;
//    }
//
//    /**
//     * @return the logins
//     */
//    public List<Login> getLogins() {
//        return logins;
//    }
//
//    /**
//     * Captura o usuário logado e finaliza a sessão do mesmo.
//     *
//     * @return
//     */
//    public String logout() {
//        SecurityUtils.getSubject().logout();
//        return "/login.xhtml?faces-redirect=true";
//    }
//
//    public void loginUser() {
//
//        try {
//
//            Subject currentUser = SecurityUtils.getSubject();
//            UsernamePasswordToken token = new UsernamePasswordToken(login.getUserName(), login.getPassword());
//
//            currentUser.login(token);
//
//        } catch (UnknownAccountException uae) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Usuário incorreto"));
//
//        } catch (IncorrectCredentialsException ice) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Senha incorreta"));
//
//        } catch (LockedAccountException lae) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", "Usuário está bloqueado"));
//
//        } catch (AuthenticationException aex) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Falha no login!", aex.toString()));
//        }
//
//    }
//
//    public void authorizedUserControl() {
//
//        if (null != SecurityUtils.getSubject().getPrincipal()) {
//
//            NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
//            nh.handleNavigation(FacesContext.getCurrentInstance(), null, "Inicio.xhtml?faces-redirect=true");
//
//        }
//    }
//}
