package com.theladders.solid.srp.job;

//SRP: OK

public class JobTitleProvider
{

	public static String titleFor(int id)
	{
		return "This is a job with id:"+ Integer.toString(id);
	}

}
