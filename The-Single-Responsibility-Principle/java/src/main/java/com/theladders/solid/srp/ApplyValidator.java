package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.ProfileStatus;

/*
 * 
 * The current responsibilities of this class are to the roles of:
 * 1) Maintainers of the business rules that decide if a resume completion is required.
 * 
 */

public class ApplyValidator
{

  public static boolean resumeCompletionRequiredFor(Jobseeker jobseeker,
                                                    JobseekerProfile profile)
  {
    return (!jobseeker.isPremium() && (
              profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
              profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
              profile.getStatus().equals(ProfileStatus.REMOVED)
              )
           );
  }

}
