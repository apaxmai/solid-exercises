package com.theladders.solid.srp.job.application;

//SRP: OK

public class ApplicationFailureException extends RuntimeException
{
  public ApplicationFailureException(String reason)
  {
    super(reason);
  }
}
