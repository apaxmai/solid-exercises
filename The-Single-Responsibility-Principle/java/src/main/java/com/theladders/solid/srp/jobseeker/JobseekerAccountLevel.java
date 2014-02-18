package com.theladders.solid.srp.jobseeker;

public class JobseekerAccountLevel
{
  private final boolean hasPremiumAccount;


  public static JobseekerAccountLevel with(boolean premiumFlag)
  {
    return new JobseekerAccountLevel(premiumFlag);
  }


  private JobseekerAccountLevel(boolean premiumFlag)
  {
    hasPremiumAccount = premiumFlag;
  }


  public boolean isPremium()
  {
    return hasPremiumAccount;
  }

}
