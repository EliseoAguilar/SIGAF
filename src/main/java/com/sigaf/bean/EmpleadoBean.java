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
import com.sigaf.bo.EmpleadoAreaBo;
import com.sigaf.pojo.TArea;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TCargo;
import com.sigaf.pojo.TEmpleado;
import com.sigaf.pojo.TEmpleadoArea;
import com.sigaf.pojo.TEmpleadoCargo;
import com.sigaf.pojo.TOcupacion;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javassist.CtMethod.ConstParameter.string;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Named(value = "empleadoBean")
@RequestScoped
@ManagedBean
public class EmpleadoBean extends Actividad {

    private IEmpleadoAreaBo iempleadoAreaBo;
    private TEmpleadoArea empleadoArea;
    private IEmpleadoBo iempleadoBo;
    private IEntidadBo ientidadBo;
    private IEmpleadoCargoBo iempleadoCargoBo;
    private List<TOcupacion> listaOcupacionesAux;
    private IOcupacionBo iocupacionBo;
    private TEmpleado empleados;
    private TEmpleado empleadoSeleccionado;
    private List<TCargo> listaCargoModificar;
    private List<TCargo> listaCargoModificarCopia;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificar;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificarCopia;
    private List<TArea> listaArea;
    private List<TEmpleado> listaEmpleados;
    private TEmpleadoCargo empleadoCargo;
    private TEmpleadoCargo empleadoCargoSeleccionado;
    private ArrayList<TOcupacion> listaOcupacion;
    private List<TCargo> listaCargo;
    private List<TEmpleadoCargo> listaEmpleadoCargos;
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
    private Integer idAreaModificar;
    private Integer idCargoModificar;
    private Integer idArea;
    private IAreaBo areaBo;
    private ICargoBo icargoBo;
    private boolean mostrarTabla;
    private Integer posicionArea;
    private TCargo cargo;
    private TOcupacion ocupacionAuxiliar;
    private String nombreOcupacion;
    private String nombreDepartamento;
    private String nombreMunicipio;
    private String direccion;
    private String msgLugarNacimiento;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaHoy;
    private boolean showImagen;
    private Boolean showImagenModificar;
    private String fotoEmpleado;
    private Integer idCargo;
    private String duiCopia;
    private Integer idAreaEmpleado;
    private String correoCopia;
    private String msgCorreoRepetido;
    private Boolean emptyImagen;
    private String msgImagen;
    private Boolean mostrarCalendario;
    private TEmpleadoCargo listaModificar;
    private int idCargoIndividual;
    private String msgFechaInicio;
    private Boolean estadoFecha;
    private IBitacoraBo bitacoraBo;
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

    public void setEmpleadoArea(TEmpleadoArea empleadoArea) {
        this.empleadoArea = empleadoArea;
    }

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public Boolean getEstadoFecha() {
        return estadoFecha;
    }

    public void setEstadoFecha(Boolean estadoFecha) {
        this.estadoFecha = estadoFecha;
    }

    public List<TCargo> getListaCargoModificarCopia() {
        return listaCargoModificarCopia;
    }

    public void setListaCargoModificarCopia(List<TCargo> listaCargoModificarCopia) {
        this.listaCargoModificarCopia = listaCargoModificarCopia;
    }

    public String getMsgFechaInicio() {
        return msgFechaInicio;
    }

    public void setMsgFechaInicio(String msgFechaInicio) {
        this.msgFechaInicio = msgFechaInicio;
    }

    public Date getFechaHoy() {
        return fechaHoy;
    }

    public void setFechaHoy(Date fechaHoy) {
        this.fechaHoy = fechaHoy;
    }

    public void updateListaAreas() {
        /* parametro 1 dado que es el codigo de la fundacion*/
        this.listaArea = this.areaBo.listArea(1);
    }

    public void updatelistEmpleados() {
        /* parametro 1 indica que es empleados */
        this.listaEmpleados = this.iempleadoBo.listEmpleadosOrSocios(idArea, 1);
    }

    public void enableShowCreateBean() {
        super.enableShowCreate();
        this.listaCargo = this.icargoBo.listCargo(1);
    }

