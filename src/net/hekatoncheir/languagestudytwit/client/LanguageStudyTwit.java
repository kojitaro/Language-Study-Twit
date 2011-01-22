package net.hekatoncheir.languagestudytwit.client;

import net.hekatoncheir.languagestudytwit.client.service.LoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.LoginService;
import net.hekatoncheir.languagestudytwit.client.service.LoginServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LanguageStudyTwit implements EntryPoint {
	
	LoginInfo mLoginInfo;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		RootPanel rootPanel = RootPanel.get();
		
		TLList l = new TLList();
		rootPanel.add(l);

		/*
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
			}

			public void onSuccess(LoginInfo result) {
				mLoginInfo = result;
				if(mLoginInfo.isLoggedIn()) {
					loadMain();
				} else {
					loadLogin();
				}
			}
		});
		*/
	}
	
	private void loadMain()
	{
		
	}
	private void loadLogin()
	{
		
	}
}
