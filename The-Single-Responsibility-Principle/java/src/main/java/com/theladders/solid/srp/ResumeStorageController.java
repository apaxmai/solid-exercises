package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeCommandProvider;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResumeStorageController
{
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  public static ResumeStorageController with(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    return new ResumeStorageController(resumeManager, myResumeManager);
  }
  
  private ResumeStorageController(ResumeManager resumeManager, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 HttpRequest request)
  {
    String resumeCommand = request.getParameter("whichResume");
    return (resumeCommand.equals(ResumeCommandProvider.useExistingResumeCommand)) ? retrieveActiveResume(jobseeker) : saveNewResume(newResumeFileName, jobseeker, request);
  }
  
  private Resume retrieveActiveResume(Jobseeker jobseeker)
  {
    return myResumeManager.getActiveResume(jobseeker.getId());
  }

  private Resume saveNewResume(String newResumeFileName, Jobseeker jobseeker, HttpRequest request)
  {
    Resume resume;
    resume = resumeManager.saveResume(jobseeker, newResumeFileName);

    String activeResumeCommand = request.getParameter("makeResumeActive");
    if( shouldMakeResumeActive(newResumeFileName, activeResumeCommand, resume) )
    {
      myResumeManager.saveAsActive(jobseeker, resume);
    }
    
    return resume;
  }
  
  private boolean shouldMakeResumeActive(String newResumeFileName, String activeResumeCommand, Resume resume)
  {
    if(resume != null && activeResumeCommand.equals(ResumeCommandProvider.makeResumeActiveCommand))
    {
      return true;
    }
    return false;
  }
}
