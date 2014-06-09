
import br.com.modelo.Pedido;
import br.com.modelo.persistencia.PedidoDAOJPA;
import br.com.modelo.persistencia.dao.PedidoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class PedidoBean {

    private Pedido pedido;
    private List<Pedido> pedidos;

    public PedidoBean() {
        pedido = new Pedido();
    }

    public String insere() {
        EntityManager manager = this.getManager();
        PedidoDAO dao = new PedidoDAOJPA(manager);
        dao.salvar(pedido);
        this.pedido = null;
        return "/index.xhtml";
    }

    public String preparaAlteracao() {
        EntityManager manager = this.getManager();
        PedidoDAO dao = new PedidoDAOJPA(manager);
        this.pedido = dao.buscarPorId(Pedido.class, pedido.getCodigo());
        return "/paginas/pedido.xhtml";
    }

    public void remove() {
        EntityManager manager = this.getManager();
        PedidoDAO dao = new PedidoDAOJPA(manager);
        dao.remover(Pedido.class, pedido.getCodigo());
        this.pedidos = null;
    }

    public List<Pedido> getPedidos() {
        if (this.pedidos == null) {
            EntityManager manager = this.getManager();
            PedidoDAO dao = new PedidoDAOJPA(manager);
            this.pedidos = dao.buscarTodos(Pedido.class);
        }
        return pedidos;
    }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
