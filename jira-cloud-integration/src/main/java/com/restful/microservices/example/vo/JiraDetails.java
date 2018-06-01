/**
 * 
 */
package com.restful.microservices.example.vo;

/**
 * @author aritnag
 *
 */
public class JiraDetails {
	
	private String userName;
	private String password;
	private String jiraUrl;
	private String bugNumber;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the jiraUrl
	 */
	public String getJiraUrl() {
		return jiraUrl;
	}
	/**
	 * @param jiraUrl the jiraUrl to set
	 */
	public void setJiraUrl(String jiraUrl) {
		this.jiraUrl = jiraUrl;
	}
	/**
	 * @return the bugNumber
	 */
	public String getBugNumber() {
		return bugNumber;
	}
	/**
	 * @param bugNumber the bugNumber to set
	 */
	public void setBugNumber(String bugNumber) {
		this.bugNumber = bugNumber;
	}
	

}
