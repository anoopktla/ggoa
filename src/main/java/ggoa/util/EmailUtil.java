package ggoa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
    public class EmailUtil {
        private static final String CONTENT_TYPE = "text/HTML; charset=UTF-8";
        private static final Logger LOGGER = LoggerFactory.getLogger(EmailUtil.class);
        private static final String PDF_FILE_NAME = "adv.pdf";



        @Value("${email.username}")
        private String emailUserName;

        @Value("${email.password}")
        private String emailPassword;

        @Value("${mail.smtp.host}")
        private String smtpHost;

        @Value("${mail.smtp.auth}")
        private boolean smtpAuthRequired;

        @Value("${mail.smtp.starttls.enable}")
        private boolean starttlsEnabled;

        @Value("${mail.smtp.port}")
        private String smtpPort;

        private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
        private static Pattern pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);
        private Matcher matcher;


        public boolean validateEmail(String email) {
            boolean isValid = false;

            try {
                if(!StringUtils.isEmpty(email)){
                    matcher = pattern.matcher(email);
                    isValid = matcher.matches();
                }
            } catch (Exception e) {
                LOGGER.error("error while validating the given email address {}", email , e);

            }
            return isValid;
        }


        public boolean sendEmail() {
     /*       String toEmail = "anoopktla@gmail.com";

            if (validateEmail(toEmail)) {

                try {

                    //todo phase 1 returning last adv to send email
                    ByteArrayDataSource byteArrayDataSource = new ByteArrayDataSource(null, MediaType.APPLICATION_PDF_VALUE);
                    Message message = new MimeMessage(getSession());
                    Multipart multipart = new MimeMultipart();

                    MimeBodyPart messageBodyPart = new MimeBodyPart();

                        messageBodyPart.setContent("Auftragsdatenverarbeitung ", CONTENT_TYPE);

                    MimeBodyPart messageAttachmentPart = new MimeBodyPart();

                    messageAttachmentPart.setDataHandler(new DataHandler(byteArrayDataSource));
                    messageAttachmentPart.setFileName(PDF_FILE_NAME);
                    multipart.addBodyPart(messageBodyPart);
                    multipart.addBodyPart(messageAttachmentPart);

                    message.setContent(multipart);


                    message.setFrom(new InternetAddress(emailUserName, "GGOA"));
                    message.setReplyTo(InternetAddress.parse(emailUserName, false));
                    message.setSubject("monthly report");
                    message.setSentDate(new Date());
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));




                    Transport.send(message);
                    LOGGER.info("successfully  sent email to {}",toEmail);*/
                    return true;


                }


        private Session getSession() {
            Properties props = new Properties();
            props.put("mail.smtp.auth", smtpAuthRequired);
            props.put("mail.smtp.starttls.enable", starttlsEnabled);
            props.put("mail.smtp.host", smtpHost);
            props.put("mail.smtp.port", smtpPort);

            return Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailUserName, emailPassword);
                }
            });
        }

    }


