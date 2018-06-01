package com.restful.microservices.example.service;

import java.io.IOException;
import java.net.URI;

import org.springframework.stereotype.Component;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

@Component
public class JiraClientUtililtyMethod {

	public JiraRestClient getJiraRestClient(String userName, String password, String jiraUrl) {
		return new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(getJiraUri(jiraUrl), userName,
				password);
	}

	private URI getJiraUri(String jiraUrl) {
		return URI.create(jiraUrl);
	}

	public Issue getIssueObject(String userName, String password, String jiraUrl, String bugNumber) throws IOException {
		Issue issue = null;
		final String issueKey = bugNumber;
		issue = this.getIssue(issueKey, userName, password, jiraUrl);
		System.out.println(issue.getDescription());
		System.out.println(issue.getWorklogs());
		return issue;
	}

	private Issue getIssue(String issueKey, String userName, String password, String jiraUrl) {
		return getJiraRestClient(userName, password, jiraUrl).getIssueClient().getIssue(issueKey).claim();
	}

	public Object getIssueObjectWorkLog(String userName, String password, String jiraUrl, String bugNumber) {
		// TODO Auto-generated method stub
		return getJiraRestClient(userName, password, jiraUrl).getIssueClient().getIssue(bugNumber);
	}

}