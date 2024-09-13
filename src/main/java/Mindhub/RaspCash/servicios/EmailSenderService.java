package Mindhub.RaspCash.servicios;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface EmailSenderService {
    public void sendSimpleEmailTo(String ReceptorEmail, String  MensajeAenviar, String tema);
}
