package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseGenericCategoryName implements ConfidentialPhraseGenericCategory
{
  public static final int id = 76;

  
  @Override
  public Integer getId()
  {
	  return id;
  }
  
  @Override
  public String name()
  {
    return "Name";
  }
	  
}
