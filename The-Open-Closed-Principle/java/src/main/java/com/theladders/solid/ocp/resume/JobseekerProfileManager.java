package com.theladders.solid.ocp.resume;

import com.theladders.solid.ocp.user.User;

//inside closed-for-modification boundary

public class JobseekerProfileManager
{
  public JobseekerProfile getJobSeekerProfile(User user)
  {
    return new JobseekerProfile(user.getId());
  }
}
