package com.managerworkspace.exception;

public class LoanDecisionNotFoundException extends RuntimeException {
  public LoanDecisionNotFoundException() {}

  public LoanDecisionNotFoundException(String message) {
    super(message);
  }
}
