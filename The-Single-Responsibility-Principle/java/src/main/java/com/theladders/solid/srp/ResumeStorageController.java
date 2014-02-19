package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeCommandProvider;
import com.theladders.solid.srp.resume.ResumeManager;

/*
 * 
 * The current responsibilities of this class are to the roles of:
 * 1) Developers that work on the resumeManagers and expose an API to the application workflow.
 * 
 */

public class ResumeStorageController
{
  private final ResumeManager   resumeManager;
  private final MyResumeManager myResumeManager;


  public static ResumeStorageController with(ResumeManager resumeManager,
                                             MyResumeManager myResumeManager)
  {
    return new ResumeStorageController(resumeManager, myResumeManager);
  }
  
  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                Jobseeker jobseeker,
                                                String resumeCommand,
                                                String activeResumeCommand)
  {
    return (ResumeCommandProvider.useExistingResumeCommand.equals(resumeCommand)) ? retrieveActiveResume(jobseeker)
                                                                                 : saveNewResume(newResumeFileName,
                                                                                                 jobseeker,
                                                                                                 activeResumeCommand);
  }
  

  private ResumeStorageController(ResumeManager resumeManager,
                                  MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  private Resume retrieveActiveResume(Jobseeker jobseeker)
  {
    return myResumeManager.getActiveResumeForJobseeker(jobseeker);
  }

  private Resume saveNewResume(String newResumeFileName,
                               Jobseeker jobseeker,
                               String activeResumeCommand)
  {
    Resume resume;
    resume = resumeManager.saveResume(jobseeker, newResumeFileName);

    if (resume != null && ResumeCommandProvider.makeResumeActiveCommand.equals(activeResumeCommand))
    {
      myResumeManager.saveAsActive(jobseeker, resume);
    }

    return resume;
  }
}
