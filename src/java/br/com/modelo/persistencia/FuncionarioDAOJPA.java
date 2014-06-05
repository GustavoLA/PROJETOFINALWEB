package br.com.modelo.persistencia;
import br.com.modelo.Funcionario;
import br.com.modelo.persistencia.dao.FuncionarioDAO;
import javax.persistence.EntityManager;
import javax.persistence.Query;


public class FuncionarioDAOJPA extends DAOJPA<Funcionario, Integer> implements FuncionarioDAO {
    
   private EntityManager manager;

    public FuncionarioDAOJPA(EntityManager manager) {
        super(manager);
        this.manager = manager;
    }

    @Override
    public boolean login(String login, String senha) {
    Query q = this.manager.createQuery("select f from Funcionario f where " 
            + "f.login = :login and "
            + "f.senha = :senha", Funcionario.class);
    
            q.setParameter("senha", senha);
            q.setParameter("login", login);
            
            if(q.getResultList().size() > 0) {
                return true;
        }
       return false;
    }


}
