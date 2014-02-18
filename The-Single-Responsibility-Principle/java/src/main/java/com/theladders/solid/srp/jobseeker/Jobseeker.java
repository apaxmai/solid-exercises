package com.theladders.solid.srp.jobseeker;

import com.theladders.solid.srp.HashCodeProvider;

public class Jobseeker
{
  private final int id;
  private JobseekerAccountLevel accountLevel;
  
  // As premium is a business logic concept,
  // we have separated it from the Jobseeker into
  // JobseekerAccountLevel. If there are additional
  // account levels or other changes to that business realm,
  // Jobseeker need not be modified.
  public Jobseeker(int id, boolean hasPremiumAccount)
  {
    this.id = id;
    this.accountLevel = JobseekerAccountLevel.with(hasPremiumAccount);
  }

  public boolean isPremium()
  {
	return accountLevel.isPremium();
  }

  public int getId()
  {
    return id;
  }

  //it is possible that in Java, the responsibility of editing
  //the algorithm used for hashCode() would fall to a role of
  //optimization, perhaps a different set of developers, so we extract.
  @Override
  public int hashCode()
  {
	return HashCodeProvider.hashCodeFor(this, this.id);
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Jobseeker other = (Jobseeker) obj;
    if (id != other.id)
      return false;
    return true;
  }
}
