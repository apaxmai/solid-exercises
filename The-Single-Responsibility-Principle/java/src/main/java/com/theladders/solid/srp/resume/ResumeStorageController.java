package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.jobseeker.Jobseeker;

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
  
  public Resume saveNewOrRetrieveExistingResume(ResumeName resumeName,
                                                Jobseeker jobseeker,
                                                String resumeCommand,
                                                String activeResumeCommand)
  {
    return (ResumeCommandProvider.useExistingResumeCommand.equals(resumeCommand)) ? retrieveActiveResume(jobseeker)
                                                                                 : saveNewResume(resumeName,
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

  private Resume saveNewResume(ResumeName resumeName,
                               Jobseeker jobseeker,
                               String activeResumeCommand)
  {
    Resume resume;
    resume = resumeManager.saveResume(jobseeker, resumeName.name());

    if (resume != null && ResumeCommandProvider.makeResumeActiveCommand.equals(activeResumeCommand))
    {
      myResumeManager.saveAsActive(jobseeker, resume);
    }

    return resume;
  }
}
