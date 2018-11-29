package webavanzadapractica14vaadinspringboot.practica14vaadinspringboot.servicios;

import com.sendgrid.*;
import java.io.IOException;


public class MailSender {
    public void sendNotificacionEvento() throws IOException{
        //Llave: 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
        Email from = new Email("test@example.com");
        String subject = "Sending with SendGrid is Fun";
        Email to = new Email("test@example.com");
        Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("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"));
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
    }
}
