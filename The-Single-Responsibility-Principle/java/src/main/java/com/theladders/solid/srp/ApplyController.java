package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplyController
{
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeStorageController resumeStorageController;

  public ApplyController(JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeStorageController = ResumeStorageController.with(resumeManager, myResumeManager);
  }


  void apply(HttpRequest request,
             Jobseeker jobseeker,
             Job job,
             String fileName)
  {
    Resume resume = resumeStorageController.saveNewOrRetrieveExistingResume(fileName, jobseeker, request);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }

}
