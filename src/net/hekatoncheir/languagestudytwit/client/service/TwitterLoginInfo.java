package net.hekatoncheir.languagestudytwit.client.service;

import java.io.Serializable;

public class TwitterLoginInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String screenName;

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

 	public String getLogoutUrl() {
 		return logoutUrl;
	}

	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}


	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String nickname) {
		this.screenName = nickname;
	}
}