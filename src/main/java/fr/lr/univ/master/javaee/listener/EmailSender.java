package fr.lr.univ.master.javaee.listener;

import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.event.DeleteAccountEvent;
import fr.lr.univ.master.javaee.event.NewFollowerEvent;
import fr.lr.univ.master.javaee.event.RegistrationEvent;
import java.util.Date;
import java.util.Properties;
import javax.enterprise.event.Observes;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author mathieuancelin
 */
public class EmailSender {

    private static final String SMTP_HOST = "smtp.free.fr";
    private static final String USER = "twitter";
    private static final String PASSWORD = "twitter";

    public void newAccount(@Observes RegistrationEvent event) throws MessagingException {
        //sendEMail(event.getUser(), "Bienvenue sur twitter.");
    }

    public void deleteAccount(@Observes DeleteAccountEvent event) throws MessagingException {
        //sendEMail(event.getUser(), "Au revoir.");
    }

    public void newFollower(@Observes NewFollowerEvent event) throws MessagingException {
        //sendEMail(event.getUser(), "Nouveau follower");
    }

    private void sendEMail(TwitterUser user, String message) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.auth", "true");
        Session session = Session.getInstance(properties, null);
        javax.mail.Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("no-reply@serli.com"));
        String email = user.getEmail();
        msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(email, false));
        msg.setSubject("Twitter nofification");
        msg.setText(message);
        msg.setSentDate(new Date());
        Transport transport = session.getTransport("smtp");
        try {
            transport.connect(SMTP_HOST, USER, PASSWORD);
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (MessagingException me) {
            System.out.println(me);
        } finally {
            try {
                transport.close();
            } catch (MessagingException e) {
                System.out.println("Exception occured while closing javax.mail.Transport: " + e.getMessage());
            }
        }
    }
}
