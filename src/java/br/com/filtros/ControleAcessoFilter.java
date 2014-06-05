package br.com.filtros;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author bruna_zakrzeski
 */
public class ControleAcessoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        //Se o usuario já foi logado então entra direto, se não tem que 
        //autenticar e criar novo login e senha
        try {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpServletResponse httpRes = (HttpServletResponse) response;

            HttpSession session = httpReq.getSession(true);
            String url = httpReq.getRequestURL().toString();

            if (session.getAttribute("usuariologado") == null && precisaAutenticar(url)) {
                httpRes.sendRedirect(httpReq.getContextPath() + "/faces/login.xhtml");

            } else {
                chain.doFilter(request, response);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    //Caso não tenha login, ele deverá autenticar não precisa de se ou senão
    // && !url.contains("teste.xhtml") -> não precisa autenticar
    private boolean precisaAutenticar(String url) {
        return !url.contains("login.xhtml")
                && !url.contains("pedido.xhtml")
                && !url.contains("faq.xhtml")
                && !url.contains("contato.xhtml")
                && !url.contains("quemsomos.xhtml")
                && !url.contains("index.xhtml")
                && !url.contains("javax.faces.resource");
    }
}
