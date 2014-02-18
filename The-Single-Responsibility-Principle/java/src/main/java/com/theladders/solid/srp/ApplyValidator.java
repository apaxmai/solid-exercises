package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.ProfileStatus;

public class ApplyValidator
{

  public static boolean resumeCompletionRequiredFor(Jobseeker jobseeker,
                                                    JobseekerProfile profile)
  {
    return (!jobseeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) || profile.getStatus()
                                                                                                      .equals(ProfileStatus.NO_PROFILE) || profile.getStatus()
                                                                                                                                                  .equals(ProfileStatus.REMOVED)));
  }

}
