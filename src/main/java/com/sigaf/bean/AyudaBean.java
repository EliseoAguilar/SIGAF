package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IConfiguracionBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TConfiguracion;
import com.sigaf.pojo.TEntidad;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.hibernate.HibernateException;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Genoves
 */
@ManagedBean
@RequestScoped

public class AyudaBean extends Actividad {

    
    private TreeNode selectedNode;
    private Boolean mostrarDocumento;
    
    private TreeNode root;
    
    
    private boolean mostrarTabla;


    public Boolean getMostrarDocumento() {
        return mostrarDocumento;
    }

    public void setMostrarDocumento(Boolean mostrarDocumento) {
        this.mostrarDocumento = mostrarDocumento;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }


    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public void Init() {

        root = new DefaultTreeNode("SIGAF", null);
        TreeNode node0 = new DefaultTreeNode("Contabilidad", root);
        TreeNode node1 = new DefaultTreeNode("Finanzas", root);
        TreeNode node2 = new DefaultTreeNode("Agronegocios", root);
        TreeNode node3 = new DefaultTreeNode("Administración", root);
        TreeNode node4 = new DefaultTreeNode("Configuración", root);
        TreeNode node5 = new DefaultTreeNode("Seguridad", root);
        TreeNode node6 = new DefaultTreeNode("Ayuda", root);

        TreeNode node00 = new DefaultTreeNode("Periodos", node0);
        TreeNode node01 = new DefaultTreeNode("Cuentas", node0);
        TreeNode node02 = new DefaultTreeNode("Partidas", node0);
        TreeNode node04 = new DefaultTreeNode("Proveedores", node0);
        TreeNode node05 = new DefaultTreeNode("Tipos de activos", node0);
        TreeNode node06 = new DefaultTreeNode("Activo fijo", node0);
        TreeNode node07 = new DefaultTreeNode("Configuración", node0);
        TreeNode node08 = new DefaultTreeNode("Reportes", node0);

        TreeNode node09 = new DefaultTreeNode("Clientes", node1);
        TreeNode node10 = new DefaultTreeNode("Solicitudes", node1);
        TreeNode node11 = new DefaultTreeNode("Resoluciones", node1);
        TreeNode node12 = new DefaultTreeNode("Créditos", node1);
        TreeNode node13 = new DefaultTreeNode("Asesorias", node1);
        TreeNode node14 = new DefaultTreeNode("Capacitaciones", node1);
        TreeNode node15 = new DefaultTreeNode("Parametros", node1);
        TreeNode node16 = new DefaultTreeNode("Reportes", node1);

        TreeNode node17 = new DefaultTreeNode("Productores", node2);
        TreeNode node18 = new DefaultTreeNode("Grupos", node2);
        TreeNode node19 = new DefaultTreeNode("Compradores", node2);
        TreeNode node20 = new DefaultTreeNode("Cultivos", node2);
        TreeNode node21 = new DefaultTreeNode("Agronegocios", node2);
        TreeNode node22 = new DefaultTreeNode("Pagos", node2);
        TreeNode node23 = new DefaultTreeNode("Políticas", node2);
        TreeNode node24 = new DefaultTreeNode("Reportes", node2);

        TreeNode node25 = new DefaultTreeNode("Cooperativas", node3);
        TreeNode node26 = new DefaultTreeNode("Áreas", node3);
        TreeNode node27 = new DefaultTreeNode("Empleados", node3);
        TreeNode node28 = new DefaultTreeNode("Socios", node3);
        TreeNode node29 = new DefaultTreeNode("Reportes", node3);

        TreeNode node34 = new DefaultTreeNode("Perfil", node4);
        TreeNode node35 = new DefaultTreeNode("Áreas", node4);
        TreeNode node36 = new DefaultTreeNode("Empleados", node4);
        TreeNode node37 = new DefaultTreeNode("Cargos", node4);
        TreeNode node38 = new DefaultTreeNode("Políticas", node4);
        TreeNode node39 = new DefaultTreeNode("Reportes", node4);

        TreeNode node43 = new DefaultTreeNode("Usuarios", node5);
        TreeNode node44 = new DefaultTreeNode("Base de datos", node5);
        TreeNode node45 = new DefaultTreeNode("Bitácora", node5);
        TreeNode node46 = new DefaultTreeNode("Reportes", node5);

        TreeNode node47 = new DefaultTreeNode("Como navegar", node6);
        TreeNode node48 = new DefaultTreeNode("Mapa del sitio", node6);

    }

    public void seleccion() {

        this.mostrarDocumento = true;

    }

}
