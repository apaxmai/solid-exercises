package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.Resume;

public class HashCodeProvider
{
	private static final int[] primes = {3,5,7,11,13,17,19,23,29,31,37};
	
	public static int hashCodeFor(Job job)
	{
		return primes[0] * hashCodeAlgorithm(job.getJobId());
	}
	public static int hashCodeFor(Jobseeker jobseeker)
	{
		return primes[1] * hashCodeAlgorithm(jobseeker.getId());
	}
	public static int hashCodeFor(Resume resume)
	{
		return primes[2] * hashCodeAlgorithm(resume.getResumeName());
	}
	
	private static int hashCodeAlgorithm(Object ...objects)
	{
		int ret = 0;
		for( Object object : objects )
		{
			if( object != null )
			{
				ret ^= object.hashCode();
			}
		}
		
		return ret;
	}




}
