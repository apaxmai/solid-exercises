package com.theladders.solid.srp;

public enum ApplyEndpointResponseType
{
  invalid(0),
  error(1),
  success(2),
  resumeCompletion(3);
  
  private int id;
  
  ApplyEndpointResponseType(int id)
  {
    this.id = id;
  }
  
  public int value() { return id; }
}
