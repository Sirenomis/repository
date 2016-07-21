package it.xeno.bean;

public class MailConnectorBean {
	
	private String host;
	private String mailStoreType;
	private String username;
	private String password;
	private String port;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getMailStoreType() {
		return mailStoreType;
	}
	public void setMailStoreType(String mailStoreType) {
		this.mailStoreType = mailStoreType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
}
