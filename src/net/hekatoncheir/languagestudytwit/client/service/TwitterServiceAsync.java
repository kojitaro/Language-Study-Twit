package net.hekatoncheir.languagestudytwit.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TwitterServiceAsync {
	void login(AsyncCallback<TwitterLoginInfo> callback);
	void statuses(long sinceTweetId, AsyncCallback<ArrayList<TwitterStatus>> callback);
	void update(String text, AsyncCallback<TwitterStatus> callback);
}
