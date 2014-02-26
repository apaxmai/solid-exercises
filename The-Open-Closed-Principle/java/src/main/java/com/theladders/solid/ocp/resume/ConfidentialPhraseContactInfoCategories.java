package com.theladders.solid.ocp.resume;

import java.util.ArrayList;
import java.util.List;

public class ConfidentialPhraseContactInfoCategories
{
	@SuppressWarnings("serial")
	public static final List<ConfidentialPhraseCategory> categories = new ArrayList<ConfidentialPhraseCategory>() {{
		add(new ConfidentialPhraseContactInfoCategoryContactInfo());
		add(new ConfidentialPhraseContactInfoCategoryEmailAddress());
		add(new ConfidentialPhraseContactInfoCategoryMailingAddress());
		add(new ConfidentialPhraseContactInfoCategoryPhoneNumber());
	}};
}
