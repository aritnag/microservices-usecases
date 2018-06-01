package com.restful.microservices.example.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicVotes;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import com.google.gson.Gson;

@Service
public class JiraClientService {

	@Autowired
	private JiraClientUtililtyMethod data;

	public Gson gson=new Gson();

	public String getIssue(String userName, String password, String jiraUrl, String bugNumber) throws IOException {
		return gson.toJson(data.getIssueObject(userName, password, jiraUrl, bugNumber));
	}

	@SuppressWarnings("unchecked")
	public Object getIssueWorkLog(String userName, String password, String jiraUrl, String bugNumber) throws InterruptedException, ExecutionException {
		Promise<Issue> issue=(Promise<Issue>) data.getIssueObjectWorkLog(userName, password, jiraUrl, bugNumber);
		return gson.toJson(issue.get().getWorklogs());
	}

}
