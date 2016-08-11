package com.qcloud.cosapi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "com.qcloud.cosapi")
@XmlAccessorType(XmlAccessType.NONE)
public class Config {
	@XmlElement(name = "AppID")
	private int APP_ID;
	@XmlElement(name = "secretID")
	private String SECRET_ID;
	@XmlElement(name = "secretKey")
	private String SECRET_KEY;
	@XmlElement(name = "bucketName")
	private String bucketName;
	@XmlElement(name = "remotePath")
	private String remotePath;
	@XmlElement(name = "localPath")
	private String localPath;

	public int getAPP_ID() {
		return APP_ID;
	}

	public void setAPP_ID(int aPP_ID) {
		APP_ID = aPP_ID;
	}

	public String getSECRET_ID() {
		return SECRET_ID;
	}

	public void setSECRET_ID(String sECRET_ID) {
		SECRET_ID = sECRET_ID;
	}

	public String getSECRET_KEY() {
		return SECRET_KEY;
	}

	public void setSECRET_KEY(String sECRET_KEY) {
		SECRET_KEY = sECRET_KEY;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getRemotePath() {
		return remotePath;
	}

	public void setRemotePath(String remotePath) {
		this.remotePath = remotePath;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}


}
