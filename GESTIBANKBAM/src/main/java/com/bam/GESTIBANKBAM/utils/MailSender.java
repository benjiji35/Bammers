package com.bam.GESTIBANKBAM.utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.bam.GESTIBANKBAM.model.Client;

public class MailSender {

	private static Properties config;

	static {
		config = new Properties();
		try {
			config.load(MailSender.class.getResourceAsStream("/bambank/env.properties"));
		} catch (IOException e) {
			e.printStackTrace(System.err);
			throw new ExceptionInInitializerError(e);
		}
		System.out.println("MailServer:: properties::");
		config.list(System.out);
	};
	
	public static boolean sendMail(Client clt) {
		boolean status = true;

		String host     = config.getProperty("mail.smtp.host");
		int    port     = Integer.parseInt(config.getProperty("mail.protocol.port", "25"));
		String[] tos    = clt.getMail().split("\\s");
		String[] bcc    = config.getProperty("bambank.mail.bcc").split("\\s");
		String from     = config.getProperty("bambank.mail.from");
		String subject  = config.getProperty("bambank.mail.account.open.notification.subject");
		String body_tpl = config.getProperty("bambank.mail.account.open.notification.body");
		String password = config.getProperty("bambank.mail.password");
		boolean debug   = true;

		String civility = ("mr".equalsIgnoreCase(clt.getCivilite())? "Sir": "Madam");
		String eol      = System.getProperty("line.separator");
		StringBuffer credentials = new StringBuffer();
		// create some properties and get the default Session
		Properties props = new Properties();
		

		props.put("mail.smtp.host", host);
		props.put("mail.protocol.port", ""+port);
		Session session = Session.getInstance(props);
		session.setDebug(debug);
		try {
			Transport transport = session.getTransport("smtps");
			// create a message
			MimeMessage msg = new MimeMessage(session);
			msg.setHeader("content-type", "text.html");
			msg.setFrom(new InternetAddress(from));
			InternetAddress[] addressTo = new InternetAddress[tos.length];
			InternetAddress[] addressBcc = new InternetAddress[bcc.length];
			for (int i = 0; i < tos.length; i++) {
				addressTo[i] = new InternetAddress(tos[i]);
			}
			for (int i=0; i < bcc.length; i++) {
				addressBcc[i] = new InternetAddress(bcc[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);
			msg.setRecipients(Message.RecipientType.BCC, addressBcc);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			mbp1.setText(MessageFormat.format(body_tpl, 
					civility, 
					eol, 
					clt.getComptes().get(0).getTransactions().get(0).getDateDebut(),
					clt.getId(), 
					clt.getHashMdp()));
			// create and fill the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();
			credentials.append("BAMBank website access credentials:")
				.append("------------------------------------")
				.append(eol)
				.append(eol)
				.append(" ").append(clt.getNom()).append(" ").append(clt.getPrenom())
				.append(eol)
				.append(eol)
				.append("  - Login:").append(clt.getId())
				.append(eol)
				.append(eol)
				.append("  - Password:").append(clt.getHashMdp())
				.append(eol)
				.append(eol)
				.append(eol).append(eol).append(eol).append(eol)
				.append("--").append(eol).append(eol)
				.append("Best regards,").append(eol).append(eol)
				.append("BAMBank Corp.");
			// Use setText(text, charset), to show it off !
			mbp2.setText(credentials.toString(), "utf-8");
			// create the Multipart and its parts to it
			Multipart mp = new MimeMultipart();
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			// add the Multipart to the message
			msg.setContent(mp);
			// send the message
			transport.connect(host, from, password);
			transport.sendMessage(msg, msg.getAllRecipients());
			transport.close();
			//Transport.send(msg);
		} catch (MessagingException mex) {
			status = false;
			mex.printStackTrace(System.err);
			Exception ex = null;
			if ((ex = mex.getNextException()) != null) {
				ex.printStackTrace();
			}
		}
		return status;
	}
}
