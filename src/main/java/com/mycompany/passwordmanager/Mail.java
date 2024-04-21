package com.mycompany.passwordmanager;

import java.util.Properties;
import java.util.Scanner;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

    public static void sendEmail(String password) {

        String to = "vito.g5412@gmail.com";
        String subject = "Mio Password Manager";
        // Imposta le proprietà per la connessione al server SMTP di Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Autenticazione
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("vito.g5412@gmail.com", "sntzirzpphmhqyfx");
            }
        });

        try {
            // Crea un oggetto MimeMessage
            Message message = new MimeMessage(session);
            // Imposta il mittente
            message.setFrom(new InternetAddress("vito.g5412@gmail.com"));
            // Imposta il destinatario
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            // Imposta l'oggetto dell'email
            message.setSubject(subject);
            // Imposta il contenuto dell'email
            message.setText(password);

            // Invia l'email
            Transport.send(message);

            System.out.println("Email inviata con successo!");
        } catch (MessagingException e) {
            System.err.println("Si è verificato un errore durante l'invio dell'email:");
            e.printStackTrace();
        }
    }
}
