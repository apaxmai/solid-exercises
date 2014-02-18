package com.theladders.solid.srp.job;

import java.util.HashMap;
import java.util.Map;

// SRP: likely OK, not sure if the actual physical storage of
// jobs should be extracted into a JobContainer, or if it is
// just a datastructure

public class JobRepository
{
  private final Map<Integer, Job> jobs;


  public JobRepository()
  {
    this.jobs = new HashMap<>();
  }


  public void addJob(Job aJob)
  {
    jobs.put(aJob.getJobId(), aJob);
  }


  public Job getJob(int jobId)
  {
    return jobs.get(jobId);
  }
}
