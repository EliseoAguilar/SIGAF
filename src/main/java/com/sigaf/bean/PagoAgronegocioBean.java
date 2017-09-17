/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAgronegocioBo;
import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.Ibo.IConfiguracionAgronegocioBo;
import com.sigaf.Ibo.IPagoAgronegocioBo;
import com.sigaf.Ibo.IPagoBo;
import com.sigaf.Ibo.IProductoAgronegocioBo;
import com.sigaf.Ibo.IProductoBo;
import com.sigaf.Ibo.IProductoCooperativaBo;
import com.sigaf.Ibo.IProductoProductorBo;
import com.sigaf.Ibo.IProductorIndividualBo;
import com.sigaf.pojo.TAgronegocio;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TComprador;
import com.sigaf.pojo.TConfiguracionAgronegocio;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPagoAgronegocio;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductoAgronegocio;
import com.sigaf.pojo.TProductoCooperativa;
import com.sigaf.pojo.TProductoProductor;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCultivo;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author yonat
 */
@Named(value = "pagoAgronegocioBean")
@RequestScoped
@ManagedBean
public class PagoAgronegocioBean extends Actividad {

    private TProducto producto;
    private TProductoProductor productoProductor;
    private TProducto productoDetalle;
   
    private TPagoAgronegocio pago;
    private Date fechaDePago;

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public TPagoAgronegocio getPago() {
        return pago;
    }

    public void setPago(TPagoAgronegocio pago) {
        this.pago = pago;
    }

  
    private Boolean mostrarAgronegocio;

    public Boolean getMostrarAgronegocio() {
        return mostrarAgronegocio;
    }

    public void setMostrarAgronegocio(Boolean mostrarAgronegocio) {
        this.mostrarAgronegocio = mostrarAgronegocio;
    }

    public TProducto getProductoDetalle() {
        return productoDetalle;
    }

    public void setProductoDetalle(TProducto productoDetalle) {
        this.productoDetalle = productoDetalle;
    }
    private TProductoCooperativa productoCooperativa;
    private TComprador compradorSeleccionado;
    private BigDecimal monto;
    private BigDecimal interes;
    private BigDecimal totales;
    private BigDecimal comisionC;
    private String duiComprador;
    private String codigoAgronegocio;
    private BigDecimal mora;
    private Integer dias;

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public String getDuiComprador() {
        return duiComprador;
    }

    public void setDuiComprador(String duiComprador) {
        this.duiComprador = duiComprador;
    }

    public String getCodigoAgronegocio() {
        return codigoAgronegocio;
    }

    public void setCodigoAgronegocio(String codigoAgronegocio) {
        this.codigoAgronegocio = codigoAgronegocio;
    }

    public BigDecimal getComisionC() {
        return comisionC;
    }

