package com.archway.util;

import java.io.PrintWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GageException extends Exception {
  private static final Logger log = LoggerFactory.getLogger(GageException.class);
  
  private static final long serialVersionUID = 1L;
  
  private String _strErrorMessage;
  
  private int _iErrorCode;
  
  private Throwable _throwable;
  
  public GageException(String errorMessage, int errorCode, Throwable throwable) {
    super(errorMessage + " Error Code=" + errorCode);
    this._strErrorMessage = errorMessage;
    this._iErrorCode = errorCode;
    this._throwable = throwable;
    if (null == throwable) {
      log.warn(errorMessage + " ErrorCode=" + errorCode);
    } else {
      log.error(errorMessage + " ErrorCode=" + errorCode, throwable);
    } 
  }
  
  public void printStackTrace(PrintWriter s) {
    super.printStackTrace(s);
    if (null != this._throwable) {
      s.println("Caused By:");
      this._throwable.printStackTrace(s);
    } 
  }
}
