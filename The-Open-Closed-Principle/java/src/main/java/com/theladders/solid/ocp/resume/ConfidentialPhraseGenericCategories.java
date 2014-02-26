package com.theladders.solid.ocp.resume;

import java.util.ArrayList;
import java.util.List;

public class ConfidentialPhraseGenericCategories
{
	@SuppressWarnings("serial")
	public static final List<ConfidentialPhraseCategory> categories = new ArrayList<ConfidentialPhraseCategory>() {{
		add(new ConfidentialPhraseGenericCategoryName());
		add(new ConfidentialPhraseGenericCategoryCompanyName());
		add(new ConfidentialPhraseGenericCategoryWorkExperience());
	}};
}
