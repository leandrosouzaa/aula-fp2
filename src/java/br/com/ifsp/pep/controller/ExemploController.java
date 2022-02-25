package br.edu.ifsp.pep.controller;

import br.edu.ifsp.pep.model.Pessoa;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author aluno
 */
@Named
@ViewScoped
public class ExemploController implements Serializable {

    private Pessoa pessoa;
    private List<Pessoa> pessoas = new ArrayList<>();

    public ExemploController() {
        System.out.println("construtor.");
        this.pessoa = new Pessoa();
    }

    public void teste() {
        System.out.println("Executou o método teste.");
        System.out.println("Nome: " + this.pessoa.getNome());
    }
    
    public void adicionar() {
        System.out.println("adicionou pessoa na lista.");
        this.pessoas.add(pessoa);
        this.pessoa = new Pessoa();
        addMessage(FacesMessage.SEVERITY_INFO, "Informação", "Cadastro realizado.");
        
    }
    
    private void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void excluir() {
        if(pessoa != null || pessoa.getNome().isEmpty()) {
            addMessage(FacesMessage.SEVERITY_WARN, "Atenção!", "Selecione uma pessoa para excluir.");
        }
        for(Pessoa p : pessoas) {
            if(p.getNome().equals(pessoa.getNome())) {
                pessoas.remove(p);
                addMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Pessoa excluida com sucesso.");
                return;
            }
        }
    }

}
