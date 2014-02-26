package com.theladders.solid.ocp.resume;

//inside closed-for-modification boundary

public class JobseekerProfile
{
  private int id;

  public JobseekerProfile(int id)
  {
    this.id = id;
  }

  public int getId()
  {
    return id;
  }
}
