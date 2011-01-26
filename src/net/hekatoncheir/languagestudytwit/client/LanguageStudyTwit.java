package net.hekatoncheir.languagestudytwit.client;

import net.hekatoncheir.languagestudytwit.client.service.LoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.LoginService;
import net.hekatoncheir.languagestudytwit.client.service.LoginServiceAsync;
import net.hekatoncheir.languagestudytwit.client.service.TwitterLoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.TwitterService;
import net.hekatoncheir.languagestudytwit.client.service.TwitterServiceAsync;
import net.hekatoncheir.languagestudytwit.client.service.TwitterServiceException;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class LanguageStudyTwit implements EntryPoint {
	
	LoginInfo mGoogleLoginInfo;
	TwitterLoginInfo mTwitterLoginInfo;
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		LoginServiceAsync loginService = GWT.create(LoginService.class);
		loginService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			public void onFailure(Throwable error) {
				
			}

			public void onSuccess(LoginInfo result) {
				mGoogleLoginInfo = result;
				if(mGoogleLoginInfo.isLoggedIn()) {
					requireTwitterLogin();
				} else {
					loadLogin();
				}
			}
		});
		
	}

	private void requireTwitterLogin()
	{
		TwitterServiceAsync twitterService = GWT.create(TwitterService.class);
		twitterService.login(new AsyncCallback<TwitterLoginInfo>() {
			public void onFailure(Throwable error) {
				if (error instanceof TwitterServiceException) {
				}
			}

			public void onSuccess(TwitterLoginInfo result) {
				mTwitterLoginInfo = result;
				if(mTwitterLoginInfo.isLoggedIn()) {
					loadMain();
				} else {
					loadTwitterLogin();
				}
			}
		});
		
	}
	private void loadMain()
	{
		RootPanel rootPanel = RootPanel.get();
		TLList l = new TLList(mGoogleLoginInfo, mTwitterLoginInfo);
		rootPanel.add(l);
	}
	private void loadLogin()
	{
		RootPanel rootPanel = RootPanel.get();
		GoogleLogin l = new GoogleLogin(mGoogleLoginInfo);
		rootPanel.add(l);
	}
	private void loadTwitterLogin()
	{
		RootPanel rootPanel = RootPanel.get();
		TwitterLogin l = new TwitterLogin(mTwitterLoginInfo);
		rootPanel.add(l);
	}
}
