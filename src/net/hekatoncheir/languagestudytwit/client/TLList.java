package net.hekatoncheir.languagestudytwit.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

public class TLList extends Composite {

	private VerticalPanel mTweetList = new VerticalPanel();
	
	public TLList() {
		
		VerticalPanel verticalPanel = new VerticalPanel();
		initWidget(verticalPanel);
		
		HorizontalPanel horizontalPanel = new HorizontalPanel();
		verticalPanel.add(horizontalPanel);
		
		TextBox mTweetText = new TextBox();
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
		mTweetList.setSize("237px", "100%");
	}

	private void tweet()
	{
		TLListCell c = new TLListCell();
		mTweetList.add(c);
	}
}
