/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IAgronegocioBo;
import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.Ibo.ICompradorBo;
import com.sigaf.Ibo.IConfiguracionAgronegocioBo;
import com.sigaf.Ibo.IPagoAgronegocioBo;
import com.sigaf.Ibo.IPagoBo;
import com.sigaf.Ibo.IProductoAgronegocioBo;
import com.sigaf.Ibo.IProductoBo;
import com.sigaf.Ibo.IProductoCooperativaBo;
import com.sigaf.Ibo.IProductoProductorBo;
import com.sigaf.Ibo.IProductorGrupalBo;
import com.sigaf.Ibo.IProductorIndividualBo;
import com.sigaf.pojo.TAgronegocio;
import com.sigaf.pojo.TAreaCultivo;
import com.sigaf.pojo.TBitacora;
import com.sigaf.pojo.TComprador;
import com.sigaf.pojo.TConfiguracionAgronegocio;
import com.sigaf.pojo.TCooperativa;
import com.sigaf.pojo.TEntidad;
import com.sigaf.pojo.TPagoAgronegocio;
import com.sigaf.pojo.TProducto;
import com.sigaf.pojo.TProductoAgronegocio;
import com.sigaf.pojo.TProductoCooperativa;
import com.sigaf.pojo.TProductoProductor;
import com.sigaf.pojo.TProductorGrupal;
import com.sigaf.pojo.TProductorIndividual;
import com.sigaf.pojo.TTipoCredito;
import com.sigaf.pojo.TTipoCultivo;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author yonat
 */
@Named(value = "agronegocioBean")
@RequestScoped
@ManagedBean
public class AgronegocioBean extends Actividad {

    private TProducto ProductoSeleccionado;
    private TProductorIndividual productorIndividualSeleccionado;
    private TProductorIndividual productorIndividualSeleccionadoVista;
    private List<TProducto> listaProducto;
    private List<TProducto> listaProductoDetalle;
    private List<TProductoAgronegocio> listaProductoAgronegocio;
    private List<TProductorIndividual> listaProductoresIndividuales;
    private TConfiguracionAgronegocio politicaAgronegocioSeleccionado;
    private TProducto producto;
    private TProductoProductor productoProductor;
    private TProducto productoDetalle;
    private TProductoCooperativa productoCooperativa;
    private TComprador compradorSeleccionado;
    private BigDecimal monto;
    private BigDecimal interes;
    private BigDecimal totales;
    private BigDecimal comisionC;
    private Boolean mostrarPago;
    private Date hoy;
    private IProductoBo productoBo;
    private IProductoProductorBo productoProductorBo;
    private IProductoCooperativaBo productoCooperativaBo;
    private ICompradorBo compradorBo;
    private IBitacoraBo bitacoraBo;    
    private IConfiguracionAgronegocioBo configuracionAgronegocioBo;
    private IAgronegocioBo agronegocioBo;
    private IProductorIndividualBo iproductorindividual;
    private BigDecimal valorRedondeadoTotal;
    private BigDecimal valorRedondeadoComision;
    private BigDecimal valorRedondeadoComisionCredito;
    private IProductoAgronegocioBo productoAgronegocioBo;
    private IPagoAgronegocioBo pagoAgronegocioBo;
    private TPagoAgronegocio pagoAgro;
    private IPagoBo ipagoBo;
    private boolean estadoFormulario;
    private Integer tipo;
    private Boolean ShowIndi;
    private Boolean ShowCoop;
    private Integer entidadSeleccionada;
    private Integer productorSeleccionado;
    private Integer idAreaSeleccionada;
    private Integer idTipoSeleccionado;
    private TProductoAgronegocio productoAgronegocio;
    private Boolean tipoefectivo;
    private Boolean tipocheque;
    private Boolean Habilitar;
    private String msgAreaCultivo;
    private String msgTipoCultivo;
    private Boolean tipocontado;
    private Boolean tipocredito;
    private Boolean PContado;
    private BigDecimal comision;
    private BigDecimal comisionCredito;
    private Integer dias;
    private String msglistaProductos;
    private String msgCheque;
    private List<TProductoProductor> listaProductoProductor;
    private List<TProductoProductor> listaProductoProductorCod;
    private List<TComprador> listaComprador;
    private List<TAgronegocio> listaAgronegocio;
    private BigDecimal total;
    private Boolean mostrarTable;
    private TConfiguracionAgronegocio politicaAgronegocio;
    private String msgCodigo;
    private String msgFecha;
    private String msgProductor;
    private String msgComprador;
    private String msgNumeroCheque;
    private String msgPlazo;
    private String msgFechaEstipulada;
    private Date fechaDePago;
    private BigDecimal mora;
    private String msgTotal;
    private String msgProductoSeleccionadoPasar;
    private String msgProductorSeleccionadoPasar;
    private String msgUnidad;
    private String msgPrecio;
    private String msgCantidad;
    private TAgronegocio agronegocio;
    private TAgronegocio agronegocioSeleccionado;
    private List<TProducto> listaProductosProductor;
    private String msgDatosPago;
    private List<SelectItem> selectOneItemIndividual;
    private List<SelectItem> selectOneItemAreas;
    private List<SelectItem> selectOneItemTipos;
    private Date fechaPagoAux;
    private BigDecimal totalDeuda;
    private BigDecimal totalDeudaFinal;
    private BigDecimal totalProductor;
    private BigDecimal totalProductorFinal;
    private BigDecimal comisionFinal;

