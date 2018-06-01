package com.restful.microservices.example.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.microservices.example.service.JiraClientService;
import com.restful.microservices.example.vo.JiraDetails;

@RestController
@RequestMapping("/fetchDetails")
public class JIRAController {

	@Autowired
	private JiraClientService jiraClient;

	@RequestMapping(value = { "/getissue" }, method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object getIssue(@RequestBody JiraDetails jiraDetails) throws IOException {
		return jiraClient.getIssue(jiraDetails.getUserName(), jiraDetails.getPassword(), jiraDetails.getJiraUrl(),
				jiraDetails.getBugNumber());
	}
	
	@RequestMapping(value = { "/issue/worklog" }, method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
	public Object retrieveExchangeValue(@RequestBody JiraDetails jiraDetails) throws Exception {
		return jiraClient.getIssueWorkLog(jiraDetails.getUserName(), jiraDetails.getPassword(), jiraDetails.getJiraUrl(),
				jiraDetails.getBugNumber());
	}

}
