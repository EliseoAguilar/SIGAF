/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.IPoliticaBo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPolitica;
import com.sigaf.pojo.TTipoCredito;
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
 * @author Genovés
 */
@Named(value = "politicaBean")
@ManagedBean
@RequestScoped
public class PoliticaBean extends Actividad {

    /**
     * Creates a new instance of PoliticaBean
     */
    IPoliticaBo ipoliticaBo;
    private TPolitica politicas;
    private List<TPolitica> listaPoliticas;
    private IBitacoraBo bitacoraBo;
    private TPolitica politicaSeleccionada;
    private TEntidad entidad;
    private TTipoCredito credito;
    private Integer tipoDeCredito;
    private Integer empleado;
    private Integer inversion;
    private Integer produccion;
    private Integer lisiados;
    private Integer personal;
    private Integer comercio;
    private Integer capital;
    private Integer inversionAgro;
    private Integer produccionAgro;
    private Integer idEntidad;
    private Boolean estadoFormulario;
    private String msgEdadMinima;
    private String msgEdadMaxima;
    private String msgTasaInteres;
    private String msgTasaInteresMora;
    private String msgMontoMaximo;
    private String msgMontoMinimo;
    private String msgPagoMinimo;
    private String msgComision;
    private String msgSeguro;
    private String msgEndeudamientoMinimo;
    private String msgMenor;
    private String msgMayor;
    private String msgFecha;
    private Boolean mostrarEmpleados;
    private Boolean mostrarProduccionCooperativa;
    private Boolean mostrarInversionCooperativa;
    private Boolean mostrarLisiados;
    private Boolean mostrarComercio;
    private Boolean mostrarAgroCapital;
    private Boolean mostrarInversionAgro;
    private Boolean mostrarProduccionAgro;
    private Integer tipoCredito;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public List<TPolitica> getListaPoliticas() {
        return listaPoliticas;
    }

    public void setListaPoliticas(List<TPolitica> listaPoliticas) {
        this.listaPoliticas = listaPoliticas;
    }

    public Integer getTipoCredito() {
        return tipoCredito;
    }

    public void setTipoCredito(Integer tipoCredito) {
        this.tipoCredito = tipoCredito;
    }

    public Boolean getMostrarEmpleados() {
        return mostrarEmpleados;
    }

    public void setMostrarEmpleados(Boolean mostrarEmpleados) {
        this.mostrarEmpleados = mostrarEmpleados;
    }

    public Boolean getMostrarProduccionCooperativa() {
        return mostrarProduccionCooperativa;
    }

    public void setMostrarProduccionCooperativa(Boolean mostrarProduccionCooperativa) {
        this.mostrarProduccionCooperativa = mostrarProduccionCooperativa;
    }

    public Boolean getMostrarInversionCooperativa() {
        return mostrarInversionCooperativa;
    }

    public void setMostrarInversionCooperativa(Boolean mostrarInversionCooperativa) {
        this.mostrarInversionCooperativa = mostrarInversionCooperativa;
    }

    public Boolean getMostrarLisiados() {
        return mostrarLisiados;
    }

    public void setMostrarLisiados(Boolean mostrarLisiados) {
        this.mostrarLisiados = mostrarLisiados;
    }

    public Boolean getMostrarComercio() {
        return mostrarComercio;
    }

    public void setMostrarComercio(Boolean mostrarComercio) {
        this.mostrarComercio = mostrarComercio;
    }

    public Boolean getMostrarAgroCapital() {
        return mostrarAgroCapital;
    }

    public void setMostrarAgroCapital(Boolean mostrarAgroCapital) {
        this.mostrarAgroCapital = mostrarAgroCapital;
    }

    public Boolean getMostrarInversionAgro() {
        return mostrarInversionAgro;
    }

    public void setMostrarInversionAgro(Boolean mostrarInversionAgro) {
        this.mostrarInversionAgro = mostrarInversionAgro;
    }

    public Boolean getMostrarProduccionAgro() {
        return mostrarProduccionAgro;
    }

    public void setMostrarProduccionAgro(Boolean mostrarProduccionAgro) {
        this.mostrarProduccionAgro = mostrarProduccionAgro;
    }

