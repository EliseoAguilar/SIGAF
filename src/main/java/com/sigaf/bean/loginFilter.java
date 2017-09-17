/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Eliseo
 */
@WebFilter(filterName = "loginFilter", urlPatterns = {"/*"})
public class loginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // check whether session variable is set
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // Get the loginBean from session attribute
        LoginBean loginBean = (LoginBean) req.getSession().getAttribute("loginBean");

        //  allow user to proccede if url is login.xhtml or user logged in or user is accessing any page in //public folder
        String reqURI = req.getRequestURI();

        String loginURL = req.getContextPath() + "/inicio/login.xhtml";

        String homeURL = req.getContextPath() + "/inicio/index.xhtml";

        /*
         System.out.println(reqURI);
        
         System.out.println(loginURL);
         */
        boolean loggedIn = loginBean != null && loginBean.getUsuarioActivo() != null;

        boolean loginRequest = reqURI.equals(loginURL);

        boolean resourceRequest = req.getRequestURI().startsWith(req.getContextPath() + "/inicio" + ResourceHandler.RESOURCE_IDENTIFIER);

        if (loggedIn && loginRequest) {
            res.sendRedirect(homeURL);
        }

   
        
        if (loggedIn && !resourceRequest) {

            if(!reqURI.split("/")[3].equals("perfil") && !reqURI.split("/")[3].equals("resources") && !reqURI.split("/")[3].equals("ayuda")){
            
                switch (loginBean.getUsuarioActivo().getTipoUsuario()) {

                case 1:

                    if (!homeURL.equals(reqURI)) {
                        if (!reqURI.split("/")[3].equals("contabilidad")) {
                            res.sendRedirect(homeURL);
                        }
                    }
                    break;

                case 2:
                    if (!homeURL.equals(reqURI)) {
                        if (!reqURI.split("/")[3].equals("finanzas")) {
                            res.sendRedirect(homeURL);
                        }
                    }
                    break;

            }
            }

        }

        if (loggedIn || loginRequest || resourceRequest) {

            if (!resourceRequest) {
                res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                res.setDateHeader("Expires", 0); // Proxies.
            }

            chain.doFilter(request, response);
        } else {
            res.sendRedirect(loginURL);
        }

    }

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
