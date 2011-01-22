package net.hekatoncheir.languagestudytwit.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;

public class TLListCell extends Composite {

	public TLListCell() {
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		horizontalPanel.setHeight("100 %");
		initWidget(horizontalPanel);
		
		Image mProfileImage = new Image("image/default_profile.png");
		horizontalPanel.add(mProfileImage);
		mProfileImage.setSize("48 px", "48 px");
		
		VerticalPanel verticalPanel = new VerticalPanel();
		horizontalPanel.add(verticalPanel);
		
		Label mProfileScreenName = new Label("ohura_2009");
		verticalPanel.add(mProfileScreenName);
		
		Label mTweet = new Label("If you need to debug JavaScript in the Browser on Android, use console.log() and adb : ./adb logcat | grep WebCore");
		verticalPanel.add(mTweet);
	}

}
