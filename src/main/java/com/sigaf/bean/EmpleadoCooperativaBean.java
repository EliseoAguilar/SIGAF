/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAreaBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICargoBo;
import com.sigaf.Ibo.IEmpleadoAreaBo;
import com.sigaf.Ibo.IEmpleadoBo;
import com.sigaf.Ibo.IEmpleadoCargoBo;
import com.sigaf.Ibo.IEntidadBo;
import com.sigaf.Ibo.IOcupacionBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCargo;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEmpleadoCargo;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TOcupacion;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import org.hibernate.HibernateException;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Genoves
 */
@Named(value = "empleadoCooperativaBean")
@RequestScoped
@ManagedBean
public class EmpleadoCooperativaBean extends Actividad {

    private Integer idEntidadSeleccionada;
    private Integer idAreaModificar;
    private int idArea;
    private int idSociosArea;
    private IEmpleadoBo iempleadoBo;
    private IEntidadBo ientidadBo;
    private IEmpleadoCargoBo iempleadoCargoBo;
    private List<TOcupacion> listaOcupacionesAux;
    private TEmpleado empleados;
    private TEmpleado empleadoSeleccionado;
    private List<TEntidad> listaEntidades;
    private TEmpleadoCargo listaModificar;
    private int idCargoIndividual;
    private Boolean estadoFormulario;
    private String msgNombre;
    private String msgApellido;
    private String msgDui;
    private String msgNit;
    private String msgSexo;
    private String msgTelefono;
    private String msgMovil;
    private String msgSalario;
    private String msgDireccion;
    private String msgCargo;
    private String msgFechaNacimiento;
    private String msgFechaIngreso;
    private String msgMunicipio;
    private String msgOcupacion;
    private String msgArea;
    private String msgCorreo;
    private String msgDuiRepetido;
    private Date fechaHoy;
    private Integer idCargoModificar;
    private TEmpleado empleadoAux;
    private IAreaBo areaBo;
    private ICargoBo icargoBo;
    private IOcupacionBo iocupacionBo;
    private TArea area;
    private TCargo cargo;
    private TOcupacion ocupacionAuxiliar;
    private String nombreOcupacion;
    private TEmpleadoCargo empleadoCargo;
    private IEmpleadoAreaBo iempleadoAreaBo;
    private String msgCorreoRepetido;
    private String correoCopia;
    private TEmpleadoCargo empleadoCargoSeleccionado;
    private ArrayList<TOcupacion> listaOcupacion;
    private List<TCargo> listaCargo;
    private List<TEmpleadoCargo> listaEmpleadoCargos;
    private String direccion;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean showImagen;
    private Boolean emptyImagen;
    private String msgImagen;
    private Boolean showImagenModificar;
    private String fotoEmpleado;
    private Integer idCargo;
    private String duiCopia;
    private List<TCargo> listaCargoModificar;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificar;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificarCopia;
    private List<TArea> listaArea;
    private List<TEmpleado> listaEmpleados;
    private List<TEmpleado> ListaSocios;
    private List<TCargo> listaCargoModificarCopia;
    private String msgFechaInicio;
    private boolean estadoFecha;
    private boolean estadoHabilitar;
    private boolean estadoBusqueda;
    private boolean estadoDatos;
    private String msgAreaSelec;
    private String msgEmpleado;
    private List<TEmpleado> listaSocioNoEmpleados;
    private IBitacoraBo bitacoraBo;
    private String msgLugarNacimiento;
    private Integer edad;

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getMsgLugarNacimiento() {
        return msgLugarNacimiento;
    }

    public void setMsgLugarNacimiento(String msgLugarNacimiento) {
        this.msgLugarNacimiento = msgLugarNacimiento;
    }

