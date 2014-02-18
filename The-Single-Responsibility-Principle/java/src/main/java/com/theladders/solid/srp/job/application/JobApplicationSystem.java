package com.theladders.solid.srp.job.application;

public class JobApplicationSystem
{
  private final JobApplicationRepository repository;


  public JobApplicationSystem(JobApplicationRepository repository)
  {
    this.repository = repository;
  }


  public JobApplicationResult apply(UnprocessedApplication application)
  {
    // there may be a change in the future about applying to
    // jobs that you have already applied to,
    // so this is business logic, so
    // we extract to JobApplicationService.
    // the other responsibility of this class is to keep track of the
    // repository.
    if (JobApplicationValidator.validateApplicationSuccess(application, repository))
    {
      SuccessfulApplication success = SuccessfulApplication.with(application);

      repository.add(success);

      return success;
    }

    return new FailedApplication();
  }
}
