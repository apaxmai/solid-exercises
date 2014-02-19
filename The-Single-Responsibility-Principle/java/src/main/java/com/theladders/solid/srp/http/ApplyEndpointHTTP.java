package com.theladders.solid.srp.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.ApplyController;
import com.theladders.solid.srp.ApplyResponse;
import com.theladders.solid.srp.ApplyWorkflow;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.JobViewProviderHTTP;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeName;
import com.theladders.solid.srp.resume.ResumeViewProviderHTTP;

/*
 * 
 * The current responsibilities of this class are to the roles of:
 * 1)   Developers that work on the HTTP endpoint that is used to apply,
 *      this includes the specification of the request.
 *      As the specification of the request determines the parsing of the request,
 *      parsing is done here as well.
 * 
 */

public class ApplyEndpointHTTP
{
  
  public ApplyEndpointHTTP()
  {
  }

  public HttpResponse handle(ApplyWorkflow workflow,
                             HttpRequest request,
                             HttpResponse response,
                             ResumeName resumeName)
  {
    Jobseeker jobseeker        = request.getSession().getJobseeker();
    String jobIdString         = request.getParameter("jobId");
    String resumeCommand       = request.getParameter("whichResume");
    String activeResumeCommand = request.getParameter("makeResumeActive");
    int jobId                  = Integer.parseInt(jobIdString); 

    ApplyResponse applyResponse = ApplyController.apply(workflow, jobseeker, jobId, resumeName, resumeCommand, activeResumeCommand);

    ResponseDecoratorHTTP responseDecorator = new ResponseDecoratorHTTP(response);
    return responseDecorator.decorateFrom(applyResponse);
    
  }
  
}
