package net.hekatoncheir.languagestudytwit.client;

import net.hekatoncheir.languagestudytwit.client.service.TwitterStatus;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

public class TLListCell extends Composite {

	public TLListCell(TwitterStatus status) {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setSize("100%", "100%");
		initWidget(horizontalPanel);
		
		Image profileImage = new Image(status.mUserProfileImageURL);
		horizontalPanel.add(profileImage);
		profileImage.setSize("48 px", "48 px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		
		Label profileScreenName = new Label(status.mUserScreenName);
		verticalPanel.add(profileScreenName);
		
		Label tweet = new Label(status.mText);
		verticalPanel.add(tweet);
	}

}
