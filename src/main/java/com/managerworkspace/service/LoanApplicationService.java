package com.managerworkspace.service;

import com.managerworkspace.model.Client;
import com.managerworkspace.model.LoanApplication;

public interface LoanApplicationService {

  LoanApplication createLoanApplication(Client client, double loanAmount);
}
