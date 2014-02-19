package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.ResumeName;

public class ApplyController
{
  private static JobseekerProfileManager jobseekerProfileManager;
  private static JobSearchService        jobSearchService;
  
  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
  }
  
  public static ApplyResponse apply(
                          ApplyWorkflow workflow,
                          Jobseeker jobseeker,
                          int jobId,
                          ResumeName resumeName,
                          String resumeCommand,
                          String activeResumeCommand)
  {
    ApplyResponse applyResponse = new ApplyResponse();
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    Job job = jobSearchService.getJob(jobId);

    if (job == null)
    {
      applyResponse.setType(ApplyEndpointResponseType.invalid);
      return applyResponse;
    }

    // this is just a data structure, no change, although maybe so?
    Map<String, Object> model = new HashMap<>();
    List<String> errList = new ArrayList<>();

    try
    {
      workflow.apply(resumeName, resumeCommand, activeResumeCommand, jobseeker, job);
    }
    catch (Exception e)
    {
      // extracted responsibility to ApplyErrorProvider
      applyResponse.addError(HumanMessageProvider.Apply_GenericFailure);
      applyResponse.setType(ApplyEndpointResponseType.error);
      return applyResponse;
    }

    // this is NOT an SRP violation, or maybe it is; depending on how you look at it.
    model.put("jobId",    job.getJobId());
    model.put("jobTitle", job.getTitle());
    applyResponse.setModel(model);

    // this was a business rules check, extracted to a Validator
    if (ApplyValidator.resumeCompletionRequiredFor(jobseeker, profile))
    {
      applyResponse.setType(ApplyEndpointResponseType.resumeCompletion);
      return applyResponse;
    }

    applyResponse.setType(ApplyEndpointResponseType.success);
    return applyResponse;
  }

}
