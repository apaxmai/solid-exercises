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
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeViewProvider;

//this gets split up

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    Jobseeker jobseeker = request.getSession().getJobseeker();
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);

    String jobIdString = request.getParameter("jobId");
    int jobId = Integer.parseInt(jobIdString);

    Job job = jobSearchService.getJob(jobId);

    if (job == null)
    {
      JobViewProvider.invalidViewFor(response, jobId);
      return response;
    }

    //this is just a data structure, no change.
    Map<String, Object> model = new HashMap<>();

    List<String> errList = new ArrayList<>();

    try
    {
      apply(request, jobseeker, job, origFileName);
    }
    catch (Exception e)
    {
      //extracted responsibility to ApplyErrorProvider
      errList.add(ApplyErrorProvider.genericFail);
      
      //extracted responsibility to *ViewProvider
      ApplyViewProvider.errorViewFor(response, errList, model);
      return response;
    }

    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());

    //this was a business rules check, extracted to Service
    if ( ApplyValidator.resumeCompletionRequiredFor(jobseeker, profile) )
    {
      //extracted responsibility to *ViewProvider
      ResumeViewProvider.completionViewFor(response, model);
      return response;
    }

    //extracted responsibility to *ViewProvider
    ApplyViewProvider.successViewFor(response, model);

    return response;
  }

  // httprequest, 
  private void apply(HttpRequest request,
                     Jobseeker jobseeker,
                     Job job,
                     String fileName)
  {
    Resume resume = saveNewOrRetrieveExistingResume(fileName,jobseeker, request);
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
    Resume resume;

    if (!"existing".equals(request.getParameter("whichResume")))
    {
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);

      if (resume != null && "yes".equals(request.getParameter("makeResumeActive")))
      {
        myResumeManager.saveAsActive(jobseeker, resume);
      }
    }
    else
    {
      resume = myResumeManager.getActiveResume(jobseeker.getId());
    }

    return resume;
  }

}