    public void Init() {

        super.enableShowData();
        this.politicas = new TPolitica();
        this.credito = new TTipoCredito();
        this.entidad = new TEntidad();
        this.tipoDeCredito = 0;
        this.politicas.setRegistro(new Date());
        this.empleado = 1;
        this.inversion = 2;
        this.produccion = 3;
        this.produccionAgro = 4;
        this.lisiados = 5;
        this.personal = 6;
        this.comercio = 7;
        this.capital = 8;
        this.inversionAgro = 9;
        this.idEntidad = 1;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Integer empleado) {
        this.empleado = empleado;
    }

    public Integer getInversion() {
        return inversion;
    }

    public void setInversion(Integer inversion) {
        this.inversion = inversion;
    }

    public Integer getProduccion() {
        return produccion;
    }

    public void setProduccion(Integer produccion) {
        this.produccion = produccion;
    }

    public Integer getIdEntidad() {
        return idEntidad;
    }

    public void setIdEntidad(Integer idEntidad) {
        this.idEntidad = idEntidad;
    }

    public TEntidad getEntidad() {
        return entidad;
    }

    public void setEntidad(TEntidad entidad) {
        this.entidad = entidad;
    }

    public TTipoCredito getCredito() {
        return credito;
    }

    public void setCredito(TTipoCredito credito) {
        this.credito = credito;
    }

    public Integer getTipoDeCredito() {
        return tipoDeCredito;
    }

    public void setTipoDeCredito(Integer tipoDeCredito) {
        this.tipoDeCredito = tipoDeCredito;
    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;
    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public IPoliticaBo getIpoliticaBo() {
        return ipoliticaBo;
    }

    public void setIpoliticaBo(IPoliticaBo ipoliticaBo) {
        this.ipoliticaBo = ipoliticaBo;
    }

    public TPolitica getPoliticas() {
        return politicas;
    }

    public void setPoliticas(TPolitica politicas) {
        this.politicas = politicas;
    }

    public TPolitica getPoliticaSeleccionada() {
        return politicaSeleccionada;
    }

    public void setPoliticaSeleccionada(TPolitica politicaSeleccionada) {
        this.politicaSeleccionada = politicaSeleccionada;
    }

    public String getMsgEdadMinima() {
        return msgEdadMinima;
    }

    public void setMsgEdadMinima(String msgEdadMinima) {
        this.msgEdadMinima = msgEdadMinima;
    }

    public String getMsgEdadMaxima() {
        return msgEdadMaxima;
    }

    public void setMsgEdadMaxima(String msgEdadMaxima) {
        this.msgEdadMaxima = msgEdadMaxima;
    }

    public String getMsgTasaInteres() {
        return msgTasaInteres;
    }

    public void setMsgTasaInteres(String msgTasaInteres) {
        this.msgTasaInteres = msgTasaInteres;
    }

    public String getMsgTasaInteresMora() {
        return msgTasaInteresMora;
    }

    public void setMsgTasaInteresMora(String msgTasaInteresMora) {
        this.msgTasaInteresMora = msgTasaInteresMora;
    }

    public String getMsgMontoMaximo() {
        return msgMontoMaximo;
    }

    public void setMsgMontoMaximo(String msgMontoMaximo) {
        this.msgMontoMaximo = msgMontoMaximo;
    }

    public String getMsgMontoMinimo() {
        return msgMontoMinimo;
    }

    public void setMsgMontoMinimo(String msgMontoMinimo) {
        this.msgMontoMinimo = msgMontoMinimo;
    }

    public String getMsgPagoMinimo() {
        return msgPagoMinimo;
    }

    public void setMsgPagoMinimo(String msgPagoMinimo) {
        this.msgPagoMinimo = msgPagoMinimo;
    }

    public String getMsgComision() {
        return msgComision;
    }

    public void setMsgComision(String msgComision) {
        this.msgComision = msgComision;
    }

    public String getMsgSeguro() {
        return msgSeguro;
    }

    public void setMsgSeguro(String msgSeguro) {
        this.msgSeguro = msgSeguro;
    }

    public String getMsgEndeudamientoMinimo() {
        return msgEndeudamientoMinimo;
    }

    public void setMsgEndeudamientoMinimo(String msgEndeudamientoMinimo) {
        this.msgEndeudamientoMinimo = msgEndeudamientoMinimo;
    }

    public String getMsgMenor() {
        return msgMenor;
    }

    public void setMsgMenor(String msgMenor) {
        this.msgMenor = msgMenor;
    }

    public String getMsgMayor() {
        return msgMayor;
    }

    public void setMsgMayor(String msgMayor) {
        this.msgMayor = msgMayor;
    }

    public Integer getLisiados() {
        return lisiados;
    }

    public void setLisiados(Integer lisiados) {
        this.lisiados = lisiados;
    }

    public Integer getPersonal() {
        return personal;
    }

    public void setPersonal(Integer personal) {
        this.personal = personal;
    }

    public Integer getComercio() {
        return comercio;
    }

    public void setComercio(Integer comercio) {
        this.comercio = comercio;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Integer getInversionAgro() {
        return inversionAgro;
    }

    public void setInversionAgro(Integer inversionAgro) {
        this.inversionAgro = inversionAgro;
    }

    public Integer getProduccionAgro() {
        return produccionAgro;
    }

    public void setProduccionAgro(Integer produccionAgro) {
        this.produccionAgro = produccionAgro;
    }

    public void crear() {

        System.out.println("Entra");

        if (this.listaPoliticas.isEmpty()) {
            this.politicas.setEstado(true);
            //this.credito.setIdTipoCredito(this.getTipoDeCredito());
            this.politicas.setTTipoCredito(new TTipoCredito(this.tipoCredito));
            this.ipoliticaBo.create(politicas);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_politica");
            auxBitacora.setAccionBitacora("Agregar política");
            auxBitacora.setDatosBitacora("Nombre: " + this.politicas.getTTipoCredito().getNombre());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.politicas.getIdPolitica());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Política agregada correctamente"));
            limpiar(1);
        } else {
            this.ipoliticaBo.update(this.tipoCredito);
            this.politicas.setEstado(true);
            //this.credito.setIdTipoCredito(this.getTipoDeCredito());
            this.politicas.setTTipoCredito(new TTipoCredito(this.tipoCredito));
            this.ipoliticaBo.create(politicas);
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_politica");
            auxBitacora.setAccionBitacora("Agregar política");
            auxBitacora.setDatosBitacora("Nombre: " + this.politicas.getTTipoCredito().getNombre());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.politicas.getIdPolitica());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Política agregada correctamente"));
            limpiar(1);
        }
    }

