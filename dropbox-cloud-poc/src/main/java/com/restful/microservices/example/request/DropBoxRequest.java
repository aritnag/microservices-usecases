package com.restful.microservices.example.request;

public class DropBoxRequest {

	private String dropBoxAppKey;
	private String dropBoxAppSecret;
	private String appName;
	/**
	 * @return the dropBoxAppKey
	 */
	public String getDropBoxAppKey() {
		return dropBoxAppKey;
	}
	/**
	 * @param dropBoxAppKey the dropBoxAppKey to set
	 */
	public void setDropBoxAppKey(String dropBoxAppKey) {
		this.dropBoxAppKey = dropBoxAppKey;
	}
	/**
	 * @return the dropBoxAppSecret
	 */
	public String getDropBoxAppSecret() {
		return dropBoxAppSecret;
	}
	/**
	 * @param dropBoxAppSecret the dropBoxAppSecret to set
	 */
	public void setDropBoxAppSecret(String dropBoxAppSecret) {
		this.dropBoxAppSecret = dropBoxAppSecret;
	}
	/**
	 * @return the appName
	 */
	public String getAppName() {
		return appName;
	}
	/**
	 * @param appName the appName to set
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}
}
