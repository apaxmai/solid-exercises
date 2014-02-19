package com.theladders.solid.srp;

public interface ResponseDecorator
{
  Object decorateFrom(ApplyResponse applyResponse);
}
