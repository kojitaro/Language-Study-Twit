package net.hekatoncheir.languagestudytwit.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TwitterServiceAsync {
	void login(AsyncCallback<TwitterLoginInfo> callback);
}
