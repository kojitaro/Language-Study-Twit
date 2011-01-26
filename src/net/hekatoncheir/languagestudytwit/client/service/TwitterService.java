package net.hekatoncheir.languagestudytwit.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("twitter")
public interface TwitterService extends RemoteService {
	public TwitterLoginInfo login() throws TwitterServiceException;
}