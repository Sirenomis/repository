package it.xeno.core;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

import org.apache.log4j.Logger;

import com.sun.mail.util.MailSSLSocketFactory;

import it.xeno.bean.MailConnectorBean;
import it.xeno.util.PropertyManager;

public class MailConnector {
	
	private Logger logger = Logger.getLogger(MailConnector.class);
	private PropertyManager props = PropertyManager.getInstance();
	

	public void check() {
		try {

			// create properties field
			Properties properties = System.getProperties();

			properties.put("mail.pop3.host", host);
			properties.put("mail.pop3.port", port);
			properties.put("mail.pop3.starttls.enable", "true");
			
//			properties.put("mail.imaps.ssl.trust", "*");
//			properties.put("mail.imaps.ssl.checkserveridentity", "false");
//			properties.put("mail.pop3s.ssl.trust", "*");
//			properties.put("mail.pop3s.ssl.checkserveridentity", "false");
			
			MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
			socketFactory.setTrustAllHosts(true);
			properties.put("mail.imaps.ssl.socketFactory", socketFactory);
			
			Session emailSession = Session.getDefaultInstance(properties);


			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore(mailStoreType);
			store.connect(host, username, password);
			
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			Message[] messages = folder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0, n = messages.length; i < n; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				System.out.println("Email Number " + (i + 1));
				System.out.println("Subject: " + message.getSubject());
				System.out.println("From: " + message.getFrom()[0]);
				//System.out.println("Text: " + message.getContent().toString());

			}

			// close the store and folder objects
			folder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MailConnector cm = new MailConnector();
		cm.check();

	}
	
	private String host;
	private String mailStoreType;
	private String username;
	private String password;
	private String port;
	
	public MailConnector(){
		host = props.getConfig("mail.host");
		mailStoreType = props.getConfig("mail.mailstoreType");
		username = props.getConfig("mail.username");
		password = props.getConfig("mail.password");
		port = props.getConfig("mail.port");
		
		logger.debug(mailStoreType);
		logger.debug(host);
		logger.debug(username);
		logger.debug(password);
		logger.debug(port);
		
	}
	
	public MailConnector(MailConnectorBean mcb){
		host = mcb.getHost();
		mailStoreType = mcb.getMailStoreType();
		username = mcb.getUsername();
		password = mcb.getPassword();
		port = mcb.getPort();
		
	}

}