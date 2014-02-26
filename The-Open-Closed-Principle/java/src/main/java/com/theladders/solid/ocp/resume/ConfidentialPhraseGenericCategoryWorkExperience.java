package com.theladders.solid.ocp.resume;

public class ConfidentialPhraseGenericCategoryWorkExperience implements ConfidentialPhraseGenericCategory
{
	  public static final int id = 82;

	  @Override
	  public Integer getId()
	  {
		  return id;
	  }
		
	  
	  @Override
	  public String name()
	  {
	    return "WorkExperience";
	  }

}