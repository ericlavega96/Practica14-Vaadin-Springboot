package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.jobs;

import org.springframework.beans.factory.annotation.Autowired;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Evento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.entidades.Gerente;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.MailSender;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioEvento;
import webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios.ServicioGerente;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class EnviarMensajes {
    @Autowired
    ServicioEvento servicioEvento;

    @Autowired
    MailSender mailSender;

    private static final Logger log = LoggerFactory.getLogger(EnviarMensajes.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = (1000 * 60 * 5))
    public void enviarMensaje() throws IOException {
        for(Evento evento : servicioEvento.listarEventos()) {
            if(!evento.isEnviado() && !evento.getFecha().after(new Date()))
                mailSender.sendNotificacionEvento(evento);
        }
        //log.info("The time is now {}", dateFormat.format(new Date()));
    }
}
