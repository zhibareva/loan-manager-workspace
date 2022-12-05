package com.managerworkspace.controller;

import com.managerworkspace.controller.annotation.APIController;
import com.managerworkspace.dto.request.SignContractRequest;
import com.managerworkspace.dto.response.ContractResponse;
import com.managerworkspace.model.Contract;
import com.managerworkspace.model.LoanDecision;
import com.managerworkspace.service.ContractService;
import com.managerworkspace.service.LoanDecisionService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@APIController
@AllArgsConstructor
@Slf4j
public class ContractController {

  @Autowired
  private final ContractService contractService;
  private final LoanDecisionService loanDecisionService;

  @PostMapping(path = "/loanApplications/{id}/contracts")
  @ResponseStatus(code = HttpStatus.OK)
  public ContractResponse signLoanContract(@PathVariable String id,
      @Valid @RequestBody SignContractRequest body) {

    Contract contract = contractService.signContract(id, body.getDateOfSignature(),
        body.isSigned());
    LoanDecision loanDecision = loanDecisionService.getLoanById(id);
    return new ContractResponse(
        contract.getId(),
        contract.getLoanApplication().getClientId().getFirstName(),
        contract.getLoanApplication().getClientId().getMiddleName(),
        contract.getLoanApplication().getClientId().getLastName(),
        loanDecision.isApprovedStatus(),
        loanDecision.getPeriodInDays(),
        loanDecision.getApprovedLoanAmount(),
        contract.getDateOfSignature(),
        contract.isSigned()
    );
  }

  @GetMapping(path = "/contracts")
  @ResponseStatus(code = HttpStatus.OK)
  public List<ContractResponse> getContractsByFilter(
      @RequestParam(value = "isSigned", required = false)
          String isSigned) {
    List<Contract> contracts = contractService.getContractByFilter(isSigned);
    List<ContractResponse> contractResponses = new ArrayList<>();
    contracts.forEach(contract -> {
      LoanDecision loanDecision = loanDecisionService.getLoanById(
          contract.getLoanApplication().getId().toString());
      contractResponses.add(
          new ContractResponse(
              contract.getId(),
              contract.getLoanApplication().getClientId().getFirstName(),
              contract.getLoanApplication().getClientId().getMiddleName(),
              contract.getLoanApplication().getClientId().getLastName(),
              loanDecision.isApprovedStatus(),
              loanDecision.getPeriodInDays(),
              loanDecision.getApprovedLoanAmount(),
              contract.getDateOfSignature(),
              contract.isSigned()
          )
      );
    });
    return contractResponses;
  }
}
