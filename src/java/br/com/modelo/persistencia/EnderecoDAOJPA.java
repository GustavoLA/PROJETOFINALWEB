package br.com.modelo.persistencia;
import br.com.modelo.Endereco;
import br.com.modelo.persistencia.dao.EnderecoDAO;
import javax.persistence.EntityManager;


public class EnderecoDAOJPA extends DAOJPA<Endereco, Integer> implements EnderecoDAO{

    public EnderecoDAOJPA(EntityManager manager) {
        super(manager);
    }
    


    
}
