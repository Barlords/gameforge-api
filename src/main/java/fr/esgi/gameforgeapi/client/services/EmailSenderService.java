package fr.esgi.gameforgeapi.client.services;

import fr.esgi.gameforgeapi.domain.functional.models.Game;
import fr.esgi.gameforgeapi.domain.functional.models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmailSenderService {

    @Autowired
    final private JavaMailSender emailSender;

    @Value("${spring.mail.username}") private String sender;

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);

            System.out.println("message envoyé");
        }
        catch (Exception e) {
            System.out.println("error lors de l'envoie du mail");
        }
    }

    public void sendAccountValidationMessage(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String senderName = "Gameforge";
        String subject = "Validation de votre compte";
        String content = "Bonjour [[name]],<br>"
                + "<br>"
                + "Nous vous envoyons ce mail suite à la création de votre compte sur notre plateforme Gameforge.<br>"
                + "Il vous reste une dernière étape avant de pouvoir profiter de nos jeux: valider votre adresse mail!!!<br>"
                + "<br>"
                + "Cliquez sur le lien ci-dessous pour vérifier votre compte :<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFIER MON COMPTE</a></h3>"
                + "Merci,<br>"
                + "L'équipe Gameforge.";

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(sender, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(subject);

        String verifyUrl = siteURL + "/users/verify?code=" + user.getVerificationCode();
        content = content.replace("[[name]]", user.getPseudo());
        content = content.replace("[[URL]]", verifyUrl);

        helper.setText(content, true);

        emailSender.send(message);

    }

    public void sendNewGameNewsletterMessage(List<User> users, Game game, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String senderName = "Gameforge";
        String subject = "Nouveau jeu disponible";
        String content = "Bonjour [[userName]],<br>"
                + "<br>"
                + "Un nouveau jeu est arrivé sur notre plateforme, venez vite l'essayer.<br>"
                + "<br>"
                + "<h3>[[gameName]]</h3><br>"
                + "<h5>[[description]]<h5><br>"
                + "<br>"
                + "<br>"
                + "Cliquez sur le lien ci-dessous pour vous connecter :<br>"
                + "<h4><a href=\"[[URL]]\" target=\"_self\">Se connecter</a></h4>"
                + "On espère vous revoir bientôt.<br>"
                + "L'équipe Gameforge.";

        for (User user : users) {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setFrom(sender, senderName);
            helper.setTo(user.getEmail());
            helper.setSubject(subject);

            content = content.replace("[[userName]]", user.getPseudo());
            content = content.replace("[[gameName]]", game.getName());
            content = content.replace("[[description]]", game.getDescription());
            content = content.replace("[[URL]]", siteURL);

            helper.setText(content, true);

            emailSender.send(message);
        }
    }



    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(sender);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text);

        FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
        helper.addAttachment("Invoice", file);

        emailSender.send(message);
    }

}
