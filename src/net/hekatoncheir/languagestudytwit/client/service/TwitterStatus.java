package net.hekatoncheir.languagestudytwit.client.service;

import java.util.Date;

import com.google.gwt.user.client.rpc.IsSerializable;

public class TwitterStatus implements IsSerializable{
	public String mText;
	public Date mCreatedAt;
	public String mUserScreenName;
	public String mUserProfileImageURL;
}
