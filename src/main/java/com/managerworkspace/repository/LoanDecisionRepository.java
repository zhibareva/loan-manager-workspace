package com.managerworkspace.repository;

import com.managerworkspace.model.LoanDecision;
import java.util.List;
import java.util.Optional;

public interface LoanDecisionRepository {

  LoanDecision save(LoanDecision loanDecision);

  List<LoanDecision> findAll();

  Optional<LoanDecision> findByLoanId(Long id);

  List<LoanDecision> findByFilter(String approvedStatus);
}
