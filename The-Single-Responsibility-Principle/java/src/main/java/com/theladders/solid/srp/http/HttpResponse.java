package com.theladders.solid.srp.http;

import com.theladders.solid.srp.Result;

// SRP: OK

public class HttpResponse
{
  private Result result;


  public String getResultType()
  {
    return result.getType();
  }


  public void setResult(Result result)
  {
    this.result = result;
  }
}