    public BigDecimal getTotalProductorFinal() {
        return totalProductorFinal;
    }

    public void setTotalProductorFinal(BigDecimal totalProductorFinal) {
        this.totalProductorFinal = totalProductorFinal;
    }

    public BigDecimal getComisionFinal() {
        return comisionFinal;
    }

    public void setComisionFinal(BigDecimal comisionFinal) {
        this.comisionFinal = comisionFinal;
    }

    public BigDecimal getTotalProductor() {
        return totalProductor;
    }

    public void setTotalProductor(BigDecimal totalProductor) {
        this.totalProductor = totalProductor;
    }

    public BigDecimal getTotalDeudaFinal() {
        return totalDeudaFinal;
    }

    public void setTotalDeudaFinal(BigDecimal totalDeudaFinal) {
        this.totalDeudaFinal = totalDeudaFinal;
    }

    public BigDecimal getTotalDeuda() {
        return totalDeuda;
    }

    public void setTotalDeuda(BigDecimal totalDeuda) {
        this.totalDeuda = totalDeuda;
    }

    public Date getFechaPagoAux() {
        return fechaPagoAux;
    }

    public void setFechaPagoAux(Date fechaPagoAux) {
        this.fechaPagoAux = fechaPagoAux;
    }

    public TProducto getProductoDetalle() {
        return productoDetalle;
    }

    public void setProductoDetalle(TProducto productoDetalle) {
        this.productoDetalle = productoDetalle;
    }

    public Date getHoy() {
        return new Date();
    }
    
    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public void setHoy(Date hoy) {
        this.hoy = hoy;
    }

    public Boolean getMostrarPago() {
        return mostrarPago;
    }

