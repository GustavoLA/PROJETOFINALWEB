/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.modelo;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;

/**
 *
 * @author gustavo_lourenco
 */
@Entity
public class Pedido {

    @Id
    @GeneratedValue
    private int codigo;
    private String nome;
    private String telefone;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dataEntrega;
    @Lob
    private String pedido;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getPedido() {
        return pedido;
    }

    public void setPedido(String pedido) {
        this.pedido = pedido;
    }
}
