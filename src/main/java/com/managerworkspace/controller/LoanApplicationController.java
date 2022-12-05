package com.managerworkspace.controller;

import com.managerworkspace.controller.annotation.APIController;
import com.managerworkspace.controller.mapper.LoanEntitiesMapper;
import com.managerworkspace.dto.request.ClientRequest;
import com.managerworkspace.dto.response.LoanDecisionResponse;
import com.managerworkspace.model.Client;
import com.managerworkspace.model.LoanApplication;
import com.managerworkspace.model.LoanDecision;
import com.managerworkspace.service.ClientService;
import com.managerworkspace.service.LoanApplicationService;
import com.managerworkspace.service.LoanDecisionService;
import java.util.List;
import java.util.stream.Collectors;
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
public class LoanApplicationController {

  @Autowired
  private final LoanApplicationService loanApplicationService;
  private final LoanDecisionService loanDecisionService;
  private final ClientService clientService;
  private final LoanEntitiesMapper loanEntitiesMapper;

  @PostMapping(path = "/loanApplications")
  @ResponseStatus(code = HttpStatus.OK)
  public LoanDecisionResponse createFirstLoanApplication(@Valid @RequestBody ClientRequest body) {
    Client client = clientService.createClient(
        body.getFirstName(),
        body.getMiddleName(),
        body.getLastName(),
        body.getPassportId(),
        body.getPlaceOfResidence(),
        body.getPhoneNumber(),
        body.getFrom(),
        body.getTo(),
        body.getPosition(),
        body.getCompanyName()
    );
    LoanApplication loanApplication = loanApplicationService.createLoanApplication(
        client,
        body.getLoanAmount());
    LoanDecision loanDecision = loanDecisionService.makeDecision(loanApplication);

    return loanEntitiesMapper.toResponse(loanDecision);
  }

  @GetMapping(path = "/loanApplications")
  @ResponseStatus(code = HttpStatus.OK)
  public List<LoanDecisionResponse> getLoanApplication(
      @RequestParam(value = "status", required = false)
          String approvedStatus) {
    return loanDecisionService.getLoanByStatus(approvedStatus).stream()
        .map(loanEntitiesMapper::toResponse)
        .collect(Collectors.toList());
  }

  @PostMapping(path = "/clients/{id}/loanApplications")
  @ResponseStatus(code = HttpStatus.OK)
  public LoanDecisionResponse createLoanApplication(@PathVariable("id") String id,
      @RequestBody double loanAmount) {
    Client client = clientService.getClientById(id);
    LoanApplication loanApplication = loanApplicationService.createLoanApplication(client,
        loanAmount);
    LoanDecision loanDecision = loanDecisionService.makeDecision(loanApplication);

    return loanEntitiesMapper.toResponse(loanDecision);
  }
}