    public TEmpleadoArea getEmpleadoArea() {
        return empleadoArea;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getMsgFechaInicio() {
        return msgFechaInicio;
    }

    public void setMsgFechaInicio(String msgFechaInicio) {
        this.msgFechaInicio = msgFechaInicio;
    }

    public boolean isEstadoFecha() {
        return estadoFecha;
    }

    public void setEstadoFecha(boolean estadoFecha) {
        this.estadoFecha = estadoFecha;
    }

    public List<TCargo> getListaCargoModificarCopia() {
        return listaCargoModificarCopia;
    }

    public void setListaCargoModificarCopia(List<TCargo> listaCargoModificarCopia) {
        this.listaCargoModificarCopia = listaCargoModificarCopia;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public String getMsgImagen() {
        return msgImagen;
    }

    public void setMsgImagen(String msgImagen) {
        this.msgImagen = msgImagen;
    }

    public Boolean getEmptyImagen() {
        return emptyImagen;
    }

    public void setEmptyImagen(Boolean emptyImagen) {
        this.emptyImagen = emptyImagen;
    }

    public void updateListaEntidades() {
        this.listaEntidades = this.ientidadBo.listTEndidad();
    }

    public void updateListaEmpleados() {
        this.listaEmpleados = this.iempleadoBo.listEmpleadosOrSocios(idArea, 1);
    }

    public List<TEmpleado> getListaSocioNoEmpleados() {
        return listaSocioNoEmpleados;
    }

    public void setListaSocioNoEmpleados(List<TEmpleado> listaSocioNoEmpleados) {
        this.listaSocioNoEmpleados = listaSocioNoEmpleados;
    }

    public List<TEmpleado> getListaSocios() {
        return ListaSocios;
    }

    public void setListaSocios(List<TEmpleado> ListaSocios) {
        this.ListaSocios = ListaSocios;
    }

    public TEmpleadoCargo getListaModificar() {
        return listaModificar;
    }

    public void setListaModificar(TEmpleadoCargo listaModificar) {
        this.listaModificar = listaModificar;
    }

    public int getIdCargoIndividual() {
        return idCargoIndividual;
    }

    public void setIdCargoIndividual(int idCargoIndividual) {
        this.idCargoIndividual = idCargoIndividual;
    }

    public List<TEntidad> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<TEntidad> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    private TEmpleadoArea empleadoArea;

    public void setEmpleadoArea(TEmpleadoArea empleadoArea) {
        this.empleadoArea = empleadoArea;
    }

    public TEmpleado getEmpleadoAux() {
        return empleadoAux;
    }

    public void setEmpleadoAux(TEmpleado empleadoAux) {
        this.empleadoAux = empleadoAux;
    }

    public String getCorreoCopia() {
        return correoCopia;
    }

    public void setCorreoCopia(String correoCopia) {
        this.correoCopia = correoCopia;
    }

    public String getMsgCorreoRepetido() {
        return msgCorreoRepetido;
    }

    public void setMsgCorreoRepetido(String msgCorreoRepetido) {
        this.msgCorreoRepetido = msgCorreoRepetido;
    }

    public String getMsgAreaSelec() {
        return msgAreaSelec;
    }

    public void setMsgAreaSelec(String msgAreaSelec) {
        this.msgAreaSelec = msgAreaSelec;
    }

    public String getMsgEmpleado() {
        return msgEmpleado;
    }

    public void setMsgEmpleado(String msgEmpleado) {
        this.msgEmpleado = msgEmpleado;
    }

    public boolean isEstadoBusqueda() {
        return estadoBusqueda;
    }

    public void setEstadoBusqueda(boolean estadoBusqueda) {
        this.estadoBusqueda = estadoBusqueda;
    }

    public boolean isEstadoDatos() {
        return estadoDatos;
    }

    public void setEstadoDatos(boolean estadoDatos) {
        this.estadoDatos = estadoDatos;
    }

    public boolean isEstadoHabilitar() {
        return estadoHabilitar;
    }

    public void setEstadoHabilitar(boolean estadoHabilitar) {
        this.estadoHabilitar = estadoHabilitar;
    }

    public IEmpleadoAreaBo getIempleadoAreaBo() {
        return iempleadoAreaBo;
    }

    public void setIempleadoAreaBo(IEmpleadoAreaBo iempleadoAreaBo) {
        this.iempleadoAreaBo = iempleadoAreaBo;
    }

    public List<TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public Integer getIdEntidadSeleccionada() {
        return idEntidadSeleccionada;
    }

    public void setIdEntidadSeleccionada(Integer idEntidadSeleccionada) {
        this.idEntidadSeleccionada = idEntidadSeleccionada;
        this.idArea = 0;

    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }
    private boolean mostrarTabla;

    public List<TArea> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<TArea> listaArea) {
        this.listaArea = listaArea;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFotoEmpleado() {
        return fotoEmpleado;
    }

    public void setFotoEmpleado(String fotoEmpleado) {
        this.fotoEmpleado = fotoEmpleado;
    }

    public Boolean getShowImagenModificar() {
        return showImagenModificar;
    }

    public void setShowImagenModificar(Boolean showImagenModificar) {
        this.showImagenModificar = showImagenModificar;
    }

    public String getNombreOcupacion() {
        return nombreOcupacion;
    }

    public void setNombreOcupacion(String nombreOcupacion) {
        this.nombreOcupacion = nombreOcupacion;
    }

    public String getDuiCopia() {
        return duiCopia;
    }

    public void setDuiCopia(String duiCopia) {
        this.duiCopia = duiCopia;
    }

    public TEmpleadoCargo getEmpleadoCargoSeleccionado() {
        return empleadoCargoSeleccionado;
    }

    public void setEmpleadoCargoSeleccionado(TEmpleadoCargo empleadoCargoSeleccionado) {
        this.empleadoCargoSeleccionado = empleadoCargoSeleccionado;
    }

    public List<TEmpleadoCargo> getListaEmpleadoCargosModificarCopia() {
        return listaEmpleadoCargosModificarCopia;
    }

    public void setListaEmpleadoCargosModificarCopia(List<TEmpleadoCargo> listaEmpleadoCargosModificarCopia) {
        this.listaEmpleadoCargosModificarCopia = listaEmpleadoCargosModificarCopia;
    }

    public List<TEmpleadoCargo> getListaEmpleadoCargosModificar() {
        return listaEmpleadoCargosModificar;
    }

    public void setListaEmpleadoCargosModificar(List<TEmpleadoCargo> listaEmpleadoCargosModificar) {
        this.listaEmpleadoCargosModificar = listaEmpleadoCargosModificar;
    }

    public List<TCargo> getListaCargoModificar() {
        return listaCargoModificar;
    }

    public void setListaCargoModificar(List<TCargo> listaCargoModificar) {
        this.listaCargoModificar = listaCargoModificar;
    }

    public List<TCargo> getListaCargo() {
        return listaCargo;
    }

    public void setListaCargo(List<TCargo> listaCargo) {
        this.listaCargo = listaCargo;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public List<TOcupacion> getListaOcupacionesAux() {
        return listaOcupacionesAux;
    }

    public void setListaOcupacionesAux(List<TOcupacion> listaOcupacionesAux) {
        this.listaOcupacionesAux = listaOcupacionesAux;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<TEmpleadoCargo> getListaEmpleadoCargos() {
        return listaEmpleadoCargos;
    }

    public void setListaEmpleadoCargos(List<TEmpleadoCargo> listaEmpleadoCargos) {
        this.listaEmpleadoCargos = listaEmpleadoCargos;
    }

    public ArrayList<TOcupacion> getListaOcupacion() {
        return listaOcupacion;
    }

    public void setListaOcupacion(ArrayList<TOcupacion> listaOcupacion) {
        this.listaOcupacion = listaOcupacion;
    }

    public boolean getShowImagen() {
        return showImagen;
    }

    public void setShowImagen(boolean showImagen) {
        this.showImagen = showImagen;
    }

    public TOcupacion getOcupacionAuxiliar() {
        return ocupacionAuxiliar;
    }

    public void setOcupacionAuxiliar(TOcupacion ocupacionAuxiliar) {
        this.ocupacionAuxiliar = ocupacionAuxiliar;
    }

    public Integer getIdCargoModificar() {
        return idCargoModificar;
    }

    public void setIdCargoModificar(Integer idCargoModificar) {
        this.idCargoModificar = idCargoModificar;
    }

    public Integer getIdAreaModificar() {
        return idAreaModificar;
    }

    public void setIdAreaModificar(Integer idAreaModificar) {
        this.idAreaModificar = idAreaModificar;
    }

    public Integer getIdArea() {

        return idArea;

    }

    public IEmpleadoCargoBo getIempleadoCargoBo() {
        return iempleadoCargoBo;
    }

    /**
     * Creates a new instance of EmpleadoBean
     *
     */
    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
        this.idAreaModificar = idArea;
    }

    public TCargo getCargo() {
        return cargo;
    }

    public void setCargo(TCargo cargo) {
        this.cargo = cargo;
    }

    public TEmpleadoCargo getEmpleadoCargo() {
        return empleadoCargo;
    }

    public void setEmpleadoCargo(TEmpleadoCargo empleadoCargo) {
        this.empleadoCargo = empleadoCargo;
    }

    public ICargoBo getIcargoBo() {
        return icargoBo;
    }

    public void setIcargoBo(ICargoBo icargoBo) {
        this.icargoBo = icargoBo;
    }

    public void setIempleadoCargoBo(IEmpleadoCargoBo iempleadoCargoBo) {
        this.iempleadoCargoBo = iempleadoCargoBo;
    }

    public IEntidadBo getIentidadBo() {
        return ientidadBo;
    }

    public void setIentidadBo(IEntidadBo ientidadBo) {
        this.ientidadBo = ientidadBo;
    }

    public IAreaBo getAreaBo() {
        return areaBo;
    }

    public void setAreaBo(IAreaBo areaBo) {
        this.areaBo = areaBo;
    }

    public IOcupacionBo getIocupacionBo() {
        return iocupacionBo;
    }

    public void setIocupacionBo(IOcupacionBo iocupacionBo) {
        this.iocupacionBo = iocupacionBo;
    }

    public TArea getArea() {
        return area;
    }

    public void setArea(TArea area) {
        this.area = area;
    }

    public IEmpleadoBo getIempleadoBo() {
        return iempleadoBo;
    }

    public void setIempleadoBo(IEmpleadoBo iempleadoBo) {
        this.iempleadoBo = iempleadoBo;
    }

    public TEmpleado getEmpleados() {
        return empleados;
    }

    public void setEmpleados(TEmpleado empleados) {
        this.empleados = empleados;
    }

    public TEmpleado getEmpleadoSeleccionado() {
        return empleadoSeleccionado;
    }

    public void setEmpleadoSeleccionado(TEmpleado empleadoSeleccionado) {

        this.empleadoSeleccionado = empleadoSeleccionado;

    }

    public Boolean getEstadoFormulario() {
        return estadoFormulario;

    }

    public void setEstadoFormulario(Boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public String getMsgNombre() {
        return msgNombre;
    }

    public void setMsgNombre(String msgNombre) {
        this.msgNombre = msgNombre;
    }

    public String getMsgApellido() {
        return msgApellido;
    }

    public void setMsgApellido(String msgApellido) {
        this.msgApellido = msgApellido;
    }

    public String getMsgDui() {
        return msgDui;
    }

    public void setMsgDui(String msgDui) {
        this.msgDui = msgDui;
    }

    public String getMsgNit() {
        return msgNit;
    }

    public void setMsgNit(String msgNit) {
        this.msgNit = msgNit;
    }

    public String getMsgSexo() {
        return msgSexo;
    }

    public void setMsgSexo(String msgSexo) {
        this.msgSexo = msgSexo;
    }

    public String getMsgTelefono() {
        return msgTelefono;
    }

    public void setMsgTelefono(String msgTelefono) {
        this.msgTelefono = msgTelefono;
    }

    public String getMsgMovil() {
        return msgMovil;
    }

    public void setMsgMovil(String msgMovil) {
        this.msgMovil = msgMovil;
    }

    public String getMsgSalario() {
        return msgSalario;
    }

///Validar
    public void setMsgSalario(String msgSalario) {
        this.msgSalario = msgSalario;
    }

    public String getMsgDireccion() {
        return msgDireccion;
    }

    public void setMsgDireccion(String msgDireccion) {
        this.msgDireccion = msgDireccion;
    }

    public String getMsgCargo() {
        return msgCargo;
    }

    public void setMsgCargo(String msgCargo) {
        this.msgCargo = msgCargo;
    }

    public String getMsgFechaNacimiento() {
        return msgFechaNacimiento;
    }

    public void setMsgFechaNacimiento(String msgFechaNacimiento) {
        this.msgFechaNacimiento = msgFechaNacimiento;
    }

    public String getMsgFechaIngreso() {
        return msgFechaIngreso;
    }

    public void setMsgFechaIngreso(String msgFechaIngreso) {
        this.msgFechaIngreso = msgFechaIngreso;
    }

    public String getMsgMunicipio() {
        return msgMunicipio;
    }

    public void setMsgMunicipio(String msgMunicipio) {
        this.msgMunicipio = msgMunicipio;
    }

    public String getMsgOcupacion() {
        return msgOcupacion;
    }

    public void setMsgOcupacion(String msgOcupacion) {
        this.msgOcupacion = msgOcupacion;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgDuiRepetido() {
        return msgDuiRepetido;
    }

    public void setMsgDuiRepetido(String msgDuiRepetido) {
        this.msgDuiRepetido = msgDuiRepetido;
    }

    public String getMsgCorreo() {
        return msgCorreo;
    }

    public void setMsgCorreo(String msgCorreo) {
        this.msgCorreo = msgCorreo;
    }

    public void updateListaSocios() {
        this.idSociosArea = this.areaBo.listArea2(this.idEntidadSeleccionada).get(0).getIdArea();
        this.ListaSocios = this.iempleadoBo.listEmpleadosOrSocios(idSociosArea, 2);
        this.listaSocioNoEmpleados = new ArrayList<>();

        for (int i = 0; i < this.ListaSocios.size(); i++) {

            System.out.println(this.ListaSocios.size());

            if (this.ListaSocios.get(i).getEstadoEmpleado() == false) {

                this.listaSocioNoEmpleados.add(this.ListaSocios.get(i));
            }

        }

    }

    public void updateListaAreas() {
        this.listaArea = this.areaBo.listArea(idEntidadSeleccionada);
    }

    public void enableShowCreateBean() {
        super.enableShowCreate();
        this.listaCargo = this.icargoBo.listCargo(1);
        /*
        Dado que se utiliza el mismo objeto 
        para ver los datos y a la hora de crear
        se limpia con una nueva instacia
         */
        this.empleadoSeleccionado = new TEmpleado();
    }

    public void Init() {
        float salario = 0;
        this.idArea = 0;
        this.idEntidadSeleccionada = 0;
        this.setFechaHoy(new Date());
        this.setEstadoFormulario(false);
        this.setShowImagen(true);
        this.empleadoSeleccionado = new TEmpleado();
        this.empleadoCargo = new TEmpleadoCargo();
        this.emptyImagen = true;
        this.ocupacionAuxiliar = new TOcupacion();
        listaOcupacion = new ArrayList<>();
        this.empleados = new TEmpleado();
        this.area = new TArea();
        this.listaOcupacionesAux = new ArrayList<>();
        this.listaOcupacion = new ArrayList<>();
        this.cargo = new TCargo();
        this.fechaFin = null;
        this.empleados.setSalarioEmpleado(BigDecimal.ZERO);
        this.listaEmpleadoCargos = new ArrayList<>();
        this.listaEmpleadoCargosModificar = new ArrayList<>();
        this.listaEmpleadoCargosModificarCopia = new ArrayList<>();
        this.empleados.setSexoEmpleado("Másculino");
        this.empleadoArea = new TEmpleadoArea();
        this.estadoHabilitar = true;
        this.estadoBusqueda = true;
        this.estadoDatos = false;
        this.correoCopia = "";
        this.showImagenModificar = false;
        this.msgCorreoRepetido = "";
        this.listaCargoModificarCopia = new VirtualFlow.ArrayLinkedList<>();
        this.listaModificar = new TEmpleadoCargo();
        this.estadoFecha = true;
        super.enableShowData();
    }

    //metodo llama a crear
    public void create() {
        if (this.estadoHabilitar == true) {
            this.empleados.setIdEmpleado(this.empleadoSeleccionado.getIdEmpleado());
            this.empleados.setNombreEmpleado(this.empleadoSeleccionado.getNombreEmpleado());
            this.empleados.setApellidoEmpleado(this.empleadoSeleccionado.getApellidoEmpleado());
            this.empleados.setDuiEmpleado(this.empleadoSeleccionado.getDuiEmpleado());
            this.empleados.setNitEmpleado(this.empleadoSeleccionado.getNitEmpleado());
            this.empleados.setFechanacimientoEmpleado(this.empleadoSeleccionado.getFechanacimientoEmpleado());
            this.empleados.setSexoEmpleado(this.empleadoSeleccionado.getSexoEmpleado());
            this.empleados.setTelefonoEmpleado(this.empleadoSeleccionado.getTelefonoEmpleado());
            this.empleados.setCelularEmpleado(this.empleadoSeleccionado.getCelularEmpleado());
            this.empleados.setCorreoEmpleado(this.empleadoSeleccionado.getCorreoEmpleado());
            this.empleados.setEstadoEmpleado(Boolean.TRUE);
            this.empleados.setEstadoE(true);
            this.iempleadoBo.update(this.empleados);
            this.area.setIdArea(this.idArea);
            this.empleadoArea.setTEmpleado(empleados);
            this.empleadoArea.setTArea(new TArea(this.idAreaModificar));
            this.iempleadoAreaBo.create(this.empleadoArea);
            for (int i = 0; i < this.listaEmpleadoCargos.size(); i++) {
                TCargo auxCargo = new TCargo();
                TEmpleadoCargo auxEmpleadoCargo = new TEmpleadoCargo();
                auxCargo.setIdCargo(this.listaEmpleadoCargos.get(i).getTCargo().getIdCargo());
                auxEmpleadoCargo.setTEmpleado(empleados);
                auxEmpleadoCargo.setTCargo(auxCargo);
                auxEmpleadoCargo.setFechainicioEmpleadoCargo(new Date());
                auxEmpleadoCargo.setEstadoEmpleadoCargo(true);
                iempleadoCargoBo.create(auxEmpleadoCargo);
            }
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_empleado");
            auxBitacora.setAccionBitacora("Agregar empleado");
            auxBitacora.setDatosBitacora("Nombre: " + this.empleados.getNombreEmpleado() + ", Apellido: " + this.empleados.getApellidoEmpleado());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.empleados.getIdEmpleado());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Registro insertado correctamente"));
        } else {
            this.empleados.setEstadoEmpleado(Boolean.TRUE);
            this.empleados.setEstadoE(true);
            this.empleados.setEstadoSocio(false);
            this.empleados.setEstadoS(false);
            this.area.setIdArea(this.idArea);
            this.iempleadoBo.create(this.empleados);
            this.empleadoArea.setTEmpleado(empleados);
            this.empleadoArea.setTArea(new TArea(this.idArea));
            this.iempleadoAreaBo.create(this.empleadoArea);
            for (int i = 0; i < this.listaEmpleadoCargos.size(); i++) {
                TCargo auxCargo = new TCargo();
                TEmpleadoCargo auxEmpleadoCargo = new TEmpleadoCargo();
                auxCargo.setIdCargo(this.listaEmpleadoCargos.get(i).getTCargo().getIdCargo());
                auxEmpleadoCargo.setTEmpleado(empleados);
                auxEmpleadoCargo.setTCargo(auxCargo);
                auxEmpleadoCargo.setFechainicioEmpleadoCargo(new Date());
                auxEmpleadoCargo.setEstadoEmpleadoCargo(true);
                iempleadoCargoBo.create(auxEmpleadoCargo);
            }
            for (int j = 0; j < this.listaOcupacion.size(); j++) {
                TOcupacion auxOcupacion = new TOcupacion();
                auxOcupacion.setTEmpleado(empleados);
                auxOcupacion.setEstadoOcupacion(true);
                auxOcupacion.setNombreOcupacion(listaOcupacion.get(j).getNombreOcupacion());
                iocupacionBo.create(auxOcupacion);
            }
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_empleado");
            auxBitacora.setAccionBitacora("Agregar empleado");
            auxBitacora.setDatosBitacora("Nombre: " + this.empleados.getNombreEmpleado() + ", Apellido: " + this.empleados.getApellidoEmpleado());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.empleados.getIdEmpleado());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Empleado guardado correctamente"));
        }
        limpiar(1);
    }

    public void modificar() {

        if (this.showImagenModificar) {
            this.empleadoSeleccionado.setFotoEmpleado(this.fotoEmpleado);
        }
        try {
            this.iempleadoBo.update(this.empleadoSeleccionado);
            this.empleadoArea.setTArea(new TArea(this.idAreaModificar));
            this.iempleadoAreaBo.update(this.empleadoArea);

            for (int i = 0; i < this.listaEmpleadoCargosModificarCopia.size(); i++) {
                this.iempleadoCargoBo.update(listaEmpleadoCargosModificar.get(i));
            }
            for (int i = this.listaEmpleadoCargosModificarCopia.size(); i < this.listaEmpleadoCargosModificar.size(); i++) {
                this.listaEmpleadoCargosModificar.get(i).setEstadoEmpleadoCargo(true);
                this.iempleadoCargoBo.create(listaEmpleadoCargosModificar.get(i));
            }
            for (int k = 0; k < this.listaOcupacionesAux.size(); k++) {
                this.listaOcupacion.get(k).setTEmpleado(empleadoSeleccionado);
                this.iocupacionBo.update(listaOcupacion.get(k));
            }
            for (int k = listaOcupacionesAux.size(); k < this.listaOcupacion.size(); k++) {
                this.listaOcupacion.get(k).setTEmpleado(empleadoSeleccionado);
                this.listaOcupacion.get(k).setEstadoOcupacion(true);
                this.iocupacionBo.create(listaOcupacion.get(k));
            }
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("t_empleado");
            auxBitacora.setAccionBitacora("Modificar empleado");
            auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionado.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionado.getApellidoEmpleado());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.empleadoSeleccionado.getIdEmpleado());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Empleado modificado correctamente"));
            limpiar(0);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error al modificar al empleado"));
        }
    }

    public void darDeBaja() {
        this.empleadoAux.setEstadoE(false);
        iempleadoBo.update(this.empleadoAux);
        System.out.println(this.empleadoAux.getNombreEmpleado());
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Dar de baja empleado");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionado.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionado.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoSeleccionado.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Empleado dado de baja correctamente"));
        super.enableShowData();
    }

    public void darDeAlta() {
        this.empleadoAux.setEstadoE(true);
        iempleadoBo.update(this.empleadoAux);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Dar de alta empleado");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionado.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionado.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoSeleccionado.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Empleado dado de alta correctamente:"));
        super.enableShowData();
    }

    public void darDeAltaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(false);
    }

    public void darDeBajaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(true);
    }

    public void darDeBajaCargo(int id) {

    }

    public void darDeAltaCargo(int id) {
        this.listaEmpleadoCargosModificar.get(id).setFechafinEmpleadoCargo(null);
    }

    public void darDeBajaCargos(int id) {
        this.listaModificar.setFechainicioEmpleadoCargo(this.listaEmpleadoCargosModificar.get(id).getFechainicioEmpleadoCargo());
        this.listaModificar.setTCargo(this.listaEmpleadoCargosModificar.get(id).getTCargo());
        this.idCargoIndividual = id;
    }

    public void guardarCargo(int id) {
        this.listaEmpleadoCargosModificar.get(id).setFechafinEmpleadoCargo(listaModificar.getFechafinEmpleadoCargo());
        this.listaEmpleadoCargosModificar.get(id).setEstadoEmpleadoCargo(false);
        this.listaModificar = new TEmpleadoCargo();
    }

    public void validarFormulario() {
        if (this.estadoHabilitar == true) {
            this.estadoFormulario = true;
            if (this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0) {
                this.msgSalario = "Debe introducir un valor";
                this.estadoFormulario = false;
            } else {
                this.msgSalario = "";
            }
            if (this.listaEmpleadoCargos.size() == 0) {
                this.msgCargo = "Seleccione al menos un cargo";
                this.estadoFormulario = false;
            } else {
                this.msgCargo = "";
            }

            if (this.empleados.getFechaingresoEmpleado() == null) {
                this.msgFechaIngreso = "Seleccione la fecha de ingreso del empleado";
                this.estadoFormulario = false;
            } else {
                this.msgFechaIngreso = "";
            }
            if (this.emptyImagen == true) {
                this.msgImagen = "no has seleccionado una imágen o no ha sigo cargada";
                this.estadoFormulario = false;
            } else {
                this.msgImagen = "";
            }
            if (this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0 || this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) == 0) {
                this.msgSalario = "Debe ingresar un valor";
                this.estadoFormulario = false;
            } else {
                this.msgSalario = "";
            }
            if (this.idAreaModificar == null) {
                this.msgArea = "Debe seleccionar una área";
                this.estadoFormulario = false;
            } else {
                this.msgArea = "";
            }
        } else {

            this.estadoFormulario = true;
            if (this.empleados.getNombreEmpleado().length() < 3) {
                this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgNombre = "";
            }
            if (this.empleados.getApellidoEmpleado().length() < 4) {
                this.msgApellido = "El apellido debe contener como mínimo 4 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgApellido = "";
            }
            if (this.empleados.getDuiEmpleado().length() < 10) {
                this.msgDui = "El DUI debe contener como mínimo 10 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgDui = "";
            }
            if (this.empleados.getNitEmpleado().length() < 15) {
                this.msgNit = "El NIT debe contener como minimo 15 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgNit = "";
            }

            if (this.empleados.getDireccionEmpleado().length() < 10) {
                this.msgDireccion = "La dirección debe contener como mínimo 10 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgDireccion = "";
            }
            if (this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0 || this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) == 0) {
                this.msgSalario = "Debe ingresar un valor";
                this.estadoFormulario = false;
            } else {
                this.msgSalario = "";
            }
            if (this.empleados.getTelefonoEmpleado().length() < 9) {
                this.msgTelefono = "El teléfono debe contener como mínimo 9 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgTelefono = "";
            }

            if (this.empleados.getCorreoEmpleado().length() < 10) {
                this.msgCorreo = "El correo debe contener como mínimo 10 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgCorreo = "";
            }

            if (this.listaEmpleadoCargos.size() == 0) {
                this.msgCargo = "Seleccione al menos un cargo";
                this.estadoFormulario = false;
            } else {
                this.msgCargo = "";
            }
            if (this.listaOcupacion.size() == 0) {
                this.msgOcupacion = "Seleccione al menos una ocupación";
                this.estadoFormulario = false;
            } else {
                this.msgOcupacion = "";
            }
            if (this.empleados.getFechanacimientoEmpleado() == null) {
                this.msgFechaNacimiento = "Seleccione la fecha de nacimieto";
                this.estadoFormulario = false;
            } else {
                this.msgFechaNacimiento = "";
            }
            if (this.empleados.getFechaingresoEmpleado() == null) {
                this.msgFechaIngreso = "Seleccione la fecha de ingreso del empleado";
                this.estadoFormulario = false;
            } else {
                this.msgFechaIngreso = "";
            }

            if (this.empleados.getLugarNacimientoEmpleado() == "") {
                this.msgLugarNacimiento = "Debe introducir el lugar";
                this.estadoFormulario = false;
            } else {
                this.msgLugarNacimiento = "";
            }

            if (this.empleados.getCelularEmpleado().length() < 9) {
                this.msgMovil = "El móvil debe contener como mínimo 9 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgMovil = "";
            }
            if (this.iempleadoBo.getDUiTEmpleado(empleados.getDuiEmpleado()) == true) {
                this.msgDuiRepetido = "Este número de DUI ya está registrado";
                this.estadoFormulario = false;
            } else {
                this.msgDuiRepetido = "";
            }

            if (this.iempleadoAreaBo.correoRepetido(empleados.getCorreoEmpleado(), this.idEntidadSeleccionada) == true) {
                this.msgCorreoRepetido = "Este correo ya está registrado";
                this.estadoFormulario = false;
            } else {
                this.msgCorreoRepetido = "";
            }
            if (this.emptyImagen == true) {
                this.msgImagen = "no has seleccionado una imágen o no ha sigo cargada";
                this.estadoFormulario = false;
            } else {
                this.msgImagen = "";
            }
            if (this.idAreaModificar == null) {
                this.msgArea = "Debe seleccionar una área";
                this.estadoFormulario = false;
            } else {
                this.msgArea = "";
            }

        }

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;
        if (this.empleadoSeleccionado.getNombreEmpleado().length() < 3) {
            this.msgNombre = "El nombre debe contener como minimo 3 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNombre = "";
        }
        if (this.empleadoSeleccionado.getApellidoEmpleado().length() < 4) {
            this.msgApellido = "El apellido debe contener como mínimo 4 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgApellido = "";
        }
        if (this.empleadoSeleccionado.getCorreoEmpleado().length() < 10) {
            this.msgCorreo = "El correo debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgCorreo = "";
        }
        if (this.empleadoSeleccionado.getDuiEmpleado().length() < 10) {
            this.msgDui = "El DUI debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDui = "";
        }
        if (this.empleadoSeleccionado.getNitEmpleado().length() < 15) {
            this.msgNit = "El NIT debe contener como minimo 15 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgNit = "";
        }

        if (this.empleadoSeleccionado.getDireccionEmpleado().length() < 10) {
            this.msgDireccion = "La dirección debe contener como mínimo 10 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgDireccion = "";
        }
        if (this.empleadoSeleccionado.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0) {
            this.msgSalario = "El Salario mínimo $254";
            this.estadoFormulario = false;
        } else {
            this.msgSalario = "";
        }
        if (this.empleadoSeleccionado.getTelefonoEmpleado().length() < 9) {
            this.msgTelefono = "El teléfono debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }

        if (this.listaEmpleadoCargosModificar.size() == 0) {
            this.msgCargo = "Seleccione al menos un cargo";
            this.estadoFormulario = false;
        } else {
            this.msgCargo = "";
        }
        if (this.listaOcupacion.size() == 0) {
            this.msgOcupacion = "Seleccione al menos una ocupación";
            this.estadoFormulario = false;
        } else {
            this.msgOcupacion = "";
        }
        if (this.empleadoSeleccionado.getFechanacimientoEmpleado() == null) {
            this.msgFechaNacimiento = "Seleccione la fecha de nacimieto";
            this.estadoFormulario = false;
        } else {
            this.msgFechaNacimiento = "";
        }

        if (this.empleadoSeleccionado.getLugarNacimientoEmpleado() == "") {
            this.msgLugarNacimiento = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugarNacimiento = "";
        }

        if (this.empleadoSeleccionado.getFechaingresoEmpleado() == null) {
            this.msgFechaIngreso = "Seleccione la fecha de ingreso del empleado";
            this.estadoFormulario = false;
        } else {
            this.msgFechaIngreso = "";
        }
        if (this.empleadoSeleccionado.getCelularEmpleado().length() < 9) {
            this.msgMovil = "El móvil debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgMovil = "";
        }
        if (this.empleadoSeleccionado.getDuiEmpleado().equals(this.duiCopia)) {
        } else if (this.iempleadoBo.getDUiTEmpleado(empleadoSeleccionado.getDuiEmpleado()) == true) {
            this.msgDuiRepetido = "Este número de DUI ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgDuiRepetido = "";
        }

        System.out.println("idEntidad: " + this.idEntidadSeleccionada);
        System.out.println("Correo: " + this.empleadoSeleccionado.getCorreoEmpleado() + " Copia: " + this.correoCopia);

        if (this.empleadoSeleccionado.getCorreoEmpleado().equals(this.correoCopia)) {

        } else if (this.iempleadoAreaBo.correoRepetido(empleadoSeleccionado.getCorreoEmpleado(), this.idEntidadSeleccionada) == true) {
            this.msgCorreoRepetido = "Este correo ya está registrado";
            this.estadoFormulario = false;
        } else {
            this.msgCorreoRepetido = "";
        }
    }

    public void limpiar(int i) {
        this.setEstadoFormulario(false);

        this.listaCargo = new ArrayList<>();
        this.empleados = new TEmpleado();
        this.empleadoSeleccionado = new TEmpleado();
        this.listaOcupacion.clear();
        this.listaOcupacionesAux.clear();
        this.listaEmpleadoCargosModificar.clear();
        this.listaEmpleadoCargos.clear();
        this.empleados.setSalarioEmpleado(BigDecimal.ZERO);
        this.listaCargoModificar = new ArrayList<>();
        this.showImagen = true;
        this.showImagenModificar = false;
        this.fotoEmpleado = "";
        this.direccion = "";
        this.msgApellido = "";
        this.msgArea = "";
        this.msgCargo = "";
        this.msgDireccion = "";
        this.msgDui = "";
        this.msgFechaIngreso = "";
        this.msgFechaNacimiento = "";
        this.msgMovil = "";
        this.msgMunicipio = "";
        this.msgNit = "";
        this.msgNombre = "";
        this.msgOcupacion = "";
        this.msgSalario = "";
        this.msgTelefono = "";
        this.msgDuiRepetido = "";
        this.msgCorreo = "";
        this.msgCorreoRepetido = "";
        this.empleados.setSexoEmpleado("Másculino");
        this.fechaFin = null;
        this.emptyImagen = true;
        this.msgImagen = "";
        if (i == 0) {
            super.enableShowData();
        } else {
            this.enableShowCreateBean();
        }
    }

    public void guardarImagen(FileUploadEvent event) throws IOException {
        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.empleados.setFotoEmpleado(guardar);
        this.showImagen = false;
        this.emptyImagen = false;
        FacesMessage message = new FacesMessage("Foto Cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void guardarImagenModificar(FileUploadEvent event) throws IOException {
        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.fotoEmpleado = guardar;
        this.showImagen = true;
        this.showImagenModificar = true;
        FacesMessage message = new FacesMessage("Foto Cargada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private String nombreArea;

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public void cargarActualizar() {

        TArea areaSelecionadaAux = new TArea();
        this.empleadoAux = this.empleadoSeleccionado;
        ArrayList<TCargo> borrar = new ArrayList();
        this.duiCopia = this.empleadoSeleccionado.getDuiEmpleado();
        this.correoCopia = this.empleadoSeleccionado.getCorreoEmpleado();
        /* tenemos el empleadoArea  para poder modificarlo
           en caso que se cambie el area del empleado
         */
        this.empleadoArea = this.iempleadoAreaBo.getTEmpleadoArea(1, this.empleadoSeleccionado.getIdEmpleado());
        areaSelecionadaAux = this.areaBo.getTArea(this.empleadoArea.getTArea().getIdArea());
        this.nombreArea = areaSelecionadaAux.getNombreArea();

        this.listaOcupacionesAux = this.iocupacionBo.listTOcupacion(this.empleadoSeleccionado.getIdEmpleado());
        this.listaOcupacion.clear();
        for (int i = 0; i < this.listaOcupacionesAux.size(); i++) {
            this.listaOcupacion.add(listaOcupacionesAux.get(i));
        }
        this.listaCargoModificar = icargoBo.listCargo(1);
        //this.listaCargoModificarCopia = icargoBo.listCargo(1);
        this.listaEmpleadoCargosModificar = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        this.listaEmpleadoCargosModificarCopia = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());

        for (int i = 0; i < this.listaCargoModificar.size(); i++) {

            for (int y = 0; y < this.listaEmpleadoCargosModificar.size(); y++) {
                if (this.listaCargoModificar.get(i).getIdCargo() == this.listaEmpleadoCargosModificar.get(y).getTCargo().getIdCargo() && this.listaEmpleadoCargosModificar.get(y).getEstadoEmpleadoCargo() != false) {
                    borrar.add(this.listaCargoModificar.get(i));
                }
            }
        }
        for (int y = 0; y < borrar.size(); y++) {
            this.listaCargoModificar.remove(borrar.get(y));
        }
    }

    public void enableShowImagen() {
        //this.estadoFormulario = true;
        this.setShowImagen(!this.getShowImagen());
    }

    public boolean isShowImagen() {
        return showImagen;
    }

    public void agregarOcupaciones() {
        if (this.nombreOcupacion != "") {
            this.ocupacionAuxiliar.setNombreOcupacion(this.nombreOcupacion);
            this.listaOcupacion.add(ocupacionAuxiliar);
            this.ocupacionAuxiliar = new TOcupacion();
            this.nombreOcupacion = "";
        }
    }

    public void eliminarDeLista(int index) {
        listaOcupacion.remove(index);
    }

    public void cargarEmpleadosCargo(int opc) {
        int posicion = 0;
        if (opc == 0 && this.idCargo != 0 && this.fechaInicio != null) {
            this.empleadoCargo.setFechainicioEmpleadoCargo(fechaInicio);
            for (int i = 0; i < this.listaCargo.size(); i++) {
                if (this.idCargo == this.listaCargo.get(i).getIdCargo()) {
                    cargo.setNombreCargo(this.listaCargo.get(i).getNombreCargo());
                    this.empleadoCargo.setTCargo(cargo);
                    this.empleadoCargo.getTCargo().setIdCargo(this.listaCargo.get(i).getIdCargo());
                    this.listaEmpleadoCargos.add(this.empleadoCargo);
                    posicion = i;
                }
            }
            this.listaCargo.remove(posicion);
        } else if (opc == 1 && this.idCargo != 0 && this.fechaInicio != null) {
            for (int y = 0; y < this.listaEmpleadoCargosModificar.size(); y++) {
                if ((this.idCargo == this.listaEmpleadoCargosModificar.get(y).getTCargo().getIdCargo())) {
                    //System.out.println("fecha inicio" + this.getFechaInicio() + " fecha fin cargo anterior " + this.listaEmpleadoCargosModificar.get(y).getFechafinEmpleadoCargo());
                    if (this.listaEmpleadoCargosModificar.get(y).getFechafinEmpleadoCargo().compareTo(this.fechaInicio) > 0) {
                        this.msgFechaInicio = "Ingrese una fecha mayor o igual a la que fue dado de baja del cargo";
                        this.estadoFecha = false;
                    } else {
                        this.estadoFecha = true;
                        this.msgFechaInicio = "";
                    }
                }
            }
            if (this.estadoFecha != false) {
                for (int i = 0; i < this.listaCargoModificar.size(); i++) {
                    if (this.idCargo == this.listaCargoModificar.get(i).getIdCargo()) {
                        //System.out.println("id: " + this.listaCargo.get(i).getIdCargo());
                        //  cargo.setNombreCargo(this.listaCargoModificar.get(i).getNombreCargo());
                        this.empleadoCargo.setTCargo(this.listaCargoModificar.get(i));
                        this.empleadoCargo.setFechainicioEmpleadoCargo(fechaInicio);
                        this.empleadoCargo.setTEmpleado(this.empleadoSeleccionado);
                        this.empleadoCargo.getTCargo().setIdCargo(this.listaCargoModificar.get(i).getIdCargo());
                        this.listaEmpleadoCargosModificar.add(this.empleadoCargo);
                        posicion = i;
                    }
                }
                this.listaCargoModificar.remove(posicion);
            }
        }
        empleadoCargo = new TEmpleadoCargo();
        cargo = new TCargo();
    }

    public void eliminarCargo(int index, int opc) {
        if (opc == 0) {
            this.listaCargo.add(this.listaEmpleadoCargos.get(index).getTCargo());
            this.listaEmpleadoCargos.remove(index);
            empleadoCargo = new TEmpleadoCargo();
            cargo = new TCargo();
        } else {

            this.listaCargoModificar.add(this.listaEmpleadoCargosModificar.get(index).getTCargo());
            this.listaEmpleadoCargosModificar.remove(index);
        }
        // System.out.println("Copia: " + this.listaEmpleadoCargosModificarCopia.size() + " auxiliar " + this.listaEmpleadoCargosModificar.size());
    }

    public void habilitarDatos() {

        if (this.estadoHabilitar == false) {
            this.estadoDatos = true;
            this.estadoBusqueda = false;
        } else {
            this.estadoDatos = false;
            this.estadoBusqueda = true;
        }
    }

    public void cargarBaja() {
        System.out.println(this.empleadoSeleccionado.getIdEmpleado());
        this.empleadoAux = this.empleadoSeleccionado;
    }

    public void reporteEmpleado() throws SQLException, JRException, IOException {
        this.getConexion();
        this.calcularEdad();
        Map<String, Object> parametros = new HashMap();
        parametros.put("idEntidad", this.idEntidadSeleccionada);
        parametros.put("idEmpleado", this.empleadoAux.getIdEmpleado());
        parametros.put("edad", this.edad);
        System.out.println(this.empleadoAux.getIdEmpleado());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/ReporteEmpleadoIndividual.jasper"));
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
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar reporte de empleado");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoAux.getNombreEmpleado() + ", Apellido: " + this.empleadoAux.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoAux.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void reporteEmpleadoPDF() throws SQLException, JRException, IOException {
        this.getConexion();
        this.calcularEdad();
        Map<String, Object> parametros = new HashMap();
        parametros.put("idEntidad", this.idEntidadSeleccionada);
        parametros.put("idEmpleado", this.empleadoAux.getIdEmpleado());
        parametros.put("edad", this.edad);
        System.out.println(this.empleadoAux.getIdEmpleado());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/ReporteEmpleadoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Empleado.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar PDF de empleado");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoAux.getNombreEmpleado() + ", Apellido: " + this.empleadoAux.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoAux.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

    }

    public void calcularEdad() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.empleadoSeleccionado.getFechanacimientoEmpleado()));
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse("" + sdf.format(this.empleadoSeleccionado.getFechanacimientoEmpleado()));
        } catch (ParseException ex) {
            Logger.getLogger(CompradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nueva = (formatter.format(fechaNac));
        Calendar fechaActual = Calendar.getInstance();
        fechaNacimiento.setTime(fechaNac);
        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        this.edad = año;
    }
}
