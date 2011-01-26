package net.hekatoncheir.languagestudytwit.client;

import net.hekatoncheir.languagestudytwit.client.service.LoginInfo;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;

public class GoogleLogin extends Composite {

	public GoogleLogin(LoginInfo loginInfo) {
		
		HTML login = new HTML("<a href=\"" + loginInfo.getLoginUrl()+ "\">Login</a>", true);
		initWidget(login);
	}

}
