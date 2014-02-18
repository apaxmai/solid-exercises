package com.theladders.solid.srp;

import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpResponse;

public class ApplyViewProvider
{
  public static void successViewFor(HttpResponse response,
                                    Map<String, Object> model)
  {
    Result result = new Result("success", model);
    response.setResult(result);
  }


  public static void errorViewFor(HttpResponse response,
                                  List<String> errList,
                                  Map<String, Object> model)
  {
    Result result = new Result("error", model, errList);
    response.setResult(result);
  }
}
