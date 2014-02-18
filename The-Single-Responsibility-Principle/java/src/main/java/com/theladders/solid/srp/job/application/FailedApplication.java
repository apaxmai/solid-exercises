package com.theladders.solid.srp.job.application;

// SRP: OK

public class FailedApplication implements JobApplicationResult
{
  @Override
  public boolean failure()
  {
    return true;
  }
}
