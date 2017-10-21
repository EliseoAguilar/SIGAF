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
import com.sigaf.bean.Actividad;
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
import java.sql.SQLException;
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
@Named(value = "socioCooperativaBean")
@RequestScoped
@ManagedBean
public class SocioCooperativaBean extends Actividad {

    private IEmpleadoBo iempleadoBo;
    private IEntidadBo ientidadBo;
    private IEmpleadoCargoBo iempleadoCargoBo;
    private List<TEmpleado> listaEmpleado;
    private List<TOcupacion> listaOcupacionesAux;
    private TEmpleado empleados;
    private TEmpleado empleadoSeleccionado;
    private TEmpleado empleadoSeleccionadoAux;
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
    private String msgLugarNaci;
    private String msgAreaSelec;
    private String msgEmpleado;
    private Integer idSocio;
    private TEmpleadoArea empleadoArea;
    private boolean estadoHabilitar;
    private boolean estadoBusqueda;
    private boolean estadoDatos;
    private Integer idArea2;
    private Integer posicionArea;
    private boolean mostrarTabla2;
    private TEmpleado empleadoAux;
    private Integer idAreaModificar;
    private Integer idCargoModificar;
    private Integer idArea;
    private IAreaBo areaBo;
    private ICargoBo icargoBo;
    private IOcupacionBo iocupacionBo;
    private TArea area;
    private TCargo cargo;
    private TOcupacion ocupacionAuxiliar;
    private String nombreOcupacion;
    private String nombreDepartamento;
    private String nombreMunicipio;
    private Integer idDepartamentoAuxiliar;
    private Integer idMunicipioAuxiliar;
    private TEmpleadoCargo empleadoCargo;
    private IEmpleadoAreaBo iempleadoAreaBo;
    private TEmpleadoCargo empleadoCargoSeleccionado;
    private ArrayList<TOcupacion> listaOcupacion;
    private List<TCargo> listaCargo;
    private List<TEmpleadoCargo> listaEmpleadoCargos;
    private String direccion;
    private Date fechaInicio;
    private Date fechaFin;
    private boolean showImagen;
    private Boolean showImagenModificar;
    private String fotoEmpleado;
    private Integer idCargo;
    private String duiCopia;
    private List<TCargo> listaCargoModificar;
    private List<TEmpleadoArea> listaEmpleadoArea;
    private List<TEmpleado> listaEmpleados;
    private List<TEmpleado> listaEmpleadosArea;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificar;
    private List<TEmpleadoCargo> listaEmpleadoCargosModificarCopia;
    private List<TArea> listaArea;
    private List<TArea> listaAreaFiltrada;
    private List<TEmpleado> listaEmpleadoFiltrados;
    private Integer idEntidadSeleccionada;
    private Integer idAreaEmpleado;
    private IBitacoraBo bitacoraBo;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public TEmpleado getEmpleadoSeleccionadoAux() {
        return empleadoSeleccionadoAux;
    }

    public void setEmpleadoSeleccionadoAux(TEmpleado empleadoSeleccionadoAux) {
        this.empleadoSeleccionadoAux = empleadoSeleccionadoAux;
    }

