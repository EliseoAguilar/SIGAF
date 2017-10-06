/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sigaf.bean;

import com.sigaf.pojo.TUsuario;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author Eliseo
 */
public class MailReset {

   private static final String HOSTNAME = "smtp.gmail.com";
    private static final String USERNAME = "sistemapass@gmail.com";
    private static final String PASSWORD = "P@ssw0rdZOR";
    private static final String EMAILORIGEM = "sistemapass@gmail.com";

    public static HtmlEmail conectaEmail() throws EmailException {
        HtmlEmail email = new HtmlEmail();
        email.setHostName(HOSTNAME);
        email.setSmtpPort(587);
        email.setSSL(true);
        email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
        email.setTLS(true);
        email.setFrom(EMAILORIGEM);
        return email;
    }

    public static void enviaEmail(TUsuario usuario, String URI) throws EmailException, MalformedURLException {


        HtmlEmail email;
        email = conectaEmail();
        email.setSubject("Restablece tu contraseña");

//        URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//        String cid2 = email.embed(url, "logo ??.gif");

        email.setHtmlMsg("<html>\n"
                + "     <head>\n"
                + "        <title>Restablece tu contraseña</title>\n"
                + "     </head>\n"
                + "     <body>\n"
                + "       <p>Hemos recibido una petición para restablecer la contraseña de tu cuenta.</p>\n"
                + "       <p>Si hiciste esta petición, haz clic en el siguiente enlace, si no hiciste esta petición puedes ignorar este correo.</p>\n"
                + "       <p>\n"
                + "         <strong>Enlace para restablecer tu contraseña</strong><br>\n"
                + "         <a href='"+URI+"'> Restablecer contraseña </a>\n"
                + "       </p>\n"
                + "     </body>\n"
                + "\n"
                + "     </html>");

        email.setTextMsg("Your email client does not support HTML messages");
        email.addTo(usuario.getTEmpleado().getCorreoEmpleado());
        email.send();
    }

}
