package br.com.modelo.persistencia;

import br.com.modelo.Pedido;
import br.com.modelo.persistencia.dao.PedidoDAO;
import javax.persistence.EntityManager;

public class PedidoDAOJPA extends DAOJPA<Pedido, Integer>
        implements PedidoDAO {

    public PedidoDAOJPA(EntityManager manager) {
        super(manager);
    }
}
