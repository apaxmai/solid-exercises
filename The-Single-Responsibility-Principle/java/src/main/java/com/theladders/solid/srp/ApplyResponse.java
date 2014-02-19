package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplyResponse
{
  private ApplyEndpointResponseType type;
  private int jobId;
  private List<String> errList;
  private Map<String, Object> model;
  
  public ApplyResponse()
  {
    errList = new ArrayList<>();
    model = new HashMap<>();
  }
  
  public ApplyEndpointResponseType getType()
  {
    return type;
  }
  public void setType(ApplyEndpointResponseType type)
  {
    this.type = type;
  }
  
  public int getJobId()
  {
    return jobId;
  }
  public void setJobId(int jobId)
  {
    this.jobId = jobId;
  }

  public List<String> getErrList()
  {
    return errList;
  }
  public void addError(String error)
  {
    errList.add(error);
  }

  public Map<String, Object> getModel()
  {
    return model;
  }
  public void setModel(Map<String, Object> model)
  {
    this.model = model;
  }


}
