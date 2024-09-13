package Mindhub.RaspCash.servicios.implementacionesServicios;

import Mindhub.RaspCash.models.Usuario;
import Mindhub.RaspCash.respositories.UsuarioRepositorio;
import Mindhub.RaspCash.servicios.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class EmailSenderServiceImplementation implements EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    //Metodo para enviar un mail
    public void sendSimpleEmailTo(String ReceptorEmail, String MensajeAenviar, String tema) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("RaspCashCrypto@gmail.com");
        message.setTo(ReceptorEmail);
        message.setText(MensajeAenviar);
        message.setSubject(tema);


        mailSender.send(message);
        System.out.println("Email enviado ...");

        Usuario usuario=usuarioRepositorio.findByEmail(ReceptorEmail);
    }
}
