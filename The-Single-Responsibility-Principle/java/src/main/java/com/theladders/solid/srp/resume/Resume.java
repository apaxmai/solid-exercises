package com.theladders.solid.srp.resume;

import com.theladders.solid.srp.HashCodeProvider;

public class Resume
{
  private final String resumeName;

  public Resume(String resumeName)
  {
	//resumename validator?
    if (resumeName == null || resumeName.equals(""))
    {
      throw new NullPointerException("bad resume name");
    }

    this.resumeName = resumeName;
  }
  
  public String getResumeName()
  {
	  return resumeName;
  }

  @Override
  public int hashCode()
  {
	return HashCodeProvider.hashCodeFor(this);
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
    Resume other = (Resume) obj;
    if (resumeName == null)
    {
      if (other.resumeName != null)
        return false;
    }
    else if (!resumeName.equals(other.resumeName))
      return false;
    return true;
  }
}
