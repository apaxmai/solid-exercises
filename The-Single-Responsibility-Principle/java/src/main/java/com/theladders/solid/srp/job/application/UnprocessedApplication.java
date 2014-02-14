package com.theladders.solid.srp.job.application;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;

public class UnprocessedApplication
{
  private final Jobseeker jobseeker;
  private final Job job;
  private final Resume resume;

  public UnprocessedApplication(Jobseeker jobseeker,
                                Job job,
                                Resume resume)
  {
    this.jobseeker = jobseeker;
    this.job = job;
    this.resume = resume;
  }

  //validation logic is derived from business logic, extract
  public boolean isValid()
  {
	return JobApplicationService.validateUnprocessedApplication(this);
  }

  public Jobseeker getJobseeker()
  {
    return jobseeker;
  }

  public Job getJob()
  {
    return job;
  }

  public Resume getResume()
  {
    return resume;
  }
}
