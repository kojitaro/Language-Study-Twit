package net.hekatoncheir.languagestudytwit.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("twitter")
public interface TwitterService extends RemoteService {
	public TwitterLoginInfo login() throws TwitterServiceException;
	
	public ArrayList<TwitterStatus> statuses(long sinceTweetId)  throws TwitterServiceException;
	public TwitterStatus update(String text) throws TwitterServiceException;
}