package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.JobViewProvider;
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
import com.theladders.solid.srp.resume.ResumeViewProvider;

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
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;


  public ApplyEndpointHTTP(JobseekerProfileManager jobseekerProfileManager,
                           JobSearchService jobSearchService)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
  }


  // handling a httprequest
  public HttpResponse handle(ApplyWorkflow workflow,
                             HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    Jobseeker jobseeker = request.getSession().getJobseeker();
    String jobIdString = request.getParameter("jobId");
    String resumeCommand = request.getParameter("whichResume");
    String activeResumeCommand = request.getParameter("makeResumeActive");
    int jobId = Integer.parseInt(jobIdString); 
    //parsing the request is the job of the endpoint, but 
    //much of the below needs to go
       
    //this is controller stuff
    //if( ...
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    Job job = jobSearchService.getJob(jobId);

    if (job == null)
    {
      JobViewProvider.invalidViewFor(response, jobId);
      return response;
    }

    // this is just a data structure, no change, although maybe so?
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();

    try
    {
      workflow.apply(resumeCommand, activeResumeCommand, jobseeker, job, origFileName);
    }
    catch (Exception e)
    {
      // extracted responsibility to ApplyErrorProvider
      errList.add(HumanMessageProvider.Apply_GenericFailure);

      // extracted responsibility to *ViewProvider
      ApplyViewProvider.errorViewFor(response, errList, model);
      return response;
    }

    // this is NOT an SRP violation, or maybe it is; depending on how you look at it.
    model.put("jobId",    job.getJobId());
    model.put("jobTitle", job.getTitle());

    // this was a business rules check, extracted to Validator
    if (ApplyValidator.resumeCompletionRequiredFor(jobseeker, profile))
    {
      // extracted responsibility to *ViewProvider
      ResumeViewProvider.completionViewFor(response, model);
      return response;
    }

    // extracted responsibility to *ViewProvider
    ApplyViewProvider.successViewFor(response, model);
    return response;
  }
}
