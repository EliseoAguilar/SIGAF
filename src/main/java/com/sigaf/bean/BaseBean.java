/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.Ibo.IBitacoraBo;
import com.sigaf.pojo.TBitacora;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Eliseo
 */
@ManagedBean
@SessionScoped
public class BaseBean extends Actividad {

    /**
     * Creates a new instance of BaseBean
     */
    private IBitacoraBo bitacoraBo;

    private String ruta;

    private File baseSeleccionada;

    private List<File> listBase;

    public IBitacoraBo getBitacoraBo() {
        return bitacoraBo;
    }

    public void setBitacoraBo(IBitacoraBo bitacoraBo) {
        this.bitacoraBo = bitacoraBo;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public List<File> getListBase() {

        this.listBase.clear();

        File folder = new File(ruta);

        File[] ficheros = folder.listFiles();

        for (File fichero : ficheros) {
            listBase.add(fichero);

        }

        return listBase;
    }

    public void setListBase(List<File> listBase) {
        this.listBase = listBase;
    }

    public static String readableFileSize(long size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "kB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

    public File getBaseSeleccionada() {
        return baseSeleccionada;
    }

    public void setBaseSeleccionada(File baseSeleccionada) {
            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("---");
            auxBitacora.setAccionBitacora("Ver información del respaldo");
            auxBitacora.setDatosBitacora("Ruta: " + baseSeleccionada.getAbsolutePath());
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);
            
        this.baseSeleccionada = baseSeleccionada;
    }

    public void init() {
        super.enableShowData();
        this.ruta = "C:\\backupSigaf";
        this.listBase = new ArrayList<>();
    }

    public void hacerBackup() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        Date date = new Date();
        String path = "C:\\backupSigaf\\bd_sigaf_" + dateFormat.format(date) + "_.sql";

        try {
            Runtime r = Runtime.getRuntime();
            //PostgreSQL variables            
            
            Process p;
            ProcessBuilder pb;
            /**
             * Ejecucion del proceso de respaldo
             */
            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_dump", "-v", "-h", this.getHost(), "-Fc", "-f", path, "-U", this.getUser(), this.getDbase());
            pb.environment().put("PGPASSWORD",this.getPassword());
            pb.redirectErrorStream(true);
            p = pb.start();
            /**
             * Mensajes de salida
             */
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;

            while ((line = br.readLine()) != null) {

                System.out.println(line);
            }

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("---");
            auxBitacora.setAccionBitacora("Crear respaldo");
            auxBitacora.setDatosBitacora("Ruta: " + path);
            auxBitacora.setHoraBitacora(date);
            auxBitacora.setFechaBitacora(date);

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Respaldo fue creado correctamente.", ""));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El respaldo no pudo ser creado correctamente.",
                    ""));
//            System.out.println(e.getMessage());
        }

    }

    public String fechaCreacion(String nombre) {
        String fecha[] = nombre.split("_");

        if (fecha.length > 1) {
            String fe[] = fecha[2].split("-");

            return fe[0] + "/" + fe[1] + "/" + fe[2];

        } else {
            return "";
        }

    }

    public String horaCreacion(String nombre) {

        String fecha[] = nombre.split("_");

        if (fecha.length > 1) {
            String fe[] = fecha[3].split("-");

            return fe[0] + ":" + fe[1] + ":" + fe[2];
        } else {
            return "";
        }

    }

    public void restaurarBackup() {

        String path = baseSeleccionada.getAbsolutePath();

        try {
            Runtime r = Runtime.getRuntime();
                   
              //PostgreSQL variables            
           
       
            Process p;
            ProcessBuilder pb;

            /**
             * Ejecucion del proceso de respaldo
             */
            //Realiza la construccion del comando
            pb = new ProcessBuilder("C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_restore", "-h", this.getHost(), "-p", this.getPuerto(), "-U", this.getUser(), "-d", this.getDbase(), "-c", "-v", path);
            pb.environment().put("PGPASSWORD", this.getPassword());
            pb.redirectErrorStream(true);
            p = pb.start();

            /**
             * Mensajes de salida
             */
            InputStream is = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            TBitacora auxBitacora = new TBitacora();
            auxBitacora.setTableBitacora("---");
            auxBitacora.setAccionBitacora("Restaurar respaldo");
            auxBitacora.setDatosBitacora("Ruta: " + path);
            auxBitacora.setHoraBitacora(new Date());
            auxBitacora.setFechaBitacora(new Date());

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            // Get the loginBean from session attribute
            LoginBean loginBean = (LoginBean) request.getSession().getAttribute("loginBean");

            auxBitacora.setTUsuario(loginBean.getUsuarioActivo());

            bitacoraBo.create(auxBitacora);

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Respaldo restaurado correctamente.", ""));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El respaldo no pudo ser restaurado.",
                    ""));

        }

    }

}