    public Integer getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Integer idSocio) {
        this.idSocio = idSocio;
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

    private List<TEmpleado> listaEmpleadoNoSocios;

    public List<TEmpleado> getListaEmpleadoNoSocios() {
        return listaEmpleadoNoSocios;
    }

    public void setListaEmpleadoNoSocios(List<TEmpleado> listaEmpleadoNoSocios) {
        this.listaEmpleadoNoSocios = listaEmpleadoNoSocios;
    }

    public TEmpleado getEmpleadoAux() {
        return empleadoAux;
    }

    public void setEmpleadoAux(TEmpleado empleadoAux) {
        this.empleadoAux = empleadoAux;
    }

    public boolean isMostrarTabla2() {
        return mostrarTabla2;
    }

    public void setMostrarTabla2(boolean mostrarTabla2) {
        this.mostrarTabla2 = mostrarTabla2;
    }

    public Integer getIdArea2() {
        return idArea2;
    }

    public void setIdArea2(Integer idArea2) {
        this.idArea2 = idArea2;
    }

    public Integer getPosicionArea() {
        return posicionArea;
    }

    public void setPosicionArea(Integer posicionArea) {
        this.posicionArea = posicionArea;
    }

    public boolean isEstadoHabilitar() {
        return estadoHabilitar;
    }

    public void setEstadoHabilitar(boolean estadoHabilitar) {
        this.estadoHabilitar = estadoHabilitar;
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

    public TEmpleadoArea getEmpleadoArea() {
        return empleadoArea;
    }

    public void setEmpleadoArea(TEmpleadoArea empleadoArea) {
        this.empleadoArea = empleadoArea;
    }

    public String getMsgLugarNaci() {
        return msgLugarNaci;
    }

    public void setMsgLugarNaci(String msgLugarNaci) {
        this.msgLugarNaci = msgLugarNaci;
    }

    public IEmpleadoAreaBo getIempleadoAreaBo() {
        return iempleadoAreaBo;
    }

    public void setIempleadoAreaBo(IEmpleadoAreaBo iempleadoAreaBo) {
        this.iempleadoAreaBo = iempleadoAreaBo;
    }

    public Integer getIdAreaEmpleado() {
        return idAreaEmpleado;
    }

    public void setIdAreaEmpleado(Integer idAreaEmpleado) {
        this.idAreaEmpleado = idAreaEmpleado;
    }

    public List<TEmpleado> getListaEmpleadosArea() {
        return listaEmpleadosArea;
    }

    public void setListaEmpleadosArea(List<TEmpleado> listaEmpleadosArea) {
        this.listaEmpleadosArea = listaEmpleadosArea;
    }

    public List<TEmpleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<TEmpleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<TEmpleadoArea> getListaEmpleadoArea() {
        return listaEmpleadoArea;
    }

    public void setListaEmpleadoArea(List<TEmpleadoArea> listaEmpleadoArea) {
        this.listaEmpleadoArea = listaEmpleadoArea;
    }

    public List<TArea> getListaAreaFiltrada() {
        return listaAreaFiltrada = this.areaBo.listArea(this.idEntidadSeleccionada);
    }

    public void setListaAreaFiltrada(List<TArea> listaAreaFiltrada) {
        this.listaAreaFiltrada = listaAreaFiltrada;
    }

    public Integer getIdEntidadSeleccionada() {
        return idEntidadSeleccionada;
    }

    public void setIdEntidadSeleccionada(Integer idEntidadSeleccionada) {
        this.idEntidadSeleccionada = idEntidadSeleccionada;
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

    public List<TEmpleado> getListaEmpleadoFiltrados() {
        return listaEmpleadoFiltrados;
    }

    public void setListaEmpleadoFiltrados(List<TEmpleado> listaEmpleadoFiltrados) {
        this.listaEmpleadoFiltrados = listaEmpleadoFiltrados;
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
       return listaCargo = this.icargoBo.listCargo(1);       
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

    public Integer getIdMunicipioAuxiliar() {
        return idMunicipioAuxiliar;
    }

    public void setIdMunicipioAuxiliar(Integer idMunicipioAuxiliar) {
        this.idMunicipioAuxiliar = idMunicipioAuxiliar;
    }

    public Integer getIdDepartamentoAuxiliar() {
        return idDepartamentoAuxiliar;
    }

    public void setIdDepartamentoAuxiliar(Integer idDepartamentoAuxiliar) {
        this.idDepartamentoAuxiliar = idDepartamentoAuxiliar;
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

    public List<TEmpleado> getListaEmpleado() {

        return listaEmpleado;
    }

    public void setListaEmpleado(List<TEmpleado> listaEmpleado) {
        this.listaEmpleado = listaEmpleado;
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
        //cargarEmpleadosCargo(this.empleadoSeleccionado.getIdEmpleado());
        // this.idAreaModificar = this.empleadoSeleccionado.getTArea().getIdArea();
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

    public void Init() {
        this.setEstadoFormulario(false);
        this.setShowImagen(true);
        this.empleadoSeleccionado = new TEmpleado();
        this.empleadoCargo = new TEmpleadoCargo();
        this.empleadoAux = new TEmpleado();
        this.empleados = new TEmpleado();
        this.ocupacionAuxiliar = new TOcupacion();
        listaOcupacion = new ArrayList<>();
        this.empleados = new TEmpleado();
        this.showImagenModificar = false;
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
        super.enableShowData();
    }

    //metodo llama a crear
    public void create() {
        Integer IdAreaSocio = 0;
        if (this.estadoHabilitar == true) {
            this.listaArea = this.areaBo.listArea2(this.idEntidadSeleccionada);
            for (int x = 0; x < this.listaArea.size(); x++) {
                IdAreaSocio = this.listaArea.get(x).getIdArea();
            }
            this.empleadoSeleccionado.setEstadoS(true);
            this.empleadoSeleccionado.setEstadoSocio(true);
            this.iempleadoBo.update(this.empleadoSeleccionado);
            this.empleadoArea.setTEmpleado(this.empleadoSeleccionado);
            this.empleadoArea.setTArea(new TArea(IdAreaSocio));
            this.iempleadoAreaBo.create(this.empleadoArea);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Socio registrado correctamente "));
        } else {
            this.empleados.setEstadoS(true);
            this.empleados.setEstadoSocio(true);
            this.empleados.setEstadoE(false);
            this.empleados.setEstadoEmpleado(false);
            this.iempleadoBo.create(this.empleados);
            this.listaArea = this.areaBo.listArea2(this.idEntidadSeleccionada);
            for (int x = 0; x < this.listaArea.size(); x++) {
                IdAreaSocio = this.listaArea.get(x).getIdArea();
            }
            this.empleadoArea.setTEmpleado(empleados);
            this.empleadoArea.setTArea(new TArea(IdAreaSocio));
            this.iempleadoAreaBo.create(this.empleadoArea);
            for (int j = 0; j < this.listaOcupacion.size(); j++) {
                TOcupacion auxOcupacion = new TOcupacion();
                auxOcupacion.setTEmpleado(empleados);
                auxOcupacion.setEstadoOcupacion(true);
                auxOcupacion.setNombreOcupacion(listaOcupacion.get(j).getNombreOcupacion());
                iocupacionBo.create(auxOcupacion);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Socio guardado correctamente"));
        }
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Agregar socio");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleados.getNombreEmpleado() + ", Apellido: " + this.empleados.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleados.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        limpiar(1);
        mostrarDatosFiltrados();
        //FacesContext.getCurrentInstance().addMessage(null,
        //new FacesMessage("Error insertar"));
        //super.enableShowData()
    }

    public void modificar() {
        if (this.showImagenModificar) {
            this.empleadoSeleccionado.setFotoEmpleado(this.fotoEmpleado);
        }
        try {
            this.iempleadoBo.update(empleadoSeleccionado);
            for (int i = 0; i < this.listaEmpleadoCargosModificarCopia.size(); i++) {
                this.listaEmpleadoCargosModificar.get(i).setFechafinEmpleadoCargo(this.fechaFin);
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
            auxBitacora.setAccionBitacora("Modificar socio");
            auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionado.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionado.getApellidoEmpleado());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());
            auxBitacora.setIdTableBitacora(this.empleadoSeleccionado.getIdEmpleado());
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
            this.bitacoraBo.create(auxBitacora);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Socio modificado correctamente"));
            limpiar(0);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Error al modificar al empleado"));
        }
    }

    public void darDeBaja() {
        this.empleadoAux.setEstadoS(false);
        iempleadoBo.update(empleadoAux);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Dar de baja socio");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoAux.getNombreEmpleado() + ", Apellido: " + this.empleadoAux.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoAux.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Socio dado de baja correctamente"));
        super.enableShowData();
    }

    public void darDeAltaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(false);
    }

    public void darDeBajaOcupacion(int index) {
        this.listaOcupacion.get(index).setEstadoOcupacion(true);
    }

    public void darDeAlta() {
        this.empleadoAux.setEstadoS(true);
        iempleadoBo.update(empleadoAux);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Dar de alta socio");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoAux.getNombreEmpleado() + ", Apellido: " + this.empleadoAux.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoAux.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Socio dado de alta correctamente"));

        super.enableShowData();
    }

    public void darDeBajaCargo(int id) {

    }

    public void darDeAltaCargo(int id) {
        this.listaEmpleadoCargosModificar.get(id).setFechafinEmpleadoCargo(null);
    }

    public void validarFormulario() {
        if (this.estadoHabilitar == true) {
            this.estadoFormulario = true;
            if (this.idArea == 0) {
                this.msgAreaSelec = "Debe seleccionar una area";
                this.estadoFormulario = false;
            } else {
                this.msgAreaSelec = "";
            }

            if (this.empleadoSeleccionado.getDuiEmpleado() == null) {
                this.msgEmpleado = "Debe seleccionar un empleado";
                this.estadoFormulario = false;
            } else {
                this.msgEmpleado = "";
            }
            if (this.empleadoSeleccionado != null) {
                if (this.empleadoSeleccionado.getEstadoSocio() == true) {
                    this.msgEmpleado = "El empleado seleccionado ya es socio de la cooperativa";
                    this.estadoFormulario = false;
                }
            } else {
                this.msgEmpleado = "";
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
            if (this.empleados.getLugarNacimientoEmpleado().length() < 5) {
                this.msgLugarNaci = "El lugar de nacimiento es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgLugarNaci = "";
            }
            if (this.empleados.getDireccionEmpleado().length() < 10) {
                this.msgDireccion = "La dirección debe contener como mínimo 10 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgDireccion = "";
            }
            if (this.empleados.getTelefonoEmpleado().length() < 9) {
                this.msgTelefono = "El teléfono debe contener como mínimo 9 caracteres";
                this.estadoFormulario = false;
            } else {
                this.msgTelefono = "";
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
        if (this.empleadoSeleccionado.getTelefonoEmpleado().length() < 9) {
            this.msgTelefono = "El teléfono debe contener como mínimo 9 caracteres";
            this.estadoFormulario = false;
        } else {
            this.msgTelefono = "";
        }
        if (this.empleadoSeleccionado.getLugarNacimientoEmpleado().length() == 0) {
            this.msgLugarNaci = "El lugar de nacimiento es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgLugarNaci = "";
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
    }

    public void limpiar(int i) {
        this.setEstadoFormulario(false);
        //this.listaCargo = new ArrayList<>();
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
        this.empleados.setSexoEmpleado("Másculino");
        this.fechaFin = null;
        //this.listaMunicipios.clear();
//        this.idDepartamentoAuxiliar=0; 
//        this.idMunicipioAuxiliar=0;
        if (i == 0) {
            super.enableShowData();
        }
    }

    public void guardarImagen(FileUploadEvent event) throws IOException {
        String tipo = event.getFile().getContentType();
        byte[] content = event.getFile().getContents();
        String b64 = Base64.encode(content);
        String guardar = "data:" + tipo + ";base64," + b64;
        this.empleados.setFotoEmpleado(guardar);
        this.showImagen = false;
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

    public void cargarActualizar() {

        this.idSocio = this.empleadoSeleccionado.getIdEmpleado();
        this.empleadoSeleccionadoAux = this.empleadoSeleccionado;
        ArrayList<TCargo> borrar = new ArrayList();
        this.duiCopia = this.empleadoSeleccionado.getDuiEmpleado();
        this.listaOcupacionesAux = this.iocupacionBo.listTOcupacion(this.empleadoSeleccionado.getIdEmpleado());
        for (int i = 0; i < this.listaOcupacionesAux.size(); i++) {
            this.listaOcupacion.add(listaOcupacionesAux.get(i));
        }
        this.listaCargoModificar = icargoBo.listCargo(1);
        this.listaEmpleadoCargosModificar = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        this.listaEmpleadoCargosModificarCopia = this.iempleadoCargoBo.listTEmpleadoCargo(this.empleadoSeleccionado.getIdEmpleado());
        for (int i = 0; i < this.listaCargoModificar.size(); i++) {
            for (int y = 0; y < this.listaEmpleadoCargosModificar.size(); y++) {
                if (this.listaCargoModificar.get(i).getIdCargo() == this.listaEmpleadoCargosModificar.get(y).getTCargo().getIdCargo()) {
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
        System.out.println("Copia: " + this.listaEmpleadoCargosModificarCopia.size() + " auxiliar " + this.listaEmpleadoCargosModificar.size());
    }

    public void mostrarDatosFiltrados() {
        if (this.idEntidadSeleccionada == 0) {
            this.listaEmpleados = new ArrayList<>();
            this.mostrarTabla = false;
        } else {
            this.listaEmpleados = new ArrayList<>();
            int posicion;
            this.listaArea = this.areaBo.listArea2(this.idEntidadSeleccionada);
            this.listaEmpleados = this.iempleadoBo.listEmpleadosOrSocios(this.listaArea.get(0).getIdArea(), 2);
            this.mostrarTabla = true;
        }
    }

    public void mostrarEmpleadosArea() {
        this.listaEmpleadosArea = new ArrayList<>();
        this.listaEmpleadoNoSocios = new ArrayList<>();
        this.listaEmpleadosArea = this.iempleadoBo.listEmpleadosActivos(this.idArea);
        for (int i = 0; i < this.listaEmpleadosArea.size(); i++) {
            System.out.println(this.listaEmpleadosArea.get(i).getApellidoEmpleado());
            if (this.listaEmpleadosArea.get(i).getEstadoSocio() == false) {
                this.listaEmpleadoNoSocios.add(this.listaEmpleadosArea.get(i));
            }
        }
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

    public void verReporteSocio() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        System.out.println(this.empleadoSeleccionadoAux.getNitEmpleado());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.empleadoSeleccionadoAux.getFechanacimientoEmpleado()));
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse("" + sdf.format(this.empleadoSeleccionadoAux.getFechanacimientoEmpleado()));
        } catch (ParseException ex) {
            Logger.getLogger(CompradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nueva = (formatter.format(fechaNac));
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la f
        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        System.out.println(año);
        parametros.put("id_socio", this.idSocio);
        parametros.put("edad", año);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/socio.jasper"));
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
        auxBitacora.setAccionBitacora("Generar reporte Socio");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionadoAux.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionadoAux.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoSeleccionadoAux.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void verReporteSocioPDF() throws SQLException, JRException, IOException {
        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Calendar fechaNacimiento = Calendar.getInstance();
        Date fechaNac = null;
        String nueva = "";
        int año = 0;
        System.out.println(this.empleadoSeleccionadoAux.getNitEmpleado());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("La fecha de hoy es: " + sdf.format(this.empleadoSeleccionadoAux.getFechanacimientoEmpleado()));
        try {
            fechaNac = new SimpleDateFormat("yyyy-MM-dd").parse("" + sdf.format(this.empleadoSeleccionadoAux.getFechanacimientoEmpleado()));
        } catch (ParseException ex) {
            Logger.getLogger(CompradorBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        nueva = (formatter.format(fechaNac));
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fechaNac);
        //Se restan la fecha actual y la f
        año = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
        int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if (mes < 0 || (mes == 0 && dia < 0)) {
            año--;
        }
        System.out.println(año);
        parametros.put("id_socio", this.idSocio);
        parametros.put("edad", año);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/administracion/socio.jasper"));
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Socio.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_empleado");
        auxBitacora.setAccionBitacora("Generar PDF Socio");
        auxBitacora.setDatosBitacora("Nombre: " + this.empleadoSeleccionadoAux.getNombreEmpleado() + ", Apellido: " + this.empleadoSeleccionadoAux.getApellidoEmpleado());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.empleadoSeleccionadoAux.getIdEmpleado());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
    }

    public void cargarEstado() {
        System.out.println(this.empleadoSeleccionado.getIdEmpleado());
        this.empleadoAux = this.empleadoSeleccionado;
    }

}
