package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseContactInfoCategoryEmailAddress implements ConfidentialPhraseContactInfoCategory
{
  private static final int id = 77;

  @Override
  public Integer getId()
  {
	  return id;
  }
  
  @Override
  public String name()
  {
    return "EmailAddress";
  }
  
}
