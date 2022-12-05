package com.managerworkspace.service.impl;

import com.managerworkspace.model.LoanApplication;
import com.managerworkspace.model.Client;
import com.managerworkspace.repository.ClientRepository;
import com.managerworkspace.repository.LoanApplicationRepository;
import com.managerworkspace.service.LoanApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

  @Autowired
  private final LoanApplicationRepository loanApplicationRepository;

  @Override
  public LoanApplication createLoanApplication(Client client, double loanAmount) {
    LoanApplication loanApplication = new LoanApplication(client, loanAmount);
    loanApplicationRepository.save(loanApplication);
    return loanApplication;
  }
}
