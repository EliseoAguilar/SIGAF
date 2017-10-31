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
        TreeNode node21 = new DefaultTreeNode("Área de cultivo", node2);
        TreeNode node22 = new DefaultTreeNode("Tipos de cultivos", node2);
        
       
        TreeNode node23 = new DefaultTreeNode("Agronegocios", node2);
       
        
        TreeNode node24 = new DefaultTreeNode("Políticas", node2);
        TreeNode node25 = new DefaultTreeNode("Reportes", node2);

        TreeNode node26 = new DefaultTreeNode("Cooperativas", node3);
        TreeNode node27 = new DefaultTreeNode("Áreas", node3);
        TreeNode node28 = new DefaultTreeNode("Empleados", node3);
        TreeNode node29 = new DefaultTreeNode("Socios", node3);
        TreeNode node30 = new DefaultTreeNode("Reportes", node3);

        TreeNode node31 = new DefaultTreeNode("Perfil", node4);
        TreeNode node32 = new DefaultTreeNode("Áreas", node4);
        TreeNode node33 = new DefaultTreeNode("Empleados", node4);
        TreeNode node34 = new DefaultTreeNode("Cargos", node4);
        TreeNode node35 = new DefaultTreeNode("Políticas", node4);
        TreeNode node36 = new DefaultTreeNode("Reportes", node4);

        TreeNode node37 = new DefaultTreeNode("Usuarios", node5);
        TreeNode node38 = new DefaultTreeNode("Base de datos", node5);
        TreeNode node39 = new DefaultTreeNode("Bitácora", node5);
        TreeNode node40 = new DefaultTreeNode("Reportes", node5);

        TreeNode node41 = new DefaultTreeNode("Mapa del sitio", node6);
        TreeNode node42 = new DefaultTreeNode("Acerca de", node6);

    }

    private String pagina;

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public void seleccion() {

        this.mostrarDocumento = true;
        if (this.selectedNode.getData() == "Contabilidad") {
            this.pagina = "24";
        }
        if (this.selectedNode.getData() == "Periodos") {
            this.pagina = "27";
        }
        if (this.selectedNode.getData() == "Cuentas") {
            this.pagina = "36";
        }
        if (this.selectedNode.getData() == "Partidas") {
            this.pagina = "42";
        }
        if (this.selectedNode.getData() == "Proveedores") {
            this.pagina = "47";
        }
        if (this.selectedNode.getData() == "Tipos de activos") {
            this.pagina = "52";
        }
        if (this.selectedNode.getData() == "Activo fijo") {
            this.pagina = "57";
        }
        if (this.selectedNode.getData() == "Configuración") {
            this.pagina = "66";
        }
        if (this.selectedNode.getData() == "Reportes") {
            this.pagina = "70";
        }
        if (this.selectedNode.getData() == "Finanzas") {
            this.pagina = "73";
        }
        if (this.selectedNode.getData() == "Clientes") {
            this.pagina = "74";
        }
        if (this.selectedNode.getData() == "Solicitudes") {
            this.pagina = "85";
        }
        if (this.selectedNode.getData() == "Resoluciones") {
            this.pagina = "140";
        }
        if (this.selectedNode.getData() == "Créditos") {
            this.pagina = "149";
        }
        if (this.selectedNode.getData() == "Asesorias") {
            this.pagina = "162";
        }
        if (this.selectedNode.getData() == "Capacitaciones") {
            this.pagina = "168";
        }
        if (this.selectedNode.getData() == "Parametros") {
            this.pagina = "174";
        }
        if (this.selectedNode.getData() == "Reportes") {
            this.pagina = "178";
        }
        if (this.selectedNode.getData() == "Agronegocios") {
            this.pagina = "180";
        }
        if (this.selectedNode.getData() == "Productores") {
            this.pagina = "181";
        }
        if (this.selectedNode.getData() == "Grupos") {
            this.pagina = "188";
        }
        if (this.selectedNode.getData() == "Compradores") {
            this.pagina = "192";
        }
        if (this.selectedNode.getData() == "Cultivos") {
            this.pagina = "197";
        }
        if (this.selectedNode.getData() == "Compradores") {
            this.pagina = "192";
        }
        if (this.selectedNode.getData() == "Cultivos") {
            this.pagina = "197";
        }
        if (this.selectedNode.getData() == "Área de cultivo") {
            this.pagina = "203";
        }
        if (this.selectedNode.getData() == "Tipos de cultivos") {
            this.pagina = "207";
        }
        if (this.selectedNode.getData() == "Agronegocios" && this.selectedNode.getParent().getData()=="Agronegocios") {
            this.pagina = "211";
        }
        if (this.selectedNode.getData() == "Políticas") {
            this.pagina = "217";
        }
        if (this.selectedNode.getData() == "Reportes") {
            this.pagina = "220";
        }
        if (this.selectedNode.getData() == "Administración") {
            this.pagina = "222";
        }
        if (this.selectedNode.getData() == "Cooperativas") {
            this.pagina = "223";
        }
        if (this.selectedNode.getData() == "Áreas") {
            this.pagina = "229";
        }
        if (this.selectedNode.getData() == "Empleados") {
            this.pagina = "233";
        }
        if (this.selectedNode.getData() == "Socios") {
            this.pagina = "244";
        }

        if (this.selectedNode.getData() == "Configuración") {
            this.pagina = "252";
        }
        if (this.selectedNode.getData() == "Perfil") {
            this.pagina = "253";
        }
        if (this.selectedNode.getData() == "Áreas") {
            this.pagina = "255";
        }
        if (this.selectedNode.getData() == "Empleados") {
            this.pagina = "259";
        }

        if (this.selectedNode.getData() == "Cargos") {
            this.pagina = "265";
        }

        if (this.selectedNode.getData() == "Políticas") {
            this.pagina = "267";
        }

        if (this.selectedNode.getData() == "Políticas") {
            this.pagina = "267";
        }

        if (this.selectedNode.getData() == "Seguridad") {
            this.pagina = "274";
        }

        if (this.selectedNode.getData() == "Usuarios") {
            this.pagina = "275";
        }
        if (this.selectedNode.getData() == "Base de datos") {
            this.pagina = "280";
        }
        if (this.selectedNode.getData() == "Bitácora") {
            this.pagina = "283";
        }

        if (this.selectedNode.getData() == "Reportes") {
            this.pagina = "286";
        }

        if (this.selectedNode.getData() == "Ayuda") {
            this.pagina = "287";
        }

        if (this.selectedNode.getData() == "Mapa del sitio") {
            this.pagina = "288";
        }
        if (this.selectedNode.getData() == "Acerca de") {
            this.pagina = "217";
        }

    }

}
