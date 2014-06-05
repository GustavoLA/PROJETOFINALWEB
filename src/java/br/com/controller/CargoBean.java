package br.com.controller;

import br.com.modelo.Cargo;
import br.com.modelo.persistencia.CargoDAOJPA;
import br.com.modelo.persistencia.dao.CargoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class CargoBean {

    private Cargo cargo;
    private List<Cargo> cargos;

    public CargoBean() {
        cargo = new Cargo();
    }

    public String insere() {
        EntityManager manager = this.getManager();
        CargoDAO dao = new CargoDAOJPA(manager);
        dao.salvar(cargo);
        this.cargo = null;
        return "/paginas/cargos.xhtml";
    }

    public String preparaAlteracao() {
        EntityManager manager = this.getManager();
        CargoDAO dao = new CargoDAOJPA(manager);
        this.cargo = dao.buscarPorId(Cargo.class, cargo.getCodigo());
        return "/paginas/cargo.xhtml";
    }

    public void remove() {
        EntityManager manager = this.getManager();
        CargoDAO dao = new CargoDAOJPA(manager);
        dao.remover(Cargo.class, cargo.getCodigo());
        this.cargos = null;
    }

    public List<Cargo> getCargos() {
        if (this.cargos == null) {
            EntityManager manager = this.getManager();
            CargoDAO dao = new CargoDAOJPA(manager);
            this.cargos = dao.buscarTodos(Cargo.class);
        }
        return cargos;
    }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
}
