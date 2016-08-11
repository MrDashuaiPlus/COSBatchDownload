package com.qcloud.cosapi.javabeans;

public class FileBean {
	private String name;
	private String access_url;
	private String directory;
	private boolean isDir = false;
	private String bucketName;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public boolean isDir() {
		return isDir;
	}

	public void setDir(boolean isDir) {
		this.isDir = isDir;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccess_url() {
		return access_url;
	}

	public void setAccess_url(String access_url) {
		this.access_url = access_url;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}
}
