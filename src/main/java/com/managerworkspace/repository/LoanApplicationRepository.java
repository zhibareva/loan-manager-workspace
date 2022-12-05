package com.managerworkspace.repository;

import com.managerworkspace.model.LoanApplication;
import java.util.List;

public interface LoanApplicationRepository {
  LoanApplication findById(String id);

  List<LoanApplication> findAll();

  LoanApplication delete(LoanApplication loanApplication);

  LoanApplication save(LoanApplication loanApplication);
}
