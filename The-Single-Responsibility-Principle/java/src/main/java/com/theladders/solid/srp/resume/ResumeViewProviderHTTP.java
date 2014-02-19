package com.theladders.solid.srp.resume;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ResumeViewProviderHTTP
{
  public static void completionViewFor(HttpResponse response,
                                       Map<String, Object> model)
  {
    Result result = new Result("completeResumePlease", model);
    response.setResult(result);
  }
}
