package br.com.controller;

import br.com.modelo.Cargo;
import br.com.modelo.Funcionario;
import br.com.modelo.persistencia.CargoDAOJPA;
import br.com.modelo.persistencia.FuncionarioDAOJPA;
import br.com.modelo.persistencia.dao.CargoDAO;
import br.com.modelo.persistencia.dao.FuncionarioDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class FuncionarioBean {

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
    private int cargoId;

    //Construtor
    public FuncionarioBean() {
        funcionario = new Funcionario();
    }

    //Método para inserir na web
    public String insere() {
        EntityManager manager = this.getManager();
        CargoDAO cargoDao = new CargoDAOJPA(manager);

        //Criado uma condição apra quando inserir um cargo no funcionario
        if (cargoId != 0) {
            Cargo cargo = cargoDao.buscarPorId(Cargo.class, cargoId);
            this.funcionario.setCargo(cargo);
        }

        FuncionarioDAO dao = new FuncionarioDAOJPA(manager);
        dao.salvar(funcionario);
        this.funcionarios = null;
        return "/paginas/funcionarios.xhtml";
    }

//Método para editar na web
    public String preparaAlteracao() {
        EntityManager manager = this.getManager();
        FuncionarioDAO dao = new FuncionarioDAOJPA(manager);
        this.funcionario = dao.buscarPorId(Funcionario.class, funcionario.getCodigo());

        return "/paginas/funcionario.xhtml";
    }

    //Método para remover na web
    public void remove() {
        EntityManager manager = this.getManager();
        FuncionarioDAO dao = new FuncionarioDAOJPA(manager);
        dao.remover(Funcionario.class, funcionario.getCodigo());
    }

    //Método para listar na web
    public List<Funcionario> getFuncionarios() {
        //Criado uma condição para que os valores voltem mais rapido. Ou seja quando for listar para
        //editar um funcionario ele já deixa carregado a lista dos funcionarios já cadastrados. Se não 
        //ele vai direto pra carregar pra inserir um novo
        if (this.funcionarios == null) {
            EntityManager manager = this.getManager();
            FuncionarioDAO dao = new FuncionarioDAOJPA(manager);
            this.funcionarios = dao.buscarTodos(Funcionario.class);
        }
        return funcionarios;
    }

//DADOS PARA BUSCAR DADOS NO FILTER
    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }
}