    public int getIdCargoIndividual() {
        return idCargoIndividual;
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

    public void setIdCargoIndividual(int idCargoIndividual) {
        this.idCargoIndividual = idCargoIndividual;
    }

    public TEmpleadoCargo getListaModificar() {
        return listaModificar;
    }

    public void setListaModificar(TEmpleadoCargo listaModificar) {
        this.listaModificar = listaModificar;
    }

    public Boolean getMostrarCalendario() {
        return mostrarCalendario;
    }

    public void setMostrarCalendario(Boolean mostrarCalendario) {
        this.mostrarCalendario = mostrarCalendario;
    }

    public Boolean getEmptyImagen() {
        return emptyImagen = true;
    }

    public void setEmptyImagen(Boolean emptyImagen) {
        this.emptyImagen = emptyImagen;
    }

    public String getMsgImagen() {
        return msgImagen;
    }

    public void setMsgImagen(String msgImagen) {
        this.msgImagen = msgImagen;
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

    public Integer getPosicionArea() {
        return posicionArea;
    }

    public void setPosicionArea(Integer posicionArea) {
        this.posicionArea = posicionArea;
    }

    public boolean isMostrarTabla() {
        return mostrarTabla;
    }

    public void setMostrarTabla(boolean mostrarTabla) {
        this.mostrarTabla = mostrarTabla;
    }

    public Integer getIdAreaEmpleado() {
        return idAreaEmpleado;
    }

    public void setIdAreaEmpleado(Integer idAreaEmpleado) {
        this.idAreaEmpleado = idAreaEmpleado;
    }

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

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
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

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
        this.idAreaModificar = idArea;
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

    public void Init() {
        hoy();
        float salario = 0;
        this.idArea = 0;
        this.setEstadoFormulario(false);
        this.setShowImagen(true);
        this.empleadoSeleccionado = new TEmpleado();
        this.empleadoCargo = new TEmpleadoCargo();
        this.listaModificar = new TEmpleadoCargo();
        this.ocupacionAuxiliar = new TOcupacion();
        listaOcupacion = new ArrayList<>();
        this.empleados = new TEmpleado();
        this.showImagenModificar = false;
        this.listaOcupacionesAux = new ArrayList<>();
        this.listaOcupacion = new ArrayList<>();
        this.cargo = new TCargo();
        this.fechaFin = null;
        this.empleados.setSalarioEmpleado(BigDecimal.ZERO);
        this.listaEmpleadoCargos = new ArrayList<>();
        //this.listaCargo = this.icargoBo.listCargo(1);
        this.listaCargoModificarCopia = new ArrayList<>();
        this.listaEmpleadoCargosModificar = new ArrayList<>();
        this.listaEmpleadoCargosModificarCopia = new ArrayList<>();
        this.empleados.setSexoEmpleado("Másculino");
        this.empleadoArea = new TEmpleadoArea();
        this.emptyImagen = true;
        this.mostrarCalendario = false;
        this.estadoFecha = true;
        super.enableShowData();
        
    }

    public void hoy() {

        this.setFechaHoy(new Date());
    }

    public void create() {//Funcion utilizada para creaer un nuevo registro

        this.empleados.setEstadoEmpleado(Boolean.TRUE);
        this.empleados.setEstadoE(true);
        this.empleados.setEstadoSocio(false);
        this.empleados.setEstadoS(false);
        this.iempleadoBo.create(this.empleados);
        /* 
           se le da el idAreaModificar dado que ese toma el valor del combo en la vista
           para crear
         */
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
        auxBitacora.setDatosBitacora("Nombre: " + this.empleados.getNombreEmpleado() + ", apellidos: " + this.empleados.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleados.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Empleado guardado correctamente"));
        this.limpiar(1);
    }

    public void modificar() {
        if (this.showImagenModificar) {
            this.empleadoSeleccionado.setFotoEmpleado(this.fotoEmpleado);
        }

        this.iempleadoAreaBo.update(this.empleadoArea);
        this.empleadoArea.setTArea(new TArea(this.idAreaModificar));
        this.iempleadoAreaBo.update(empleadoArea);
        this.iempleadoBo.update(this.empleadoSeleccionado);

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

    }

    public void darDeBaja() {
        this.empleadoSeleccionado.setEstadoE(false);
        try {
            iempleadoBo.update(empleadoSeleccionado);
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
            super.enableShowData();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Empleado dado de baja correctamente"));
        } catch (HibernateException he) {
        }
    }

    public void darDeAlta() {
        this.empleadoSeleccionado.setEstadoE(Boolean.TRUE);
        try {
            iempleadoBo.update(this.empleadoSeleccionado);
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
                    new FacesMessage("Empleado dado de alta correctamente"));
        } catch (HibernateException he) {
        }
        super.enableShowData();
    }

    public void darDeAltaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(false);
    }

    public void darDeBajaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(true);
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
        if (this.empleados.getSalarioEmpleado().compareTo(BigDecimal.ZERO) < 0) {
            this.msgSalario = "Debe introducir un valor";
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

        if (this.empleados.getLugarNacimientoEmpleado() == "") {
            this.msgLugarNacimiento = "Debe introducir el lugar";
            this.estadoFormulario = false;
        } else {
            this.msgLugarNacimiento = "";
        }

        if (this.empleados.getFechaingresoEmpleado() == null) {
            this.msgFechaIngreso = "Seleccione la fecha de ingreso del empleado";
            this.estadoFormulario = false;
        } else {
            this.msgFechaIngreso = "";
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
        if (this.iempleadoAreaBo.correoRepetido(empleados.getCorreoEmpleado(), 1) == true) {
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
            this.msgSalario = "Debe ingresar un valor";
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
        if (this.empleadoSeleccionado.getCorreoEmpleado().equals(this.correoCopia)) {
        } else if (this.iempleadoAreaBo.correoRepetido(empleadoSeleccionado.getCorreoEmpleado(), 1) == true) {
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
    }

    public void limpiar(int i) {
        this.setEstadoFormulario(false);
        this.empleados = new TEmpleado();
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
        this.msgCorreoRepetido = "";
        this.msgCorreo = "";
        this.empleados.setSexoEmpleado("Másculino");
        this.emptyImagen = true;
        this.msgImagen = "";
        this.fechaFin = null;
        this.fechaInicio = null;
        if (i == 0) {
            this.enableShowData();
        } else {
            /*
        Para volver a llenar la lista de cargos.    
             */
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
    }

    public void guardarImagenModificar(FileUploadEvent event) throws IOException {
        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.fotoEmpleado = guardar;
        this.emptyImagen = false;
        this.showImagen = true;
        this.showImagenModificar = true;
        this.emptyImagen = false;
    }
    
    private String nombreArea;

    public String getNombreArea() {
        return nombreArea;
    }

    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }

    public void cargarActualizar() {
        this.emptyImagen = false;
        
        TArea areaSelecionadaAux = new TArea();
        ArrayList<TCargo> borrar = new ArrayList();
        this.duiCopia = this.empleadoSeleccionado.getDuiEmpleado();
        this.correoCopia = this.empleadoSeleccionado.getCorreoEmpleado();
        /* tenemos el empleadoArea  para poder modificarlo
           en caso que se cambie el area del empleado
         */
        this.empleadoArea = this.iempleadoAreaBo.getTEmpleadoArea(1, this.empleadoSeleccionado.getIdEmpleado());
       areaSelecionadaAux= this.areaBo.getTArea(this.empleadoArea.getTArea().getIdArea());
       this.nombreArea= areaSelecionadaAux.getNombreArea();
        this.listaOcupacion.clear();
        this.listaOcupacionesAux = this.iocupacionBo.listTOcupacion(this.empleadoSeleccionado.getIdEmpleado());
        for (int i = 0; i < this.listaOcupacionesAux.size(); i++) {
            this.listaOcupacion.add(listaOcupacionesAux.get(i));
        }
        this.listaCargoModificar = icargoBo.listCargo(1);
        this.listaCargoModificarCopia = icargoBo.listCargo(1);
        this.listaEmpleadoCargosModificar = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        this.listaEmpleadoCargosModificarCopia = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        for (int i = 0; i < this.listaCargoModificar.size(); i++) {
            for (int y = 0; y < this.listaEmpleadoCargosModificar.size(); y++) {
                if (this.listaCargoModificar.get(i).getIdCargo() == this.listaEmpleadoCargosModificar.get(y).getTCargo().getIdCargo()
                        && this.listaEmpleadoCargosModificar.get(y).getEstadoEmpleadoCargo() == true) {
                    borrar.add(this.listaCargoModificar.get(i));
                }
            }
        }
        for (int y = 0; y < borrar.size(); y++) {
            this.listaCargoModificar.remove(borrar.get(y));
        }
    }

    public void enableShowImagen() {
        this.emptyImagen = true;
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

    public void cargarEmpleadosCargo(int opc) throws ParseException {
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
                //Aca se agrega el registro a la tabla
                for (int i = 0; i < this.listaCargoModificar.size(); i++) {
                    if (this.idCargo == this.listaCargoModificar.get(i).getIdCargo()) {
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
        this.fechaInicio = null;
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
    }

    public void generarEmpleadosFundacion() throws Exception {
        this.getConexion();
        this.calcularEdad();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_entidad", 1);
        parametros.put("idEmpleado", this.empleadoSeleccionado.getIdEmpleado());
        parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteEmpleadoIndividual.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte empleado");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionado.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionado.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoSeleccionado.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void generarEmpleadosFundacionPDF() throws Exception {

        this.getConexion();
        this.calcularEdad();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_entidad", 1);
        parametros.put("idEmpleado", this.empleadoSeleccionado.getIdEmpleado());
         parametros.put("edad", this.edad);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/Configuracion/ReporteEmpleadoIndividual.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=ListadoEmpleadoIndividual.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar PDF empleado");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionado.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionado.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoSeleccionado.getIdEmpleado());
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