    public void setMostrarPago(Boolean mostrarPago) {
        this.mostrarPago = mostrarPago;
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

    public BigDecimal getComisionC() {
        return comisionC;
    }

    public void setComisionC(BigDecimal comisionC) {
        this.comisionC = comisionC;
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

    public TProductorIndividual getProductorIndividualSeleccionadoVista() {
        return productorIndividualSeleccionadoVista;
    }

    public void setProductorIndividualSeleccionadoVista(TProductorIndividual productorIndividualSeleccionadoVista) {
        this.productorIndividualSeleccionadoVista = productorIndividualSeleccionadoVista;
    }

    public TProductorIndividual getProductorIndividualSeleccionado() {
        return productorIndividualSeleccionado;
    }

    public void setProductorIndividualSeleccionado(TProductorIndividual productorIndividualSeleccionado) {
        this.productorIndividualSeleccionado = productorIndividualSeleccionado;
    }

    public List<TProductorIndividual> getListaProductoresIndividuales() {
        return listaProductoresIndividuales;
    }

    public void setListaProductoresIndividuales(List<TProductorIndividual> listaProductoresIndividuales) {
        this.listaProductoresIndividuales = listaProductoresIndividuales;
    }

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

    public BigDecimal getValorRedondeadoComisionCredito() {
        return valorRedondeadoComisionCredito;
    }

    public void setValorRedondeadoComisionCredito(BigDecimal valorRedondeadoComisionCredito) {
        this.valorRedondeadoComisionCredito = valorRedondeadoComisionCredito;
    }

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

    public IPagoBo getIpagoBo() {
        return ipagoBo;
    }

    public void setIpagoBo(IPagoBo ipagoBo) {
        this.ipagoBo = ipagoBo;
    }

    public TPagoAgronegocio getPagoAgro() {
        return pagoAgro;
    }

    public void setPagoAgro(TPagoAgronegocio pagoAgro) {
        this.pagoAgro = pagoAgro;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public TConfiguracionAgronegocio getPoliticaAgronegocio() {
        return politicaAgronegocio = this.configuracionAgronegocioBo.getConfiguracion(0);
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

    public List<TAgronegocio> getListaAgronegocio() {
        return listaAgronegocio;
    }

    public void setListaAgronegocio(List<TAgronegocio> listaAgronegocio) {
        this.listaAgronegocio = listaAgronegocio;
    }

    public List<TProductoProductor> getListaProductoProductorCod() {
        return listaProductoProductorCod;
    }

    public void setListaProductoProductorCod(List<TProductoProductor> listaProductoProductorCod) {
        this.listaProductoProductorCod = listaProductoProductorCod;
    }

    public List<TProductoProductor> getListaProductoProductor() {
        return listaProductoProductor;
    }

    public void setListaProductoProductor(List<TProductoProductor> listaProductoProductor) {
        this.listaProductoProductor = listaProductoProductor;
    }

    public List<TComprador> getListaComprador() {
        return listaComprador;
    }

    public void setListaComprador(List<TComprador> listaComprador) {
        this.listaComprador = listaComprador;
    }

    public String getMsgFechaEstipulada() {
        return msgFechaEstipulada;
    }

    public void setMsgFechaEstipulada(String msgFechaEstipulada) {
        this.msgFechaEstipulada = msgFechaEstipulada;
    }

    public String getMsgCheque() {
        return msgCheque;
    }

    public void setMsgCheque(String msgCheque) {
        this.msgCheque = msgCheque;
    }

    public String getMsgCodigo() {
        return msgCodigo;
    }

    public void setMsgCodigo(String msgCodigo) {
        this.msgCodigo = msgCodigo;
    }

    public String getMsgFecha() {
        return msgFecha;
    }

    public void setMsgFecha(String msgFecha) {
        this.msgFecha = msgFecha;
    }

    public String getMsgComprador() {
        return msgComprador;
    }

    public void setMsgComprador(String msgComprador) {
        this.msgComprador = msgComprador;
    }

    public String getMsgNumeroCheque() {
        return msgNumeroCheque;
    }

    public void setMsgNumeroCheque(String msgNumeroCheque) {
        this.msgNumeroCheque = msgNumeroCheque;
    }

    public String getMsgPlazo() {
        return msgPlazo;
    }

    public void setMsgPlazo(String msgPlazo) {
        this.msgPlazo = msgPlazo;
    }

    public String getMsglistaProductos() {
        return msglistaProductos;
    }

    public void setMsglistaProductos(String msglistaProductos) {
        this.msglistaProductos = msglistaProductos;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public BigDecimal getComisionCredito() {
        return comisionCredito;
    }

    public void setComisionCredito(BigDecimal comisionCredito) {
        this.comisionCredito = comisionCredito;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

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

    public String getMsgDatosPago() {
        return msgDatosPago;
    }

    public void setMsgDatosPago(String msgDatosPago) {
        this.msgDatosPago = msgDatosPago;
    }

    public List<TProducto> getListaProductosProductor() {
        return listaProductosProductor;
    }

    public void setListaProductosProductor(List<TProducto> listaProductosProductor) {
        this.listaProductosProductor = listaProductosProductor;
    }

    public String getMsgProductoSeleccionadoPasar() {
        return msgProductoSeleccionadoPasar;
    }

    public void setMsgProductoSeleccionadoPasar(String msgProductoSeleccionadoPasar) {
        this.msgProductoSeleccionadoPasar = msgProductoSeleccionadoPasar;
    }

    public String getMsgProductorSeleccionadoPasar() {
        return msgProductorSeleccionadoPasar;
    }

    public void setMsgProductorSeleccionadoPasar(String msgProductorSeleccionadoPasar) {
        this.msgProductorSeleccionadoPasar = msgProductorSeleccionadoPasar;
    }

    public String getMsgUnidad() {
        return msgUnidad;
    }

    public void setMsgUnidad(String msgUnidad) {
        this.msgUnidad = msgUnidad;
    }

    public String getMsgPrecio() {
        return msgPrecio;
    }

    public void setMsgPrecio(String msgPrecio) {
        this.msgPrecio = msgPrecio;
    }

    public String getMsgCantidad() {
        return msgCantidad;
    }

    public void setMsgCantidad(String msgCantidad) {
        this.msgCantidad = msgCantidad;
    }

    public BigDecimal getMora() {
        return mora;
    }

    public void setMora(BigDecimal mora) {
        this.mora = mora;
    }

    public TConfiguracionAgronegocio getPoliticaAgronegocioSeleccionado() {
        return politicaAgronegocioSeleccionado;
    }

    public void setPoliticaAgronegocioSeleccionado(TConfiguracionAgronegocio politicaAgronegocioSeleccionado) {
        this.politicaAgronegocioSeleccionado = politicaAgronegocioSeleccionado;
    }

    public Date getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(Date fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public String getMsgTotal() {
        return msgTotal;
    }

    public void setMsgTotal(String msgTotal) {
        this.msgTotal = msgTotal;
    }

    public void updateListaAgronegocios() {
        this.listaAgronegocio = this.agronegocioBo.listAgronegocio();

    }

    public void setEstadoFormulario(boolean estadoFormulario) {
        this.estadoFormulario = estadoFormulario;
    }

    public void init() {

        super.enableShowData();
        this.tipocontado = true;
        this.PContado = true;
        this.producto = new TProducto();
        this.productoProductor = new TProductoProductor();
        this.productoAgronegocio = new TProductoAgronegocio();
        this.total = BigDecimal.ZERO;
        this.tipoefectivo = true;
        this.Habilitar = true;
        this.pagoAgro = new TPagoAgronegocio();
        this.productoAgronegocio.setTProducto(new TProducto());
        this.productoAgronegocio.setTAgronegocio(new TAgronegocio());
        this.productorIndividualSeleccionado = new TProductorIndividual();
        this.tipocheque = false;
        this.agronegocio = new TAgronegocio();
        this.agronegocio.setTComprador(new TComprador());
        this.listaProductoAgronegocio = new ArrayList<TProductoAgronegocio>();
        this.productoProductor.setTProducto(new TProducto());
        this.productoProductor.setTProductorIndividual(new TProductorIndividual());
        this.productoCooperativa = new TProductoCooperativa();
        this.productoCooperativa.setTEntidad(new TEntidad());
        this.productoCooperativa.setTProducto(new TProducto());
        this.listaProducto = new ArrayList<TProducto>();
        this.listaProductoDetalle = new ArrayList<TProducto>();
        this.listaComprador = new ArrayList<TComprador>();
        this.listaProductoresIndividuales = new ArrayList<TProductorIndividual>();
        this.compradorSeleccionado = new TComprador();
        this.compradorSeleccionado.setDuiComprador("");
        this.listaProductoProductor = new ArrayList<TProductoProductor>();
        this.valorRedondeadoTotal = new BigDecimal("0.00");
        this.valorRedondeadoComision = new BigDecimal("0.00");

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
    
    public void crear() {

        mostrarCalculos();
        this.agronegocio.setTComprador(this.compradorSeleccionado);
        correlativo();
        if (this.tipoefectivo == true) {
            this.pagoAgro.setForma("Efectivo");
            this.pagoAgro.setCheque("No aplica");
        } else {
            this.pagoAgro.setForma("Cheque");
        }
        if (this.tipocontado == true) {
            this.agronegocio.setTipo("Contado");// 1 = contado
            this.agronegocio.setEstado("Finalizado");
        } else {
            this.agronegocio.setTipo("Crédito");// 2 = credito
            this.agronegocio.setEstado("Pendiente");
        }
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.println("Hora: " + hourFormat.format(date));
        this.agronegocio.setTotal(this.total);
        this.agronegocio.setTConfiguracionAgronegocio(this.politicaAgronegocio);
        this.agronegocio.setEstadoregistro(true);
        //Generando el numero de recibo
        Integer numero = this.ipagoBo.getCorrelativo();
        Integer numero2 = this.pagoAgronegocioBo.getCorrelativo();
        if (numero > numero2) {
            numero++;
            this.pagoAgro.setReferencia(numero);
        } else {
            numero2++;
            this.pagoAgro.setReferencia(numero2);
        }
        if (this.tipocontado == true) {
            this.agronegocio.setComision(this.comision);
            this.agronegocio.setInteres(BigDecimal.ZERO);
            this.agronegocio.setFechapago(this.agronegocio.getFecha());
            this.pagoAgro.setHora("" + hourFormat.format(date));
            this.pagoAgro.setInteres(BigDecimal.ZERO);
            this.pagoAgro.setOtrosinteres(BigDecimal.ZERO);
            this.pagoAgro.setDias(0);
            this.pagoAgro.setMonto(this.total);
            this.agronegocio.setPlazo(0);
            this.pagoAgro.setComision(this.comision);
            this.agronegocioBo.create(this.agronegocio);
            this.pagoAgro.setTAgronegocio(this.agronegocio);
            this.pagoAgro.setFecha(this.agronegocio.getFecha());
            this.pagoAgronegocioBo.create(pagoAgro);
        } else {
            this.agronegocio.setComision(this.comisionCredito);
            this.agronegocio.setInteres((this.interes));
            this.agronegocioBo.create(this.agronegocio);
        }
        
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_agronegocio");
        auxBitacora.setAccionBitacora("Crear agronegocio");
        auxBitacora.setDatosBitacora("Código:" + this.agronegocio.getCodigo()
                + ", Tipo: " + this.agronegocio.getTipo()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.agronegocio.getIdAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);

        
        
        
        for (int i = 0; i < this.listaProductoAgronegocio.size(); i++) {
            this.listaProductoAgronegocio.get(i).setTAgronegocio(this.agronegocio);
            this.productoAgronegocioBo.create(this.listaProductoAgronegocio.get(i));
        }
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agronegocio guardado correctamente", null);
        FacesContext.getCurrentInstance().addMessage("", mensaje);
        correlativo();
        limpiarAgronegocio();
        limpiar();

    }
    
    public void darDeBaja() {

        this.agronegocioSeleccionado.setEstadoregistro(false);
        this.agronegocioBo.update(this.agronegocioSeleccionado);
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_agronegocio");
        auxBitacora.setAccionBitacora("Dar de baja agronegocio");
        auxBitacora.setDatosBitacora("Código:" + this.agronegocioSeleccionado.getCodigo()
                + ", Tipo: " + this.agronegocioSeleccionado.getTipo()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.agronegocioSeleccionado.getIdAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        
        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agronegocio dado de baja correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);
        super.enableShowData();

    }

    public void darDeAlta() {

        this.agronegocioSeleccionado.setEstadoregistro(true);
        this.agronegocioBo.update(this.agronegocioSeleccionado);
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_agronegocio");
        auxBitacora.setAccionBitacora("Dar de alta agronegocio");
        auxBitacora.setDatosBitacora("Código:" + this.agronegocioSeleccionado.getCodigo()
                + ", Tipo: " + this.agronegocioSeleccionado.getTipo()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.agronegocioSeleccionado.getIdAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);        
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Agronegocio dado de alta correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);     
        super.enableShowData();

    }   

    public String verEstado(boolean estado) {
        
        if (estado) {
            return "Activo";
        } else {
            return "Inactivo";
        }
    }

    public void validarFormulario() {
        
        this.agronegocio.setFechapago(this.fechaPagoAux);

        this.estadoFormulario = true;
        if (this.agronegocio.getCodigo().length() == 0) {
            this.msgCodigo = "El codigo es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgCodigo = "";
        }
        if (this.agronegocio.getFecha() == null) {
            this.msgFecha = "La fecha es requeria";
            this.estadoFormulario = false;
        } else {
            this.msgFecha = "";
        }
        if (this.tipocredito == true) {
            if (this.agronegocio.getPlazo() == null || this.agronegocio.getPlazo() < 1) {
                this.msgPlazo = "El plazo es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgPlazo = "";
            }
            if (this.agronegocio.getFechapago() == null) {
                this.msgFechaEstipulada = "La fecha estipulada de pago es requerida";
                this.estadoFormulario = false;
            } else {
                this.msgFechaEstipulada = "";
            }
            if (this.mostrarTable == false) {
                this.msgDatosPago = "Deber de calcular los valores del pago";
                this.estadoFormulario = false;
            } else {
                this.msgDatosPago = "";
            }
            if (this.agronegocio.getPlazo() == null || this.agronegocio.getPlazo() < 1) {
                this.msgPlazo = "El plazo es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgPlazo = "";
            }
        }
        if (this.tipoefectivo == false) {
            if ("".equals(this.pagoAgro.getCheque())) {
                this.msgCheque = "El numero de cheque es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgCheque = "";
            }
        }
        if (this.productorIndividualSeleccionado.getNombresProdcutorIndividual() == "") {
            this.msgProductor = "El productor es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgProductor = "";
        }
        if (this.ProductoSeleccionado == null) {
            this.msgProductoSeleccionadoPasar = "Debe seleccionar un cultivo";
            this.estadoFormulario = false;
        } else {
            this.msgProductoSeleccionadoPasar = "";
        }
        if (this.compradorSeleccionado == null) {
            this.msgComprador = "El comprador es requerido";
            this.estadoFormulario = false;
        } else {
            this.msgComprador = "";
        }
        if (this.listaProductoAgronegocio.size() == 0) {
            this.msglistaProductos = "Debe agregar por lo menos un producto";
            this.estadoFormulario = false;
        } else {
            this.msglistaProductos = "";
        }
        if (this.total.doubleValue() == 0.0) {
            this.msgTotal = "El total debe ser mayor que cero";
        } else {
            this.msgTotal = "";
        }

    }

    public void limpiar() {

        this.estadoFormulario = false;
        this.msgProductor = "";
        this.msgProductoSeleccionadoPasar = "";
        this.msgProductorSeleccionadoPasar = "";
        this.msgUnidad = "";
        this.msgPrecio = "";
        this.msgCantidad = "";
        this.msgComprador = "";
        this.msglistaProductos = "";
        this.msgPlazo = "";
        this.listaProducto.clear();
        this.producto = new TProducto();

    }

    public void enableShowDataBean() {

        limpiarAgronegocio();
        limpiar();
        correlativo();
        this.mostrarPago=false;
        super.setShowData(true);
        super.setShowCreate(false);
      

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
            this.msgCheque = "";
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
            this.msgCheque = "";
        }
    }

    public BigDecimal calcularTotal(int cantidad, BigDecimal precio) {

        BigDecimal total;
        total = precio.multiply(new BigDecimal(cantidad));
        return total;

    }

    public void productos() {

        this.listaProductosProductor = new ArrayList<TProducto>();
        this.listaProducto.clear();
        this.listaProductosProductor.clear();
        this.listaProductoProductor = this.productoProductorBo.listTProductoProductor(this.productorIndividualSeleccionado.getIdProductorIndividual());
        for (int i = 0; i < this.listaProductoProductor.size(); i++) {
            this.listaProducto.add(this.productoBo.getTProducto(this.listaProductoProductor.get(i).getTProducto().getIdproducto()));
        }
        for (int i = 0; i < this.listaProductoProductor.size(); i++) {
            if (this.listaProducto.get(i).getEstado() == true) {
                this.listaProductosProductor.add(this.listaProducto.get(i));
            }
        }

    }

    public void validarPasar() {

        Boolean validar = true;
        if (this.productorIndividualSeleccionado.getNombresProdcutorIndividual() == "") {
            this.msgProductor = "Debe seleccionar un productor";
            validar = false;
        } else {
            this.msgProductor = "";
        }
        if (this.ProductoSeleccionado == null) {
            this.msgProductoSeleccionadoPasar = "Debe seleccionar un cultivo";
            validar = false;
        } else {
            this.msgProductoSeleccionadoPasar = "";
        }
        if (productoAgronegocio.getUnidad() == "") {
            this.msgUnidad = "Debe introducir la unidad";
            validar = false;
        } else {
            this.msgUnidad = "";
        }
        if (productoAgronegocio.getPrecio() == null) {
            this.msgPrecio = "Debe introducir el precio";
            validar = false;
        } else {
            this.msgPrecio = "";
        }
        if (productoAgronegocio.getCantidad() == null) {
            this.msgCantidad = "Debe introducir la cantidad";
            validar = false;
        } else {
            this.msgCantidad = "";
        }
        if (validar == true) {
            this.pasar(1);
        }

    }
    
    public void limpiarListaDetalle(){
        
        this.listaProductoAgronegocio.clear();  
        this.ProductoSeleccionado= new TProducto();
        this.total = new BigDecimal("0");
        this.comision = new BigDecimal("0");
        
    }

    public void pasar(Integer i) {

        this.productoAgronegocio.setNombre(this.ProductoSeleccionado.getNombre());
        this.productoAgronegocio.setTProducto(this.ProductoSeleccionado);
        this.listaProductoAgronegocio.add(this.productoAgronegocio);
        this.total = this.total.add(this.productoAgronegocio.getPrecio().multiply(new BigDecimal(this.productoAgronegocio.getCantidad())));
        this.comision = this.total.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));
        this.productoAgronegocio = new TProductoAgronegocio();
        this.mostrarTable = false;
        this.ProductoSeleccionado= new TProducto();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cultivo agregado al detalle correctamente", null);
        FacesContext.getCurrentInstance().addMessage("", mensaje);

    }

    public void quitar(int i) {

        this.total = this.total.subtract(this.listaProductoAgronegocio.get(i).getPrecio().multiply(new BigDecimal(this.listaProductoAgronegocio.get(i).getCantidad())));
        this.comision = this.total.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)));
        this.listaProductoAgronegocio.remove(i);
        this.productoAgronegocio = new TProductoAgronegocio();
        this.mostrarTable = false;

    }

    public void mostrarCalculos() {

        if (this.agronegocio.getFecha() == null) {
            this.msgFecha = "La fecha es requerida";
        } else {
            this.msgFecha = "";
        }
        if (this.agronegocio.getPlazo() == null || this.agronegocio.getPlazo() < 1) {
            this.msgPlazo = "El plazo es requerido";
        } else {
            this.msgPlazo = "";
        }
        if (this.total.doubleValue() == 0.0) {
            this.msgTotal = "El total debe ser mayor que cero";
        } else {
            this.msgTotal = "";
        }
        if (this.agronegocio.getFecha() != null && (this.agronegocio.getPlazo() != null && this.total.doubleValue() != 0.0)) {
            this.msgFechaEstipulada = "";
            this.msgDatosPago = "";
            Double inters;
            this.mostrarTable = true;
            this.monto = this.total;
            inters = (this.monto.doubleValue() * (this.politicaAgronegocio.getTasaInteresAgronegocio().doubleValue() / 100.0) * (this.agronegocio.getPlazo()) / 12.0);
            this.interes = (new BigDecimal(inters)).setScale(2, BigDecimal.ROUND_HALF_UP);
            this.totales = (this.monto.add(this.interes)).setScale(2, BigDecimal.ROUND_HALF_UP);;
            this.comisionCredito = (this.totales.multiply(this.politicaAgronegocio.getTasaComisionAgronegocio().divide(new BigDecimal(100)))).setScale(2, BigDecimal.ROUND_HALF_UP);
            
            int dias = 1;
            Calendar calendar = Calendar.getInstance();
            for (int i = 0; i < this.agronegocio.getPlazo(); i++) {
                calendar.setTime(this.agronegocio.getFecha()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.MONTH, dias);  // numero de días a añadir, o restar en caso de días<0
                dias = dias + 1;

            }
            this.fechaPagoAux=calendar.getTime();
           

        }
    }

    public void mostrarCompradores() {

        this.listaComprador.clear();
        List<TComprador> compradores = this.compradorBo.listComprador();
        for (TComprador comprador : compradores) {
            if (comprador.getEstadoComprador() == true) {
                this.listaComprador.add(comprador);
            }
        }

    }

    public void mostrarProductorIndividual() {

        this.listaProductoresIndividuales.clear();
        listaProductoresIndividuales = this.iproductorindividual.listProductorIndividualActivos();

    }

    public void cargarVistaAgronegocio() {

        this.totales = new BigDecimal("0");
        this.comision = new BigDecimal("0");
        this.totalProductor= new BigDecimal("0");
        this.pagoAgro.setComision(new BigDecimal("0"));
        this.pagoAgro.setInteres(new BigDecimal("0"));
        this.pagoAgro.setOtrosinteres(new BigDecimal("0"));
        this.pagoAgro.setDias(0);
        this.listaProductoAgronegocio = this.productoAgronegocioBo.listProductoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());
        this.listaProductoProductorCod = this.productoProductorBo.listTProductoProductorCod(this.listaProductoAgronegocio.get(0).getTProducto().getIdproducto());
        this.productorIndividualSeleccionadoVista = this.iproductorindividual.getProdcutorIndividual(this.listaProductoProductorCod.get(0).getTProductorIndividual().getIdProductorIndividual());
        this.politicaAgronegocio = this.configuracionAgronegocioBo.getConfiguracion(this.agronegocioSeleccionado.getTConfiguracionAgronegocio().getIdConfiguracionAgronegocio());
        if ("Contado".equals(this.agronegocioSeleccionado.getTipo())) {
            this.pagoAgro = this.pagoAgronegocioBo.getPagoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());
            this.monto = this.agronegocioSeleccionado.getTotal();
            this.interes = new BigDecimal("0");
            this.totales = this.monto.add(this.interes);
            this.totalDeuda=this.totales;
            this.totalDeudaFinal= this.totales;
            this.totalProductor = this.totales.subtract(this.pagoAgro.getComision());
        }
        if ("Crédito".equals(this.agronegocioSeleccionado.getTipo())) {
            Double inters;
            this.monto = this.agronegocioSeleccionado.getTotal();
            if ("Pendiente".equals(this.agronegocioSeleccionado.getEstado())) {
                inters = (this.monto.doubleValue() * (this.politicaAgronegocio.getTasaInteresAgronegocio().doubleValue() / 100.0) * (this.agronegocioSeleccionado.getPlazo()) / 12.0);
                this.interes = new BigDecimal(inters);
                this.totalDeuda= this.agronegocioSeleccionado.getTotal().add(this.agronegocioSeleccionado.getInteres());
                this.totalDeudaFinal= this.monto.add(this.interes);
                this.totalProductor = this.totalDeuda.subtract(this.agronegocioSeleccionado.getComision());
                
                
            } else if ("Finalizado".equals(this.agronegocioSeleccionado.getEstado())) {
                this.pagoAgro = this.pagoAgronegocioBo.getPagoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());
                inters = (this.monto.doubleValue() * (this.politicaAgronegocio.getTasaInteresAgronegocio().doubleValue() / 100.0) * (this.agronegocioSeleccionado.getPlazo()) / 12.0);
                this.interes = new BigDecimal(inters);
                this.totales = this.monto.add(this.pagoAgro.getInteres()).add(this.pagoAgro.getOtrosinteres());
              
                this.totalDeudaFinal= this.monto.add(this.interes);
                this.totalDeuda= this.totales;
               
                this.totalProductor = this.totalDeudaFinal.subtract(this.agronegocioSeleccionado.getComision());
              
                this.comisionFinal = this.pagoAgro.getComision();
       
                this.totalProductorFinal= this.totales.subtract(this.comisionFinal);
                
                
                
            }

        }

    }

    public String cambiarAsistencia(Boolean asistencia) {

        if (asistencia == true) {
            return "Si";
        } else {
            return "No";
        }

    }

    public void limpiarAgroVista() {

        this.pagoAgro = new TPagoAgronegocio();
        this.comision = new BigDecimal("0");
        this.totales = new BigDecimal("0");

    }

    public void cargarNuevoAgronegocio() {

        this.listaProducto = new ArrayList<TProducto>();
        this.productorIndividualSeleccionado = new TProductorIndividual();
        this.productorIndividualSeleccionado.setNombresProdcutorIndividual("");
        this.compradorSeleccionado = null;
        this.listaProductoAgronegocio = new ArrayList<TProductoAgronegocio>();
        this.mostrarTable = false;
        this.agronegocio = new TAgronegocio();
        this.total = new BigDecimal("0");
        this.comision = new BigDecimal("0");
        correlativo();

    }

    public void limpiarAgronegocio() {
        
        this.msgProductor = "";
        this.msgProductoSeleccionadoPasar = "";
        this.msgProductorSeleccionadoPasar = "";
        this.msgUnidad = "";
        this.msgPrecio = "";
        this.msgCantidad = "";
        this.msgComprador = "";
        this.msglistaProductos = "";
        this.msgPlazo = "";
        this.msgDatosPago = "";
        this.msgFecha = "";
        this.msgTotal = "";
        this.listaProducto = new ArrayList<TProducto>();
        this.productorIndividualSeleccionado = new TProductorIndividual();
        this.compradorSeleccionado = new TComprador();
        this.listaProductoAgronegocio = new ArrayList<TProductoAgronegocio>();
        this.ProductoSeleccionado = null;
        this.mostrarTable = false;
        this.agronegocio = new TAgronegocio();
        this.totales = new BigDecimal("0");
        this.total = new BigDecimal("0");
        this.pagoAgro = new TPagoAgronegocio();
        cargarNuevoAgronegocio();

    }

    public void validarFormularioPago() {

        this.estadoFormulario = true;
        if (this.tipoefectivo == false) {
            if ("".equals(this.pagoAgro.getCheque())) {
                this.msgCheque = "El numero de cheque es requerido";
                this.estadoFormulario = false;
            } else {
                this.msgCheque = "";
            }
        }

    }

    public void cargarAgronegocio() {

        if ("Pendiente".equals(this.agronegocioSeleccionado.getEstado())) {
            this.mostrarPago = true;
            mostrarCalculosPago();
            this.setShowData(false);
        } else {
            FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "El agronegocio ya esta finalizado", null);
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
        }

    }

    public void registtrarPago() {

        this.pagoAgro.setMonto(this.monto);
        this.pagoAgro.setTAgronegocio(this.agronegocioSeleccionado);
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        //Generando el numero de recibo
        Integer numero = this.ipagoBo.getCorrelativo();
        Integer numero2 = this.pagoAgronegocioBo.getCorrelativo();
        if (numero > numero2) {
            numero++;
            this.pagoAgro.setReferencia(numero);
        } else {
            numero2++;
            this.pagoAgro.setReferencia(numero2);
        }
        this.pagoAgro.setFecha(date);
        this.pagoAgro.setHora("" + hourFormat.format(date));
        this.pagoAgro.setInteres(this.interes.setScale(2, BigDecimal.ROUND_HALF_UP));
        this.pagoAgro.setOtrosinteres(this.mora.setScale(2, BigDecimal.ROUND_HALF_UP));
        this.pagoAgro.setDias(this.dias);
        this.pagoAgro.setComision(this.comision.setScale(2, BigDecimal.ROUND_HALF_UP));
        if (this.tipoefectivo == true) {
            this.pagoAgro.setForma("Efectivo");
            this.pagoAgro.setCheque("No aplica");
        } else {

            this.pagoAgro.setForma("Cheque");
        }
        this.pagoAgronegocioBo.create(pagoAgro);
        this.agronegocioSeleccionado.setEstado("Finalizado");
        this.agronegocioBo.update(this.agronegocioSeleccionado);
        this.estadoFormulario = false;
        this.mostrarPago = false;
        
         TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_agronegocio");
        auxBitacora.setAccionBitacora("Agregar pago de agronegocio");
        auxBitacora.setDatosBitacora("Código:" + this.agronegocioSeleccionado.getCodigo()
                + ", Tipo: " + this.agronegocioSeleccionado.getTipo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.agronegocioSeleccionado.getIdAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);       
        
        
        
        this.enableShowData();
        FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago realizado correctamente", null);
        FacesContext.getCurrentInstance().addMessage(null, mensaje);

    }

    public void mostrarCalculosPago() {

        this.monto = this.agronegocioSeleccionado.getTotal();
        this.politicaAgronegocioSeleccionado = this.configuracionAgronegocioBo.getConfiguracion(this.agronegocioSeleccionado.getTConfiguracionAgronegocio().getIdConfiguracionAgronegocio());
        Double inters;
        inters = (this.monto.doubleValue() * (this.politicaAgronegocioSeleccionado.getTasaInteresAgronegocio().doubleValue() / 100.0) * (this.agronegocioSeleccionado.getPlazo()) / 12.0);
        this.interes = new BigDecimal(inters);
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
        this.totales = this.monto.add(this.interes.setScale(2, BigDecimal.ROUND_HALF_UP));
        this.mora = BigDecimal.ZERO;
        if (this.fechaDePago.compareTo(this.agronegocioSeleccionado.getFechapago()) > 0) {
            this.dias = (int) ((this.fechaDePago.getTime() - this.agronegocioSeleccionado.getFechapago().getTime()) / 86400000);
        this.mora = ((this.totales.multiply(this.politicaAgronegocioSeleccionado.getTasaMoraAgronegocio().divide(new BigDecimal(100), 7, RoundingMode.HALF_UP).divide(new BigDecimal(30), 7, RoundingMode.HALF_UP).multiply(new BigDecimal(this.dias)))));
       
        } else {
            this.dias = 0;
        }
        
      
        
        
        this.totales = this.monto.add(this.interes.add(this.mora.setScale(2, BigDecimal.ROUND_HALF_UP)));
        this.comision = this.totales.multiply(this.politicaAgronegocioSeleccionado.getTasaComisionAgronegocio().divide(new BigDecimal(100)));

    }
    
    public void verReporteAgronegocio() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_agronegocio", this.agronegocioSeleccionado.getIdAgronegocio());
        parametros.put("nombreProductor", this.productorIndividualSeleccionadoVista.getNombresProdcutorIndividual());
        parametros.put("apellidoProductor", this.productorIndividualSeleccionadoVista.getApellidosProductorIndividual());
       
        parametros.put("duiProductor", this.productorIndividualSeleccionadoVista.getDuiProductorIndividual());
        parametros.put("nitProductor", this.productorIndividualSeleccionadoVista.getNitProductorIndividual());
        parametros.put("telefonoProductor", this.productorIndividualSeleccionadoVista.getTelefonoProductorIndividual());
        parametros.put("direccionProductor", this.productorIndividualSeleccionadoVista.getDireccionProductorIndividual());
        parametros.put("celularTelefono", this.productorIndividualSeleccionadoVista.getMovilProductorIndividual());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));
        if (this.agronegocioSeleccionado.getEstado().equals("Pendiente")) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividual.jasper"));
        }
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
        auxBitacora.setTableBitacora("t_agronegocio");
        auxBitacora.setAccionBitacora("Generar reporte de agronegocio");
        auxBitacora.setDatosBitacora("Código:" + this.agronegocioSeleccionado.getCodigo()
                + ", Tipo: " + this.agronegocioSeleccionado.getTipo());
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.agronegocioSeleccionado.getIdAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);       
               
    }

    public void verReporteAgronegocioPDF() throws SQLException, JRException, IOException {

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_agronegocio", this.agronegocioSeleccionado.getIdAgronegocio());
        parametros.put("nombreProductor", this.productorIndividualSeleccionadoVista.getNombresProdcutorIndividual());
        parametros.put("apellidoProductor", this.productorIndividualSeleccionadoVista.getApellidosProductorIndividual());
        parametros.put("duiProductor", this.productorIndividualSeleccionadoVista.getDuiProductorIndividual());
        parametros.put("nitProductor", this.productorIndividualSeleccionadoVista.getNitProductorIndividual());
        parametros.put("telefonoProductor", this.productorIndividualSeleccionadoVista.getTelefonoProductorIndividual());
        parametros.put("direccionProductor", this.productorIndividualSeleccionadoVista.getDireccionProductorIndividual());
        parametros.put("celularTelefono", this.productorIndividualSeleccionadoVista.getMovilProductorIndividual());
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));
        if (this.agronegocioSeleccionado.getEstado().equals("Pendiente")) {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividualPendiente.jasper"));
        } else {
            jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/agronegocio/agronegocioIndividual.jasper"));
        }
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, this.getConn());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Agronegocio.pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
        stream.flush();
        stream.close();
        FacesContext.getCurrentInstance().responseComplete();
        
        TBitacora auxBitacora = new TBitacora();
        auxBitacora.setTableBitacora("t_agronegocio");
        auxBitacora.setAccionBitacora("Descargar reporte de agronegocio");
        auxBitacora.setDatosBitacora("Código:" + this.agronegocioSeleccionado.getCodigo()
                + ", Tipo: " + this.agronegocioSeleccionado.getTipo()
                );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.agronegocioSeleccionado.getIdAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);
        

    }   
    
    public void reciboAgronegocio() throws SQLException, JRException, IOException {
        
        
        this.pagoAgro = this.pagoAgronegocioBo.getPagoAgronegocio(this.agronegocioSeleccionado.getIdAgronegocio());

        this.getConexion();
        Map<String, Object> parametros = new HashMap();
        parametros.put("id_pago", this.pagoAgro.getIdPagoAgronegocio());
         File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/Reportes/finanza/reciboAgronegocio.jasper"));
        
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
        auxBitacora.setTableBitacora("t_pago");
        auxBitacora.setAccionBitacora("Generar recibo de pago agronegocio");
        auxBitacora.setDatosBitacora("Numero de recibo:" + this.pagoAgro.getReferencia()
               );
        auxBitacora.setHoraBitacora(new Date());
        auxBitacora.setFechaBitacora(new Date());
        auxBitacora.setIdTableBitacora(this.pagoAgro.getIdPagoAgronegocio());
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");
        auxBitacora.setTUsuario(loginBean.getUsuarioActivo());
        this.bitacoraBo.create(auxBitacora);       
               
    }
    
    
}
