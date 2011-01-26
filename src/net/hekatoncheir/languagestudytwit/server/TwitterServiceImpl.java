package net.hekatoncheir.languagestudytwit.server;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.RequestToken;
import net.hekatoncheir.languagestudytwit.client.service.TwitterLoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.TwitterService;
import net.hekatoncheir.languagestudytwit.client.service.TwitterServiceException;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TwitterServiceImpl 
	extends RemoteServiceServlet
	implements TwitterService
{

	private static final Logger log = Logger.getLogger(TwitterServiceImpl.class.getName());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TwitterLoginInfo login() throws TwitterServiceException{
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (user == null) {
			throw new TwitterServiceException();
		}
		
		HttpServletRequest request = getThreadLocalRequest();
		
		Twitter twitter = (Twitter)request.getSession().getAttribute("twitter");
		if( twitter != null ){
				try {
					twitter4j.User twitterUser = twitter.verifyCredentials();
					TwitterLoginInfo loginInfo = new TwitterLoginInfo();
					
					loginInfo.setLoggedIn(true);
					loginInfo.setScreenName(twitterUser.getScreenName());
					
					return loginInfo;
				} catch (TwitterException e) {
				} catch (java.lang.IllegalStateException e2){
				}
				
		}
		
		// ログインできてない
		twitter = new TwitterFactory().getInstance();
		request.getSession().setAttribute("twitter", twitter);
		
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/twitter_callback");

        RequestToken requestToken = null;
        try{
        	requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
        }catch(TwitterException e){
        	log.info(e.toString());
        	throw new TwitterServiceException();
        }
        request.getSession().setAttribute("requestToken", requestToken);
        
		TwitterLoginInfo loginInfo = new TwitterLoginInfo();
		loginInfo.setLoggedIn(false);
		loginInfo.setLoginUrl(requestToken.getAuthenticationURL());
		
		return loginInfo;
	}
}