package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;

import java.io.IOException;


public class MailSender {

    @Autowired
    ServicioGerente servicioGerente;

    public void sendNotificacionEvento(Evento evento) throws IOException{
        //Llave: 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
        //for(Gerente gerente : servicioGerente.listaGerentes()) {
            Email from = new Email("test@example.com");
            String subject = "["+ evento.getIdEvento() + " - " +evento.getNombre()+"] Notificación de evento";
            //Email to = new Email(gerente.getCorreo());
            Email to = new Email("adonisveloz@hotmail.com");
            Content content = new Content("text/plain", "El evento " + evento.getNombre() + " esta a punto de empezar.");
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid("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");
            Request request = new Request();
            try {
                request.setMethod(Method.POST);
                request.setEndpoint("mail/send");
                request.setBody(mail.build());
                Response response = sg.api(request);
                System.out.println(response.getStatusCode());
                System.out.println(response.getBody());
                System.out.println(response.getHeaders());
            } catch (IOException ex) {
                throw ex;
            }
        //}
    }
}
