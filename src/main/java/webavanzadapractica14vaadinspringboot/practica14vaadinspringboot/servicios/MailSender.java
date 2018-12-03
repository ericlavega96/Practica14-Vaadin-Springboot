package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;

import java.io.IOException;

@Component
public class MailSender {

    @Autowired
    ServicioGerente servicioGerente;

    @Autowired
    ServicioEvento servicioEvento;

    public void sendNotificacionEvento(Evento evento) throws IOException{
        //Llave: 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
        if(!servicioGerente.isEmpty()) {
            for (Gerente gerente : servicioGerente.listaGerentes()) {
                Email from = new Email("test@example.com");
                String subject = "[" + evento.getIdEvento() + " - " + evento.getNombre() + "] Notificación de evento";
                Email to = new Email(gerente.getCorreo());
                Content content = new Content("text/plain", "Este mensaje está dirigido a " + gerente.getNombres() +
                        " " + gerente.getApellidos() + ". El evento " + evento.getNombre() + " esta a punto de empezar.");
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
                    System.out.println("        - El correo ha sigo enviado correctamente para el gerente "+ gerente.getNombres() +
                            " " + gerente.getApellidos() +".");
                } catch (IOException ex) {
                    throw ex;
                }
            }
            evento.setEnviado(true);
            servicioEvento.modificar(evento);
        }
    }
}
