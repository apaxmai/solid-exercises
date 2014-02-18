package com.theladders.solid.srp.job.application;

public class JobApplicationValidator
{
	public static boolean validateApplicationSuccess(
			UnprocessedApplication application,
			JobApplicationRepository repository)
	{
		return (validateUnprocessedApplication(application) &&
				!repository.applicationExistsFor(
						application.getJobseeker(),
						application.getJob()
						)
				);
	}

	public static boolean validateUnprocessedApplication(UnprocessedApplication application)
	{
		return ((application.getJobseeker() != null) &&
				(application.getJob() != null) &&
				(application.getResume() != null));
	}
}
