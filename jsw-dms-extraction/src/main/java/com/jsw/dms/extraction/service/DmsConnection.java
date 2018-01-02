package com.jsw.dms.extraction.service;

import javax.security.auth.Subject;

import com.filenet.api.collection.ObjectStoreSet;
import com.filenet.api.core.Connection;
import com.filenet.api.core.Domain;
import com.filenet.api.core.Factory;
import com.filenet.api.core.ObjectStore;
import com.filenet.api.util.UserContext;
import com.ibm.casemgmt.api.context.P8ConnectionCache;
import com.ibm.casemgmt.api.objectref.ObjectStoreReference;

public class DmsConnection {
	private Connection fileNetConn;
	private P8ConnectionCache p8ConnCache;
	private Subject subject;
	public Domain domain;
	public ObjectStore objectStoreObj;
	public ObjectStoreReference osRef = null;
	private UserContext uc;
	private String domainName;
	private ObjectStoreSet ost;
	private boolean isConnected;
	private String userName;
	private String password;
	private String stanza;
	private String uri;

	public DmsConnection(Connection fileNetConn, P8ConnectionCache p8ConnCache, Subject subject, Domain domain,
			ObjectStore objectStoreObj, ObjectStoreReference osRef) {
		super();
		this.fileNetConn = fileNetConn;
		this.p8ConnCache = p8ConnCache;
		this.subject = subject;
		this.domain = domain;
		this.objectStoreObj = objectStoreObj;
		this.osRef = osRef;
	}

	public void initConnection() {
		fileNetConn = Factory.Connection.getConnection(uri);
		subject = UserContext.createSubject(fileNetConn, userName, password, stanza);
		uc.pushSubject(subject);
		domain = fetchDomain();
		domainName = domain.get_Name();
		ost = getOSSet();
		isConnected = true;
	}

	public Domain fetchDomain() {
		domain = Factory.Domain.fetchInstance(fileNetConn, null, null);
		return domain;
	}

	public ObjectStoreSet getOSSet() {
		ost = domain.get_ObjectStores();
		return ost;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public ObjectStore fetchOS(String name) {
		ObjectStore os = Factory.ObjectStore.fetchInstance(domain, name, null);
		return os;
	}

	public DmsConnection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Connection getFileNetConn() {
		return fileNetConn;
	}

	public void setFileNetConn(Connection fileNetConn) {
		this.fileNetConn = fileNetConn;
	}

	public P8ConnectionCache getP8ConnCache() {
		return p8ConnCache;
	}

	public void setP8ConnCache(P8ConnectionCache p8ConnCache) {
		this.p8ConnCache = p8ConnCache;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public ObjectStore getObjectStoreObj() {
		return objectStoreObj;
	}

	public void setObjectStoreObj(ObjectStore objectStoreObj) {
		this.objectStoreObj = objectStoreObj;
	}

	public ObjectStoreReference getOsRef() {
		return osRef;
	}

	public void setOsRef(ObjectStoreReference osRef) {
		this.osRef = osRef;
	}

	public UserContext getUc() {
		return uc;
	}

	public void setUc(UserContext uc) {
		this.uc = uc;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public ObjectStoreSet getOst() {
		return ost;
	}

	public void setOst(ObjectStoreSet ost) {
		this.ost = ost;
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

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

}
