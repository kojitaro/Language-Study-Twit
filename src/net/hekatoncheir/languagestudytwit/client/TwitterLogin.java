package net.hekatoncheir.languagestudytwit.client;

import net.hekatoncheir.languagestudytwit.client.service.TwitterLoginInfo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class TwitterLogin extends Composite {

	public TwitterLogin(TwitterLoginInfo loginInfo) {
		
		HTML login = new HTML("<a href=\"" + loginInfo.getLoginUrl()+ "\">Login</a>", true);
		initWidget(login);
	}

}
