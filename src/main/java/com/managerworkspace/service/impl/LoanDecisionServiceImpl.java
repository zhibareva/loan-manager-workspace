package com.managerworkspace.service.impl;

import com.managerworkspace.exception.LoanDecisionNotFoundException;
import com.managerworkspace.model.LoanApplication;
import com.managerworkspace.model.LoanDecision;
import com.managerworkspace.repository.LoanDecisionRepository;
import com.managerworkspace.service.ContractService;
import com.managerworkspace.service.LoanDecisionService;
import com.managerworkspace.utils.DecisionGenerator;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanDecisionServiceImpl implements LoanDecisionService {


  @Autowired
  private final LoanDecisionRepository loanDecisionRepository;
  private final ContractService contractService;
  private final DecisionGenerator decisionGenerator;

  @Override
  public LoanDecision makeDecision(LoanApplication loanApplication) {
    boolean isApproved = decisionGenerator.getDecision();
    double value = loanApplication.getLoanAmount() * DecisionGenerator.COEFFICIENT;

    int days = 0;
    double amount = 0.00;
    if (isApproved) {
      days = decisionGenerator.getDays();
      amount = decisionGenerator.roundAmount(value);
    }
    LoanDecision loanDecision = new LoanDecision(loanApplication, isApproved, days, amount);
    loanDecisionRepository.save(loanDecision);
    if (isApproved) {
      contractService.createContract(loanApplication);
    }

    return loanDecision;
  }

  @Override
  public List<LoanDecision> getLoanByStatus(String approvedStatus) {
    return loanDecisionRepository.findByFilter(approvedStatus);
  }

  @Override
  public LoanDecision getLoanById(String id) {
    return loanDecisionRepository.findByLoanId(Long.parseLong(id))
        .orElseThrow(() -> new LoanDecisionNotFoundException("There is no such loan"));
  }
}