    public void setComisionC(BigDecimal comisionC) {
        this.comisionC = comisionC;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getInteres() {
        return interes;
    }

    public void setInteres(BigDecimal interes) {
        this.interes = interes;
    }

    public BigDecimal getTotales() {
        return totales;
    }

    public void setTotales(BigDecimal totales) {
        this.totales = totales;
    }

    public TComprador getCompradorSeleccionado() {
        return compradorSeleccionado;
    }

    public void setCompradorSeleccionado(TComprador compradorSeleccionado) {
        this.compradorSeleccionado = compradorSeleccionado;
    }

    public TProductoCooperativa getProductoCooperativa() {
        return productoCooperativa;
    }

    public void setProductoCooperativa(TProductoCooperativa productoCooperativa) {
        this.productoCooperativa = productoCooperativa;
    }

    public TProductoProductor getProductoProductor() {
        return productoProductor;
    }

    public void setProductoProductor(TProductoProductor productoProductor) {
        this.productoProductor = productoProductor;
    }
    private TProducto ProductoSeleccionado;
    private TProductorIndividual productorIndividualSeleccionado;

    public TProductorIndividual getProductorIndividualSeleccionado() {
        return productorIndividualSeleccionado;
    }

    public void setProductorIndividualSeleccionado(TProductorIndividual productorIndividualSeleccionado) {
        this.productorIndividualSeleccionado = productorIndividualSeleccionado;
    }
    private List<TProducto> listaProducto;
    private List<TProducto> listaProductoDetalle;

    private List<TAgronegocio> listaAgronegocioPagos;

    public List<TAgronegocio> getListaAgronegocioPagos() {
        return listaAgronegocioPagos;
    }

    public void setListaAgronegocioPagos(List<TAgronegocio> listaAgronegocioPagos) {
        this.listaAgronegocioPagos = listaAgronegocioPagos;
    }
    private List<TProductorIndividual> listaProductoresIndividuales;

    public List<TProductorIndividual> getListaProductoresIndividuales() {
        return listaProductoresIndividuales;
    }

    public void setListaProductoresIndividuales(List<TProductorIndividual> listaProductoresIndividuales) {
        this.listaProductoresIndividuales = listaProductoresIndividuales;
    }

    private List<TProductoAgronegocio> listaProductoAgronegocio;

    public List<TProductoAgronegocio> getListaProductoAgronegocio() {
        return listaProductoAgronegocio;
    }

    public void setListaProductoAgronegocio(List<TProductoAgronegocio> listaProductoAgronegocio) {
        this.listaProductoAgronegocio = listaProductoAgronegocio;
    }

    public List<TProducto> getListaProductoDetalle() {
        return listaProductoDetalle;
    }

    public void setListaProductoDetalle(List<TProducto> listaProductoDetalle) {
        this.listaProductoDetalle = listaProductoDetalle;
    }

    private IProductoBo productoBo;
    private IProductoProductorBo productoProductorBo;
    private IProductoCooperativaBo productoCooperativaBo;
    private ICompradorBo compradorBo;
    private IConfiguracionAgronegocioBo configuracionAgronegocioBo;
    private IAgronegocioBo agronegocioBo;
    private IProductorIndividualBo iproductorindividual;
    private IPagoBo ipagoBo;

    public IPagoBo getIpagoBo() {
        return ipagoBo;
    }

    public void setIpagoBo(IPagoBo ipagoBo) {
        this.ipagoBo = ipagoBo;
    }

    private BigDecimal valorRedondeadoTotal;
    private BigDecimal valorRedondeadoComision;

    public BigDecimal getValorRedondeadoTotal() {
        return valorRedondeadoTotal;
    }

    public void setValorRedondeadoTotal(BigDecimal valorRedondeadoTotal) {
        this.valorRedondeadoTotal = valorRedondeadoTotal;
    }

    public BigDecimal getValorRedondeadoComision() {
        return valorRedondeadoComision;
    }

    public void setValorRedondeadoComision(BigDecimal valorRedondeadoComision) {
        this.valorRedondeadoComision = valorRedondeadoComision;
    }

    public IProductorIndividualBo getIproductorindividual() {
        return iproductorindividual;
    }

    public void setIproductorindividual(IProductorIndividualBo iproductorindividual) {
        this.iproductorindividual = iproductorindividual;
    }

    private TAgronegocio agronegocio;
    private TAgronegocio agronegocioSeleccionado;

    public TAgronegocio getAgronegocioSeleccionado() {
        return agronegocioSeleccionado;
    }

    public void setAgronegocioSeleccionado(TAgronegocio agronegocioSeleccionado) {
        this.agronegocioSeleccionado = agronegocioSeleccionado;
    }

    public TAgronegocio getAgronegocio() {
        return agronegocio;
    }

    public void setAgronegocio(TAgronegocio agronegocio) {
        this.agronegocio = agronegocio;
    }

    public IAgronegocioBo getAgronegocioBo() {
        return agronegocioBo;
    }

    public void setAgronegocioBo(IAgronegocioBo agronegocioBo) {
        this.agronegocioBo = agronegocioBo;
    }

    public IConfiguracionAgronegocioBo getConfiguracionAgronegocioBo() {
        return configuracionAgronegocioBo;
    }

    public void setConfiguracionAgronegocioBo(IConfiguracionAgronegocioBo configuracionAgronegocioBo) {
        this.configuracionAgronegocioBo = configuracionAgronegocioBo;
    }
    private IProductoAgronegocioBo productoAgronegocioBo;
    private IPagoAgronegocioBo pagoAgronegocioBo;
    private BigDecimal total;
    private Boolean mostrarTable;
    private TConfiguracionAgronegocio politicaAgronegocio;
    private TConfiguracionAgronegocio politicaAgronegocioSeleccionado;

    public TConfiguracionAgronegocio getPoliticaAgronegocioSeleccionado() {
        return politicaAgronegocioSeleccionado;
    }

    public void setPoliticaAgronegocioSeleccionado(TConfiguracionAgronegocio politicaAgronegocioSeleccionado) {
        this.politicaAgronegocioSeleccionado = politicaAgronegocioSeleccionado;
    }

    public TConfiguracionAgronegocio getPoliticaAgronegocio() {
        return politicaAgronegocio;
    }

    public void setPoliticaAgronegocio(TConfiguracionAgronegocio politicaAgronegocio) {
        this.politicaAgronegocio = politicaAgronegocio;
    }

    public Boolean getMostrarTable() {
        return mostrarTable;
    }

    public void setMostrarTable(Boolean mostrarTable) {
        this.mostrarTable = mostrarTable;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public IPagoAgronegocioBo getPagoAgronegocioBo() {
        return pagoAgronegocioBo;
    }

    public void setPagoAgronegocioBo(IPagoAgronegocioBo pagoAgronegocioBo) {
        this.pagoAgronegocioBo = pagoAgronegocioBo;
    }

    public IProductoAgronegocioBo getProductoAgronegocioBo() {
        return productoAgronegocioBo;
    }

    public void setProductoAgronegocioBo(IProductoAgronegocioBo productoAgronegocioBo) {
        this.productoAgronegocioBo = productoAgronegocioBo;
    }

    public ICompradorBo getCompradorBo() {
        return compradorBo;
    }

    public void setCompradorBo(ICompradorBo compradorBo) {
        this.compradorBo = compradorBo;
    }

    public IProductoCooperativaBo getProductoCooperativaBo() {
        return productoCooperativaBo;
    }

    public void setProductoCooperativaBo(IProductoCooperativaBo productoCooperativaBo) {
        this.productoCooperativaBo = productoCooperativaBo;
    }

    public IProductoProductorBo getProductoProductorBo() {
        return productoProductorBo;
    }

    public void setProductoProductorBo(IProductoProductorBo productoProductorBo) {
        this.productoProductorBo = productoProductorBo;
    }

    public IProductoBo getProductoBo() {
        return productoBo;
    }

    public void setProductoBo(IProductoBo productoBo) {
        this.productoBo = productoBo;
    }

    private List<TComprador> listaComprador;

    private List<TAgronegocio> listaAgronegocio;

    public List<TAgronegocio> getListaAgronegocio() {
        return listaAgronegocio;
    }

    public void setListaAgronegocio(List<TAgronegocio> listaAgronegocio) {
        this.listaAgronegocio = listaAgronegocio;
    }

    private List<TProductoProductor> listaProductoProductor;

    public List<TProductoProductor> getListaProductoProductor() {
        return listaProductoProductor;
    }

    public void setListaProductoProductor(List<TProductoProductor> listaProductoProductor) {
        this.listaProductoProductor = listaProductoProductor;
    }

    public List<TComprador> getListaComprador() {

        this.listaComprador.clear();

        List<TComprador> compradores = this.compradorBo.listComprador();
        for (TComprador comprador : compradores) {
            if (comprador.getEstadoComprador() == true) {
                this.listaComprador.add(comprador);
            }
        }
        return listaComprador;
    }

    public void setListaComprador(List<TComprador> listaComprador) {
        this.listaComprador = listaComprador;
    }

    //propiedades de validacion
    private String msgNombre;

    private String msgArea;
    private String msgTipo;
    private String msgSistema;
    private String msgOrganizacion;
    private String msgProductor;
    private String msgAreaCultivo;
    private String msgTipoCultivo;
    private Boolean tipocontado;
    private Boolean tipocredito;
    private Boolean PContado;
    private BigDecimal comision;

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    private Boolean tipoefectivo;
    private Boolean tipocheque;
    private Boolean Habilitar;

    public Boolean getTipoefectivo() {
        return tipoefectivo;
    }

    public void setTipoefectivo(Boolean tipoefectivo) {
        this.tipoefectivo = tipoefectivo;
    }

    public Boolean getTipocheque() {
        return tipocheque;
    }

    public void setTipocheque(Boolean tipocheque) {
        this.tipocheque = tipocheque;
    }

    public Boolean getHabilitar() {
        return Habilitar;
    }

    public void setHabilitar(Boolean Habilitar) {
        this.Habilitar = Habilitar;
    }

    public Boolean getTipocontado() {
        return tipocontado;
    }

    public void setTipocontado(Boolean tipocontado) {
        this.tipocontado = tipocontado;
    }

    public Boolean getTipocredito() {
        return tipocredito;
    }

    public void setTipocredito(Boolean tipocredito) {
        this.tipocredito = tipocredito;
    }

    public Boolean getPContado() {
        return PContado;
    }

    public void setPContado(Boolean PContado) {
        this.PContado = PContado;
    }

    public Boolean getPCredito() {
        return PCredito;
    }

    public void setPCredito(Boolean PCredito) {
        this.PCredito = PCredito;
    }
    private Boolean PCredito;

    public String getMsgProductor() {
        return msgProductor;
    }

    public void setMsgProductor(String msgProductor) {
        this.msgProductor = msgProductor;
    }

    public String getMsgAreaCultivo() {
        return msgAreaCultivo;
    }

    public void setMsgAreaCultivo(String msgAreaCultivo) {
        this.msgAreaCultivo = msgAreaCultivo;
    }

    public String getMsgTipoCultivo() {
        return msgTipoCultivo;
    }

    public void setMsgTipoCultivo(String msgTipoCultivo) {
        this.msgTipoCultivo = msgTipoCultivo;
    }

    public String getMsgArea() {
        return msgArea;
    }

    public void setMsgArea(String msgArea) {
        this.msgArea = msgArea;
    }

    public String getMsgTipo() {
        return msgTipo;
    }

    public void setMsgTipo(String msgTipo) {
        this.msgTipo = msgTipo;
    }

    public String getMsgSistema() {
        return msgSistema;
    }

    public void setMsgSistema(String msgSistema) {
        this.msgSistema = msgSistema;
    }

    public String getMsgOrganizacion() {
        return msgOrganizacion;
    }

    public void setMsgOrganizacion(String msgOrganizacion) {
        this.msgOrganizacion = msgOrganizacion;
    }

    private boolean estadoFormulario;

    private Integer tipo;
    private Boolean ShowIndi;
    private Boolean ShowCoop;
    private Integer entidadSeleccionada;
    private Integer productorSeleccionado;
    private Integer idAreaSeleccionada;
    private Integer idTipoSeleccionado;
    private TProductoAgronegocio productoAgronegocio;

    public TProductoAgronegocio getProductoAgronegocio() {
        return productoAgronegocio;
    }

    public void setProductoAgronegocio(TProductoAgronegocio productoAgronegocio) {
        this.productoAgronegocio = productoAgronegocio;
    }

    public Integer getIdAreaSeleccionada() {
        return idAreaSeleccionada;
    }

    public void setIdAreaSeleccionada(Integer idAreaSeleccionada) {
        this.idAreaSeleccionada = idAreaSeleccionada;
    }

    public Integer getIdTipoSeleccionado() {
        return idTipoSeleccionado;
    }

    public void setIdTipoSeleccionado(Integer idTipoSeleccionado) {
        this.idTipoSeleccionado = idTipoSeleccionado;
    }

    public Integer getProductorSeleccionado() {
        return productorSeleccionado;
    }

    public void setProductorSeleccionado(Integer productorSeleccionado) {
        this.productorSeleccionado = productorSeleccionado;
    }

    public Integer getEntidadSeleccionada() {
        return entidadSeleccionada;
    }

    public void setEntidadSeleccionada(Integer entidadSeleccionada) {
        this.entidadSeleccionada = entidadSeleccionada;
    }

    public Boolean getShowIndi() {
        return ShowIndi;
    }

    public void setShowIndi(Boolean ShowIndi) {
        this.ShowIndi = ShowIndi;
    }

    public Boolean getShowCoop() {
        return ShowCoop;
    }

    public void setShowCoop(Boolean ShowCoop) {
        this.ShowCoop = ShowCoop;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
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

    public void init() {

        super.enableShowData();

        this.tipocontado = true;
        this.PContado = true;
        this.producto = new TProducto();
        this.productoProductor = new TProductoProductor();
        this.productoAgronegocio = new TProductoAgronegocio();
        this.pago = new TPagoAgronegocio();
        this.pago.setTAgronegocio(new TAgronegocio());

        this.tipoefectivo = true;
        this.Habilitar = true;
        this.productoAgronegocio.setTProducto(new TProducto());
        this.productoAgronegocio.setTAgronegocio(new TAgronegocio());
       
        this.productorIndividualSeleccionado = new TProductorIndividual();

        this.agronegocio = new TAgronegocio();
        this.agronegocio.setTComprador(new TComprador());
        this.agronegocioSeleccionado = new TAgronegocio();
        this.agronegocioSeleccionado.setTComprador(new TComprador());
        this.listaProductoAgronegocio = new ArrayList<TProductoAgronegocio>();
        this.productoProductor.setTProducto(new TProducto());
        this.productoProductor.setTProductorIndividual(new TProductorIndividual());
        this.productoCooperativa = new TProductoCooperativa();
        this.productoCooperativa.setTEntidad(new TEntidad());
        this.productoCooperativa.setTProducto(new TProducto());
        this.listaProducto = new ArrayList<TProducto>();
        this.listaProductoDetalle = new ArrayList<TProducto>();
        this.listaComprador = new ArrayList<TComprador>();
        this.compradorSeleccionado = new TComprador();
        this.listaProductoProductor = new ArrayList<TProductoProductor>();
        
    }

    private List<SelectItem> selectOneItemIndividual;
    private List<SelectItem> selectOneItemAreas;
    private List<SelectItem> selectOneItemTipos;

    public List<SelectItem> getSelectOneItemTipos() {
        return selectOneItemTipos;
    }

    public void setSelectOneItemTipos(List<SelectItem> selectOneItemTipos) {
        this.selectOneItemTipos = selectOneItemTipos;
    }

    public List<SelectItem> getSelectOneItemAreas() {

        this.selectOneItemAreas = new ArrayList<SelectItem>();
        List<TAreaCultivo> productores = this.productoBo.listTAreaCultivo();
        for (TAreaCultivo individual : productores) {
            if (individual.getEstadoAreaCultivo() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdAreaCultivo(), individual.getNombreAreaCultivo());
                this.selectOneItemAreas.add(selectItem);
            }
        }

        return selectOneItemAreas;
    }

    public void setSelectOneItemAreas(List<SelectItem> selectOneItemAreas) {
        this.selectOneItemAreas = selectOneItemAreas;
    }

    public TProducto getProducto() {
        return producto;
    }

    public void setProducto(TProducto producto) {
        this.producto = producto;
    }

    public TProducto getProductoSeleccionado() {
        return ProductoSeleccionado;
    }

    public void setProductoSeleccionado(TProducto ProductoSeleccionado) {
        this.ProductoSeleccionado = ProductoSeleccionado;
    }

    public List<TProducto> getListaProducto() {
        return listaProducto;
    }

    public void setListaProducto(List<TProducto> listaProducto) {
        this.listaProducto = listaProducto;
    }

    public List<SelectItem> getSelectOneItemIndividual() {

        this.selectOneItemIndividual = new ArrayList<SelectItem>();
        List<TProductorIndividual> productores = this.productoBo.listTProductorIndividual();
        for (TProductorIndividual individual : productores) {
            if (individual.getEstadoProductorIndividual() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdProductorIndividual(), individual.getNombresProdcutorIndividual());
                this.selectOneItemIndividual.add(selectItem);
            }
        }
        return selectOneItemIndividual;
    }

    public void setSelectOneItemIndividual(List<SelectItem> selectOneItemIndividual) {
        this.selectOneItemIndividual = selectOneItemIndividual;
    }

    public void correlativo() {

        Integer correlativo = this.agronegocioBo.getCorrelativo();

        if (correlativo < 10) {

            this.agronegocio.setCodigo("AGRO-0000" + correlativo);

        } else if (correlativo < 100) {

            this.agronegocio.setCodigo("AGRO-000" + correlativo);

        } else if (correlativo < 1000) {

            this.agronegocio.setCodigo("AGRO-00" + correlativo);

        } else if (correlativo < 10000) {

            this.agronegocio.setCodigo("AGRO-0" + correlativo);
        } else {
            this.agronegocio.setCodigo("AGRO" + correlativo);
        }

    }

    public void modificar() {

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores modificado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();
        limpiar();
    }

    public void darDeBaja() {

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();

    }

    public void darDeAlta() {

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Grupo de productores dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

        super.enableShowData();

    }

    public void crear() {

    }

    public String verEstado(boolean estado) {
        if (estado) {
            return "Activo";

        } else {
            return "Inactivo";
        }
    }

    public void validarFormulario() {
        this.estadoFormulario = true;

    }

    public void validarFormularioModificar() {
        this.estadoFormulario = true;

    }

    public void limpiar() {

        this.estadoFormulario = false;

        this.msgNombre = "";
        this.msgArea = "";
        this.msgTipo = "";
        this.msgSistema = "";
        this.msgOrganizacion = "";
        this.listaProducto.clear();
        this.producto = new TProducto();

    }

    public void enableShowDataBean() {
        limpiar();
        super.setShowData(true);
        super.setShowCreate(false);
        this.listaAgronegocio = this.agronegocioBo.listAgronegocio();

    }

    public int sumarParticipantes(int hombres, int mujeres) {

        return hombres + mujeres;

    }

    public void llenar() {

        if (this.tipo == 1) {

            this.ShowIndi = true;
            this.ShowCoop = false;

        } else if (this.tipo == 3) {

            this.ShowCoop = true;
            this.ShowIndi = false;

        } else {

            this.ShowCoop = false;
            this.ShowIndi = false;
        }

    }

    public void mostrarTipos() {

        this.selectOneItemTipos = new ArrayList<SelectItem>();
        List<TTipoCultivo> productores = this.productoBo.listTTipoCultivo(this.idAreaSeleccionada);
        for (TTipoCultivo individual : productores) {
            if (individual.getEstadoTipoCultivo() == true) {
                SelectItem selectItem = new SelectItem(individual.getIdTipoCultivo(), individual.getNombreTipoCultivo());
                this.selectOneItemTipos.add(selectItem);
            }
        }

    }

    public void llenarProductos() {
        this.listaProducto.add(this.producto);
        this.producto = new TProducto();
    }

    public void removerActivos(int posicion) {

        this.listaProducto.remove(posicion);
    }

    public void cambiarTipo1() {

        if (this.tipocontado == false) {
            this.tipocredito = true;
            this.tipocontado = false;
            this.PContado = false;
            this.PCredito = true;

        }
        if (this.tipocontado == true) {
            this.tipocredito = false;
            this.tipocontado = true;
            this.PContado = true;
            this.PCredito = false;

        }

    }

    public void cambiarTipo2() {

        if (this.tipocredito == false) {
            this.tipocredito = false;
            this.tipocontado = true;
            this.PContado = true;
            this.PCredito = false;

        }
        if (this.tipocredito == true) {
            this.tipocredito = true;
            this.tipocontado = false;
            this.PContado = false;
            this.PCredito = true;

        }

    }

    public void cambiarForma2() {

        if (this.tipocheque == false) {

            this.tipoefectivo = true;
            this.Habilitar = true;
        }

        if (this.tipocheque == true) {

            this.tipoefectivo = false;
            this.Habilitar = false;
        }
    }

    public void cambiarForma1() {

        if (this.tipoefectivo == false) {

            this.tipocheque = true;
            this.Habilitar = false;
        }

        if (this.tipoefectivo == true) {

            this.tipocheque = false;
            this.Habilitar = true;
        }
    }

    public void productos() {

        this.listaProducto.clear();

        this.listaProductoProductor = this.productoProductorBo.listTProductoProductor(this.productorIndividualSeleccionado.getIdProductorIndividual());

        for (int i = 0; i < this.listaProductoProductor.size(); i++) {

            this.listaProducto.add(this.productoBo.getTProducto(this.listaProductoProductor.get(i).getTProducto().getIdproducto()));

        }

    }

    public void pasar(Integer i) {

        this.productoAgronegocio.setNombre(this.listaProducto.get(i).getNombre());
        this.productoAgronegocio.setTProducto(this.listaProducto.get(i));

        this.listaProductoAgronegocio.add(this.productoAgronegocio);

        this.total = this.total.add(this.productoAgronegocio.getPrecio().multiply(new BigDecimal(this.productoAgronegocio.getCantidad())));

        this.comision = this.total.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

        this.productoAgronegocio = new TProductoAgronegocio();

    }

    public void quitar(int i) {

        this.total = this.total.subtract(this.listaProductoAgronegocio.get(i).getPrecio().multiply(new BigDecimal(this.listaProductoAgronegocio.get(i).getCantidad())));

        this.comision = this.total.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

        this.listaProductoAgronegocio.remove(i);

    }

    public void mostrarCalculos() {

        this.monto = this.agronegocioSeleccionado.getTotal();

        System.out.println(this.agronegocioSeleccionado.getTConfiguracionAgronegocio().getIdConfiguracionAgronegocio());

        this.politicaAgronegocioSeleccionado = this.configuracionAgronegocioBo.getConfiguracion(this.agronegocioSeleccionado.getTConfiguracionAgronegocio().getIdConfiguracionAgronegocio());

        System.out.println(this.politicaAgronegocioSeleccionado.getTasaInteresAgronegocio());

        this.interes = (this.monto.multiply(this.politicaAgronegocioSeleccionado.getTasaInteresAgronegocio().divide(new BigDecimal(100)).multiply(new BigDecimal(this.agronegocioSeleccionado.getPlazo()).divide(new BigDecimal(12)))));

        //obteniniedo el numero de desembolso
        DateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd");
        inFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
        Date purchaseDate = new Date();

        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");

        try {
            this.fechaDePago = (formatoDelTexto.parse(inFormat.format(purchaseDate)));
        } catch (ParseException ex) {
            Logger.getLogger(PagoAgronegocioBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //calculo de dias de retraso 
        this.totales = this.monto.add(this.interes);

        this.mora = BigDecimal.ZERO;

        if (this.fechaDePago.compareTo(this.agronegocioSeleccionado.getFechapago()) > 0) {

            this.dias = (int) ((this.fechaDePago.getTime() - this.agronegocioSeleccionado.getFechapago().getTime()) / 86400000);

            this.mora = ((this.totales.multiply(this.politicaAgronegocioSeleccionado.getTasaMoraAgronegocio().divide(new BigDecimal(100)).divide(new BigDecimal(30)).multiply(new BigDecimal(this.dias)))));

        } else {

            this.dias = 0;
        }

        this.totales = this.monto.add(this.interes.add(this.mora));
        this.comision = this.totales.multiply(this.politicaAgronegocioSeleccionado.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

    }

    public void mostrarProductorIndividual() {

        this.listaProductoresIndividuales = this.iproductorindividual.listProductorIndividual();

    }

    public void mostrarAgronegocios() {

        this.listaAgronegocioPagos = this.agronegocioBo.listAgronegocioCodigo(this.codigoAgronegocio);
        this.mostrarAgronegocio = true;

    }
    
    public void mostrarAgronegociosDui() {

        this.listaAgronegocioPagos = this.agronegocioBo.listAgronegocioDui(this.duiComprador);
        
        
        
        
        this.mostrarAgronegocio = true;

    }

    public void cargarAgronegocio() {

        if ("Pendiente".equals(this.agronegocioSeleccionado.getEstado())) {

            this.enableShowCreate();

            mostrarCalculos();

        } else {

            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "El agronegocio ya esta finalizado", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);

        }

    }

    public void registtrarPago() {

        this.pago.setMonto(this.total);
        this.pago.setTAgronegocio(this.agronegocioSeleccionado);

        Date date = new Date();

        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Hora: " + hourFormat.format(date));
        
        //Generando el numero de recibo
        Integer numero= this.ipagoBo.getCorrelativo(); 
        Integer numero2= this.pagoAgronegocioBo.getCorrelativo();
        
        
        if(numero>numero2){
            numero++;
            this.pago.setReferencia(numero);            
        }else{           
            numero2++;
            this.pago.setReferencia(numero2);
        }     

        this.pago.setFecha(date);
        this.pago.setHora("" + hourFormat.format(date));
        this.pago.setInteres(this.interes);
        this.pago.setOtrosinteres(this.mora);
        this.pago.setDias(this.dias);
        this.pago.setComision(this.comision);
        if (this.tipoefectivo == true) {
            this.pago.setForma("Efectivo");
            this.pago.setCheque("No aplica");

        } else {

            this.pago.setForma("Cheque");
        }

        this.pagoAgronegocioBo.create(pago);
        this.agronegocioSeleccionado.setEstado("Finalizado");
        this.agronegocioBo.update(this.agronegocioSeleccionado);

        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago realizado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void generarRecibo() throws Exception {

        String urlDatabase = "jdbc:postgresql://localhost:5432/bd_sigaf";
        Connection conn = DriverManager.getConnection(urlDatabase, "postgres", "root");
        Map<String, Object> parametros = new HashMap();

        TPagoAgronegocio pagoAgro = this.pagoAgronegocioBo.getPagoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());

        parametros.put("id_pago", pagoAgro.getIdPagoAgronegocio());

        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/reciboAgronegocio.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, conn);

        System.out.println(bytes.length);

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

}
