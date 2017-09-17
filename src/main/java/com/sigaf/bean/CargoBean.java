/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICargoBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCargo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author Genoves
 */
@Named(value = "cargoBean")
@RequestScoped
@ManagedBean
public class CargoBean extends Actividad {

    ICargoBo icargoBo;
    TCargo cargo;
    TCargo cargoSeleccionado;
    List<TCargo> listaCargos;
    String nombreAux;
    private boolean estadoFormulario;
    private String msgNombre;
    private String msgNombreRepetido;
    private IBitacoraBo bitacoraBo;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public ICargoBo getIcargoBo() {
        return icargoBo;
    }

    public void setIcargoBo(ICargoBo icargoBo) {
        this.icargoBo = icargoBo;
    }

    public TCargo getCargoSeleccionado() {
        return cargoSeleccionado;
    }

    public void setCargoSeleccionado(TCargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    public TCargo getCargo() {
        return cargo;
    }

    public void setCargo(TCargo cargo) {
        this.cargo = cargo;
    }

    public void updateListaCargos() {
        listaCargos = this.icargoBo.listCargo(1);
    }

    public List<TCargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<TCargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public String getNombreAux() {
        return nombreAux;
    }

    public void setNombreAux(String nombreAux) {
        this.nombreAux = nombreAux;
    }

    public String getMsgNombreRepetido() {
        return msgNombreRepetido;
    }

    public void setMsgNombreRepetido(String msgNombreRepetido) {
        this.msgNombreRepetido = msgNombreRepetido;
    }

    /**
     * Creates a new instance of DepartamentoBean
     */
    public void Init() {
        this.cargo = new TCargo();
        this.cargoSeleccionado = new TCargo();
        super.enableShowData();
        this.estadoFormulario = false;
    }

    public boolean isEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public void crear() {
        System.out.println(this.cargo.getNombreCargo());
        this.cargo.setEstadoCargo(1);
        this.icargoBo.create(this.cargo);
        this.cargo = new TCargo();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cargo");
        auxBitacora.setAccionBitacora("Agregar cargo");
        auxBitacora.setDatosBitacora("Nombre: " + this.cargo.getNombreCargo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.cargo.getIdCargo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cargo agregado correctamente"));
        this.estadoFormulario = false;
    }

    public void modificar() {
        System.out.println(this.cargoSeleccionado.getNombreCargo());
        this.icargoBo.update(this.cargoSeleccionado);
        this.estadoFormulario = false;
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cargo");
        auxBitacora.setAccionBitacora("Modificar cargo");
        auxBitacora.setDatosBitacora("Nombre: " + this.cargoSeleccionado.getNombreCargo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.cargoSeleccionado.getIdCargo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Cargo modificado correctamente"));
        super.enableShowData();
    }

    public void darDeBaja() {
        System.out.println(this.cargoSeleccionado.getNombreCargo());
        try {
            this.cargoSeleccionado.setEstadoCargo(0);
            this.icargoBo.update(this.cargoSeleccionado);
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cargo");
            auxBitacora.setAccionBitacora("Dar de baja cargo");
            auxBitacora.setDatosBitacora("Nombre: " + this.cargoSeleccionado.getNombreCargo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.cargoSeleccionado.getIdCargo());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cargo dado de baja correctamente"));
            super.enableShowData();
        } catch (Exception e) {
        }
    }

    public void darDeAlta() {
        System.out.println(this.cargoSeleccionado.getNombreCargo());
        try {
            this.cargoSeleccionado.setEstadoCargo(1);
            this.icargoBo.update(this.cargoSeleccionado);
            this.estadoFormulario = false;
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_cargo");
            auxBitacora.setAccionBitacora("Dar de alta cargo");
            auxBitacora.setDatosBitacora("Nombre: " + this.cargoSeleccionado.getNombreCargo());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.cargoSeleccionado.getIdCargo());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Cargo dado de alta correctamente"));
            super.enableShowData();
        } catch (Exception e) {
        }
    }

    public void validarFormulario() {
        this.estadoFormulario = true;
        if (this.cargo.getNombreCargo().length() <= 5) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        this.listaCargos = this.icargoBo.listCargo(1);
        int repetido = 0;
        for (int i = 0; i < this.listaCargos.size(); i++) {
            if (this.cargo.getNombreCargo().toLowerCase().equals(this.listaCargos.get(i).getNombreCargo().toLowerCase())) {
                repetido++;
            }
        }
        System.out.println(repetido);
        if (repetido > 0) {
            this.msgNombreRepetido = "El nombre del cargo ya existe";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;
        if (this.cargoSeleccionado.getNombreCargo().length() <= 5) {
            this.msgNombre = "El nombre debe contener como minimo 5 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        this.listaCargos = this.icargoBo.listCargo(1);
        int repetido = 0;
        for (int i = 0; i < this.listaCargos.size(); i++) {
            if (this.cargoSeleccionado.getNombreCargo().toLowerCase().equals(this.listaCargos.get(i).getNombreCargo().toLowerCase()) && !this.nombreAux.toLowerCase().equals(this.listaCargos.get(i).getNombreCargo().toLowerCase())) {
                repetido++;
            }
        }
        System.out.println(repetido);
        if (repetido > 0) {
            this.msgNombreRepetido = "El nombre del cargo ya existe";
            this.estadoFormulario = false;
        } else {
            this.msgNombreRepetido = "";
        }
    }

    public void limpiar(int i) {
        this.msgNombre = "";
        this.msgNombreRepetido = "";
        if (i == 0) {
            this.estadoFormulario = false;
            //System.out.println("limpiando");
            this.cargo = new TCargo();
        } else {
            this.cargo = new TCargo();
            this.estadoFormulario = false;
            super.enableShowData();
        }
    }

    public void verReporteCargo() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_departamento", this.cargoSeleccionado.getIdCargo());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/cargo.jasper"));
        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, this.getConn());
        System.out.println(bytes.length);
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cargo");
        auxBitacora.setAccionBitacora("Generar reporte cargo");
        auxBitacora.setDatosBitacora("Nombre: " + this.cargoSeleccionado.getNombreCargo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.cargoSeleccionado.getIdCargo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void verReporteCargoPDF() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_departamento", this.cargoSeleccionado.getIdCargo());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/cargo.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cargo.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_cargo");
        auxBitacora.setAccionBitacora("Generar PDF cargo");
        auxBitacora.setDatosBitacora("Nombre: " + this.cargoSeleccionado.getNombreCargo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.cargoSeleccionado.getIdCargo());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

}
