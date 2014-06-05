
package br.com.modelo.persistencia;

import br.com.modelo.persistencia.dao.DAO;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

public class DAOJPA <T, I extends Serializable> implements DAO<T, I>{
    
    private EntityManager manager;

    public DAOJPA(EntityManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void salvar(T entity) {
        this.manager.merge(entity);
    }

    @Override
    public void remover(Class<T> classe, I pk) {
        T entity = this.buscarPorId(classe, pk);
        this.manager.remove(entity);
    }

    @Override
    public T buscarPorId(Class<T> classe, I pk) {
        try{
            return this.manager.find(classe, pk);
        }catch(NoResultException e){
            return null;           
        }
    }

    @Override
    public List<T> buscarTodos(Class<T> classe) {
       Query q = this.manager.createQuery
               ("select x from " + classe.getSimpleName() + " x");
       return q.getResultList();
    }
    
}
