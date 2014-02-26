package com.theladders.solid.ocp.jobseeker;

//inside closed-for-modification boundary

public class JobseekerConfidentialityProfileDao
{
  public JobseekerConfidentialityProfile fetchJobSeekerConfidentialityProfile(int id)
  {
    return new JobseekerConfidentialityProfile();
  }
}
