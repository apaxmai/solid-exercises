package com.theladders.solid.srp.http;

import com.theladders.solid.srp.ApplyResponse;
import com.theladders.solid.srp.ResponseDecorator;
import com.theladders.solid.srp.job.JobViewProviderHTTP;
import com.theladders.solid.srp.resume.ResumeViewProviderHTTP;

public class ResponseDecoratorHTTP implements ResponseDecorator
{
  private HttpResponse httpResponse;
  
  public ResponseDecoratorHTTP(HttpResponse httpResponse)
  {
    this.httpResponse = httpResponse;
  }
  
  @Override
  public HttpResponse decorateFrom(ApplyResponse applyResponse)
  {

    switch ( applyResponse.getType() )
    {
      case invalid:
        JobViewProviderHTTP.invalidViewFor(httpResponse, applyResponse.getJobId());
        break;
      case error:
        ApplyViewProviderHTTP.errorViewFor(httpResponse, applyResponse.getErrList(), applyResponse.getModel() );
        break;
      case resumeCompletion:
        ResumeViewProviderHTTP.completionViewFor(httpResponse, applyResponse.getModel() );
        break;
      case success:
        ApplyViewProviderHTTP.successViewFor(httpResponse, applyResponse.getModel() );
        break;
    }
    return httpResponse;
    
  }

}
