package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseGenericCategoryCompanyName implements ConfidentialPhraseGenericCategory
{
  private static final int id = 81;

  @Override
  public Integer getId()
  {
	  return id;
  }
	  
  @Override
  public String name()
  {
	  return "CompanyName";
  }
		  
}
