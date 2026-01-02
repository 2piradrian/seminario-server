package com.group3.users.config.helpers;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailHelper {

    @Value("${application.host.url}")
    private String baseUrl;

    @Getter
    @Value("${application.client.url}")
    private String clientUrl;

    @Value("${application.client.recover}")
    private String recoverPath;

    public String verifyEmailHTML(String token){

        String link = baseUrl + "/api/auth/verify-email/" + token;

        return buildEmailTemplate("Verifica tu correo",
            "Haz clic en el botón de abajo para verificar tu cuenta.",
            link,
            "Verificar correo");
    }

    public String recoverPasswordHTML(String token){

        String link = clientUrl + recoverPath + token;

        return buildEmailTemplate("Restablecer contraseña",
            "Hemos recibido una solicitud para cambiar tu contraseña. Haz clic abajo para continuar.",
            link,
            "Cambiar contraseña");
    }

    private String buildEmailTemplate(String title, String message, String link, String buttonText) {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <style>
                /* Contenedor: Fondo blanco para limpieza, Borde con color --border */
                .container { 
                    font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif; 
                    max-width: 600px; 
                    margin: 0 auto; 
                    padding: 20px; 
                    border: 1px solid #8e8c8c43; 
                    border-radius: 8px; 
                    background-color: #ffffff; 
                }
                
                .header { text-align: center; margin-bottom: 20px; }
                .logo { max-width: 150px; height: auto; }
                
                /* Contenido: Color --on-surface (#191c20) */
                .content { 
                    color: #191c20; 
                    line-height: 1.6; 
                    text-align: center; 
                }
                
                /* Botón: Fondo --primary (#E3550B), Texto --on-primary (#ffffff) */
                .button { 
                    display: inline-block; 
                    padding: 12px 24px; 
                    background-color: #E3550B; 
                    color: #ffffff !important; 
                    text-decoration: none; 
                    border-radius: 5px; 
                    font-weight: bold; 
                    margin-top: 20px; 
                }
                
                /* Footer: Color --on-background (#63607A) */
                .footer { 
                    margin-top: 30px; 
                    font-size: 12px; 
                    color: #63607A; 
                    text-align: center; 
                }
            </style>
        </head>
        <body style="background-color: #FBF8FF; padding: 20px; margin: 0;">
            <div class="container">
                <div class="header">
                    <img src="cid:fortuneLogo" alt="Logo de Fortune" class="logo">
                </div>
                <div class="content">
                    <h2 style="color: #E3550B;">%s</h2>
                    <p>%s</p>
                    <a href="%s" class="button">%s</a>
                </div>
                <div class="footer">
                    <p>Si no solicitaste este correo, por favor ignóralo.</p>
                    <p>&copy; 2026 Fortune. Todos los derechos reservados.</p>
                </div>
            </div>
        </body>
        </html>
        """.formatted(title, message, link, buttonText);
    }

}
