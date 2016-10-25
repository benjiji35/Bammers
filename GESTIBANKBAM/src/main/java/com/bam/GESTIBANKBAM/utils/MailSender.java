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
import com.bam.GESTIBANKBAM.model.Compte;
import com.bam.GESTIBANKBAM.model.Personne;

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

	public static boolean sendMail(Personne prs) {
		boolean status = true;
		Client clt     = (prs instanceof Client)? (Client)prs: null;

		String host     = config.getProperty("mail.smtp.host");
		int    port     = Integer.parseInt(config.getProperty("mail.protocol.port", "25"));
		String[] tos    = prs.getMail().split("\\s");
		String[] bcc    = config.getProperty("bambank.mail.bcc").split("\\s");
		String from     = config.getProperty("bambank.mail.from");
		String account_subject  = config.getProperty("bambank.mail.account.open.notification.subject");
		String person_subject   = config.getProperty("bambank.mail.access.credential.notification.subject");
		String body_account_tpl = config.getProperty("bambank.mail.account.open.notification.body");
		String body_person_tpl  = config.getProperty("bambank.mail.access.credential.notification.body");
		String password = config.getProperty("bambank.mail.password");
		boolean debug   = true;

		String civility = ("mr".equalsIgnoreCase(prs.getCivilite())? "Sir": "Madam");
		String eol      = System.getProperty("line.separator");
		StringBuffer credentials = new StringBuffer();
		// create some properties and get the default Session
		Properties props = new Properties();

		props.put("mail.smtp.host", host);
		props.put("mail.protocol.port", ""+port);
		props.put("mail.smtp.port", config.getProperty("mail.smtp.port"));
		props.put("mail.smtp.auth", config.getProperty("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", config.getProperty("mail.smtp.starttls.enable"));
		props.put("mail.smtp.socketFactory.class", config.getProperty("mail.smtp.socketFactory.class"));
		props.put("mail.smtp.socketFactory.port", config.getProperty("mail.smtp.socketFactory.port"));
		props.put("mail.smtp.socketFactory.fallback", config.getProperty("mail.smtp.socketFactory.fallback"));
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
			msg.setSentDate(new Date());
			// create and fill the first message part
			MimeBodyPart mbp1 = new MimeBodyPart();
			if (clt == null) {
				// it is a password remembrance assistance request
				// in other words, the user does not remember a part of (or all the whole of) his credentials
				msg.setSubject(person_subject);
				mbp1.setText(MessageFormat.format(body_person_tpl, 
						civility, 
						eol, 
						prs.getId(), 
						prs.getHashMdp()));
			} else {
				// it is a password generation
				Compte compte = clt.getComptes().iterator().next();
				msg.setSubject(account_subject);
				mbp1.setText(MessageFormat.format(body_account_tpl, 
						civility, 
						eol, 
						compte.getTransactions().get(0).getDateDebut(),
						prs.getId(), 
						prs.getHashMdp()));
			}
			// create and fill the second message part
			MimeBodyPart mbp2 = new MimeBodyPart();
			credentials.append("BAMBank website access credentials:")
				.append("------------------------------------")
				.append(eol)
				.append(eol)
				.append(" ").append(prs.getNom()).append(" ").append(prs.getPrenom())
				.append(eol)
				.append(eol)
				.append("  - Login:").append(prs.getId())
				.append(eol)
				.append(eol)
				.append("  - Password:").append(prs.getHashMdp())
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
