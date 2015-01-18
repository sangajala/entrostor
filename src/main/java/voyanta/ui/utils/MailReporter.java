package voyanta.ui.utils;

//import com.venkyold.org.abstractTest;
//
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import javax.activation.FileDataSource;
//import javax.mail.*;
//import javax.mail.internet.*;

/**
 * Created with IntelliJ IDEA.
 * User: GBY9AMA9
 * Date: 20/04/13
 * Time: 18:46
 * To change this template use File | Settings | File Templates.
 */
public class MailReporter {
//        public static Properties emaildata;
//        static String senderEmailID =  abstractTest.PROPERTIES.getProperty("emailid");
//        static String senderPassword = abstractTest.PROPERTIES.getProperty("password");;
//        static String emailSMTPserver = "smtp.gmail.com";
//        static String emailServerPort = "465";
//
//        String receiverEmailID = null;
//        String emailSubject = null;
//        String emailBody = null;
//        Calendar c = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("MM.dd.yyyy kk:mm");
//        public MailReporter(String receiverEmailID, String emailSubject, String emailBody, String file)
//        {
//            this.receiverEmailID=receiverEmailID;
//            this.emailSubject=emailSubject;
//            this.emailBody=emailBody;
//
//
//            Properties props = new Properties();
//            props.put("mail.smtp.user",senderEmailID);
//            props.put("mail.smtp.host", emailSMTPserver);
//            props.put("mail.smtp.port", emailServerPort);
//            props.put("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.auth", "true");
//// props.put("mail.smtp.debug", "true");
//            props.put("mail.smtp.socketFactory.port", emailServerPort);
//            props.put("mail.smtp.socketFactory.class",
//                    "javax.net.ssl.SSLSocketFactory");
//            props.put("mail.smtp.socketFactory.fallback", "false");
//
//            SecurityManager security = System.getSecurityManager();
//
//            try
//            {
//                Authenticator auth = new SMTPAuthenticator();
//                Session session = Session.getInstance(props, auth);
//// session.setDebug(true);
//
//                MimeMessage msg = new MimeMessage(session);
//
//                BodyPart messageBodypart1 = new MimeBodyPart();  //part1 contains subject
//                BodyPart messageBodypart2 = new MimeBodyPart();  // part2 contains file attachment
//                BodyPart messageBodypart3 = new MimeBodyPart();
//                messageBodypart1.setText(emailBody);
//                String filename = "C:\\\\Automation\\\\svn\\\\ram-core\\\\target\\\\qa-logs\\\\SampleTest_Results.XML";
//                String filename1 = "C:\\\\Automation\\\\svn\\\\ram-core\\\\target\\\\qa-logs\\\\defaultStyleSheet.xsl";
//                DataSource source = new FileDataSource(filename);
//                DataSource source1 = new FileDataSource(filename1);
//                messageBodypart3.setDataHandler(new DataHandler(source1));
//                messageBodypart2.setDataHandler(new DataHandler(source));
//                messageBodypart2.setFileName("SampleTest_Results.XML");	//sample
//                messageBodypart3.setFileName("defaultStyleSheet.xsl");  // default
//                Multipart multipart = new MimeMultipart();
//                multipart.addBodyPart(messageBodypart1);
//                multipart.addBodyPart(messageBodypart2);
//                multipart.addBodyPart(messageBodypart3);
//
//                msg.setContent(multipart);
//                msg.setSubject(emailSubject);
//                msg.setFrom(new InternetAddress(senderEmailID));
//                msg.addRecipient(Message.RecipientType.TO,
//                        new InternetAddress(receiverEmailID));
//                Transport.send(msg);
//            }
//            catch (Exception mex)
//            {
//                mex.printStackTrace();
//            }
//
//
//        }
//        public class SMTPAuthenticator extends javax.mail.Authenticator
//        {
//            public PasswordAuthentication getPasswordAuthentication()
//            {
//                return new PasswordAuthentication(senderEmailID, senderPassword);
//            }
//        }
//
//        public static void main(String[] args)
//        {
//           // emaildata = loadproperties();
//            String email = abstractTest.PROPERTIES.getProperty("emailid");//"sriram.angajala@gmail.com";//emaildata.getProperty("email");
//            String pass= abstractTest.PROPERTIES.getProperty("password");//"Krishh77$";//emaildata.getProperty("password");
//            String filepath = abstractTest.PROPERTIES.getProperty("path");//"C:\\Automation\\svn\\pom-ven-webdriver\\test-output\\old\\index.html";//emaildata.getProperty("filepath");
//            senderEmailID = email;//emaildata.getProperty("email");
//            senderPassword = pass;	//emaildata.getProperty("password");
//
//            com.venkyold.org.advance.MailReporter mailSender=new com.venkyold.org.advance.MailReporter("jkumar12398@gmail.com","Selenium Webdriver Execution Report","Hi this is a test mail",filepath);
//        }
//        public static Properties loadproperties()
//        {    Properties pro = new Properties();
//            try
//            {
//                pro.load(new FileInputStream("src\\sendmail\\email.properties"));
//            }catch (IOException e)
//            {
//                System.out.println("file not found");
//            }
//            return pro;
//
//
//        }



}
