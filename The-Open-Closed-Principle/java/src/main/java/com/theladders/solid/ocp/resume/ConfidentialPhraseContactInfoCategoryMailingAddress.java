package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseContactInfoCategoryMailingAddress implements ConfidentialPhraseContactInfoCategory
{
  public static final int id = 79;
  
  @Override
  public Integer getId()
  {
	  return id;
  }
  
  @Override
  public String name()
  {
    return "MailingAddress";
  }
  
}
