package net.hekatoncheir.languagestudytwit.client;

import java.util.ArrayList;

import net.hekatoncheir.languagestudytwit.client.service.LoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.TwitterLoginInfo;
import net.hekatoncheir.languagestudytwit.client.service.TwitterService;
import net.hekatoncheir.languagestudytwit.client.service.TwitterServiceAsync;
import net.hekatoncheir.languagestudytwit.client.service.TwitterServiceException;
import net.hekatoncheir.languagestudytwit.client.service.TwitterStatus;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HTML;

public class TLList extends Composite implements com.google.gwt.user.client.Window.ScrollHandler{

	private VerticalPanel mTweetList = new VerticalPanel();
	private TextBox mTweetText;
	private VerticalPanel verticalPanel;
	
	private long mLatestTweetId;
	
	public TLList(LoginInfo loginInfo, TwitterLoginInfo twitterLoginInfo) {
		Window.addWindowScrollHandler(this);
		
		verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		HTML logout = new HTML("<a href=\"" + loginInfo.getLogoutUrl()+ "\">Logout</a>", true);
		verticalPanel.add(logout);
		verticalPanel.setCellHorizontalAlignment(logout, HasHorizontalAlignment.ALIGN_RIGHT);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		mTweetText = new TextBox();
		horizontalPanel.add(mTweetText);
		mTweetText.setSize("375px", "58px");
		
		Button mTweetButton = new Button("Tweet");
		mTweetButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				tweet();
			}
		});
		horizontalPanel.add(mTweetButton);
		horizontalPanel.setCellVerticalAlignment(mTweetButton, HasVerticalAlignment.ALIGN_BOTTOM);
		
		verticalPanel.add(mTweetList);
		mTweetList.setSize("100%", "100%");
		
		// start load statuses
		loadStatuses(-1);
	}
	
	private void loadStatuses(long sinceTweetId)
	{
		TwitterServiceAsync twitterService = GWT.create(TwitterService.class);
		twitterService.statuses(sinceTweetId, new AsyncCallback<ArrayList<TwitterStatus>>() {
			public void onFailure(Throwable error) {
				if (error instanceof TwitterServiceException) {
				}
			}

			public void onSuccess(ArrayList<TwitterStatus> statuses) {
			    for (TwitterStatus status : statuses) {
			    	if( mLatestTweetId == status.mTweetId)continue;
			    	
					TLListCell c = new TLListCell(status);
					mTweetList.add(c);		
					
					mLatestTweetId = status.mTweetId;
			    }
			}
		});
	}
	private void tweet()
	{
		TwitterServiceAsync twitterService = GWT.create(TwitterService.class);
		twitterService.update(mTweetText.getText(), new AsyncCallback<TwitterStatus>() {
			public void onFailure(Throwable error) {
				if (error instanceof TwitterServiceException) {
				}
			}

			public void onSuccess(TwitterStatus status) {
				TLListCell c = new TLListCell(status);
				mTweetList.insert(c, 0);			    	
			}
		});
		
	}

	@Override
	public void onWindowScroll(
			com.google.gwt.user.client.Window.ScrollEvent event)
	{
		int scrollTop = Window.getScrollTop();
		int windowHeight = Window.getClientHeight();
		
		int contentHeight = verticalPanel.getOffsetHeight();
		
		if( scrollTop >= contentHeight-windowHeight){
			// 追加でロード
			loadStatuses(mLatestTweetId);
		}
	}
}
