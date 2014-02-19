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

/*
 * 
 * The current responsibilities of this class are to the roles of:
 * 1) Developers that work on the HTTP endpoint that it used to apply, this includes the specification of the request.
 * 2) jobapplicationsystem / resumestoragecontroller
 * 
 */

public class ApplyWorkflow
{
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeStorageController resumeStorageController;

  public ApplyWorkflow(JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeStorageController = ResumeStorageController.with(resumeManager, myResumeManager);
  }


  void apply(String resumeCommand,
             String activeResumeCommand,
             Jobseeker jobseeker,
             Job job,
             String fileName)
  {
    Resume resume = resumeStorageController.saveNewOrRetrieveExistingResume(fileName,
                                                                            jobseeker,
                                                                            resumeCommand,
                                                                            activeResumeCommand);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }
  
}
