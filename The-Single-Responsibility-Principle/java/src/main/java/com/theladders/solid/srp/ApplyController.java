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
import com.theladders.solid.srp.resume.ResumeCommandProvider;
import com.theladders.solid.srp.resume.ResumeManager;

// this gets split up

public class ApplyController
{
  private final JobApplicationSystem jobApplicationSystem;
  private final ResumeManager        resumeManager;
  private final MyResumeManager      myResumeManager;


  public ApplyController(JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }


  void apply(HttpRequest request,
             Jobseeker jobseeker,
             Job job,
             String fileName)
  {
    //get from container
    Resume resume = saveNewOrRetrieveExistingResume(fileName, jobseeker, request);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }

  
  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 HttpRequest request)
  {
    String resumeCommand = request.getParameter("whichResume");
    return (resumeCommand.equals(ResumeCommandProvider.useExistingResumeCommand)) ? retrieveActiveResume(jobseeker) : saveNewResume(newResumeFileName, jobseeker, request);
  }
  
  //these could be in a container type e.g. resumeContainer
  private Resume retrieveActiveResume(Jobseeker jobseeker)
  {
    return myResumeManager.getActiveResume(jobseeker.getId());
  }

  private Resume saveNewResume(String newResumeFileName, Jobseeker jobseeker, HttpRequest request)
  {
    Resume resume;
    resume = resumeManager.saveResume(jobseeker, newResumeFileName);

    String activeResumeCommand = request.getParameter("makeResumeActive");
    if (resume != null && activeResumeCommand.equals(ResumeCommandProvider.makeResumeActiveCommand))
    {
      myResumeManager.saveAsActive(jobseeker, resume);
    }
    
    return resume;
  }
  
}
