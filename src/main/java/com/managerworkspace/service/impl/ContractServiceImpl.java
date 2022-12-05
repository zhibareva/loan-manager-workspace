package com.managerworkspace.service.impl;

import com.managerworkspace.model.Contract;
import com.managerworkspace.model.LoanApplication;
import com.managerworkspace.repository.ContractRepository;
import com.managerworkspace.repository.LoanApplicationRepository;
import com.managerworkspace.service.ContractService;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContractServiceImpl implements ContractService {

  @Autowired
  private final ContractRepository contractRepository;

  @Override
  public Contract signContract(String id, LocalDate dateOfSignature, boolean signed) {
    Contract contract = contractRepository.findByLoanId(Long.parseLong(id));
    contract.setDateOfSignature(dateOfSignature);
    contract.setSigned(signed);
    return contractRepository.update(contract);
  }

  @Override
  public Contract createContract(LoanApplication loanApplication) {
    Contract contract = new Contract(loanApplication, null, false);
    return contractRepository.save(contract);
  }

  @Override
  public List<Contract> getContractByFilter(String isSigned) {
    return contractRepository.findByFilter(isSigned);
  }
}
