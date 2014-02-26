package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseContactInfoCategoryPhoneNumber implements ConfidentialPhraseContactInfoCategory
{
  public static final int id = 78;
  
  @Override
  public Integer getId()
  {
	  return id;
  }
  
  @Override
  public String name()
  {
    return "PhoneNumber";
  }
  
}
