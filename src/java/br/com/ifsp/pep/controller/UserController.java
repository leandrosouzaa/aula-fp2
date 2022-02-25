package br.com.ifsp.pep.controller;

import br.com.ifsp.pep.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserController implements Serializable{

    private List<User> users = new ArrayList<User>();
    private User user;

    public UserController() {
        this.adicionar();
        this.user = new User();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUsers() {
        return users;
    }

    public void adicionar() {
        User u = new User("leandrosouza", "secreta");
        this.users.add(u);
        
        u = new User("cesaralberto", "ifsp");
        this.users.add(u);
        
        u = new User("carlospedro", "carlinhos");
        this.users.add(u);
    }
    
    public void login() {
        System.out.println("logar");
        User u = autenticar(user.getUsername(), user.getPassword());
        
        if(u == null) {
            addMessage(FacesMessage.SEVERITY_WARN, "Ops!", "Usuário ou senha inválido");
        } else {
            addMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Seja bem vindo " + u.getUsername() + ".");
        }
    }

    private User autenticar(String username, String password) {
        for(User u : users) {
            if(u.getUsername().equals(username) && u.getPassword().equals(password))
                return u;
        }
        
        return null;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
}
