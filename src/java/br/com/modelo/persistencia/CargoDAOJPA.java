
package br.com.modelo.persistencia;

import br.com.modelo.Cargo;
import br.com.modelo.persistencia.dao.CargoDAO;
import javax.persistence.EntityManager;


public class CargoDAOJPA extends DAOJPA<Cargo, Integer> 
implements CargoDAO{

    public CargoDAOJPA(EntityManager manager) {
        super(manager);
    }
    
    
}
