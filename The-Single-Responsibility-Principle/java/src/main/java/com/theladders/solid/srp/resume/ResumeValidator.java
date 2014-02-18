package com.theladders.solid.srp.resume;

public class ResumeValidator
{
  public static boolean validateResumeName(String resumeName)
  {
    return !(resumeName == null || resumeName.equals(""));
  }


  public static void throwResumeNameFailedValidation()
  {
    throw new NullPointerException("bad resume name");
  }
}
