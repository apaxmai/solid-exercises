package com.theladders.solid.ocp.resume;

import java.util.ArrayList;
import java.util.List;

// this class is closed for modification under category modification,
// although not so for new category groups. It could be closed under new
// groups as well, but it would require additional dynamic code and slowness (crystal ball)

public class ConfidentialPhraseCategories
{
	private static List<ConfidentialPhraseCategory> genericCategories = ConfidentialPhraseGenericCategories.categories;
	private static List<ConfidentialPhraseCategory> contactInfoCategories = ConfidentialPhraseContactInfoCategories.categories;
	
	public static List<ConfidentialPhraseCategory> allCategories()
	{
	  List<ConfidentialPhraseCategory> unionList = new ArrayList<ConfidentialPhraseCategory>(genericCategories);
	  unionList.addAll(contactInfoCategories);

	  return unionList;
	}

	public static List<ConfidentialPhraseCategory> contactInfoCategories()
	{
	  return contactInfoCategories;
	}
	
	
}
