package fr.esgi.gameforgeapi.bootstrap.config.client;

import fr.esgi.gameforgeapi.client.services.EmailSenderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
@ComponentScan("fr.esgi")
public class ClientConfiguration {

}
