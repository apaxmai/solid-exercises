package com.theladders.solid.srp.job;

import com.theladders.solid.srp.HashCodeProvider;

// SRP: OK

public class Job
{
  private final int    id;
  private final String title;


  // "This is a job with id:" .... extracted
  public Job(int id)
  {
    this.id = id;
    this.title = JobTitleProvider.titleFor(id);
  }


  public int getJobId()
  {
    return id;
  }


  public String getTitle()
  {
    return title;
  }


  // it is possible that in Java, the responsibility of editing
  // the algorithm used for hashCode() would fall to a role of
  // optimization, perhaps a different set of developers, so we extract.
  @Override
  public int hashCode()
  {
    return HashCodeProvider.hashCodeFor(this, this.id);
  }


  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Job other = (Job) obj;
    if (id != other.id)
      return false;
    return true;
  }
}
