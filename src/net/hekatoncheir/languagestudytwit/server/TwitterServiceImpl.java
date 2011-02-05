package net.hekatoncheir.languagestudytwit.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import twitter4j.Paging;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.http.RequestToken;
import net.hekatoncheir.languagestudytwit.client.service.TwitterLoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.TwitterService;
import net.hekatoncheir.languagestudytwit.client.service.TwitterServiceException;
import net.hekatoncheir.languagestudytwit.client.service.TwitterStatus;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.twitter.Autolink;

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
	public ArrayList<TwitterStatus> statuses(long sinceTweetId)  throws TwitterServiceException
	{
		ArrayList<TwitterStatus> r = new ArrayList<TwitterStatus>();
		
		HttpServletRequest request = getThreadLocalRequest();
		
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		
		List<Status> statuses;
		try {
			if( sinceTweetId > 0 ){
				Paging page= new Paging(1);
				page.maxId(sinceTweetId);
				statuses = twitter.getFriendsTimeline(page);
			}else{
				statuses = twitter.getFriendsTimeline();
			}
		} catch (TwitterException e) {
        	throw new TwitterServiceException();
		}
		//Autolink autolink = new Autolink();
	    for (Status status : statuses) {
	    	TwitterStatus s = new TwitterStatus();
	    	s.mText = status.getText();
//	    	s.mText = autolink.autoLink(status.getText());
	    	s.mCreatedAt = status.getCreatedAt();
			s.mUserScreenName = status.getUser().getScreenName();
			s.mUserProfileImageURL = status.getUser().getProfileImageURL().toString();
			s.mTweetId = status.getId();
			
			r.add(s);
	    }
		
		return r;
	}
	public TwitterStatus update(String text) throws TwitterServiceException
	{
		Autolink autolink = new Autolink();
		HttpServletRequest request = getThreadLocalRequest();

		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		Status status;
		try {
			status = twitter.updateStatus(text);
		} catch (TwitterException e) {
        	throw new TwitterServiceException();
		}
    	TwitterStatus s = new TwitterStatus();
    	s.mText = autolink.autoLink(status.getText());
    	s.mCreatedAt = status.getCreatedAt();
		s.mUserScreenName = status.getUser().getScreenName();
		s.mUserProfileImageURL = status.getUser().getProfileImageURL().toString();
		
		return s;
	}
}