package com.managerworkspace.service;

import com.managerworkspace.model.Contract;
import com.managerworkspace.model.LoanApplication;
import java.time.LocalDate;
import java.util.List;

public interface ContractService {

  Contract signContract(String id, LocalDate dateOfSignature, boolean signed);

  Contract createContract(LoanApplication loanApplication);

  List<Contract> getContractByFilter(String isSigned);
}
