package com.restful.microservices.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.restful.microservices.example.request.DropBoxRequest;
import com.restful.microservices.example.util.DropBoxAuthClient;

@RestController
@RequestMapping(value = { "/dropbox" })
public class PostController {

	@Autowired
	private DropBoxAuthClient dropboxClient;

	@RequestMapping(value = { "/size" }, method = RequestMethod.POST)
	public Long getDropBoxSize(@RequestBody DropBoxRequest dropboxRequest) throws Exception {
		return dropboxClient.getDropboxSize(dropboxClient.authDropbox(dropboxRequest.getDropBoxAppKey(),
				dropboxRequest.getDropBoxAppSecret(), dropboxRequest.getAppName()));
	}

}
