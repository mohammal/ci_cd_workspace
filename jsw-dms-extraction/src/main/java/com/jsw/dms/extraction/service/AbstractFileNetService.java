package com.jsw.dms.extraction.service;

public abstract class AbstractFileNetService {
	protected String objectStore;
	protected String filenetDocPath;
	private String userName;
	private String password;
	private String stanza;
	private String uri;

	protected DmsConnection getDMSConnection() {
		DmsConnection ceConnection = new DmsConnection(); 
		// TODO This can be a
		// prototype spring
		// bean, singleton
		// doesnt work
		ceConnection.setPassword(password);
		ceConnection.setStanza(stanza);
		ceConnection.setUri(uri);
		ceConnection.setUserName(userName);
		ceConnection.initConnection();
		return ceConnection;
	}

	public String getObjectStore() {
		return objectStore;
	}

	public void setObjectStore(String objectStore) {
		this.objectStore = objectStore;
	}

	public String getFilenetDocPath() {
		return filenetDocPath;
	}

	public void setFilenetDocPath(String filenetDocPath) {
		this.filenetDocPath = filenetDocPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStanza() {
		return stanza;
	}

	public void setStanza(String stanza) {
		this.stanza = stanza;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
