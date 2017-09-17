/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author yonat
 */
public class Actividad {

    private String user = "user_sigaf";
    private String host = "localhost";
    private String dbase = "bd_sigaf";
    private String password = "adminsigaf";
    private String puerto = "5432";

    
    
    private String btn
            = "    display: inline-block;"
            + "    padding: 2px 2px;"
            + "    margin-bottom: 0;"
            + "    font-size: 14px;"
            + "    font-weight: normal;"
            + "    line-height: 1.428571429;"
            + "    text-align: center;"
            + "    white-space: nowrap;"
            + "    vertical-align: middle;"
            + "    cursor: pointer;"
            + "    background-image: none;"
            + "    border: 1px solid transparent;"
            + "    border-radius: 4px;"
            + "    -webkit-user-select: none;"
            + "    -moz-user-select: none;"
            + "    -ms-user-select: none;"
            + "    -o-user-select: none;"
            + "    user-select: none;";

    private Boolean showCreate;
    private Boolean showCreatePersona;
    private Boolean showData;
    private Boolean showUpdate;
    private Boolean showView;
    private Boolean showViewPersona;
    private Boolean showCreateCooperativa;
    private Boolean showCreateAgropecuario;
    private Boolean showCreateComercio;
    private Boolean showCreateLisiado;

    private String urlDatabase;

    private Connection conn;

    public String getUrlDatabase() {
        return urlDatabase;
    }

    public void setUrlDatabase(String urlDatabase) {
        this.urlDatabase = urlDatabase;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    public Boolean getShowCreateComercio() {
        return showCreateComercio;
    }

    public void setShowCreateComercio(Boolean showCreateComercio) {
        this.showCreateComercio = showCreateComercio;
    }

    public Boolean getShowCreatePersona() {
        return showCreatePersona;
    }

    public void setShowCreatePersona(Boolean showCreatePersona) {
        this.showCreatePersona = showCreatePersona;
    }

    public Boolean getShowViewPersona() {
        return showViewPersona;
    }

    public void setShowViewPersona(Boolean showViewPersona) {
        this.showViewPersona = showViewPersona;
    }

    public Boolean getShowCreateLisiado() {
        return showCreateLisiado;
    }

    public void setShowCreateLisiado(Boolean showCreateLisiado) {
        this.showCreateLisiado = showCreateLisiado;
    }

    public Boolean getShowCreateAgropecuario() {
        return showCreateAgropecuario;
    }

    public void setShowCreateAgropecuario(Boolean showCreateAgropecuario) {
        this.showCreateAgropecuario = showCreateAgropecuario;
    }

    public Boolean getShowCreateCooperativa() {
        return showCreateCooperativa;
    }

    public void setShowCreateCooperativa(Boolean showCreateCooperativa) {
        this.showCreateCooperativa = showCreateCooperativa;
    }

    public Boolean getShowView() {
        return showView;
    }

    public void setShowView(Boolean showView) {
        this.showView = showView;
    }
    private String btnCrear = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #5cb85c;"
            + "border-color: #4cae4c;";

    private String btnLimpiar = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #428bca;"
            + "border-color: #357ebd;";

    private String btnCancelar = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #d9534f;"
            + "border-color: #d43f3a;";

    private String btnDarDeBaja
            = "background-color: #d9534f;"
            + "border-color: #d43f3a;";

    private String btnDarDeAlta
            = "background-color: #5cb85c;"
            + "border-color: #4cae4c;";

    private String btnModificar = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #428bca;"
            + "border-color: #357ebd;";

    private String btnVer = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #5bc0de;"
            + "border-color: #46b8da;";

    private String btnNuevo = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #5cb85c;"
            + "border-color: #4cae4c;";

    private String btnAprobado = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: #27AE60;"
            + "border-color: #4cae4c;";

    private String btnNoAprobado = btn + "background-image:none;"
            + "color: #fff;"
            + "background-color: ;"
            + "border-color: #d9534f;";

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String getBtnAprobado() {
        return btnAprobado;
    }

    public void setBtnAprobado(String btnAprobado) {
        this.btnAprobado = btnAprobado;
    }

    public String getBtnNuevo() {
        return btnNuevo;
    }

    public void setBtnNuevo(String btnNuevo) {
        this.btnNuevo = btnNuevo;
    }

    public String getBtnDarDeBaja() {
        return btnDarDeBaja;
    }

    public void setBtnDarDeBaja(String btnDarDeBaja) {
        this.btnDarDeBaja = btnDarDeBaja;
    }

    public String getBtnDarDeAlta() {
        return btnDarDeAlta;
    }

    public void setBtnDarDeAlta(String btnDarDeAlta) {
        this.btnDarDeAlta = btnDarDeAlta;
    }

    public String getBtnModificar() {
        return btnModificar;
    }

    public void setBtnModificar(String btnModificar) {
        this.btnModificar = btnModificar;
    }

    public String getBtnVer() {
        return btnVer;
    }

    public void setBtnVer(String btnVer) {
        this.btnVer = btnVer;
    }

    public String getBtnLimpiar() {
        return btnLimpiar;
    }

    public void setBtnLimpiar(String btnLimpiar) {
        this.btnLimpiar = btnLimpiar;
    }

    public String getBtnCancelar() {
        return btnCancelar;
    }

    public void setBtnCancelar(String btnCancelar) {
        this.btnCancelar = btnCancelar;
    }

    public String getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(String btnCrear) {
        this.btnCrear = btnCrear;
    }

    public Boolean getShowUpdate() {
        return showUpdate;
    }

    public void setShowUpdate(Boolean showUpdate) {
        this.showUpdate = showUpdate;
    }

    public Boolean getShowCreate() {
        return showCreate;
    }

    public void setShowCreate(Boolean showCreate) {
        this.showCreate = showCreate;
    }

    public Boolean getShowData() {
        return showData;
    }

    public void setShowData(Boolean showData) {
        this.showData = showData;
    }

    public void enableShowCreate() {
        this.showCreate = true;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showCreateComercio = false;
    }

    public void enableShowCreateCooperativa() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = true;
        this.showCreateLisiado = false;
        this.showCreateComercio = false;
    }

    public void enableShowCreateAgropecuario() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = true;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showCreateComercio = false;
    }

    public void enableShowCreateLisiados() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = true;
        this.showCreateComercio = false;
    }

    public void enableShowCreateComercio() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showCreateComercio = true;
    }

    public void enableShowData() {
        this.showCreate = false;
        this.showData = true;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showViewPersona = false;
        this.showCreatePersona = false;
        this.showCreateComercio = false;
    }

    public void enableShowUpdate() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = true;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showCreateComercio = false;
    }

    public void enableShowView() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = true;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showCreateComercio = false;
    }

    public void enableShowPersona() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showCreatePersona = true;
        this.showViewPersona = false;
        this.showCreateComercio = false;
    }

    public void enableShowViewPersona() {
        this.showCreate = false;
        this.showData = false;
        this.showUpdate = false;
        this.showView = false;
        this.showCreateAgropecuario = false;
        this.showCreateCooperativa = false;
        this.showCreateLisiado = false;
        this.showViewPersona = true;
        this.showCreatePersona = false;
        this.showCreateComercio = false;
    }

    public void getConexion() throws SQLException {

        this.urlDatabase = "jdbc:postgresql://"+host+":"+puerto+"/"+dbase;
        this.conn = DriverManager.getConnection(urlDatabase,user, password);

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDbase() {
        return dbase;
    }

    public void setDbase(String dbase) {
        this.dbase = dbase;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }
    
    
    

}
