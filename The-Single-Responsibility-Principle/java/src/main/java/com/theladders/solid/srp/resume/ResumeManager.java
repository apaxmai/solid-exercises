package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ResumeManager
{
  private final ResumeRepository resumeRepository;


  public ResumeManager(ResumeRepository resumeRepository)
  {
    this.resumeRepository = resumeRepository;
  }


  // is this doing >1 things? creating resume from filename and saving
  public Resume saveResume(Jobseeker jobseeker,
                           String fileName)
  {

    Resume resume = new Resume(fileName);
    resumeRepository.saveResume(jobseeker.getId(), resume);

    return resume;
  }
}
