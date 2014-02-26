package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseContactInfoCategoryContactInfo implements ConfidentialPhraseContactInfoCategory
{
  private static final int id = 80;
  
  @Override
  public Integer getId()
  {
	  return id;
  }
  
  @Override
  public String name()
  {
    return "ContactInfo";
  }
  
}
