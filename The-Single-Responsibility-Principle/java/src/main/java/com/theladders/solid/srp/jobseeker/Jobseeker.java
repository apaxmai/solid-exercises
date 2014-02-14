package com.theladders.solid.srp.jobseeker;

import com.theladders.solid.srp.HashCodeProvider;

public class Jobseeker
{
  private final int id;
  private final boolean hasPremiumAccount;

  
  //todo
  // MyBusinessLogicClass.accountLevelFor(jobseeker);
  public Jobseeker(int id, boolean hasPremiumAccount)
  {
    this.id = id;
    this.hasPremiumAccount = hasPremiumAccount;
  }

  public boolean isPremium()
  {
	//return JobseekerService.accountLevelFor(this);
    return hasPremiumAccount;
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
	return HashCodeProvider.hashCodeFor(this);
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
