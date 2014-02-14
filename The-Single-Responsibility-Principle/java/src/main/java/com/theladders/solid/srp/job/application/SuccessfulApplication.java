package com.theladders.solid.srp.job.application;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;

//SRP: OK

public class SuccessfulApplication implements JobApplicationResult
{
  private final Jobseeker jobseeker;
  private final Job job;
  private final Resume resume;
  
  public static SuccessfulApplication with(UnprocessedApplication application)
  {
     return new SuccessfulApplication(application.getJobseeker(),
              application.getJob(),
              application.getResume());
  }
		  
  public SuccessfulApplication(Jobseeker jobseeker,
                               Job job,
                               Resume resume)
  {
    this.jobseeker = jobseeker;
    this.job = job;
    this.resume = resume;
  }

  @Override
  public boolean failure()
  {
    return false;
  }

  //eliminate getters
  public Object getJobseeker()
  {
    return jobseeker;
  }

  public Object getJob()
  {
    return job;
  }

  public boolean isFor(Jobseeker jobseeker, Job job)
  {
	return (this.jobseeker.equals(jobseeker) &&
			this.job.equals(job) );
  }
  
}
