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
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeViewProvider;

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
	  
	public HttpResponse handle(	ApplyController controller,
								HttpRequest request,
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
			controller.apply(request, jobseeker, job, origFileName);
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

		//this was a business rules check, extracted to Validator
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
}
