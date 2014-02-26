package com.theladders.solid.ocp.jobseeker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.ocp.resume.ConfidentialPhrase;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategories;

//inside closed-for-modification boundary

public class JobseekerConfidentialityProfile
{
  private Map<String, List<ConfidentialPhrase>> confidentialityProfile;

  public JobseekerConfidentialityProfile()
  {
    confidentialityProfile = new HashMap<>();
  }

  public boolean resetAllConfidentialFlags()
  {
	  boolean isChanged = false;
	  
	  for(ConfidentialPhraseCategory category : ConfidentialPhraseCategories.allCategories() )
	  {
		  isChanged |= resetConfidentialFlagsForCategory(category);
	  }
	  
	  return isChanged;
  }
  
  public boolean resetContactInfoConfidentialFlags()
  {
    boolean isChanged = false;
    
	  for(ConfidentialPhraseCategory category : ConfidentialPhraseCategories.contactInfoCategories() )
	  {
		  isChanged |= resetConfidentialFlagsForCategory(category);
	  }
	
    return isChanged;
  }
  
  public boolean resetConfidentialFlagsForCategory(ConfidentialPhraseCategory category)
  {
    boolean isChanged = false;

    List<ConfidentialPhrase> phrases = this.getConfidentialPhrases(category);
    if (phrases != null)
    {
      for (ConfidentialPhrase phrase : phrases)
      {
        if (phrase.isConfidential())
        {
          phrase.setConfidential(false);
          isChanged = true;
        }
      }
    }

    return isChanged;
  }

  private List<ConfidentialPhrase> getConfidentialPhrases(ConfidentialPhraseCategory category)
  {
    return confidentialityProfile.get(category.name());
  }

}
