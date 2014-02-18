package com.theladders.solid.srp.job.application;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.Jobseeker;

// SRP: OK

public class JobApplicationRepository
{
  private final List<SuccessfulApplication> applications;


  public JobApplicationRepository()
  {
    this.applications = new ArrayList<>();
  }


  public void add(SuccessfulApplication application)
  {
    applications.add(application);
  }


  public boolean applicationExistsFor(Jobseeker jobseeker,
                                      Job job)
  {
    for (SuccessfulApplication application : applications)
    {
      // applicationRepository don't need to know about
      // getJobseeker or getJob
      // or the use of .equals
      // but it does need to know about applications. So we call
      // application.isFor(jobseeker, job)
      // I can see how a role that edits the application/jobseeker/job
      // classes
      // may not need to edit the jobapplicationrepository, so we split
      // these.
      if (application.isFor(jobseeker, job))
      {
        return true;
      }
    }

    return false;
  }
}