    public void limpiar(int opc) {

        this.estadoFormulario = false;
        this.politicas = new TPolitica();
        this.politicas.setRegistro(new Date());
        this.msgComision = "";
        this.msgEdadMaxima = "";
        this.msgEdadMinima = "";
        this.msgEndeudamientoMinimo = "";
        this.msgMontoMaximo = "";
        this.msgMontoMinimo = "";
        this.msgPagoMinimo = "";
        this.msgSeguro = "";
        this.msgTasaInteres = "";
        this.msgFecha = "";
        this.msgTasaInteresMora = "";
        this.listaPoliticas = this.ipoliticaBo.listPolitica(this.tipoCredito);
        if (opc == 1) {
            super.enableShowData();
        }
    }

    public void validarFormulario() {

        this.estadoFormulario = true;
        if (this.politicas.getCapacidadEndeudamientoMinimo() == null) {
            this.msgEndeudamientoMinimo = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgEndeudamientoMinimo = "";
        }
        if (this.politicas.getCapacidadPagoMinimo() == null) {
            this.msgPagoMinimo = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgPagoMinimo = "";
        }
        if (this.politicas.getComision() == null) {
            this.msgComision = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgComision = "";
        }
        if (this.politicas.getEdadMaxima() == null) {
            this.msgEdadMaxima = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgEdadMaxima = "";
        }
        if (this.politicas.getEdadMaxima() != null) {
            if (this.politicas.getEdadMaxima() < 18) {
                this.msgEdadMaxima = "La edad debe ser mayor que 18 años";
                this.estadoFormulario = false;
            } else {
                this.msgEdadMaxima = "";
            }
        }
        if (this.politicas.getEdadMinima() == null) {
            this.msgEdadMinima = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgEdadMinima = "";
        }
        if (this.politicas.getEdadMinima() != null) {
            if (this.politicas.getEdadMinima() < 18) {
                this.msgEdadMinima = "La edad mínima debe ser mayor que 18 años";
                this.estadoFormulario = false;
            } else {
                this.msgEdadMinima = "";
            }
        }
        if (this.politicas.getEdadMaxima() != null) {
            if (this.politicas.getEdadMinima() > this.politicas.getEdadMaxima()) {
                this.msgMayor = "La edad mínima debe ser menor que la edad máxima";
                this.estadoFormulario = false;
            } else {
                this.msgMayor = "";
            }
        }
        if (this.politicas.getMontoMaximo() == null) {
            this.msgMontoMaximo = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgMontoMaximo = "";
        }
        if (this.politicas.getMontoMinimo() == null) {
            this.msgMontoMinimo = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgMontoMinimo = "";
        }
        if (this.politicas.getMontoMaximo() != null) {
            if (this.politicas.getMontoMinimo().compareTo(this.politicas.getMontoMaximo()) > 0) {
                this.msgMenor = "El monto mínimo debe ser menor que el monto máximo";
                this.estadoFormulario = false;
            } else {
                this.msgMenor = "";
            }
        }
        if (this.politicas.getSeguroDeuda() == null) {
            this.msgSeguro = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgSeguro = "";
        }
        if (this.politicas.getTasaInteres() == null) {
            this.msgTasaInteres = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgTasaInteres = "";
        }
        if (this.politicas.getTasaInteresMora() == null) {
            this.msgTasaInteresMora = "Se requiere un valor";
            this.estadoFormulario = false;
        } else {
            this.msgTasaInteresMora = "";
        }
        if (this.politicas.getRegistro() == null) {
            this.msgFecha = "Se requiere una fecha";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
    }

    public void asignar(Integer valor) {

        this.tipoDeCredito = valor;

    }

    public void mostrarTiposCreditos() {

        if (this.tipoCredito == 1) {

            this.listaPoliticas = this.ipoliticaBo.listPolitica(1);

        } else if (this.tipoCredito == 3) {

            this.listaPoliticas = this.ipoliticaBo.listPolitica(3);

        } else if (this.tipoCredito == 2) {

            this.listaPoliticas = this.ipoliticaBo.listPolitica(2);

        } else if (this.tipoCredito == 5) {
            this.listaPoliticas = this.ipoliticaBo.listPolitica(5);

        } else if (this.tipoCredito == 7) {
            this.listaPoliticas = this.ipoliticaBo.listPolitica(7);

        } else if (this.tipoCredito == 8) {
            this.listaPoliticas = this.ipoliticaBo.listPolitica(8);

        } else if (this.tipoCredito == 9) {
            this.listaPoliticas = this.ipoliticaBo.listPolitica(9);

        } else if (this.tipoCredito == 4) {
            this.listaPoliticas = this.ipoliticaBo.listPolitica(4);

        } else {

        }

    }

    public void mostrarTipos() {

        if (this.tipoCredito == 1) {

            this.mostrarEmpleados = true;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;

        } else if (this.tipoCredito == 3) {

            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = true;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;
        } else if (this.tipoCredito == 2) {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = true;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;
        } else if (this.tipoCredito == 5) {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = true;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;
        } else if (this.tipoCredito == 7) {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = true;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;
        } else if (this.tipoCredito == 8) {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = true;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;
        } else if (this.tipoCredito == 9) {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = true;
            this.mostrarProduccionAgro = false;
        } else if (this.tipoCredito == 4) {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = true;
        } else {
            this.mostrarEmpleados = false;
            this.mostrarProduccionCooperativa = false;
            this.mostrarInversionCooperativa = false;
            this.mostrarLisiados = false;
            this.mostrarComercio = false;
            this.mostrarAgroCapital = false;
            this.mostrarInversionAgro = false;
            this.mostrarProduccionAgro = false;
        }
    }

    public void verReportePolitica() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_politica", this.politicaSeleccionada.getIdPolitica());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/politica.jasper"));
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
        auxBitacora.setTableBitacora("t_politica");
        auxBitacora.setAccionBitacora("Generar reporte política");
        auxBitacora.setDatosBitacora("Nombre: " + this.politicaSeleccionada.getTTipoCredito().getNombre());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.politicaSeleccionada.getIdPolitica());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void verReportePoliticaPDF() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_politica", this.politicaSeleccionada.getIdPolitica());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/politica.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Politica.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_politica");
        auxBitacora.setAccionBitacora("Generar PDF política");
        auxBitacora.setDatosBitacora("Nombre: " + this.politicaSeleccionada.getTTipoCredito().getNombre());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.politicaSeleccionada.getIdPolitica());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }
}
