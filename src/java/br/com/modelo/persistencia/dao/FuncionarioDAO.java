package br.com.modelo.persistencia.dao;
import br.com.modelo.Funcionario;


public interface FuncionarioDAO extends DAO<Funcionario, Integer> {
    
    boolean login (String login, String senha);
}
