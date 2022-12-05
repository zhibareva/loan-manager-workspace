package com.managerworkspace.service;

import com.managerworkspace.model.LoanDecision;
import com.managerworkspace.model.LoanApplication;
import java.util.List;

public interface LoanDecisionService {

  LoanDecision makeDecision(LoanApplication loanApplication);

  List<LoanDecision> getLoanByStatus(String approvedStatus);

  LoanDecision getLoanById(String id);
}
